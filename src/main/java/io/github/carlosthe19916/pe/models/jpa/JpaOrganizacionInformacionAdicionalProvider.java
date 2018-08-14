package io.github.carlosthe19916.pe.models.jpa;

import io.github.carlosthe19916.core.models.OrganizationModel;
import io.github.carlosthe19916.core.models.jpa.entities.OrganizationEntity;
import io.github.carlosthe19916.pe.models.OrganizacionInformacionAdicionalModel;
import io.github.carlosthe19916.pe.models.OrganizationInformacionAdicionalProvider;
import io.github.carlosthe19916.pe.models.jpa.entities.OrganizacionInformacionAdicionalEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Transactional
@ApplicationScoped
public class JpaOrganizacionInformacionAdicionalProvider implements OrganizationInformacionAdicionalProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<OrganizacionInformacionAdicionalModel> getOrganizacionInformacionAdicional(String id) {
        OrganizationEntity companyEntity = em.find(OrganizationEntity.class, id);
        if (companyEntity == null) return Optional.empty();

        EntityGraph<?> graph = em.getEntityGraph("graph.InformacionAdicionalOrganizacion");
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.fetchgraph", graph);

        OrganizacionInformacionAdicionalEntity additionalInfoEntity = em.find(OrganizacionInformacionAdicionalEntity.class, id, hints);
        if (additionalInfoEntity == null) {
            OrganizacionInformacionAdicionalEntity newAdditionalInfo = new OrganizacionInformacionAdicionalEntity();
            newAdditionalInfo.setOrganizacion(companyEntity);
            em.persist(newAdditionalInfo);

            additionalInfoEntity = newAdditionalInfo;
        }

        return Optional.of(new OrganizacionInformacionAdicionalAdapter(em, additionalInfoEntity));
    }

    @Override
    public Optional<OrganizacionInformacionAdicionalModel> getOrganizacionInformacionAdicional(OrganizationModel organization) {
        return getOrganizacionInformacionAdicional(organization.getId());
    }

}
