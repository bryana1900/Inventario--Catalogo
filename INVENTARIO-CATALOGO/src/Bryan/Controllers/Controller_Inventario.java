package Bryan.Controllers;
import Bryan.Inventario.Inventario;
import Bryan.Logic.Gestor;
import Bryan.ProductosCongelados.ProductosCongelados;
import Bryan.ProductosRefrigerados.ProductosRefrigerados;
import Bryan.ProductosSinRefrigerar.ProductosSinRefrigerar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


import static java.time.temporal.ChronoUnit.DAYS;

public class Controller_Inventario {
    @FXML
    private TableView<Inventario> TablaInventario;
    @FXML
    private TableColumn<Inventario, LocalDate> ColumCaducidad;
    @FXML
    private TableColumn<Inventario, Integer> ColumCantidad;
    @FXML
    private TableColumn<Inventario, String> ColumNombre;
    @FXML
    private TableColumn<Inventario, String> ColumTipo;
    public ObservableList<Inventario> inventarios;

    @FXML
    private TableView<ProductosCongelados> TableCongelados;
    @FXML
    private TableColumn<ProductosCongelados, String> ProductosCongelados;
    public ObservableList<ProductosCongelados> productosCongelados;

    @FXML
    private TableView<ProductosRefrigerados> TableRefrigerados;
    @FXML
    private TableColumn<ProductosRefrigerados, String> ProductosRefrigerados;
    public ObservableList<ProductosRefrigerados> productosRefrigerados;

    @FXML
    private TableView<ProductosSinRefrigerar> TableSinRefri;
    @FXML
    private TableColumn<ProductosSinRefrigerar, String> ProductosSin;
    public ObservableList<ProductosSinRefrigerar> productosSinRefrigerars;

    @FXML
    private Button cerrar;
    @FXML
    private RadioButton ProductosSinrefrigerar,Productosrefrigerados,Productoscongelados;
    @FXML
    private Label DiasCaducar;
    @FXML
    private DatePicker fechaActualC;
    @FXML
    private DatePicker Caducidad;
    public TextField cantidadC;
    public TextField nombre;
    private int cero =0;
    private String tipos;
    public int codigo;
    private int cantidad;
    /**
     * Creo un evento para registrar datos
     */
    @FXML
    void registrar(ActionEvent event) throws SQLException, ClassNotFoundException {
        /**
         * Obtengo los datos y declaro con condicion que los campos no pueden ir vacio
         */
        if(nombre.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,"¡ERROR!", "Por favor ingrese el nombre del producto");
            return;
        }
        if(cantidadC.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese la cantidad de productos");
            return;
        }
        /**
         * Creo condicion para que no se ingresen cantidades iguales a 0
         */
        cantidad = Integer.parseInt(cantidadC.getText());
        if(cantidad <= cero){
            showAlert(Alert.AlertType.ERROR, "¡ERROR!", "Por favor ingrese una cantidad mayor a 0");
            return;
        }
        /**
         * Obtengo los datos de los radio buttons
         */
        if (Productoscongelados.isSelected()){
            tipos = Productoscongelados.getText();
        }else if (Productosrefrigerados.isSelected()){
            tipos = Productosrefrigerados.getText();
        }else if (ProductosSinrefrigerar.isSelected()){
            tipos = ProductosSinrefrigerar.getText();
        }
        /**
         * Creo codigo usando math.random
         */
        codigo = (int) (Math.random()*100);
        /**
         * Obtengo la fecha de datepicker javafx y lo transformo a localdate
         */
        LocalDate date = Caducidad.getValue();
        /**
         * Guardo los datos en el Gestor
         */
        Gestor gestor = new Gestor();
        Inventario Tmp = new Inventario(nombre.getText(),tipos,cantidad,date,codigo);
        inventarios.add(Tmp);
        TablaInventario.setItems(inventarios);
        gestor.insertarInventario(Tmp);
        /**
         * Muestro Alerta de que los datos fueron guardados exitosamente
         */
        showAlert(Alert.AlertType.CONFIRMATION, "PRODUCTO INGRESADO CORECCTAMENTE!", "PRODUCTO: " + nombre.getText() + " INGRESADO");
        /**
         * Limpio los campos para un nuevo registro
         */
        nombre.clear();
        cantidadC.clear();
        }
    /**
     * Creo evento para calcular los dias
     */
    @FXML
    void calcularDias(ActionEvent event) {
        /**
         * Obtengo las fechas de datepicker y las transformo a LocalDate
         */
        LocalDate date = Caducidad.getValue();
        LocalDate dateActual = fechaActualC.getValue();
        /**
         * Uso long days para calcular los dias
         */
        long dias = DAYS.between(dateActual,date);
        DiasCaducar.setText("Faltan "+dias+" dias a caducar");
    }
    /**
     * Creo evento para eliminar datos de mysql
     */
    @FXML
    void eliminar(ActionEvent event) throws SQLException, ClassNotFoundException {
        Gestor gestor = new Gestor();
        /**
         * Obtengo el producto seleccionado de la tabla
         */
        Inventario p = this.TablaInventario.getSelectionModel().getSelectedItem();
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
             * elimino el producto de la lista
             */
            this.inventarios.remove(p);
            // Refresco la lista
            this.TablaInventario.refresh();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Producto eliminado");
            alert.showAndWait();
            gestor.eliminarInventario(p);
        }
    }
    /**
     * Creo evento para modificar datos de mysql
     */
    @FXML
    void modificar(ActionEvent event) throws SQLException, ClassNotFoundException {
        Gestor gestor = new Gestor();
        /**
         * Obtengo el producto seleccionado
         */
        Inventario p = this.TablaInventario.getSelectionModel().getSelectedItem();
        /**
         * Si el producto es nulo, lanzo error
         */
        if (p == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una persona");
            alert.showAndWait();
        } else {
            try {
                /**
                 * Obtengo los datos
                 */
                this.nombre.getText();
                this.cantidadC.getText();
                int cantidad = Integer.parseInt(cantidadC.getText());
                if(cantidad <= cero){
                    showAlert(Alert.AlertType.ERROR, "¡ERROR!", "No puedes modificar una cantidad inferior a 0");
                    return;
                }
                if (ProductosSinrefrigerar.isSelected()){
                    tipos = ProductosSinrefrigerar.getText();
                }else if (Productosrefrigerados.isSelected()){
                    tipos = Productosrefrigerados.getText();
                }else if (Productoscongelados.isSelected()){
                    tipos = Productoscongelados.getText();
                }
                LocalDate date = Caducidad.getValue();
                /**
                 * Creo clase inventario
                 */
                Inventario aux = new Inventario(nombre.getText(),tipos,cantidad,date,codigo);
                /**
                 * Compruebo si el producto esta en el lista
                 */
                if (!this.inventarios.contains(aux)) {
                    // Modifico el objeto
                    p.setNombre(aux.getNombre());
                    p.setCantidad(aux.getCantidad());
                    p.setTipo(aux.getTipo());
                    p.setFecha(aux.getFecha());
                    gestor.modificarInventario(aux);
                    /**
                     * Refresco la tabla
                     */
                    this.TablaInventario.refresh();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("Producto modificada");
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
                alert.setTitle("¡Error!");
                alert.setContentText("Lo que intentaste esta en Formato incorrecto");
                alert.showAndWait();
            }
        }
    }
    /**
     * Creo evento para inicializar y mostrar datos de mysql
     */
    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        Gestor gestor = new Gestor();
        inventarios = FXCollections.observableArrayList(gestor.listarInventario());
        ColumNombre.setCellValueFactory(new PropertyValueFactory<Inventario, String>("nombre"));
        ColumTipo.setCellValueFactory(new PropertyValueFactory<Inventario, String>("tipo"));
        ColumCaducidad.setCellValueFactory(new PropertyValueFactory<Inventario, LocalDate>("fecha"));
        ColumCantidad.setCellValueFactory(new PropertyValueFactory<Inventario, Integer>("cantidad"));
        TablaInventario.setItems(inventarios);
    }
    /**
     * Creo evento para seleccionar datos del table view
     */
    @FXML
    void seleccionar(MouseEvent event) {
        Inventario s = this.TablaInventario.getSelectionModel().getSelectedItem();
        if (s != null) {
            this.nombre.setText(s.getNombre());
            this.cantidadC.setText(String.valueOf(s.getCantidad()));
        }
        ProductosCongelados pc = this.TableCongelados.getSelectionModel().getSelectedItem();
        if (pc != null) {
            this.nombre.setText(pc.getNombre());
        }
        ProductosRefrigerados pr = this.TableRefrigerados.getSelectionModel().getSelectedItem();
        if (pr != null) {
            this.nombre.setText(pr.getNombre());
        }
        ProductosSinRefrigerar ps = this.TableSinRefri.getSelectionModel().getSelectedItem();
        if (ps != null) {
            this.nombre.setText(ps.getNombre());
        }
    }
    /**
     * Creo showalert para las notificaciones
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    /**
     * Creo evento de selecionar tipo y que el table view lo muestre
     */
    public void seleccionartipoproducto(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Gestor gestor = new Gestor();
        if (Productoscongelados.isSelected()){
            productosCongelados = FXCollections.observableArrayList(gestor.listarCongelados());
            ProductosCongelados.setCellValueFactory(new PropertyValueFactory<ProductosCongelados, String>("nombre"));
            TableCongelados.setItems(productosCongelados);
        }else if (Productosrefrigerados.isSelected()){
            productosRefrigerados = FXCollections.observableArrayList(gestor.listarRefrigerados());
            ProductosRefrigerados.setCellValueFactory(new PropertyValueFactory<ProductosRefrigerados, String>("nombre"));
            TableRefrigerados.setItems(productosRefrigerados);
        }else if (ProductosSinrefrigerar.isSelected()){
            productosSinRefrigerars = FXCollections.observableArrayList(gestor.listarSinRefrigerar());
            ProductosSin.setCellValueFactory(new PropertyValueFactory<ProductosSinRefrigerar, String>("nombre"));
            TableSinRefri.setItems(productosSinRefrigerars);
        }
    }
    /**
     * Creo evento cerrar la pagina
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
            Logger.getLogger(Controller_Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
