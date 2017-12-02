/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.modelGeneric;

import br.com.venda.map2.model.Funcionario;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felpz
 */
public class GenericFuncionarioController {

    public GenericFuncionarioController() {

    }

    public List<GenericFuncionario> listarAll(List<Funcionario> lista) {
        List<GenericFuncionario> genericos = new ArrayList<>();
        try {
            lista.forEach(func -> {
                genericos.add(new GenericFuncionario(func));
            });
        } catch (Exception ex) {
            Logger.getLogger(GenericFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genericos;
    }
}
