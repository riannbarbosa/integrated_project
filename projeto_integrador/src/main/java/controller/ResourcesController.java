package controller;

import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import dao.ResourcesDAO;
import model.Resources;


@Named
@ViewScoped
public class ResourcesController implements Serializable   {

    @Inject
    private FacesContext facesContext;

    @Inject
    private ResourcesDAO resourcesDAO;


    private List<Resources> listResources;

    private Resources resources;

    private Resources selectedRes;



    @PostConstruct
    public void init() {

        this.listResources = resourcesDAO.listAllRes();

    }

    public void newResource() {
        this.setResources(new Resources());
    }
    public void addNewResource() {

        try{
            if(this.resources.getId() == null) {
                resourcesDAO.saveRes(this.resources);
                this.facesContext.addMessage(null, new FacesMessage("Material Adicionado!"));
            }else{
                this.resourcesDAO.updateRes(this.resources);
                this.facesContext.addMessage(null, new FacesMessage("Dados Atualizados!"));

            }

        }catch(Exception e){
            String errorMessage = getMensagemErro(e);
            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));

        }
    }


    public void remover() {
        try {
            this.resourcesDAO.deleteRes(this.resources);
            this.listResources = resourcesDAO.listAllRes();
            this.resources = null;
            this.facesContext.addMessage(null, new FacesMessage("Material Removido"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-resources");
        } catch (Exception e) {
            String errorMessage = getMensagemErro(e);
            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
        }
    }

    public void prepareEdit() {
        this.resources = this.selectedRes;
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


    public ResourcesDAO getResourcesDAO() {
        return resourcesDAO;
    }

    public void setResourcesDAO(ResourcesDAO resourcesDAO) {
        this.resourcesDAO = resourcesDAO;
    }

    public List<Resources> getListResources() {
        return listResources;
    }

    public void setListResources(List<Resources> listResources) {
        this.listResources = listResources;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public Resources getSelectedRes() {
        return selectedRes;
    }

    public void setSelectedRes(Resources selectedRes) {
        this.selectedRes = selectedRes;
    }
}
