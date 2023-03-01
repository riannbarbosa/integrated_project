package controller;

import dao.ClientsDAO;
import model.Clients;
import model.User;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dao.AddressDAO;
import model.Addresses;
import model.Clients;
import dao.ClientsDAO;


@Named
@ViewScoped
public class ClientsController implements Serializable   {

    @Inject
    private FacesContext facesContext;

    @Inject
    private ClientsDAO clientsDAO;


    private List<Addresses> listAddresses;
    private List<Clients> listClients;

    private Clients clients;

    @PostConstruct
    public void init() {

        this.listClients = clientsDAO.listAllCli();

    }

    public void newClient() {
        this.setClients(new Clients());
    }
    public void addNewClient() {

        try{
        if(this.clients.getId() == null) {
            clientsDAO.saveCli(this.clients);
            this.facesContext.addMessage(null, new FacesMessage("Cliente Adicionado!"));
        }else{
            this.clientsDAO.updateCli(this.clients);
            this.facesContext.addMessage(null, new FacesMessage("Dados Atualizados!"));

        }

        }catch(Exception e){
            String errorMessage = getMensagemErro(e);
            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));

        }
    }

    public void remover() {
        try {
            this.clientsDAO.deleteCli(this.clients);
            this.listClients = clientsDAO.listAllCli();
            this.clients = null;
            this.facesContext.addMessage(null, new FacesMessage("Cliente Removido"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-usuarios");
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


    public ClientsDAO getClientsDAO() {
        return clientsDAO;
    }

    public void setClientsDAO(ClientsDAO clientsDAO) {
        this.clientsDAO = clientsDAO;
    }



    public List<Addresses> getListAddresses() {
        return listAddresses;
    }

    public void setListAddresses(List<Addresses> listAddresses) {
        this.listAddresses = listAddresses;
    }

    public List<Clients> getListClients() {
        return listClients;
    }

    public void setListClients(List<Clients> listClients) {
        this.listClients = listClients;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }
}
