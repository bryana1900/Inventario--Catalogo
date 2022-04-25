package Bryan.Controllers;

import Bryan.Imagen.Imagen;
import Bryan.Logic.Gestor;
import Bryan.ProductosCongelados.ProductosCongelados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

public class Controller_ProductosCongelados {
    public TextField nombreC;
    public TextField paisC;
    public TextField precioC;
    public TextField tempMax;
    public TextField tempMin;
    public int codigo;
    @FXML
    private Button cerrar;
    @FXML
    private ImageView ImagenCarga;

    @FXML
    private TableView<ProductosCongelados> TablaProductosC;
    @FXML
    private TableColumn<ProductosCongelados, String> ColumNombreC;
    @FXML
    private TableColumn<ProductosCongelados, String> ColumPaisC;
    @FXML
    private TableColumn<ProductosCongelados, Integer> codigoC;
    @FXML
    private TableColumn<ProductosCongelados, String> ColumPrecioC;
    @FXML
    private TableColumn<ProductosCongelados, String> ColumTempMaxC;
    @FXML
    private TableColumn<ProductosCongelados, String> ColumTempMinC;
    public ObservableList<ProductosCongelados> productosCongelados;
    /**
     * Creo Showalert para notificaciones
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    /**
     * Creo evento registrar para enviar datos en el gestor
     */
    @FXML
    void registrar(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        /**
         * Obtengo los datos digitados por el usuario
         */
        if(nombreC.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese el nombre del producto");
            return;
        }
        if(paisC.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese el pais de origen del producto");
            return;
        }
        if(precioC.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese el precio del producto");
            return;
        }
        if(tempMax.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese la temperatura maxima del producto");
            return;
        }
        if(tempMin.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese la temperatura minima del producto");
            return;
        }
        /**
         * Genero codigo usando math.random
         */
        codigo = (int) (Math.random()*100);
        /**
         * Envio los datos al gestor
         */
        Gestor gestor = new Gestor();
        ProductosCongelados RC = new ProductosCongelados(nombreC.getText(),paisC.getText(),precioC.getText(),codigo,tempMax.getText(),tempMin.getText());
        productosCongelados.add(RC);
        TablaProductosC.setItems(productosCongelados);
        gestor.insertarCongelados(RC);
    }
    /**
     * Creo evento de inicializar para mostrar los datos en el table view
     */
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        Gestor gestor = new Gestor();
        productosCongelados = FXCollections.observableArrayList(gestor.listarCongelados());
        ColumNombreC.setCellValueFactory(new PropertyValueFactory<ProductosCongelados, String>("nombre"));
        ColumPaisC.setCellValueFactory(new PropertyValueFactory<ProductosCongelados, String>("pais"));
        ColumPrecioC.setCellValueFactory(new PropertyValueFactory<ProductosCongelados, String>("precio"));
        ColumTempMaxC.setCellValueFactory(new PropertyValueFactory<ProductosCongelados, String>("tempMax"));
        ColumTempMinC.setCellValueFactory(new PropertyValueFactory<ProductosCongelados, String>("tempMin"));
        codigoC.setCellValueFactory(new PropertyValueFactory<ProductosCongelados, Integer>("codigo"));
        TablaProductosC.setItems(productosCongelados);
    }
    /**
     * Creo evento para seleccionar datos del table view
     */
    public void seleccionar(MouseEvent mouseEvent) {
        ProductosCongelados s = this.TablaProductosC.getSelectionModel().getSelectedItem();
        if (s != null) {
            this.nombreC.setText(s.getNombre());
            this.paisC.setText(s.getPais());
            this.tempMin.setText(s.getTempMin());
            this.tempMax.setText(s.getTempMax());
            this.precioC.setText(s.getPrecio());
        }
    }
    /**
     * Creo evento para eliminar datos
     */
    @FXML
    private void eliminar(ActionEvent event) throws SQLException, ClassNotFoundException {
        Gestor gestor = new Gestor();
        /**
         * Obtengo el producto seleccionado
         */
        ProductosCongelados p = this.TablaProductosC.getSelectionModel().getSelectedItem();
        /**
         * Si el producto es nulo, lanzo error
         */
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("¡Error!");
            alert.setContentText("Debes seleccionar un producto");
            alert.showAndWait();
        } else {
            /**
             * Lo elimino de la lista
             */
            this.productosCongelados.remove(p);
            /**
             * Refresco la lista
             */
            this.TablaProductosC.refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Producto eliminado");
            alert.showAndWait();
            gestor.eliminarCongelados(p);
        }
    }
    /**
     * Creo evento para modificar datos
     */
    public void modificar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Gestor gestor =new Gestor();
        /**
         * Obtengo el producto seleccionado
         */
        ProductosCongelados p = this.TablaProductosC.getSelectionModel().getSelectedItem();
        /**
         * Si el producto es nulo, lanzo error
         */
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("¡ERROR!");
            alert.setContentText("Debes seleccionar un producto");
            alert.showAndWait();
        } else {
            try {
                /**
                 * Obtengo los datos
                 */
                this.nombreC.getText();
                this.paisC.getText();
                this.tempMin.getText();
                this.tempMax.getText();
                /**
                 * Creo una clase
                 */
                ProductosCongelados aux = new ProductosCongelados(nombreC.getText(),paisC.getText(),precioC.getText(),codigo,tempMax.getText(),tempMin.getText());
                /**
                 * Compruebo si el producto esta en el lista
                 */
                if (!this.productosCongelados.contains(aux)) {
                    /**
                     * Modifico los objeto
                     */
                    p.setNombre(aux.getNombre());
                    p.setPais(aux.getPais());
                    p.setTempMax(aux.getTempMax());
                    p.setTempMin(aux.getTempMin());
                    p.setPrecio(aux.getPrecio());
                    gestor.modificarCongelados(aux);
                    /**
                     * Refresco la tabla
                     */
                    this.TablaProductosC.refresh();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Producto modificado");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("¡Error!");
                    alert.setContentText("El producto existe");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("¡ERROR!");
                alert.setContentText("FORMATO INCORRECTO");
                alert.showAndWait();
            }
        }
    }
    /**
     * Creo evento para cargar la imagen y guardarla
     */
    public void cargar(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        FileChooser fileChooser = new FileChooser();
        /**
         * Set extension filter
         */
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        /**
         * Show open file dialog
         */
        File file = fileChooser.showOpenDialog(null);
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        ImagenCarga.setImage(image);
        Gestor gestor = new Gestor();
        Imagen imagen = new Imagen();
        imagen.setId(gestor.obtenerCodigoImagen());
        imagen.setImagen(new FileInputStream(file));
        gestor.insertarImagen(imagen);
        System.out.println(imagen.getId());
    }
    /**
     * Creo evento para cerrar la pestaña
     */
    public void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bryan/FXML_MAIN/VistaPrincipal.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.cerrar.getScene().getWindow();
            myStage.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller_VistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
