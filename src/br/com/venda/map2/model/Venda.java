/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.model;

import br.com.venda.map2.prototype.IPrototype;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Computador
 */
@Entity
public class Venda implements IPrototype<Venda> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Cliente cliente;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Item> itens;
    @OneToOne(fetch = FetchType.EAGER)
    private Funcionario funcionario;
    private String modoDePagamento;
    @Temporal(TemporalType.DATE)
    private Date dtVenda;
    private double valor;

    public Venda() {
    }

    public Venda(Cliente cliente, List<Item> itens, Funcionario funcionario, String modoDePagamento, Date dtVenda, double valor) {
        this.cliente = cliente;
        this.itens = itens;
        this.funcionario = funcionario;
        this.modoDePagamento = modoDePagamento;
        this.dtVenda = dtVenda;
        this.valor = valor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Item> getItem() {
        return itens;
    }

    public void setItem(List<Item> itens) {
        this.itens = itens;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getModoDePagamento() {
        return modoDePagamento;
    }

    public void setModoDePagamento(String modoDePagamento) {
        this.modoDePagamento = modoDePagamento;
    }

    public Date getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(Date dtVenda) {
        this.dtVenda = dtVenda;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public Venda clone() {
        return new Venda();
    }

}
