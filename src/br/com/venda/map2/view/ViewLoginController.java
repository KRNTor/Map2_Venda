/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.view;

import br.Main;
import br.com.venda.map2.exception.DAOException;
import br.com.venda.map2.facade.Facade;
import br.com.venda.map2.model.Funcionario;
import br.com.venda.map2.util.CriptografiaUtil;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javax.swing.JOptionPane;

/**
 *
 * @author Computador
 */
public class ViewLoginController {

    @FXML
    private JFXTextField tfLogin;
    @FXML
    private JFXPasswordField tfSenha;

    @FXML
    public void logar() {
        try {
            //Colocar Criptografia MD5
            Funcionario func = new Facade().getFuncionarioByLogin(this.tfLogin.getText(), CriptografiaUtil.md5(this.tfSenha.getText()));
            if (func != null) {
                if (func.isStatus()) {
                    Main.showViewConta(func);
                } else {
                    JOptionPane.showMessageDialog(null, "funcionario indisponível", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao logar", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(ViewLoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(ViewLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cadastrar_Se() {
        try {
            Main.showStageCadastrarFuncinario(null);
        } catch (IOException ex) {
            Logger.getLogger(ViewLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
