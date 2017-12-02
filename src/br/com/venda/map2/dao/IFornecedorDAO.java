/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.dao;

import br.com.venda.map2.model.Fornecedor;

/**
 *
 * @author Computador
 */
public interface IFornecedorDAO extends IDaoGeneric<Fornecedor> {
    Fornecedor getFornecedorByName(String nome);
}
