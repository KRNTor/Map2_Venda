/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.modelGeneric;

import br.com.venda.map2.model.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felpz
 */
public class GenericItemController {

    public GenericItemController() {

    }

    public List<GenericItem> listarAll(List<Item> lista) {
        List<GenericItem> genericos = new ArrayList<>();
        try {
            lista.forEach(item -> {
                genericos.add(new GenericItem(item));
            });
        } catch (Exception ex) {
            Logger.getLogger(GenericItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genericos;
    }
}
