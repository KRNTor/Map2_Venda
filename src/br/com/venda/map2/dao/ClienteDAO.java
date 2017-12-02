/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.dao;

import br.com.venda.map2.exception.DAOException;
import br.com.venda.map2.model.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Projections.id;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Computador
 */
public class ClienteDAO extends DaoGenericImpl<Cliente> implements IClienteDAO {

    private EntityManager em;

    @Override
    public Cliente getByName(String nome) throws DAOException {
        Cliente resultado = null;
        try {
            Criteria criteria = getCriteria();
            criteria.add(Restrictions.eq("nome", nome));
            resultado = (Cliente) criteria.uniqueResult();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("");
        }
    }
}
