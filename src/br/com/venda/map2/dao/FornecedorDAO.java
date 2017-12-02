/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.dao;

import br.com.venda.map2.model.Fornecedor;
import br.com.venda.map2.util.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Computador
 */
public class FornecedorDAO extends DaoGenericImpl<Fornecedor> implements IFornecedorDAO {

    private EntityManager em;

    @Override
    public Fornecedor getFornecedorByName(String nome) {
        try {
            em = JPAUtil.getEntityManager();
            Query query = em.createQuery("select f from Fornecedor f where f.nome= :nome");
            query.setParameter("nome", nome);
            return (Fornecedor) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
//            throw new Exception(PropertiesUtil.getMsgValue(PropertiesUtil.MSG_ERROR_BUSCAR + " USUARIO NÃO ENCONTRADO!"));
        } finally {
            JPAUtil.close();
        }
        return null;
    }

}
