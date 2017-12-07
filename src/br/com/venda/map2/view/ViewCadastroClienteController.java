/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.view;

import br.com.venda.map2.exception.DAOException;
import br.com.venda.map2.facade.Facade;
import br.com.venda.map2.model.Cliente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Computador
 */
public class ViewCadastroClienteController implements Initializable {

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
    private Cliente cliente;

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
            if (btnCadastrar.getText().equalsIgnoreCase("cadastrar")) {
                this.cliente = new Cliente();
                this.cliente.setNome(this.tfnome.getText());
                this.cliente.setCpf(this.tfcpf.getText());
                this.cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(dia + '/' + mes + '/' + ano));
                this.cliente.getEndereco().setRua(this.tfrua.getText());
                this.cliente.getEndereco().setCep(this.tfcep.getText());
                this.cliente.getEndereco().setBairro(this.tfbairro.getText());
                this.cliente.getEndereco().setCidade(this.tfcidade.getText());
                this.cliente.getEndereco().setComplemento(this.tfcomplemento.getText());
                this.cliente.getEndereco().setNumero(Integer.valueOf(this.tfnumero.getText()));
                this.fac.saveCliente(this.cliente);
                JOptionPane.showMessageDialog(null, "cliente cadastrado com sucesso!", "cadastro cliente", JOptionPane.INFORMATION_MESSAGE);
                this.stage.close();
            }
            if (this.btnCadastrar.getText().equalsIgnoreCase("editar")) {
                this.cliente.setNome(this.tfnome.getText());
                this.cliente.setCpf(this.tfcpf.getText());
                this.cliente.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(dia + '/' + mes + '/' + ano));
                this.cliente.getEndereco().setRua(this.tfrua.getText());
                this.cliente.getEndereco().setCep(this.tfcep.getText());
                this.cliente.getEndereco().setBairro(this.tfbairro.getText());
                this.cliente.getEndereco().setCidade(this.tfcidade.getText());
                this.cliente.getEndereco().setComplemento(this.tfcomplemento.getText());
                this.cliente.getEndereco().setNumero(Integer.valueOf(this.tfnumero.getText()));
                this.fac.updateCliente(this.cliente);
                JOptionPane.showMessageDialog(null, "cliente editado com sucesso!", "cadastro cliente", JOptionPane.INFORMATION_MESSAGE);
                this.stage.close();

            }
        } catch (DAOException ex) {
            Logger.getLogger(ViewCadastroClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ViewCadastroClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        fillAndLock(cliente);
    }

    private void fillAndLock(Cliente cliente) {
        if (cliente != null) {
            //--------------fill--------------
            this.tfnome.setText(cliente.getNome());
            this.tfcpf.setText(cliente.getCpf());
            this.dtNascimento.setValue(LOCAL_DATE(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento())));
            this.tfrua.setText(cliente.getEndereco().getRua());
            this.tfbairro.setText(cliente.getEndereco().getBairro());
            this.tfcep.setText(cliente.getEndereco().getCep());
            this.tfuf.setText(cliente.getEndereco().getUF());
            this.tfcidade.setText(cliente.getEndereco().getCidade());
            this.tfcomplemento.setText(cliente.getEndereco().getComplemento());
            this.tfnumero.setText(String.valueOf(cliente.getEndereco().getNumero()));
            this.btnCadastrar.setText("editar");
            //--------------lock--------------
            this.tfnome.setEditable(true);
            this.tfcpf.setEditable(true);
            this.tfbairro.setEditable(true);
            this.tfcep.setEditable(true);
            this.tfcidade.setEditable(true);
            this.tfnumero.setEditable(true);
            this.tfcomplemento.setEditable(true);
            this.tfuf.setEditable(true);
            this.tfrua.setEditable(true);
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
