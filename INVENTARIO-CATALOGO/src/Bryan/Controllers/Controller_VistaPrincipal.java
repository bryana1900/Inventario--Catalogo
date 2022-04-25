package Bryan.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Button;

public class Controller_VistaPrincipal {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button Inventario;

    @FXML
    void ProductosSinRefrigerar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bryan/FXML_MAIN/ProductosSinRefrigerar.fxml"));
        root = loader.load();
        Controller_ProductosSinRefrigerar controller_productosSinRefrigerar = loader.getController();
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> controller_productosSinRefrigerar.closeWindows());
    }

    @FXML
    void ProductosCongelados(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bryan/FXML_MAIN/ProductosCongelados.fxml"));
        root = loader.load();
        Controller_ProductosCongelados controller_productosCongelados = loader.getController();
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> controller_productosCongelados.closeWindows());
    }

    @FXML
    void ProductosRefrigerados(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bryan/FXML_MAIN/ProductosRefrigerados.fxml"));
        root = loader.load();
        Controller_ProductosRefrigerados controller_productosRefrigerados = loader.getController();
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> controller_productosRefrigerados.closeWindows());
    }

    @FXML
    void Inventario(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bryan/FXML_MAIN/Inventario.fxml"));
        root = loader.load();
        Controller_Inventario controller_inventario = loader.getController();
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e -> controller_inventario.closeWindows());
    }

}
