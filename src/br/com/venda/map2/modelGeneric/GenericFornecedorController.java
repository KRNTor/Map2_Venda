/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.modelGeneric;

import br.com.venda.map2.model.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felpz
 */
public class GenericFornecedorController {

    public GenericFornecedorController() {

    }

    public List<GenericFornecedor> listarAll(List<Fornecedor> lista) {
        List<GenericFornecedor> genericos = new ArrayList<>();
        try {
            lista.forEach(forn -> {
                genericos.add(new GenericFornecedor(forn));
            });
        } catch (Exception ex) {
            Logger.getLogger(GenericFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genericos;
    }
}
