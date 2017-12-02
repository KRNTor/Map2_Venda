/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.modelGeneric;

import br.com.venda.map2.model.Fornecedor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author felpz
 */
public class GenericFornecedor {

    private StringProperty nome;
    private StringProperty cnpj;

    public GenericFornecedor(Fornecedor forn) {
        try {
            this.nome = new SimpleStringProperty(forn.getNome());
            this.cnpj = new SimpleStringProperty(forn.getCnpj());
        } catch (Exception ex) {
            Logger.getLogger(GenericFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getCnpj() {
        return cnpj.get();
    }

    public void setCnpj(String cnpj) {
        this.cnpj.set(cnpj);
    }

    @Override
    public String toString() {
        return "GenericFornecedor{" + "nome=" + nome + ", cnpj=" + cnpj + '}';
    }

}
