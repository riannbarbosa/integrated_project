package controller;

import dao.ResourcesDAO;
import model.Resources;
import model.Orders;
import dao.OrdersDAO;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class DashboardController implements Serializable {

    @Inject
    private FacesContext facesContext;

    @Inject
    private EntityManager em;

    private double sumOfResources = 0;

    private double sumValue = 0;

    @PostConstruct
    public void init() {
        try {
            sumOfResources = em.createQuery("SELECT SUM(r.value) FROM Resources r", Double.class).getSingleResult();

        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to retrieve sum of resources"));
            e.printStackTrace();
        }
        try {
            sumValue = em.createQuery("SELECT SUM(s.value) FROM Orders s WHERE s.status = 'true'", Double.class).getSingleResult();

        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to retrieve sum of orders"));
            e.printStackTrace();
        }

    }


    public double realProfitCalc() {
        if (sumValue >= 0 && sumOfResources >= 0) {
            return sumValue - sumOfResources;
        }

        return 0;

    }

    public double getSumOfResources() {
        return sumOfResources;
    }

    public void setSumOfResources(float sumOfResources) {
        this.sumOfResources = sumOfResources;
    }

    public double getSumValue() {
        return sumValue;
    }

    public void setSumValue(double sumValue) {
        this.sumValue = sumValue;
    }
}