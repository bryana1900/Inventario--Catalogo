package Bryan.Imagen;
import java.io.InputStream;

public class Imagen {
    private String id;
    private InputStream imagen;
    /**
     * Creo get y set
     */
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public InputStream getImagen() {
        return imagen;
    }
    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }
    /**
     * Creo constructor vacio
     */
    public Imagen() {
    }
    /**
     * Creo constrcutor
     */
    public Imagen(String id) {
        this.id = id;
    }
    /**
     * Creo to string
     */
    @Override
    public String toString() {
        return "Imagen{" +
                "id='" + id + '\'' +
                '}';
    }
}
