package io.github.carlosthe19916.core.services;

import io.github.carlosthe19916.core.ProfileResource;
import io.github.carlosthe19916.core.models.UserModel;
import io.github.carlosthe19916.core.models.UserProvider;
import io.github.carlosthe19916.core.models.utils.ModelToRepresentation;
import io.github.carlosthe19916.core.representations.idm.UserRepresentation;
import io.github.carlosthe19916.core.security.ISecurityContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@ApplicationScoped
public class DefaultProfileResource implements ProfileResource {

    @Inject
    private UserProvider userProvider;

    @Inject
    private ISecurityContext securityContext;

    @Override
    public UserRepresentation getProfile() {
        Optional<UserModel> userModel = userProvider.getUserByIdentityId(securityContext.getIdentityId());
        return ModelToRepresentation.toRepresentation(userModel.orElseGet(this::firstLogin), true);
    }

    private UserModel firstLogin() {
        UserModel user = userProvider.addUser(securityContext.getUsername(), securityContext.getIdentityId(), securityContext.getIdentityProviderAlias());
        user.setEmail(securityContext.getEmail());
        user.setFullName(securityContext.getFullName());
        return user;
    }

}
