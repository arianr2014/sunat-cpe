package io.github.carlosthe19916.pe.services;

import io.github.carlosthe19916.core.models.OrganizationModel;
import io.github.carlosthe19916.core.models.OrganizationProvider;
import io.github.carlosthe19916.core.models.PermissionType;
import io.github.carlosthe19916.core.security.ISecurityContext;
import io.github.carlosthe19916.pe.BajasResource;
import io.github.carlosthe19916.pe.managers.TypeManager;
import io.github.carlosthe19916.pe.models.BajaModel;
import io.github.carlosthe19916.pe.models.BajaProvider;
import io.github.carlosthe19916.pe.models.utils.ModelToRepresentation;
import io.github.carlosthe19916.pe.representations.idm.BajaRepresentation;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class DefaultBajasResource implements BajasResource {

    private static final Logger logger = Logger.getLogger(DefaultBajasResource.class);

    @Inject
    private ISecurityContext securityContext;

    @Inject
    private TypeManager typeManager;

    @Inject
    private BajaProvider bajaProvider;

    @Inject
    private ResourceManager resourceManager;

    @Inject
    private OrganizationProvider organizationProvider;

    @Override
    public List<BajaRepresentation> getBajas(String organizationId, String filterText, int offset, int limit) {
        if (!securityContext.isAdmin() && !securityContext.hasPermission(PermissionType.document_view, organizationId)) {
            throw new ForbiddenException();
        }

        OrganizationModel organization = organizationProvider.getOrganization(organizationId).orElseThrow(NotFoundException::new);
        return bajaProvider.buscarBajas(organization, filterText, offset, limit)
                .stream()
                .map(f -> ModelToRepresentation.toRepresentation(f, true))
                .collect(Collectors.toList());
    }

    @Override
    public BajaRepresentation crearBaja(String organizationId, Boolean async, BajaRepresentation rep) {
        if (!securityContext.isAdmin() && !securityContext.hasPermission(PermissionType.document_manage, organizationId)) {
            throw new ForbiddenException();
        }

        BajaModel baja = resourceManager.crearBaja(organizationId, rep);
        Future<BajaModel> future = typeManager.buildBaja(organizationId, baja.getId());
        if (!async) {
            try {
                baja = future.get();
            } catch (InterruptedException | ExecutionException e) {
                logger.error(e);
                Thread.currentThread().interrupt();
            }
        }

        return ModelToRepresentation.toRepresentation(baja, true);
    }

    @Override
    public BajaRepresentation getBaja(String organizationId, String documentId) {
        if (!securityContext.isAdmin() && !securityContext.hasPermission(PermissionType.document_view, organizationId)) {
            throw new ForbiddenException();
        }

        OrganizationModel organization = organizationProvider.getOrganization(organizationId).orElseThrow(NotFoundException::new);
        BajaModel baja = bajaProvider.getBaja(organization, documentId).orElseThrow(NotFoundException::new);
        return ModelToRepresentation.toRepresentation(baja, true);
    }

    @Override
    public BajaRepresentation actualizarBaja(String organizationId, String idDocumento, Boolean async, BajaRepresentation rep) {
        throw new ForbiddenException();
    }

    @Override
    public void eliminarBaja(String organizationId, String idDocumento) {
        throw new ForbiddenException();
    }
}
