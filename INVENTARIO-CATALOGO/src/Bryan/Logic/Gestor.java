package Bryan.Logic;

import Bryan.Imagen.Imagen;
import Bryan.Imagen.ImagenDAO;
import Bryan.Inventario.Inventario;
import Bryan.Inventario.InventarioDAO;
import Bryan.ProductosCongelados.ProductosCongelados;
import Bryan.ProductosCongelados.ProductosCongeladosDAO;
import Bryan.ProductosRefrigerados.ProductosRefrigerados;
import Bryan.ProductosRefrigerados.ProductosRefrigeradosDAO;
import Bryan.ProductosSinRefrigerar.ProductosSinRefrigerar;
import Bryan.ProductosSinRefrigerar.ProductosSinRefrigerarDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Gestor {
    private ProductosCongeladosDAO productosCongeladosDAO;
    private ProductosRefrigeradosDAO productosRefrigeradosDAO;
    private ProductosSinRefrigerarDAO productosSinRefrigerarDAO;
    private InventarioDAO inventarioDAO;
    private ImagenDAO imagenDAO;
    /**
     * Funcion de crear datos en los daos
     */
    public Gestor() {
        productosCongeladosDAO = new ProductosCongeladosDAO();
        productosRefrigeradosDAO = new ProductosRefrigeradosDAO();
        productosSinRefrigerarDAO = new ProductosSinRefrigerarDAO();
        inventarioDAO = new InventarioDAO();
        imagenDAO = new ImagenDAO();
    }
    /**
     * Funcion de insertar datos en el DAO de la clase Productos Congelados
     */
    public boolean insertarCongelados(ProductosCongelados tmp) throws SQLException, ClassNotFoundException {
        boolean duplicado=false;
        for(ProductosCongelados productosCongelados:productosCongeladosDAO.listarCongelados())
        {
            if(productosCongelados.equals(tmp))
                duplicado=true;
        }
        if(!duplicado)
            productosCongeladosDAO.insertarCongelados(tmp);
        return duplicado;
    }
    /**
     * Funcion de listar datos del DAO de la clase Productos Congelados
     */
    public ArrayList<ProductosCongelados> listarCongelados() throws SQLException, ClassNotFoundException {
        return productosCongeladosDAO.listarCongelados();
    }
    /**
     * Funcion de eliminar datos en el DAO de la clase Productos Congelados
     */
    public boolean eliminarCongelados(ProductosCongelados tmp) throws SQLException, ClassNotFoundException {
        boolean existe=false;
        if(!existe)
            productosCongeladosDAO.eliminarCongelados(tmp);
        return existe;
    }
    /**
     * Funcion de modificar datos en el DAO de la clase Productos Congelados
     */
    public void modificarCongelados(ProductosCongelados tmp) throws SQLException, ClassNotFoundException {
        productosCongeladosDAO.modificarCongelados(tmp);
    }
    /**
     * Funcion de insertar datos en el DAO de la clase Productos Refrigerados
     */
    public boolean insertarRefrigerados(ProductosRefrigerados tmp) throws SQLException, ClassNotFoundException {
        boolean duplicado=false;
        for(ProductosRefrigerados productosRefrigerados:productosRefrigeradosDAO.listarRefrigerados())
        {
            if(productosRefrigerados.equals(tmp))
                duplicado=true;
        }
        if(!duplicado)
            productosRefrigeradosDAO.insertarRefrigerados(tmp);
        return duplicado;
    }
    /**
     * Funcion de listar datos del DAO de la clase Productos Refrigerados
     */
    public ArrayList<ProductosRefrigerados> listarRefrigerados() throws SQLException, ClassNotFoundException {
        return productosRefrigeradosDAO.listarRefrigerados();
    }
    /**
     * Funcion de eliminar datos en el DAO de la clase Productos Refrigerados
     */
    public boolean eliminarRefrigerados(ProductosRefrigerados tmp) throws SQLException, ClassNotFoundException {
        boolean existe=false;
        if(!existe)
            productosRefrigeradosDAO.eliminarRefrigerados(tmp);
        return existe;
    }
    /**
     * Funcion de modificar datos en el DAO de la clase Productos Refrigerados
     */
    public void modificarRefrgerados(ProductosRefrigerados tmp) throws SQLException, ClassNotFoundException {
        productosRefrigeradosDAO.modificarRefrigerados(tmp);
    }
    /**
     * Funcion de insertar datos en el DAO de la clase Productos Sin Refrigerados
     */
    public boolean insertarSinRefrigerar(ProductosSinRefrigerar tmp) throws SQLException, ClassNotFoundException {
        boolean duplicado=false;
        for(ProductosSinRefrigerar productosSinRefrigerar:productosSinRefrigerarDAO.listarSinRefrigerar())
        {
            if(productosSinRefrigerar.equals(tmp))
                duplicado=true;
        }
        if(!duplicado)
            productosSinRefrigerarDAO.insertarSinRefrigerar(tmp);
        return duplicado;
    }
    /**
     * Funcion de listar datos del DAO de la clase Productos Sin Refrigerados
     */
    public ArrayList<ProductosSinRefrigerar> listarSinRefrigerar() throws SQLException, ClassNotFoundException {
        return productosSinRefrigerarDAO.listarSinRefrigerar();
    }
    /**
     * Funcion de eliminar datos en el DAO de la clase Productos Sin Refrigerados
     */
    public boolean eliminarSinRefrigerar(ProductosSinRefrigerar tmp) throws SQLException, ClassNotFoundException {
        boolean existe=false;
        if(!existe)
            productosSinRefrigerarDAO.eliminarSinRefrigerar(tmp);
        return existe;
    }
    /**
     * Funcion de modificar datos en el DAO de la clase Productos Sin Refrigerados
     */
    public void modificarSinRefrigerar(ProductosSinRefrigerar tmp) throws SQLException, ClassNotFoundException {
        productosSinRefrigerarDAO.modificarSinRefrigerar(tmp);
    }
    /**
     * Funcion de insertar datos en el DAO de la clase Inventario
     */
    public boolean insertarInventario(Inventario tmp) throws SQLException, ClassNotFoundException {
        boolean duplicado=false;
        for(Inventario inventario:inventarioDAO.listarInventario())
        {
            if(inventario.equals(tmp))
                duplicado=true;
        }
        if(!duplicado)
            inventarioDAO.insertarInventario(tmp);
        return duplicado;
    }
    /**
     * Funcion de listar datos en el DAO de la clase Inventario
     */
    public ArrayList<Inventario> listarInventario() throws SQLException, ClassNotFoundException {
        return inventarioDAO.listarInventario();
    }
    /**
     * Funcion de eliminar datos en el DAO de la clase Inventario
     */
    public boolean eliminarInventario(Inventario tmp) throws SQLException, ClassNotFoundException {
        boolean existe=false;
        if(!existe)
            inventarioDAO.eliminarInventario(tmp);
        return existe;
    }
    /**
     * Funcion de modificar datos en el DAO de la clase Inventario
     */
    public void modificarInventario(Inventario tmp) throws SQLException, ClassNotFoundException {
        inventarioDAO.modificarInventario(tmp);
    }
    /**
     * Funcion de insertar datos en el DAO de la clase Imagen
     */
    public void insertarImagen(Imagen tmpImagen) throws SQLException, ClassNotFoundException {
        imagenDAO.insertarImagen(tmpImagen);
    }
    /**
     * Funcion de obtener datos codigo en el DAO de la clase Imagen
     */
    public String obtenerCodigoImagen() throws SQLException, ClassNotFoundException {
        return imagenDAO.obtenerCodigoImagen();
    }
    /**
     * Funcion de obetner datos imagen en el DAO de la clase Imagen
     */
    public Imagen obtenerImagen(String codigo) throws SQLException, ClassNotFoundException {
        return imagenDAO.obtenerImagen(codigo);
    }
}
