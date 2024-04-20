public class Productos {

    private String nombreProducto;
    private float costoProducto;
    private int cantidadProducto;
    private String marcaProducto;
    private String descripcionProducto;

    public Productos() {

    }

    public Productos(String nombreProducto, float costoProducto, String marcaProducto, String descripcionProducto,
            int cantidadProducto) {

        this.nombreProducto = nombreProducto;
        this.costoProducto = costoProducto;
        this.marcaProducto = marcaProducto;
        this.descripcionProducto = descripcionProducto;
        this.cantidadProducto = cantidadProducto;

    }

    public Productos(String nombreProducto, int cantidadProducto) {
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidadProducto;
    }

    public void setnombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getnombreProducto() {
        return nombreProducto;
    }

    public void setcostoProducto(float costoProducto) {
        this.costoProducto = costoProducto;
    }

    public float getcostoProducto() {
        return costoProducto;
    }

    public void setmarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public String getmarcaProducto() {
        return marcaProducto;
    }

    public void setdescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getdescripcionProducto() {
        return descripcionProducto;
    }

    public void setcantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    public int getcantidadProducto() {
        return cantidadProducto;
    }

    public int cantidadProducto() {
        return cantidadProducto;
    }


}