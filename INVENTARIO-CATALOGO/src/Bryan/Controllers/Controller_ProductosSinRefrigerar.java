package Bryan.Controllers;

import Bryan.Imagen.Imagen;
import Bryan.Logic.Gestor;
import Bryan.ProductosSinRefrigerar.ProductosSinRefrigerar;
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
import javafx.scene.input.MouseEvent;
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


public class Controller_ProductosSinRefrigerar {
    public TextField nombre;
    public TextField pais;
    public TextField recomendaciones;
    public TextField precio;
    int codigo;
    String tipodeclasificacion;
    @FXML
    private RadioButton Carne,Frutas,Verduras,Lacteos;
    @FXML
    private Button cerrar;
    @FXML
    private ImageView ImagenCarga;

    @FXML
    TableView<ProductosSinRefrigerar> TablaProductosSin;
    @FXML
    private TableColumn<ProductosSinRefrigerar, String> ColumNombre;
    @FXML
    private TableColumn<ProductosSinRefrigerar, String> ColumPais;
    @FXML
    private TableColumn<ProductosSinRefrigerar, String> ColumRecomendaciones;
    @FXML
    private TableColumn<ProductosSinRefrigerar, String> ColumTipo;
    @FXML
    private TableColumn<ProductosSinRefrigerar, String> ColumPrecio;
    @FXML
    private TableColumn<ProductosSinRefrigerar, String> ColumCodigo;
    public ObservableList<ProductosSinRefrigerar> productosSinRefrigerars;

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public void registrar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(nombre.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese Nombre del producto");
            return;
        }
        if(pais.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese pais de origen del Producto");
            return;
        }
        if(recomendaciones.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese una Recomendacion");
            return;
        }
        if(precio.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese un precio para el producto");
            return;
        }
        if (Carne.isSelected()){
            tipodeclasificacion = Carne.getText();
        }else if (Frutas.isSelected()){
            tipodeclasificacion = Frutas.getText();
        }else if (Verduras.isSelected()){
            tipodeclasificacion = Verduras.getText();
        }else if (Lacteos.isSelected()){
            tipodeclasificacion = Lacteos.getText();
        }
        codigo = (int) (Math.random()*100);

        Gestor gestor = new Gestor();
        ProductosSinRefrigerar Tmp = new ProductosSinRefrigerar(nombre.getText(),pais.getText(),precio.getText(),codigo,recomendaciones.getText(),tipodeclasificacion);
        productosSinRefrigerars.add(Tmp);
        TablaProductosSin.setItems(productosSinRefrigerars);
        gestor.insertarSinRefrigerar(Tmp);
        showAlert(Alert.AlertType.CONFIRMATION, "Producto ingresado Correctamente!", "Producto " + nombre.getText() + " ingresado");
        nombre.clear();
        pais.clear();
        recomendaciones.clear();
        precio.clear();
    }

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        Gestor gestor = new Gestor();
        productosSinRefrigerars = FXCollections.observableArrayList(gestor.listarSinRefrigerar());
        ColumNombre.setCellValueFactory(new PropertyValueFactory<ProductosSinRefrigerar, String>("nombre"));
        ColumPais.setCellValueFactory(new PropertyValueFactory<ProductosSinRefrigerar, String>("pais"));
        ColumRecomendaciones.setCellValueFactory(new PropertyValueFactory<ProductosSinRefrigerar, String>("recomendaciones"));
        ColumTipo.setCellValueFactory(new PropertyValueFactory<ProductosSinRefrigerar, String>("tipo"));
        ColumPrecio.setCellValueFactory(new PropertyValueFactory<ProductosSinRefrigerar, String>("precio"));
        ColumCodigo.setCellValueFactory(new PropertyValueFactory<ProductosSinRefrigerar, String>("codigo"));
        TablaProductosSin.setItems(productosSinRefrigerars);
    }

    @FXML
    private void eliminar(ActionEvent event) throws SQLException, ClassNotFoundException {
        Gestor gestor = new Gestor();
        // Obtengo la persona seleccionada
        ProductosSinRefrigerar p = this.TablaProductosSin.getSelectionModel().getSelectedItem();

        // Si la persona es nula, lanzo error
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una persona");
            alert.showAndWait();
        } else {

            // La elimino de la lista
            this.productosSinRefrigerars.remove(p);
            // Refresco la lista
            this.TablaProductosSin.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Persona eliminada");
            alert.showAndWait();
            gestor.eliminarSinRefrigerar(p);

        }

    }

    public void modificar(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Gestor gestor =new Gestor();
        // Obtengo la persona seleccionada
        ProductosSinRefrigerar p = this.TablaProductosSin.getSelectionModel().getSelectedItem();


        // Si la persona es nula, lanzo error
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("¡ERROR!");
            alert.setContentText("Debes seleccionar un producto");
            alert.showAndWait();
        } else {

            try {
                // Obtengo los datos del formulario
                this.nombre.getText();
                this.pais.getText();
                this.precio.getText();
                this.recomendaciones.getText();

                if (Carne.isSelected()){
                    tipodeclasificacion = Carne.getText();
                }else if (Frutas.isSelected()){
                    tipodeclasificacion = Frutas.getText();
                }else if (Verduras.isSelected()){
                    tipodeclasificacion = Verduras.getText();
                }else if (Lacteos.isSelected()){
                    tipodeclasificacion = Lacteos.getText();
                }

                // Creo una persona
                ProductosSinRefrigerar aux = new ProductosSinRefrigerar(nombre.getText(),pais.getText(),precio.getText(),codigo,recomendaciones.getText(),tipodeclasificacion);

                // Compruebo si la persona esta en el lista
                if (!this.productosSinRefrigerars.contains(aux)) {

                    // Modifico el objeto
                    p.setNombre(aux.getNombre());
                    p.setPais(aux.getPais());
                    p.setTipo(aux.getTipo());
                    p.setRecomendaciones(aux.getRecomendaciones());
                    p.setPrecio(aux.getPrecio());
                    gestor.modificarSinRefrigerar(aux);

                    // Refresco la tabla
                    this.TablaProductosSin.refresh();


                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Producto modificado");
                    alert.showAndWait();

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("El producto existe");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Formato incorrecto");
                alert.showAndWait();
            }

        }

    }

    public void cargar(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
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
     * Creo evento para seleccionar datos del table view
     */
    public void seleccionar(MouseEvent mouseEvent) {
        ProductosSinRefrigerar s = this.TablaProductosSin.getSelectionModel().getSelectedItem();
        if (s != null) {
            this.nombre.setText(s.getNombre());
            this.pais.setText(s.getPais());
            this.recomendaciones.setText(s.getRecomendaciones());
            this.precio.setText(s.getPrecio());
        }
    }

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
