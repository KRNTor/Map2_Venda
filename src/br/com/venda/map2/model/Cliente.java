/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Computador
 */
@Entity
@DiscriminatorValue("cliente")
public class Cliente extends Pessoa {

    private int qtdCompras;

    public Cliente() {
        this.qtdCompras = 0;
    }

    public int getQtdCompras() {
        return qtdCompras;
    }

    public void setQtdCompras(int qtdCompras) {
        this.qtdCompras = qtdCompras;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + getId() + ", qtdCompras=" + qtdCompras + '}';
    }

}
