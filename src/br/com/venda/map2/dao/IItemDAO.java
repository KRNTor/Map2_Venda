/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.dao;

import br.com.venda.map2.model.Item;

/**
 *
 * @author Computador
 */
public interface IItemDAO extends IDaoGeneric<Item> {

    Item getByName(String nome);

}
