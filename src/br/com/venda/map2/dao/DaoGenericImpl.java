package br.com.venda.map2.dao;

import br.com.venda.map2.util.JPAUtil;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import br.com.venda.map2.exception.DAOException;

public class DaoGenericImpl<T> implements IDaoGeneric<T> {

    private Class classe;

    public DaoGenericImpl() {
        this.classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T save(T t) throws DAOException {
        EntityManager manager = JPAUtil.getEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(t);
            manager.getTransaction().commit();
            JPAUtil.close();
            return t;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            throw new DAOException("Problema ao salvar objeto no BD..");
        }
    }

    @Override
    public T update(T t) throws DAOException {
        EntityManager manager = JPAUtil.getEntityManager();
        try {
            manager.getTransaction().begin();
            manager.merge(t);
            manager.flush();
            manager.getTransaction().commit();
//            manager.refresh(t); por algum motivo naum funciona =]
            JPAUtil.close();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("");
        }
    }

    @Override
    public T remove(T t) throws DAOException {
        EntityManager manager = JPAUtil.getEntityManager();
        try {
            manager.getTransaction().begin();
            t = manager.merge(t);
            manager.remove(t);
            manager.flush();
            JPAUtil.close();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("");
        }
    }

    @Override
    public T getById(Long id) throws DAOException {
        T resultado = null;
        try {
            Criteria criteria = getCriteria(); //select * from Pessoa
            criteria.add(Restrictions.eq("id", id)); //where id = id
            resultado = (T) criteria.uniqueResult();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("");
        }
    }

    @Override
    public List<T> getAll() throws DAOException {
        List<T> resultado = null;
        try {
            Criteria criteria = getCriteria();//select * from pessoa orderBy id;
            criteria.addOrder(Order.asc("id")); //orderBy id;
            resultado = (List<T>) criteria.list();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("");
        }
    }

    @Override
    public Criteria getCriteria() {
        EntityManager manager = JPAUtil.getEntityManager();
        Session session = ((Session) manager.getDelegate());
        return session.createCriteria(classe);
    }

}
