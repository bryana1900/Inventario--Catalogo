package Bryan.Controllers;
import Bryan.Imagen.Imagen;
import Bryan.Logic.Gestor;
import Bryan.ProductosRefrigerados.ProductosRefrigerados;
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
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller_ProductosRefrigerados {
    public TextField nombreR;
    public TextField paisR;
    public TextField precioR;
    public TextField tipoR;
    int codigo;
    @FXML
    private Button cerrar;
    @FXML
    private ImageView ImagenCarga;

    @FXML
    private TableView<ProductosRefrigerados> TablaProductosR;
    @FXML
    private TableColumn<ProductosRefrigerados, String> ColumCodigoR;
    @FXML
    private TableColumn<ProductosRefrigerados, String> ColumNombreR;
    @FXML
    private TableColumn<ProductosRefrigerados, String> ColumPaisR;
    @FXML
    private TableColumn<ProductosRefrigerados, String> ColumPrecioR;
    @FXML
    private TableColumn<ProductosRefrigerados, String> ColumTipoR;
    public ObservableList<ProductosRefrigerados> productosRefrigeradoss;
    /**
     * Creo showalert
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    /**
     * Creo evento para registrar
     */
    @FXML
    void registrar(ActionEvent event) throws SQLException, ClassNotFoundException {
        /**
         * Obtengos datos digitados y compruebo de que no esten vacios
         */
        if(nombreR.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese nombre del producto");
            return;
        }
        if(paisR.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese pais de origen del producto");
            return;
        }
        if(precioR.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese precio del producto");
            return;
        }
        if(tipoR.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor el tipo de producto");
            return;
        }
        codigo = (int) (Math.random()*100);
        /**
         * Envio datos del gestor
         */
        Gestor gestor = new Gestor();
        ProductosRefrigerados RF = new ProductosRefrigerados(nombreR.getText(),paisR.getText(),precioR.getText(),codigo,tipoR.getText());
        productosRefrigeradoss.add(RF);
        TablaProductosR.setItems(productosRefrigeradoss);
        gestor.insertarRefrigerados(RF);
        showAlert(Alert.AlertType.CONFIRMATION, "Producto ingresado correctamente!", "Producto: " + nombreR.getText() + " ingresado");
        nombreR.clear();
        paisR.clear();
        tipoR.clear();
        precioR.clear();
    }
    /**
     * Creo evento para mostrar datos en el table view
     */
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        Gestor gestor = new Gestor();
        productosRefrigeradoss = FXCollections.observableArrayList(gestor.listarRefrigerados());
        ColumNombreR.setCellValueFactory(new PropertyValueFactory<ProductosRefrigerados, String>("nombre"));
        ColumPaisR.setCellValueFactory(new PropertyValueFactory<ProductosRefrigerados, String>("pais"));
        ColumTipoR.setCellValueFactory(new PropertyValueFactory<ProductosRefrigerados, String>("tiporefrigerador"));
        ColumPrecioR.setCellValueFactory(new PropertyValueFactory<ProductosRefrigerados, String>("precio"));
        ColumCodigoR.setCellValueFactory(new PropertyValueFactory<ProductosRefrigerados, String>("codigo"));
        TablaProductosR.setItems(productosRefrigeradoss);
    }
    /**
     * Creo evento para eliminar
     */
    @FXML
    private void eliminar(ActionEvent event) throws SQLException, ClassNotFoundException {
        Gestor gestor = new Gestor();
        /**
         * Obtengo producto seleccionadp
         */
        ProductosRefrigerados p = this.TablaProductosR.getSelectionModel().getSelectedItem();
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
            /**
             * Elimino
             */
            this.productosRefrigeradoss.remove(p);
            /**
             * Refresco la tabla de lista
             */
            this.TablaProductosR.refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Producto eliminado");
            alert.showAndWait();
            gestor.eliminarRefrigerados(p);
        }
    }
    /**
     * Creo evento para modificar
     */
    public void modificar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Gestor gestor =new Gestor();
        /**
         * Obtengo datos
         */
        ProductosRefrigerados p = this.TablaProductosR.getSelectionModel().getSelectedItem();
        /**
         * Si es nulo, lanzo error
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
                 * Obtengo datos
                 */
                this.nombreR.getText();
                this.paisR.getText();
                this.tipoR.getText();
                this.precioR.getText();
                /**
                 * Creo una clase
                 */
                ProductosRefrigerados aux = new ProductosRefrigerados(nombreR.getText(),paisR.getText(),precioR.getText(),codigo,tipoR.getText());
                /**
                 * Compruebo de que el producto este
                 */
                if (!this.productosRefrigeradoss.contains(aux)) {
                    /**
                     * Modifico
                     */
                    p.setNombre(aux.getNombre());
                    p.setPais(aux.getPais());
                    p.setTiporefrigerador(aux.getTiporefrigerador());
                    p.setPrecio(aux.getPrecio());
                    gestor.modificarRefrgerados(aux);
                    /**
                     * Refresco la tabla
                     */
                    this.TablaProductosR.refresh();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Producto modificado");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("!ERROR¡");
                    alert.setContentText("El producto existe");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("!ERROR¡");
                alert.setContentText("Formato incorrecto");
                alert.showAndWait();
            }
        }
    }
    /**
     * Creo evento para cargar la imagen
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
     * Cierro la pestaña
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
