/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.venda.map2.view;

import br.Main;
import br.com.venda.map2.exception.DAOException;
import br.com.venda.map2.facade.Facade;
import br.com.venda.map2.model.*;
import br.com.venda.map2.modelGeneric.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author felpz
 */
public class ViewContaController implements Initializable {

    @FXML
    private Pane paneCliente;
    @FXML
    private Pane paneFuncionario;
    @FXML
    private Pane paneFornecedor;
    @FXML
    private Pane paneItem;
    @FXML
    private Pane paneVenda;
    @FXML
    private Label lbName;
    @FXML
    private JFXButton btnCadCliente;
    @FXML
    private JFXButton btnCadFuncionario;
    @FXML
    private JFXButton btnCadFornecedor;
    @FXML
    private JFXButton btnCadItem;
    @FXML
    private JFXButton btnCadVenda;
    @FXML
    private TableView<GenericCliente> tableCliente;
    @FXML
    private TableColumn<GenericCliente, String> colunaClienteNome;
    @FXML
    private TableColumn<GenericCliente, String> colunaClienteQunt;
    @FXML
    private TableView<GenericFuncionario> tableFuncionario;
    @FXML
    private TableColumn<GenericFuncionario, String> colunaFuncionarioNome;
    @FXML
    private TableColumn<GenericFuncionario, String> colunaFuncionarioLogin;
    @FXML
    private TableView<GenericFornecedor> tableFornecedor;
    @FXML
    private TableColumn<GenericFornecedor, String> colunaFornecedorNome;
    @FXML
    private TableColumn<GenericFornecedor, String> colunaFornecedorCnpj;
    @FXML
    private TableView<GenericItem> tableItem;
    @FXML
    private TableColumn<GenericItem, String> colunaItemNome;
    @FXML
    private TableColumn<GenericItem, String> colunaItemEstq;
    @FXML
    private TableView<GenericVenda> tableVenda;
    @FXML
    private TableColumn<GenericVenda, String> colunaVendaData;
    @FXML
    private TableColumn<GenericVenda, String> colunaVendaNomeCliente;
    @FXML
    private TableColumn<GenericVenda, String> colunaVendaValor;
    @FXML
    private JFXTextField tfBuscaCliente;
    @FXML
    private JFXTextField tfBuscaFuncionario;
    @FXML
    private JFXTextField tfBuscaFornecedor;
    @FXML
    private JFXTextField tfBuscaItem;

    private Stage stage;
    private Funcionario func;
    private Cliente cliente;
    private Fornecedor forn;
    private Venda venda;
    private Item item;
    private Facade fac;

    private ObservableList<GenericCliente> olCl;
    private final GenericClienteController controllerCl = new GenericClienteController();
    private ObservableList<GenericFuncionario> olFunc;
    private final GenericFuncionarioController controllerFunc = new GenericFuncionarioController();
    private ObservableList<GenericFornecedor> olForn;
    private final GenericFornecedorController controllerForn = new GenericFornecedorController();
    private ObservableList<GenericItem> olItem;
    private final GenericItemController controllerItem = new GenericItemController();
    private ObservableList<GenericVenda> olVenda;
    private final GenericVendaController controllerVenda = new GenericVendaController();

    @FXML
    public void showPaneCliente() {
        this.paneCliente.setVisible(true);
        this.paneFuncionario.setVisible(false);
        this.paneFornecedor.setVisible(false);
        this.paneItem.setVisible(false);
        this.paneVenda.setVisible(false);
        preencherTableCliente();
    }

    @FXML
    public void showPaneFuncionario() {
        this.paneCliente.setVisible(false);
        this.paneFuncionario.setVisible(true);
        this.paneFornecedor.setVisible(false);
        this.paneItem.setVisible(false);
        this.paneVenda.setVisible(false);
        preencherTableFuncionario();
    }

    @FXML
    public void showPaneFornecedor() {
        this.paneCliente.setVisible(false);
        this.paneFuncionario.setVisible(false);
        this.paneFornecedor.setVisible(true);
        this.paneItem.setVisible(false);
        this.paneVenda.setVisible(false);
        preencherTableFornecedor();
    }

    @FXML
    public void showPaneItem() {
        this.paneCliente.setVisible(false);
        this.paneFuncionario.setVisible(false);
        this.paneFornecedor.setVisible(false);
        this.paneItem.setVisible(true);
        this.paneVenda.setVisible(false);
        preencherTableItem();
    }

    @FXML
    public void showPaneVenda() {
        this.paneCliente.setVisible(false);
        this.paneFuncionario.setVisible(false);
        this.paneFornecedor.setVisible(false);
        this.paneItem.setVisible(false);
        this.paneVenda.setVisible(true);
        preencherTableVenda();
    }

    @FXML
    public void showCadCliente() {
        try {
            Main.showStageCadastrarCliente(cliente);
        } catch (IOException ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void showCadFuncionario() {
        try {
            Main.showStageCadastrarFuncinario(func);
        } catch (IOException ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void showCadFornecedor() {
        try {
            Main.showStageCadastrarFornecedor(forn);
        } catch (IOException ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void showCadItem() {
        try {
            Main.showStageCadastrarItem(item);
        } catch (IOException ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void showCadVenda() {
        try {
            Main.showStageCadastrarVenda(func, venda);
        } catch (IOException ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void showPaneLogin() {
        try {
            Main.showViewLogin();
        } catch (IOException ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preencherTableCliente() {
        try {
            this.colunaClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            this.colunaClienteQunt.setCellValueFactory(new PropertyValueFactory<>("qunt"));
            this.olCl = FXCollections.observableArrayList(this.controllerCl.listarAll(this.fac.getAllCliente()));
            this.tableCliente.setItems(null);
            this.tableCliente.setItems(this.olCl);
            this.tableCliente.setRowFactory(tv -> {
                TableRow<GenericCliente> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        try {
                            GenericCliente rowData = row.getItem();
                            Cliente cliente = this.fac.getClienteByName(rowData.getNome());
                            Main.showStageCadastrarCliente(cliente);
                        } catch (Exception ex) {
                            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return row;
            });
        } catch (Exception ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void repreencherTableCliente(List<Cliente> lista) {
        try {
            this.colunaClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            this.colunaClienteQunt.setCellValueFactory(new PropertyValueFactory<>("qunt"));
            this.olCl = FXCollections.observableArrayList(this.controllerCl.listarAll(lista));
            this.tableCliente.setItems(null);
            this.tableCliente.setItems(this.olCl);
            this.tableCliente.setRowFactory(tv -> {
                TableRow<GenericCliente> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        try {
                            GenericCliente rowData = row.getItem();
                            Cliente cliente = this.fac.getClienteByName(rowData.getNome());
                            Main.showStageCadastrarCliente(cliente);
                        } catch (Exception ex) {
                            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return row;
            });
        } catch (Exception ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preencherTableFuncionario() {
        try {
            this.colunaFuncionarioNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            this.colunaFuncionarioLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
            this.olFunc = FXCollections.observableArrayList(this.controllerFunc.listarAll(this.fac.getAllFuncionario()));
            this.tableFuncionario.setItems(null);
            this.tableFuncionario.setItems(this.olFunc);
            this.tableFuncionario.setRowFactory(tv -> {
                TableRow<GenericFuncionario> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        try {
                            GenericFuncionario rowData = row.getItem();
                            Funcionario funcionario = this.fac.getFuncionarioByName(rowData.getNome());
                            Main.showStageCadastrarFuncinario(funcionario);
                        } catch (Exception ex) {
                            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return row;
            });
        } catch (Exception ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preencherTableFornecedor() {
        try {
            this.colunaFornecedorNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            this.colunaFornecedorCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
            this.olForn = FXCollections.observableArrayList(this.controllerForn.listarAll(this.fac.getAllFornecedor()));
            this.tableFornecedor.setItems(null);
            this.tableFornecedor.setItems(this.olForn);
            this.tableFornecedor.setRowFactory(tv -> {
                TableRow<GenericFornecedor> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        try {
                            GenericFornecedor rowData = row.getItem();
                            Fornecedor fornecedor = this.fac.getFornecedorByName(rowData.getNome());
                            Main.showStageCadastrarFornecedor(fornecedor);
                        } catch (Exception ex) {
                            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return row;
            });
        } catch (Exception ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preencherTableItem() {
        try {
            this.colunaItemNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            this.colunaItemEstq.setCellValueFactory(new PropertyValueFactory<>("qunt_estq"));
            this.olItem = FXCollections.observableArrayList(this.controllerItem.listarAll(this.fac.getAllItem()));
            this.tableItem.setItems(null);
            this.tableItem.setItems(this.olItem);
            this.tableItem.setRowFactory(tv -> {
                TableRow<GenericItem> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        try {
                            GenericItem rowData = row.getItem();
                            Item item = this.fac.getItemByName(rowData.getNome());
                            Main.showStageCadastrarItem(item);
                        } catch (Exception ex) {
                            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return row;
            });
        } catch (Exception ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void preencherTableVenda() {
        try {
            List<Venda> vendas = new ArrayList<>();
            for (Venda v : this.fac.getAllVenda()) {
                if (vendas.isEmpty()) {
                    vendas.add(v);
                }
                if (!vendas.contains(v)) {
                    vendas.add(v);
                }
            }
            this.colunaVendaData.setCellValueFactory(new PropertyValueFactory<>("data"));
            this.colunaVendaNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome_cliente"));
            this.colunaVendaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
            this.olVenda = FXCollections.observableArrayList(this.controllerVenda.listarAll(vendas));
            this.tableVenda.setItems(null);
            this.tableVenda.setItems(this.olVenda);
            this.tableVenda.setRowFactory(tv -> {
                TableRow<GenericVenda> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        try {
                            GenericVenda rowData = row.getItem();
                            Venda venda = this.fac.getVendaById(rowData.getId(Long.MIN_VALUE));
                            Main.showStageCadastrarVenda(func, venda);
                        } catch (Exception ex) {
                            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                return row;
            });
        } catch (Exception ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void deleteClienteRow() {
        try {
            if (this.tableCliente.getSelectionModel().getSelectedItem() != null) {
                Cliente cliente = this.fac.getClienteByName(this.tableCliente.getSelectionModel().getSelectedItem().getNome());
                int value = JOptionPane.showConfirmDialog(null, "deseja mesmo excluir o cliente: " + cliente.getNome() + "?");
                if (value == 0) {
                    cliente.setStatus(false);
                    this.fac.updateCliente(cliente);
                }
            }
            preencherTableCliente();
        } catch (DAOException ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void deleteFuncionarioRow() {
        try {
            if (this.tableFuncionario.getSelectionModel().getSelectedItem() != null) {
                Funcionario func = this.fac.getFuncionarioByName(this.tableFuncionario.getSelectionModel().getSelectedItem().getNome());
                int value = JOptionPane.showConfirmDialog(null, "deseja mesmo excluir o funcionario: " + func.getNome().toUpperCase() + "?");
                if (value == 0) {
                    func.setStatus(false);
                    this.fac.updateFuncionario(func);
                }
            }
            preencherTableFuncionario();
        } catch (DAOException ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void deleteFornecedorRow() {
        try {
            if (this.tableFornecedor.getSelectionModel().getSelectedItem() != null) {
                Fornecedor fornecedor = this.fac.getFornecedorByName(this.tableFornecedor.getSelectionModel().getSelectedItem().getNome());
                int value = JOptionPane.showConfirmDialog(null, "deseja mesmo excluir o Fornecedor: " + fornecedor.getNome().toUpperCase() + "?");
                if (value == 0) {
                    fornecedor.setStatus(false);
                    this.fac.updateFornecedor(fornecedor);
                }
            }
            preencherTableFornecedor();
        } catch (DAOException ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void deleteItemRow() {
        try {
            if (this.tableItem.getSelectionModel().getSelectedItem() != null) {
                Item item = this.fac.getItemByName(this.tableItem.getSelectionModel().getSelectedItem().getNome());
                int value = JOptionPane.showConfirmDialog(null, "deseja mesmo excluir o Item: " + item.getNome() + "?");
                if (value == 0) {
                    item.setStatus(false);
                    this.fac.updateItem(item);
                }
            }
            preencherTableItem();
        } catch (DAOException ex) {
            Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void searchByName() {
        if (!this.tfBuscaCliente.getText().equals("")) {
            try {
                repreencherTableCliente(this.fac.getClienteListByName(this.tfBuscaCliente.getText()));
            } catch (DAOException ex) {
                Logger.getLogger(ViewContaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setFuncionario(Funcionario func) {
        this.func = func;
        this.lbName.setText("nome: " + func.getNome());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.fac = new Facade();
    }

}
