package controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

//Controlador da página de Login
@Named
@RequestScoped
public class LoginController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private SecurityContext securityContext;

    private User user;

    @PostConstruct
    public void inicializarUsuario() {
        user = new User();
    }

    public void login() throws IOException{
        if (facesContext.getExternalContext().getAuthType() != null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existe um usuário autenticado! Use a opção logout primeiro.", ""));
            }
        } else {
            Credential credential = new UsernamePasswordCredential(user.getUsuario(), new Password(user.getSenha()));
            AuthenticationStatus status = securityContext.authenticate(
                    (HttpServletRequest)facesContext.getExternalContext().getRequest(),
                    (HttpServletResponse)facesContext.getExternalContext().getResponse(),
                    AuthenticationParameters.withParams().credential(credential));
            if (status.equals(AuthenticationStatus.SUCCESS))
                facesContext.getExternalContext().redirect("dashboard.xhtml");
            else if (status.equals(AuthenticationStatus.SEND_FAILURE)) {
                user = new User();
                try {
                    throw new Exception();
                } catch (Exception e) {
                    facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Inválido!", "Usuário ou senha incorretos."));
                }
            }
        }
    }
    public void logout() throws IOException {
        facesContext.getExternalContext().invalidateSession();
        facesContext.getExternalContext().redirect("logout.xhtml");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}