/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br;

import br.com.venda.map2.model.*;
import br.com.venda.map2.view.ViewCadastroClienteController;
import br.com.venda.map2.view.ViewCadastroFornecedorController;
import br.com.venda.map2.view.ViewCadastroFuncionarioController;
import br.com.venda.map2.view.ViewCadastroItemController;
import br.com.venda.map2.view.ViewContaController;
import br.com.venda.map2.view.ViewVendaController;
import static java.awt.SystemColor.control;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author felpz
 */
public class Main extends Application {

    private static Stage primaryStage;
    private static BorderPane mainPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Vendas");
        Main.primaryStage.setResizable(false);
        showViewMain();
        showViewLogin();
    }

    public void showViewMain() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("com/venda/map2/view/ViewMain.fxml"));
        Main.mainPane = loader.load();
        Scene scene = new Scene(Main.mainPane);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.setResizable(false);
        Main.primaryStage.show();
    }

    public static void showViewLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("com/venda/map2/view/ViewLogin.fxml"));
        BorderPane borderPane = loader.load();
        Main.mainPane.setCenter(borderPane);
    }

    public static void showViewConta(Funcionario func) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("com/venda/map2/view/ViewConta.fxml"));
        BorderPane borderPane = loader.load();
        Main.mainPane.setCenter(borderPane);
        ViewContaController control = (ViewContaController) loader.getController();
        control.setStage(Main.primaryStage);
        control.setFuncionario(func);
    }

    public static void showStageCadastrarFuncinario(Funcionario funcionario) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("com/venda/map2/view/ViewCadastroFuncionario.fxml"));
        BorderPane borderPane = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.primaryStage);
        stage.setTitle("cadastrar funcionario");
        stage.setResizable(false);
        ViewCadastroFuncionarioController control = (ViewCadastroFuncionarioController) loader.getController();
        control.setFuncionario(funcionario);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static void showStageCadastrarCliente(Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("com/venda/map2/view/ViewCadastroCliente.fxml"));
        BorderPane borderPane = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.primaryStage);
        stage.setTitle("cadastrar cliente");
        stage.setResizable(false);
        ViewCadastroClienteController control = (ViewCadastroClienteController) loader.getController();
        control.setStage(stage);
        control.setCliente(cliente);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static void showStageCadastrarFornecedor(Fornecedor fornecedor) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("com/venda/map2/view/ViewCadastroFornecedor.fxml"));
        BorderPane borderPane = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.primaryStage);
        stage.setTitle("cadastrar fornecedor");
        stage.setResizable(false);
        ViewCadastroFornecedorController control = (ViewCadastroFornecedorController) loader.getController();
        control.setStage(stage);
        control.setFornecedor(fornecedor);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static void showStageCadastrarItem(Item item) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("com/venda/map2/view/ViewCadastroItem.fxml"));
        BorderPane borderPane = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.primaryStage);
        stage.setTitle("cadastrar item");
        stage.setResizable(false);
        ViewCadastroItemController control = (ViewCadastroItemController) loader.getController();
        control.setStage(stage);
        control.setItem(item);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static void showStageCadastrarVenda(Funcionario func, Venda venda) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("com/venda/map2/view/ViewVenda.fxml"));
        BorderPane borderPane = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.primaryStage);
        stage.setTitle("registrar venda");
        stage.setResizable(false);
        ViewVendaController control = (ViewVendaController) loader.getController();
        control.setStage(stage);
        control.setFuncionario(func);
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static void main(String[] main) {
        launch(main);
    }

}
