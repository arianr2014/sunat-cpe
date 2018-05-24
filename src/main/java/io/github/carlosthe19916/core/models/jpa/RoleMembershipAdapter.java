package io.github.carlosthe19916.core.models.jpa;

import io.github.carlosthe19916.core.models.OrganizationModel;
import io.github.carlosthe19916.core.models.RoleMembershipModel;
import io.github.carlosthe19916.core.models.RoleModel;
import io.github.carlosthe19916.core.models.UserModel;
import io.github.carlosthe19916.core.models.jpa.entities.RoleMembershipEntity;
import io.github.carlosthe19916.core.models.OrganizationModel;
import io.github.carlosthe19916.core.models.RoleMembershipModel;
import io.github.carlosthe19916.core.models.RoleModel;
import io.github.carlosthe19916.core.models.UserModel;
import io.github.carlosthe19916.core.models.jpa.entities.RoleMembershipEntity;

import javax.persistence.EntityManager;

public class RoleMembershipAdapter implements RoleMembershipModel, JpaModel<RoleMembershipEntity> {

    private final RoleMembershipEntity roleMembership;

    public RoleMembershipAdapter(RoleMembershipEntity roleMembership) {
        this.roleMembership = roleMembership;
    }

    public static RoleMembershipEntity toEntity(RoleMembershipModel model, EntityManager em) {
        if (model instanceof RoleMembershipAdapter) {
            return ((RoleMembershipAdapter)model).getEntity();
        }
        return em.getReference(RoleMembershipEntity.class, model.getId());
    }

    @Override
    public RoleMembershipEntity getEntity() {
        return roleMembership;
    }

    @Override
    public String getId() {
        return roleMembership.getId();
    }

    @Override
    public UserModel getUser() {
        return new UserAdapter(roleMembership.getUser());
    }

    @Override
    public RoleModel getRole() {
        return new RoleAdapter(roleMembership.getRole());
    }

    @Override
    public OrganizationModel getOrganization() {
        return new OrganizationAdapter(roleMembership.getOrganization());
    }
}
