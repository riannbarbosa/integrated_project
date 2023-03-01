package dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.User;

//Classe DAO responsável pelas regras de negócio envolvendo operações de persistência de dados
//Indica-se a acriação de um DAO específico para cada Modelo

//Anotação EJB que indica que Bean (objeto criado para a classe) será comum para toda a aplicação
//Isso faz com que recursos computacionais sejam otimizados
@Stateful
public class UserDAO implements Serializable{

    @Inject
    //Cria a conexão e controla a transação com o SGBD (usado pelo Hibernate)
    private EntityManager em;

    public User findId(Integer id) {
        return em.find(User.class, id);
    }

    //Query usando a API Criteria do Hibernate
    //Indicada para consultas complexas
    public Boolean isUnicUser(String u) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
            Root<User> user = criteria.from(User.class);
        criteria.select(user);
        criteria.where(cb.like(user.get("usuario"), u));
        if (em.createQuery(criteria).getResultList().isEmpty())
            return true;
        return false;
    }

    //Query usando a linguagem HQL do Hibernate
    //Idnicada para consultas simples
    public List<User> listAll() {
        return em.createQuery("SELECT a FROM User a ", User.class).getResultList();
    }

    public void save(User u) {
        em.persist(u);
    }

    public void update(User u) {
        em.merge(u);
    }

    public void delete(User u) {
        em.remove(em.getReference(User.class, u.getId()));
    }

}