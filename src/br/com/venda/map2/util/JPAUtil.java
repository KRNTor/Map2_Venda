/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.util;

import javax.persistence.*;

/**
 *
 * @author Felps
 */
public class JPAUtil {

    private static final EntityManagerFactory fac = Persistence.createEntityManagerFactory("VendasMap2PU");
    private static EntityManager em;

    public static EntityManager getEntityManager() {
        try {
            if (em == null || !em.isOpen()) {
                em = fac.createEntityManager();
            }
            return em;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void close() {
        try {
            if (em != null && em.isOpen()) {
                em.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
