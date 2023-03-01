package dao;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

import model.Clients;

@Stateful
public class ClientsDAO implements Serializable {

    @Inject
    private EntityManager em;

    public List<Clients> listAllCli() {
        return em.createQuery("SELECT a FROM Clients a ", Clients.class).getResultList();
    }

    public void saveCli(Clients u) {
        em.persist(u);
    }

    public void updateCli(Clients u) { em.merge(u);}

    public void deleteCli(Clients u) { em.remove(em.getReference(Clients.class, u.getId()));}









}
