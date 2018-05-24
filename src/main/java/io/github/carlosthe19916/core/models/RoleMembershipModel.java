package io.github.carlosthe19916.core.models;

public interface RoleMembershipModel {
    String getId();
    UserModel getUser();
    RoleModel getRole();
    OrganizationModel getOrganization();
}
