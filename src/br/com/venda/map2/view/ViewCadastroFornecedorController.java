/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.view;

import br.com.venda.map2.exception.DAOException;
import br.com.venda.map2.facade.Facade;
import br.com.venda.map2.model.Endereco;
import br.com.venda.map2.model.Fornecedor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Computador
 */
public class ViewCadastroFornecedorController implements Initializable {

    @FXML
    private TextField tfnome;
    @FXML
    private TextField tfcnpj;
    @FXML
    private TextField tfrua;
    @FXML
    private TextField tfbairro;
    @FXML
    private TextField tfcep;
    @FXML
    private TextField tfuf;
    @FXML
    private TextField tfcidade;
    @FXML
    private TextField tfcomplemento;
    @FXML
    private TextField tfnumero;
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXToggleButton tbEditar;

    private Facade fa;
    private Stage stage;
    private Fornecedor fornecedor;

    @FXML
    public void cadastrar() {
        try {
            if (btnCadastrar.getText().equalsIgnoreCase("cadastrar")) {
                Fornecedor f = new Fornecedor();
                f.setNome(this.tfnome.getText());
                f.setCnpj(this.tfcnpj.getText());
                Endereco end = new Endereco();
                end.setRua(this.tfrua.getText());
                end.setCep(this.tfcep.getText());
                end.setBairro(this.tfbairro.getText());
                end.setCidade(this.tfcidade.getText());
                end.setComplemento(this.tfcomplemento.getText());
                end.setNumero(Integer.valueOf(this.tfnumero.getText()));
                f.setEndereco(end);
                this.fa.saveFornecedor(f);
                JOptionPane.showMessageDialog(null, "fornecedor cadastrado com sucesso!");
                this.stage.close();
            }
            if (btnCadastrar.getText().equalsIgnoreCase("editar")) {
                fornecedor.setNome(this.tfnome.getText());
                fornecedor.setCnpj(this.tfcnpj.getText());
                Endereco end = new Endereco();
                end.setRua(this.tfrua.getText());
                end.setCep(this.tfcep.getText());
                end.setBairro(this.tfbairro.getText());
                end.setCidade(this.tfcidade.getText());
                end.setComplemento(this.tfcomplemento.getText());
                end.setNumero(Integer.valueOf(this.tfnumero.getText()));
                fornecedor.setEndereco(end);
                this.fa.saveFornecedor(fornecedor);
                JOptionPane.showMessageDialog(null, "funcionario editado com sucesso!");
                this.stage.close();
            }
        } catch (DAOException ex) {
            Logger.getLogger(ViewCadastroFornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.fa = new Facade();
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
        fillAndLock(fornecedor);
    }

    private void fillAndLock(Fornecedor fornecedor) {
        if (fornecedor != null) {
            this.tfnome.setText(fornecedor.getNome());
            this.tfcnpj.setText(fornecedor.getCnpj());
            this.tfrua.setText(fornecedor.getEndereco().getRua());
            this.tfbairro.setText(fornecedor.getEndereco().getBairro());
            this.tfcep.setText(fornecedor.getEndereco().getCep());
            this.tfuf.setText(fornecedor.getEndereco().getUF());
            this.tfcidade.setText(fornecedor.getEndereco().getCidade());
            this.tfcomplemento.setText(fornecedor.getEndereco().getComplemento());
            this.tfnumero.setText(String.valueOf(fornecedor.getEndereco().getNumero()));
            this.btnCadastrar.setText("editar");
            //--------------lock--------------
            this.tfnome.setEditable(false);
            this.tfcnpj.setEditable(false);
            this.tfbairro.setEditable(false);
            this.tfcep.setEditable(false);
            this.tfcidade.setEditable(false);
            this.tfnumero.setEditable(false);
            this.tfcomplemento.setEditable(false);
            this.tfuf.setEditable(false);
            this.tfrua.setEditable(false);
            this.tbEditar.setVisible(true);
        }
    }

    @FXML
    private void setEditable() {
        if (this.tbEditar.isSelected()) {
            this.tfnome.setEditable(true);
            this.tfcnpj.setEditable(true);
            this.tfbairro.setEditable(true);
            this.tfcep.setEditable(true);
            this.tfcidade.setEditable(true);
            this.tfnumero.setEditable(true);
            this.tfcomplemento.setEditable(true);
            this.tfuf.setEditable(true);
            this.tfrua.setEditable(true);
        } else {
            this.tfnome.setEditable(false);
            this.tfcnpj.setEditable(false);
            this.tfbairro.setEditable(false);
            this.tfcep.setEditable(false);
            this.tfcidade.setEditable(false);
            this.tfnumero.setEditable(false);
            this.tfcomplemento.setEditable(false);
            this.tfuf.setEditable(false);
            this.tfrua.setEditable(false);
        }
    }

}
