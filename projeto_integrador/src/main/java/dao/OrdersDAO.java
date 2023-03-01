package dao;

import model.Resources;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

import model.Orders;
import org.hibernate.criterion.Order;

@Stateful
public class OrdersDAO implements Serializable {

    @Inject
    private EntityManager em;

    public List<Orders> listAllOrders() {
        return em.createQuery("SELECT a FROM Orders a ", Orders.class).getResultList();
    }



    public void saveOrd(Orders u) {
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }

    public void updateOrd(Orders u) { em.merge(u);}

    public void deleteOrd(Orders u) { em.remove(em.getReference(Orders.class, u.getId()));}




}
