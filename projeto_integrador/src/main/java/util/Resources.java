package util;

import javax.faces.context.FacesContext;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {


    // Define regra específica para CDI injetar objetos de classes onde o construtor comum não pode ser utilizado
// Também define o escopo do objeto criado


        @Produces
        @PersistenceContext
        private EntityManager em;

        @Produces
        @RequestScoped
        public FacesContext produceFacesContext() {
            return FacesContext.getCurrentInstance();
        }
    }
