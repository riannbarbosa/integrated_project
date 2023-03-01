package util;

import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.User;
import dao.UserDAO;
//Executa classe uma única vez ao iniciar a aplicação no servidor
//Recurso usado para criar o primeiro usuário ADMINISTRADOR no sistema, se o mesmo não existir ainda
@WebListener
public class AdminSetup implements ServletContextListener {

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @Inject
    private UserDAO usuarioDAO;

    private User admin;

    public void contextInitialized(ServletContextEvent event) {
        if (usuarioDAO.isUnicUser("admin")){
            admin = new User();
            admin.setEmail("admin@admin.com");
            String senhaPadrao = "admin";
            admin.setSenha(passwordHash.generate(senhaPadrao.toCharArray()));
            admin.setUsuario("admin");
            usuarioDAO.save(admin);
        }
    }
}