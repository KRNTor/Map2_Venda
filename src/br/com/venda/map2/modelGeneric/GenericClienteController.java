/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.modelGeneric;

import br.com.venda.map2.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felpz
 */
public class GenericClienteController {

    public GenericClienteController() {

    }

    public List<GenericCliente> listarAll(List<Cliente> lista) {
        List<GenericCliente> genericos = new ArrayList<>();
        try {
            lista.forEach(cliente -> {
                genericos.add(new GenericCliente(cliente));
            });
        } catch (Exception ex) {
            Logger.getLogger(GenericClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genericos;
    }
}
