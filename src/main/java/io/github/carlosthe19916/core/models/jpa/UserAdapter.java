package io.github.carlosthe19916.core.models.jpa;

import io.github.carlosthe19916.core.models.ModelType;
import io.github.carlosthe19916.core.models.UserModel;
import io.github.carlosthe19916.core.models.jpa.entities.UserEntity;

import javax.persistence.EntityManager;

public class UserAdapter implements UserModel, JpaModel<UserEntity> {

    private final UserEntity user;

    public UserAdapter(UserEntity user) {
        this.user = user;
    }

    public static UserEntity toEntity(UserModel model, EntityManager em) {
        if (model instanceof UserAdapter) {
            return ((UserAdapter) model).getEntity();
        }
        return em.getReference(UserEntity.class, model.getId());
    }

    @Override
    public UserEntity getEntity() {
        return user;
    }

    @Override
    public String getId() {
        return user.getId();
    }

    @Override
    public ModelType getModelType() {
        return ModelType.ORGANIZATION;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getIdentityId() {
        return user.getIdentityId();
    }

    @Override
    public String getIdentityProvider() {
        return user.getIdentityProvider();
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public void setEmail(String email) {
        user.getEmail();
    }

    @Override
    public String getFullName() {
        return user.getFullName();
    }

    @Override
    public void setFullName(String fullName) {
        user.setFullName(fullName);
    }

}
