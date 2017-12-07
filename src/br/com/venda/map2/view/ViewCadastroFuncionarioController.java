/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.view;

import br.com.venda.map2.exception.DAOException;
import br.com.venda.map2.facade.Facade;
import br.com.venda.map2.model.Funcionario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import br.com.venda.map2.util.CriptografiaUtil;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;

/**
 *
 * @author Computador
 */
public class ViewCadastroFuncionarioController implements Initializable {

    @FXML
    private JFXTextField tfnome;
    @FXML
    private JFXTextField tfcpf;
    @FXML
    private JFXDatePicker dtNascimento;
    @FXML
    private JFXTextField tfrua;
    @FXML
    private JFXTextField tfbairro;
    @FXML
    private JFXTextField tfcep;
    @FXML
    private JFXTextField tfuf;
    @FXML
    private JFXTextField tfcidade;
    @FXML
    private JFXTextField tfcomplemento;
    @FXML
    private JFXTextField tflogin;
    @FXML
    private JFXPasswordField tfsenha;
    @FXML
    private JFXTextField tfsalario;
    @FXML
    private JFXTextField tffuncao;
    @FXML
    private JFXTextField tfnumero;
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXToggleButton tbEditar;

    private Facade fac;
    private String dia;
    private String mes;
    private String ano;
    private Stage stage;
    private Funcionario funcionario;

    @FXML
    public void cadastrar() {
        try {
            if (this.dtNascimento.getValue() != null) {
                this.dia = String.valueOf(this.dtNascimento.getValue().getDayOfMonth());
                this.mes = String.valueOf(this.dtNascimento.getValue().getMonthValue());
                this.ano = String.valueOf(this.dtNascimento.getValue().getYear());
            } else {
                this.dia = "30";
                this.mes = "11";
                this.ano = "0002";
            }
            if (this.btnCadastrar.getText().equalsIgnoreCase("cadastrar")) {
                this.funcionario = new Funcionario();
                this.funcionario.setNome(this.tfnome.getText());
                this.funcionario.setCpf(this.tfcpf.getText());
                this.funcionario.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(dia + '/' + mes + '/' + ano));
                this.funcionario.setLogin(this.tflogin.getText());
                this.funcionario.setSenha(crip(this.tfsenha.getText()));
                this.funcionario.getEndereco().setRua(this.tfrua.getText());
                this.funcionario.getEndereco().setCep(this.tfcep.getText());
                this.funcionario.getEndereco().setBairro(this.tfbairro.getText());
                this.funcionario.getEndereco().setCidade(this.tfcidade.getText());
                this.funcionario.getEndereco().setComplemento(this.tfcomplemento.getText());
                this.funcionario.getEndereco().setNumero(Integer.valueOf(this.tfnumero.getText()));
                this.funcionario.setSalario(Double.valueOf(this.tfsalario.getText()));
                this.funcionario.setFuncao(this.tffuncao.getText());
                this.fac.saveFuncionario(this.funcionario);
                JOptionPane.showMessageDialog(null, "funcionario cadastrado com sucesso!");
                this.stage.close();
            }
            if (this.btnCadastrar.getText().equalsIgnoreCase("editar")) {
                this.funcionario.setNome(this.tfnome.getText());
                this.funcionario.setCpf(this.tfcpf.getText());
                this.funcionario.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(dia + '/' + mes + '/' + ano));
                this.funcionario.setLogin(this.tflogin.getText());
                if (this.tfsenha.getText().isEmpty()) {
                    this.funcionario.setSenha(this.funcionario.getSenha());
                } else {
                    this.funcionario.setSenha(crip(this.tfsenha.getText()));
                }
                this.funcionario.getEndereco().setRua(this.tfrua.getText());
                this.funcionario.getEndereco().setCep(this.tfcep.getText());
                this.funcionario.getEndereco().setBairro(this.tfbairro.getText());
                this.funcionario.getEndereco().setCidade(this.tfcidade.getText());
                this.funcionario.getEndereco().setComplemento(this.tfcomplemento.getText());
                this.funcionario.getEndereco().setNumero(Integer.valueOf(this.tfnumero.getText()));
                this.funcionario.setSalario(Double.valueOf(this.tfsalario.getText()));
                this.funcionario.setFuncao(this.tffuncao.getText());
                this.fac.updateFuncionario(this.funcionario);
                JOptionPane.showMessageDialog(null, "funcionario editado com sucesso!");
                this.stage.close();
            }
        } catch (DAOException ex) {
            Logger.getLogger(ViewCadastroFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ViewCadastroFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String crip(String senha) {
        return CriptografiaUtil.md5(senha);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        if (funcionario != null) {
            fillAndLock(funcionario);
        }
    }

    private void fillAndLock(Funcionario funcionario) {
        if (funcionario != null) {
            this.tfnome.setText(funcionario.getNome());
            this.tfcpf.setText(funcionario.getCpf());
            this.dtNascimento.setValue(LOCAL_DATE(new SimpleDateFormat("dd/MM/yyyy").format(funcionario.getDataNascimento())));
            this.tfrua.setText(funcionario.getEndereco().getRua());
            this.tfbairro.setText(funcionario.getEndereco().getBairro());
            this.tfcep.setText(funcionario.getEndereco().getCep());
            this.tfuf.setText(funcionario.getEndereco().getUF());
            this.tfcidade.setText(funcionario.getEndereco().getCidade());
            this.tfcomplemento.setText(funcionario.getEndereco().getComplemento());
            this.tfnumero.setText(String.valueOf(funcionario.getEndereco().getNumero()));
            this.tflogin.setText(funcionario.getLogin());
            this.tffuncao.setText(funcionario.getFuncao());
            this.tfsalario.setText(String.valueOf(funcionario.getSalario()));

            this.btnCadastrar.setText("editar");
            //--------------lock--------------
            this.tfnome.setEditable(false);
            this.tfcpf.setEditable(false);
            this.tfbairro.setEditable(false);
            this.tfcep.setEditable(false);
            this.tfcidade.setEditable(false);
            this.tfnumero.setEditable(false);
            this.tfcomplemento.setEditable(false);
            this.tfuf.setEditable(false);
            this.tfrua.setEditable(false);
            this.tflogin.setEditable(false);
            this.tfsenha.setEditable(false);
            this.tffuncao.setEditable(false);
            this.tfsalario.setEditable(false);
            this.tbEditar.setVisible(true);
        }
    }

    //Ficou faltando mexer com as DATAS!!!!!    
    @FXML
    private void setEditable() {
        if (this.tbEditar.isSelected()) {
            this.tfnome.setEditable(true);
            this.tfcpf.setEditable(true);
            this.tfbairro.setEditable(true);
            this.tfcep.setEditable(true);
            this.tfcidade.setEditable(true);
            this.tfnumero.setEditable(true);
            this.tfcomplemento.setEditable(true);
            this.tfuf.setEditable(true);
            this.tfrua.setEditable(true);
            this.tflogin.setEditable(true);
            this.tfsenha.setEditable(true);
            this.tffuncao.setEditable(true);
            this.tfsalario.setEditable(true);
        } else {
            this.tfnome.setEditable(false);
            this.tfcpf.setEditable(false);
            this.tfbairro.setEditable(false);
            this.tfcep.setEditable(false);
            this.tfcidade.setEditable(false);
            this.tfnumero.setEditable(false);
            this.tfcomplemento.setEditable(false);
            this.tfuf.setEditable(false);
            this.tfrua.setEditable(false);
            this.tflogin.setEditable(false);
            this.tfsenha.setEditable(false);
            this.tffuncao.setEditable(false);
            this.tfsalario.setEditable(false);
        }
    }

    private static LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.fac = new Facade();
        this.tbEditar.setVisible(false);
    }
}
