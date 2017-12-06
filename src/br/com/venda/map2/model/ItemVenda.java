/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.model;

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
public class ItemVenda {

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
    private int quantidade_saida;
    private boolean status;

    public ItemVenda() {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getQuantidade_saida() {
        return quantidade_saida;
    }

    public void setQuantidade_saida(int quantidade_saida) {
        this.quantidade_saida = quantidade_saida;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", nome=" + nome + ", precoCompraItem=" + precoCompraItem + ", precoVendaItem=" + precoVendaItem + ", fornecedor=" + fornecedor + ", validade=" + validade + ", quantidade=" + quantidade_saida + '}';
    }
}
