package dao;

import model.Clients;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

import model.Addresses;
public class AddressDAO implements Serializable {


    @Inject
    private EntityManager em;

    public List<Addresses> listAllAdd() {
        return em.createQuery("SELECT a FROM Addresses a ", Addresses.class).getResultList();
    }

    @Transactional

    public void saveAdd(Addresses u) {
        em.persist(u);
    }

    public void updateAdd(Addresses u) { em.merge(u);}

    public void deleteAdd(Addresses u) { em.remove(em.getReference(Clients.class, u.getId()));}



}
