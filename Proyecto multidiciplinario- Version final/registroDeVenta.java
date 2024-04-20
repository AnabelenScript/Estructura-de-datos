import java.util.ArrayList;

public class registroDeVenta {
    private ArrayList<Productos> listaCarrito= new ArrayList<>();
    private float cuentaTotal;
    String tipoVenta;

    public registroDeVenta(ArrayList<Productos> listaCarrito, float cuentaTotal, String tipoVenta) {
        this. listaCarrito= listaCarrito;
        this. cuentaTotal= cuentaTotal;
        this. tipoVenta= tipoVenta;
    }

    public registroDeVenta(){

    }

    public void setlistaCarrito(ArrayList<Productos> listaCarrito){
        this.listaCarrito=listaCarrito;
    }
    public ArrayList<Productos> getlistaCarrito(){
        return listaCarrito;
    }

    public void setcuentaTotal(float cuentaTotal){
        this.cuentaTotal=cuentaTotal;
    }
    public float getcuentaTotal(){
        return cuentaTotal;
    }

    public void imprimirRegistrosVenta(ArrayList<registroDeVenta> listaVentas) {
        if (listaVentas.isEmpty()) {
            System.out.println("\n---- No hay registros de venta.----\n");
        } else {
            System.out.println("Registros de Venta:");
            for (int i = 0; i < listaVentas.size(); i++) {
                registroDeVenta venta = listaVentas.get(i);
                ArrayList<Productos> productosVenta = venta.getlistaCarrito();
                System.out.println("-------------------------------------");
                System.out.println("Venta #" + (i + 1) + " - Tipo: " + venta.getTipoVenta());
                
                 for (Productos producto : productosVenta) {
                     System.out.println("- Producto: " + producto.getnombreProducto() + ", Cantidad: " + producto.getcantidadProducto());
                }
                System.out.println("El costo total de la venta: " + venta.getcuentaTotal());
               
            }
        }
    }  
    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }
}

