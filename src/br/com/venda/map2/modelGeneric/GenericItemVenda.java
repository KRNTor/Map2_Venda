/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.modelGeneric;

import br.com.venda.map2.model.ItemVenda;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author felpz
 */
public class GenericItemVenda {

    private StringProperty nome;
    private IntegerProperty qunt_cart;
    private DoubleProperty valor_und;

    public GenericItemVenda(ItemVenda item) {
        try {
            this.nome = new SimpleStringProperty(item.getNome());
            this.qunt_cart = new SimpleIntegerProperty(item.getQuantidade_saida());
            this.valor_und = new SimpleDoubleProperty(item.getPrecoVendaItem());
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

    public int getQunt_cart() {
        return qunt_cart.get();
    }

    public void setQunt_cart(int qunt_cart) {
        this.qunt_cart.set(qunt_cart);
    }

    public Double getValor_und() {
        return valor_und.get();
    }

    public void setValor_und(Double valor_und) {
        this.valor_und.set(valor_und);
    }

    @Override
    public String toString() {
        return "GenericItem{" + "nome=" + nome + ", qunt_estq=" + qunt_cart + ", valor_und=" + valor_und + '}';
    }

}
