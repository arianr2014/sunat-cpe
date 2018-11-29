package io.github.carlosthe19916.core.bootstrap;

import io.github.carlosthe19916.core.models.*;
import io.github.carlosthe19916.core.models.utils.DefaultKeyProviders;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Checks if MASTER organization already exists
 * (if MASTER organization does not exists, it means is the first time the server is being bootstrapped).
 * If is the first time the server is being bootstrapped, then it will create default roles:
 * 1. ROLE "owner" with PERMISSIONS: "organization_admin, organization_edit, organization_view, component_manage, component_view, etc."
 * 2. ROLE: "collaborator" with PERMISSIONS: "document_manage, document_view, etc."
 */
@Startup
@Singleton
public class ServerBootstrap {

    private static final Logger logger = Logger.getLogger(ServerBootstrap.class);

    @Inject
    private RoleProvider roleProvider;

    @Inject
    private DefaultKeyProviders defaultKeyProviders;

    @Inject
    private OrganizationProvider organizationProvider;

    @PostConstruct
    private void init() {
        logger.info("Server Bootstrap...");
        bootstrap();
    }

    private void bootstrap() {
        Optional<OrganizationModel> organization = organizationProvider.getOrganization(OrganizationModel.MASTER_ID);
        if (!organization.isPresent()) {
            createDefaultRoles();
            createMasterOrganization();
        }
    }

    private void createDefaultRoles() {
        logger.info("Initializing Default Roles");

        Set<PermissionType> ownerPermissions = new HashSet<>();
        ownerPermissions.add(PermissionType.organization_admin);
        ownerPermissions.add(PermissionType.organization_edit);
        ownerPermissions.add(PermissionType.organization_view);
        ownerPermissions.add(PermissionType.component_manage);
        ownerPermissions.add(PermissionType.component_view);

        ownerPermissions.add(PermissionType.document_manage);
        ownerPermissions.add(PermissionType.document_view);

        Set<PermissionType> collaboratorPermissions = new HashSet<>();
        collaboratorPermissions.add(PermissionType.organization_view);

        roleProvider.addRole(RoleModel.OWNER_ID, "Organization Owner", ownerPermissions, true);
        roleProvider.addRole(RoleModel.COLLABORATOR_ID, "Organization Collaborator", collaboratorPermissions, false);
    }

    private void createMasterOrganization() {
        logger.info("Initializing Admin Organization " + OrganizationModel.MASTER_ID);

        try {
            OrganizationModel organization = organizationProvider.addOrganization(OrganizationModel.MASTER_ID, OrganizationModel.MASTER_ID, OrganizationType.master);
            organization.setUseCustomCertificates(true);
            organization.setUseCustomSmtpConfig(true);

            defaultKeyProviders.createProviders(organization);
        } catch (ModelException e) {
            throw new ModelRuntimeException("Could not configure master organization", e);
        }
    }
}
