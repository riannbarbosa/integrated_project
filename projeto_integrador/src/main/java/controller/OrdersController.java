package controller;

import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import dao.OrdersDAO;
import model.Orders;


@Named
@ViewScoped
public class OrdersController implements Serializable   {

    @Inject
    private FacesContext facesContext;

    @Inject
    private EntityManager em;

    @Inject
    private OrdersDAO ordersDAO;


    private List<String> clientList;

    private List<Orders> listOrders;




    private Orders orders;





    @PostConstruct
    public void init() {

        this.listOrders = ordersDAO.listAllOrders();

    }

    public void newOrder() {
        this.setOrders(new Orders());
    }
    public void addNewOrder() {

        try{
            if(this.orders.getId() == null) {
                ordersDAO.saveOrd(this.orders);
                this.facesContext.addMessage(null, new FacesMessage("Encomenda Adicionada!"));
            }else{
                this.ordersDAO.updateOrd(this.orders);
                this.facesContext.addMessage(null, new FacesMessage("Dados Atualizados!"));

            }

        }catch(Exception e){
            String errorMessage = getMensagemErro(e);
            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));

        }
    }

    public void displayNameClient() {
        try {
            clientList = em.createQuery("SELECT c.name FROM Clients c", String.class).getResultList();
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to retrieve sum of resources"));
            e.printStackTrace();
        }
    }





    public void remover() {
        try {
            this.ordersDAO.deleteOrd(this.orders);
            this.listOrders = ordersDAO.listAllOrders();
            this.orders = null;
            this.facesContext.addMessage(null, new FacesMessage("Encomenda Removida"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-orders");
        } catch (Exception e) {
            String errorMessage = getMensagemErro(e);
            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
        }
    }


    private String getMensagemErro(Exception e) {
        String erro = "Falha no sistema!. Contacte o administrador do sistema.";
        if (e == null)
            return erro;
        Throwable t = e;
        while (t != null) {
            erro = t.getLocalizedMessage();
            t = t.getCause();
        }
        return erro;
    }

    public List<Orders> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Orders> listOrders) {
        this.listOrders = listOrders;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public OrdersDAO getOrdersDAO() {
        return ordersDAO;
    }

    public void setOrdersDAO(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    public List getClientList() {
        return clientList;
    }

    public void setClientList(List clientList) {
        this.clientList = clientList;
    }
}
