/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.model;

import br.com.venda.map2.prototype.IPrototype;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Computador
 */
@Entity
public class Item implements IPrototype<Item> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private double precoCompraItem;
    private double precoVendaItem;
    @OneToOne(fetch = FetchType.EAGER)
    private Fornecedor fornecedor;
    @Temporal(TemporalType.DATE)
    private Date validade;
    private int quantidade;
    private boolean status;

    public Item() {
        this.status = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoCompraItem() {
        return precoCompraItem;
    }

    public void setPrecoCompraItem(double precoCompraItem) {
        this.precoCompraItem = precoCompraItem;
    }

    public double getPrecoVendaItem() {
        return precoVendaItem;
    }

    public void setPrecoVendaItem(double precoVendaItem) {
        this.precoVendaItem = precoVendaItem;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public Item clone() {
        return new Item();
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", nome=" + nome + ", precoCompraItem=" + precoCompraItem + ", precoVendaItem=" + precoVendaItem + ", fornecedor=" + fornecedor + ", validade=" + validade + ", quantidade=" + quantidade + '}';
    }

}
