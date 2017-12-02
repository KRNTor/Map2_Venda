/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.modelGeneric;

import br.com.venda.map2.model.Funcionario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author felpz
 */
public class GenericFuncionario {

    private StringProperty nome;
    private StringProperty login;

    public GenericFuncionario(Funcionario func) {
        try {
            this.nome = new SimpleStringProperty(func.getNome());
            this.login = new SimpleStringProperty(func.getLogin());

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

    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    @Override
    public String toString() {
        return "GenericFuncionario{" + "nome=" + nome + ", login=" + login + '}';
    }

}
