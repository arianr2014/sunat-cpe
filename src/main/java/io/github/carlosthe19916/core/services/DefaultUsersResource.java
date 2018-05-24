package io.github.carlosthe19916.core.services;

import io.github.carlosthe19916.core.UsersResource;
import io.github.carlosthe19916.core.models.UserModel;
import io.github.carlosthe19916.core.models.UserProvider;
import io.github.carlosthe19916.core.representations.idm.UserRepresentation;
import io.github.carlosthe19916.core.UsersResource;
import io.github.carlosthe19916.core.models.UserModel;
import io.github.carlosthe19916.core.models.UserProvider;
import io.github.carlosthe19916.core.models.utils.ModelToRepresentation;
import io.github.carlosthe19916.core.representations.idm.UserRepresentation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.List;

@Transactional
@ApplicationScoped
public class DefaultUsersResource implements UsersResource {

    @Inject
    private UserProvider userProvider;

    @Override
    public void createUser() {
        throw new ForbiddenException();
    }

    @Override
    public List<UserRepresentation> getUsers(String userId, String username) {
        if (userId != null) {
            return userProvider.getUser(userId)
                    .map(model -> Collections.singletonList(ModelToRepresentation.toRepresentation(model, false)))
                    .orElseGet(Collections::emptyList);
        }
        if (username != null) {
            return userProvider.getUserByUsername(username)
                    .map(model -> Collections.singletonList(ModelToRepresentation.toRepresentation(model, false)))
                    .orElseGet(Collections::emptyList);
        } else {
            throw new IllegalStateException("Unimplemented");
        }
    }

    @Override
    public UserRepresentation getUser(String userId) {
        UserModel userModel = userProvider.getUser(userId).orElseThrow(NotFoundException::new);
        return ModelToRepresentation.toRepresentation(userModel, true);
    }

    @Override
    public UserRepresentation updateUser(String userId, UserRepresentation rep) {
        UserModel userModel = userProvider.getUser(userId).orElseThrow(NotFoundException::new);
        return ModelToRepresentation.toRepresentation(userModel, true);
    }

    @Override
    public void deleteUser(String userId) {
        throw new ForbiddenException();
    }

}
