/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.modelGeneric;

import br.com.venda.map2.model.ItemVenda;

/**
 *
 * @author felpz
 */
public class GenericItemVendaController {

    public GenericItemVendaController() {

    }

    public GenericItemVenda parseItem(ItemVenda item) {
        return new GenericItemVenda(item);
    }
}
