/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.modelGeneric;

import br.com.venda.map2.model.Item;

/**
 *
 * @author felpz
 */
public class GenericItemVendaController {

    public GenericItemVendaController() {

    }

    public GenericItemVenda parseItem(Item item, String qunt) {
        return new GenericItemVenda(item, qunt);
    }
}
