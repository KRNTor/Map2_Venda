/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Computador
 */
@Entity
@Table(name = "PESSOA")
@DiscriminatorValue("funcionario")
public class Funcionario extends Pessoa {

    private double salario;
    private String funcao;
    private String login;
    private String senha;

    public Funcionario() {
    }

    public Funcionario(double salario, String funcao, String login, String senha) {
        this.salario = salario;
        this.funcao = funcao;
        this.login = login;
        this.senha = senha;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "id=" + getId() + ", salario=" + salario + ", funcao=" + funcao + ", login=" + login + ", senha=" + senha + '}';
    }

}
