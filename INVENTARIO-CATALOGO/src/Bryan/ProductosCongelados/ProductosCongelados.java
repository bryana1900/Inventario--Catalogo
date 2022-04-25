package Bryan.ProductosCongelados;

import Bryan.ProductosPadre.Productos;

public class ProductosCongelados extends Productos {
    private String tempMax;
    private String tempMin;
    /**
     * Get y Set
     */
    public String getTempMax() {return tempMax;}
    public void setTempMax(String tempMax) {this.tempMax = tempMax;}

    public String getTempMin() {return tempMin;}
    public void setTempMin(String tempMin) {this.tempMin = tempMin;}
    /**
     * Contructor Vacio
     */
    public ProductosCongelados() {
    }
    /**
     * Constructor con datos
     */
    public ProductosCongelados(String nombre, String pais, String precio, int codigo, String tempMax, String tempMin) {
        super(nombre, pais, precio, codigo);
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }
    /**
     * To String
     */
    @Override
    public String toString() {
        return "ProductosCongelados{" + super.toString()+
                "tempMax='" + tempMax + '\'' +
                ", tempMin='" + tempMin + '\'' +
                '}';
    }
}
