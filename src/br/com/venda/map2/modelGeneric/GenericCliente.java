/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.modelGeneric;

import br.com.venda.map2.model.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author felpz
 */
public class GenericCliente {

    private StringProperty nome;
    private IntegerProperty qunt;

    public GenericCliente(Cliente cliente) {
        try {
            this.nome = new SimpleStringProperty(cliente.getNome());
            this.qunt = new SimpleIntegerProperty(cliente.getQtdCompras());

        } catch (Exception ex) {
            Logger.getLogger(GenericCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public Integer getQunt() {
        return qunt.get();
    }

    public void setQunt(Integer qunt) {
        this.qunt.set(qunt);
    }

    @Override
    public String toString() {
        return "GenericCliente{" + "nome=" + nome + ", qunt=" + qunt + '}';
    }

}
