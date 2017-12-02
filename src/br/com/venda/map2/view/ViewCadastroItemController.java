/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.view;

import br.com.venda.map2.exception.DAOException;
import br.com.venda.map2.facade.Facade;
import br.com.venda.map2.model.Fornecedor;
import br.com.venda.map2.model.Item;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 *
 * @author Computador
 */
public class ViewCadastroItemController implements Initializable {

    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfPreco;
    @FXML
    private JFXTextField tfPrecovenda;
    @FXML
    private JFXDatePicker dtValidade;
    @FXML
    private JFXComboBox cbFornecedor;
    @FXML
    private JFXTextField tfQuantidade;
    @FXML
    private JFXButton btnCadastrar;
    @FXML
    private JFXToggleButton tbEditar;

    private Facade fac;
    private String dia;
    private String mes;
    private String ano;
    private Stage stage;
    private Item item;

    @FXML
    public void cadastrarAndEditar() {
        try {
            if (this.dtValidade.getValue() != null) {
                this.dia = String.valueOf(this.dtValidade.getValue().getDayOfMonth());
                this.mes = String.valueOf(this.dtValidade.getValue().getMonthValue());
                this.ano = String.valueOf(this.dtValidade.getValue().getYear());
            } else {
                this.dia = "30";
                this.mes = "11";
                this.ano = "0002";
            }
            if (btnCadastrar.getText().equalsIgnoreCase("cadastrar")) {
                Item i = new Item();
                i.setNome(this.tfNome.getText());
                i.setPrecoCompraItem(Double.valueOf(this.tfPreco.getText()));
                i.setPrecoVendaItem(Double.valueOf(this.tfPrecovenda.getText()));
                i.setValidade(new SimpleDateFormat("dd/MM/yyyy").parse(this.dia + '/' + this.mes + '/' + this.ano));
                Fornecedor f = new Facade().getFornecedorByName(String.valueOf(this.cbFornecedor.getSelectionModel().getSelectedItem()));
                i.setFornecedor(f);
                i.setQuantidade(Integer.valueOf(this.tfQuantidade.getText()));
                this.fac.saveItem(i);
                this.stage.close();
            }
            if (btnCadastrar.getText().equalsIgnoreCase("editar")) {
                this.item.setNome(this.tfNome.getText());
                this.item.setPrecoCompraItem(Double.valueOf(this.tfPreco.getText()));
                this.item.setPrecoVendaItem(Double.valueOf(this.tfPrecovenda.getText()));
                this.item.setValidade(new SimpleDateFormat("dd/MM/yyyy").parse(this.dia + '/' + this.mes + '/' + this.ano));
                Fornecedor f = new Facade().getFornecedorByName(String.valueOf(this.cbFornecedor.getSelectionModel().getSelectedItem()));
                this.item.setFornecedor(f);
                this.item.setQuantidade(Integer.valueOf(this.tfQuantidade.getText()));
                this.fac.updateItem(this.item);
                this.stage.close();
            }
        } catch (ParseException ex) {
            Logger.getLogger(ViewCadastroItemController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(ViewCadastroItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setItem(Item item) {
        this.item = item;
        fillAndLock(item);
    }

    private void fillAndLock(Item item) {
        if (item != null) {
            //--------------fill--------------
            this.tfNome.setText(item.getNome());
            this.tfPreco.setText(String.valueOf(item.getPrecoCompraItem()));
            this.tfPrecovenda.setText(String.valueOf(item.getPrecoVendaItem()));
            this.tfQuantidade.setText(String.valueOf(item.getQuantidade()));
            this.cbFornecedor.setValue(item.getFornecedor().getNome());
            this.btnCadastrar.setText("editar");
            //--------------lock--------------
            this.tfNome.setEditable(false);
            this.tfPreco.setEditable(false);
            this.tfPrecovenda.setEditable(false);
            this.tfQuantidade.setEditable(false);
            this.tbEditar.setVisible(true);
        }
    }

    @FXML
    private void setEditable() {
        if (this.tbEditar.isSelected()) {
            this.tfNome.setEditable(true);
            this.tfPreco.setEditable(true);
            this.tfPrecovenda.setEditable(true);
            this.tfQuantidade.setEditable(true);
        } else {
            this.tfNome.setEditable(false);
            this.tfPreco.setEditable(false);
            this.tfPrecovenda.setEditable(false);
            this.tfQuantidade.setEditable(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.fac = new Facade();
            List<Fornecedor> forn = this.fac.getAllFornecedor();
            forn.forEach((f) -> {
                this.cbFornecedor.getItems().add(f.getNome());
            });
            this.btnCadastrar.setText("cadastrar");
            this.tbEditar.setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(ViewCadastroItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void excluirItem(Item item) throws DAOException{
        fac.removeItem(item);
    }

}
