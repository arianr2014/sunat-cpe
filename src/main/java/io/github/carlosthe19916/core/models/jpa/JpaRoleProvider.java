package io.github.carlosthe19916.core.models.jpa;

import io.github.carlosthe19916.core.models.*;
import io.github.carlosthe19916.core.models.jpa.entities.RoleEntity;
import io.github.carlosthe19916.core.models.ModelCreationEvent;
import io.github.carlosthe19916.core.models.ModelRemoveEvent;
import io.github.carlosthe19916.core.models.RoleModel;
import io.github.carlosthe19916.core.models.RoleProvider;
import io.github.carlosthe19916.core.models.jpa.entities.RoleEntity;
import io.github.carlosthe19916.core.models.utils.ModelUtils;
import io.github.carlosthe19916.core.models.PermissionType;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@ApplicationScoped
public class JpaRoleProvider implements RoleProvider {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private Event<ModelCreationEvent<RoleModel>> creationEvent;

    @Inject
    private Event<ModelRemoveEvent<RoleModel>> removeEvent;

    @Override
    public RoleModel addRole(String name, Set<PermissionType> permissions, boolean autoGrant) {
        return addRole(ModelUtils.generateId(), name, permissions, autoGrant);
    }

    @Override
    public RoleModel addRole(String id, String name, Set<PermissionType> permissions, boolean autoGrant) {
        RoleEntity entity = new RoleEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setPermissions(permissions);
        entity.setAutoGrant(autoGrant);
        entity.setCreatedOn(new Date());

        em.persist(entity);

        final RoleAdapter role = new RoleAdapter(entity);
        creationEvent.fire(() -> role);
        return role;
    }

    @Override
    public Optional<RoleModel> getRole(String id) {
        RoleEntity entity = em.find(RoleEntity.class, id);
        if (entity == null) return Optional.empty();
        return Optional.of(new RoleAdapter(entity));
    }

    @Override
    public Set<RoleModel> getAutoGrantedRoles() {
        TypedQuery<RoleEntity> query = em.createNamedQuery("ListAutoGrantRoles", RoleEntity.class);
        return query.getResultList()
                .stream()
                .map(RoleAdapter::new)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean deleteRole(RoleModel role) {
        RoleEntity entity = RoleAdapter.toEntity(role, em);
        em.remove(entity);
        removeEvent.fire(() -> role);
        return true;
    }

}
