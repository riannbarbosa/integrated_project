package dao;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

import model.Resources;

@Stateful
public class ResourcesDAO implements Serializable {

    @Inject
    private EntityManager em;

    public List<Resources> listAllRes() {
        return em.createQuery("SELECT a FROM Resources a ", Resources.class).getResultList();
    }



    public void saveRes(Resources u) {
        em.persist(u);
    }

    public void updateRes(Resources u) { em.merge(u);}

    public void deleteRes(Resources u) { em.remove(em.getReference(Resources.class, u.getId()));}









}
