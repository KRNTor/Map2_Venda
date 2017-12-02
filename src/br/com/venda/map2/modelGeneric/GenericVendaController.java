/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.modelGeneric;

import br.com.venda.map2.model.Venda;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felpz
 */
public class GenericVendaController {

    public GenericVendaController() {

    }

    public List<GenericVenda> listarAll(List<Venda> lista) {
        List<GenericVenda> genericos = new ArrayList<>();
        try {
            lista.forEach(venda -> {
                genericos.add(new GenericVenda(venda));
            });
        } catch (Exception ex) {
            Logger.getLogger(GenericVendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genericos;
    }
}
