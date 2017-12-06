/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.view;

import br.com.venda.map2.exception.DAOException;
import br.com.venda.map2.facade.Facade;
import br.com.venda.map2.model.Cliente;
import br.com.venda.map2.model.Funcionario;
import br.com.venda.map2.model.Item;
import br.com.venda.map2.model.ItemVenda;
import br.com.venda.map2.model.Pessoa;
import br.com.venda.map2.model.Venda;
import br.com.venda.map2.modelGeneric.GenericItem;
import br.com.venda.map2.modelGeneric.GenericItemController;
import br.com.venda.map2.modelGeneric.GenericItemVenda;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author felpz
 */
public class ViewVendaController implements Initializable {

    @FXML
    private JFXTextField tfValor;
    @FXML
    private JFXDatePicker dtVenda;
    @FXML
    private JFXComboBox cbCliente;
    @FXML
    private TableView<GenericItem> tableItem;
    @FXML
    private TableColumn<GenericItem, String> colunaItemNome;
    @FXML
    private TableColumn<GenericItem, String> colunaItemEstq;
    @FXML
    private TableColumn<GenericItem, String> colunaItemValor;
    @FXML
    private TableView<GenericItemVenda> tableItem2;
    @FXML
    private TableColumn<GenericItemVenda, String> colunaItemNome2;
    @FXML
    private TableColumn<GenericItemVenda, String> colunaItemQunt2;
    @FXML
    private TableColumn<GenericItemVenda, String> colunaItemValor2;

    private Facade fac;
    private Funcionario func;
    private Stage stage;
    private String dia;
    private String mes;
    private String ano;
    private double value = 0;
    private ObservableList<GenericItem> olItem;
    private ObservableList<GenericItemVenda> olItem2;
    private final GenericItemController controllerItem = new GenericItemController();
    private List<ItemVenda> listaItem = new ArrayList<>();
    private List<GenericItemVenda> listaGenItem = new ArrayList<>();

    @FXML
    public void cadastrar() {
        try {
            if (this.dtVenda.getValue() != null) {
                this.dia = String.valueOf(this.dtVenda.getValue().getDayOfMonth());
                this.mes = String.valueOf(this.dtVenda.getValue().getMonthValue());
                this.ano = String.valueOf(this.dtVenda.getValue().getYear());
            } else {
                this.dia = "30";
                this.mes = "11";
                this.ano = "0002";
            }
            Venda venda = new Venda();
            Cliente c = this.fac.getClienteByName((String) this.cbCliente.getSelectionModel().getSelectedItem());
            venda.setCliente(c);
            venda.setDtVenda(new SimpleDateFormat("dd/MM/yyyy").parse(dia + '/' + mes + '/' + ano));
            venda.setFuncionario(this.func);
            venda.setItem(this.listaItem);
            venda.setValor(Double.valueOf(this.tfValor.getText()));
            System.out.println("venda " + venda);
            this.fac.saveVenda(venda);
            JOptionPane.showMessageDialog(null, "venda realizada com sucesso!");
            this.stage.close();
        } catch (DAOException ex) {
            Logger.getLogger(ViewVendaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ViewVendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preencherTableItem() {
        try {
            this.colunaItemNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            this.colunaItemEstq.setCellValueFactory(new PropertyValueFactory<>("qunt_estq"));
            this.colunaItemValor.setCellValueFactory(new PropertyValueFactory<>("valor_und"));
            this.olItem = FXCollections.observableArrayList(this.controllerItem.listarAll(this.fac.getAllItem()));
            this.tableItem.setItems(null);
            this.tableItem.setItems(this.olItem);
            this.tableItem.setRowFactory(tv -> {
                TableRow<GenericItem> row = new TableRow<>();

                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        try {
                            String qunt = JOptionPane.showInputDialog("informe a quantidade:");
                            GenericItem rowData = row.getItem();
                            Item item = this.fac.getItemByName(rowData.getNome());
                            ItemVenda iVenda = initItemVenda(item, Integer.valueOf(qunt));
                            this.listaGenItem.add(new GenericItemVenda(iVenda));
                            preencherTableItemCart(this.listaGenItem);
                            this.value = this.value + (item.getPrecoVendaItem() * Integer.valueOf(qunt));
                            this.tfValor.setText("" + this.value);
                        } catch (DAOException ex) {
                            Logger.getLogger(ViewVendaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return row;
            });
        } catch (Exception ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ItemVenda initItemVenda(Item item, int qunt) {
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setNome(item.getNome());
        itemVenda.setFornecedor(item.getFornecedor());
        itemVenda.setPrecoCompraItem(item.getPrecoCompraItem());
        itemVenda.setPrecoVendaItem(item.getPrecoVendaItem());
        itemVenda.setQuantidade_saida(qunt);
        itemVenda.setValidade(item.getValidade());
        this.listaItem.add(itemVenda);
        return itemVenda;
    }

    private void preencherTableItemCart(List<GenericItemVenda> listaAux) {
        try {
            System.out.println("lista: " + listaAux);
            this.colunaItemNome2.setCellValueFactory(new PropertyValueFactory<>("nome"));
            this.colunaItemQunt2.setCellValueFactory(new PropertyValueFactory<>("qunt_cart"));
            this.colunaItemValor2.setCellValueFactory(new PropertyValueFactory<>("valor_und"));
            this.olItem2 = FXCollections.observableArrayList(listaAux);
            this.tableItem2.setItems(null);
            this.tableItem2.setItems(this.olItem2);
        } catch (Exception ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setFuncionario(Funcionario func) {
        this.func = func;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.fac = new Facade();
            for (Pessoa c : this.fac.getAllCliente()) {
                this.cbCliente.getItems().add(c.getNome());
            }
            preencherTableItem();
        } catch (DAOException ex) {
            Logger.getLogger(ViewVendaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
