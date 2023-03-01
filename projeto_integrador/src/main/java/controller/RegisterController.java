package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import model.User;
import org.primefaces.PrimeFaces;

import dao.UserDAO;

//Escopo do objeto da classe (Bean)
//ApplicationScoped é usado quando o objeto é único na aplicação (compartilhado entre usuários), permanece ativo enquanto a aplicação estiver ativa
//SessionScoped é usado quando o objeto é único por usuário, permanece ativo enquanto a sessão for ativa
//ViewScoped é usado quando o objeto permanece ativo enquanto não houver um redirect (acesso a nova página)
//RequestScoped é usado quando o objeto só existe naquela requisição específica
//Quanto maior o escopo, maior o uso de memória no lado do servidor (objeto permanece ativo por mais tempo)
//Escopos maiores que Request exigem que classes sejam serializáveis assim como todos os seus atributos (recurso de segurança)
//atributos que não podem    ser serializáveis devem ser marcados como transient (eles são novamente criados a cada nova requisição independente do escopo da classe)
@ViewScoped
//Torna classe disponível na camada de visão (html) - são chamados de Beans ou ManagedBeans (gerenciados pelo JSF/EJB)
@Named
public class RegisterController implements Serializable {

    //Anotação que marca atributo para ser gerenciado pelo CDI
    //O CDI criará uma instância do objeto automaticamente quando necessário
    @Inject
    private FacesContext facesContext;

    @Inject
    transient private Pbkdf2PasswordHash passwordHash;

    @Inject
    private UserDAO userDAO;


    private User user;

    private List<User> listUsers;

    private List<SelectItem> permissoes;

    private List<Integer> permissoesSelecionadas;

    //Anotação que força execução do método após o construtor da classe ser executado
    @PostConstruct
    public void init() {
        //Verifica se usuário está autenticado e possui a permissão adequada

        //Inicializa elementos importantes
        this.listUsers = userDAO.listAll();
    }

    //Chamado pelo botão novo
    public void novoCadastro() {
        this.setUser(new User());
    }

    //Chamado ao salvar cadastro de usuário (novo ou edição)
    public void save() {

            try {
                this.user.setSenha(this.passwordHash.generate(this.user.getSenha().toCharArray()));
                if (this.user.getId() == null) {
                    this.userDAO.save(this.user);
                    this.facesContext.addMessage(null, new FacesMessage("Usuário Criado"));
                } else {
                    this.userDAO.update(this.user);
                    this.facesContext.addMessage(null, new FacesMessage("Usuário Atualizado"));
                }
            } catch (Exception e) {
                String errorMessage = getMensagemErro(e);
                this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
            }
        }


    //Realiza validações adicionais (não relizadas no modelo) e/ou complexas/interdependentes




    //Chamado pelo botão remover da tabela
    public void remover() {
        try {
            this.userDAO.delete(this.user);
            this.listUsers = userDAO.listAll();
            this.user = null;
            this.facesContext.addMessage(null, new FacesMessage("Usuário Removido"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-usuarios");
        } catch (Exception e) {
            String errorMessage = getMensagemErro(e);
            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
        }
    }



    //Captura mensagem de erro das validações do Hibernate
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

    //GETs e SETs

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public List<SelectItem> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<SelectItem> permissoes) {
        this.permissoes = permissoes;
    }
}