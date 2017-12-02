/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.modelGeneric;

import br.com.venda.map2.model.Item;
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
public class GenericItem {

    private StringProperty nome;
    private IntegerProperty qunt_estq;
    private DoubleProperty valor_und;

    public GenericItem(Item item) {
        try {
            this.nome = new SimpleStringProperty(item.getNome());
            this.qunt_estq = new SimpleIntegerProperty(item.getQuantidade());
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

    public Integer getQunt_estq() {
        return qunt_estq.get();
    }

    public void setQunt_estq(Integer qunt_estq) {
        this.qunt_estq.set(qunt_estq);
    }

    public Double getValor_und() {
        return valor_und.get();
    }

    public void setValor_und(Double valor_und) {
        this.valor_und.set(valor_und);
    }

    @Override
    public String toString() {
        return "GenericItem{" + "nome=" + nome + ", qunt_estq=" + qunt_estq + ", valor_und=" + valor_und + '}';
    }

}
