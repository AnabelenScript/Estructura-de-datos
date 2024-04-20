import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ventaConDescuento extends ventaProducto {
    private float descuento=0.8f;

    public ventaConDescuento(ArrayList<Productos> listaCarrito) {
        for (Productos producto : listaCarrito) {
            this.listaCarrito.add(new Productos(producto.getnombreProducto(), producto.getcantidadProducto()));
        }
    }

    @Override
    protected void realizarPago(ArrayList<Productos> listaProductos) {
        Scanner scanner = new Scanner(System.in);
        for (Productos productoCarrito : listaCarrito) {
            for (Productos productoInventario : listaProductos) {
                if (productoCarrito.getnombreProducto().equalsIgnoreCase(productoInventario.getnombreProducto())) {
                    cuentaTotal += productoInventario.getcostoProducto() * productoCarrito.getcantidadProducto();
                    break;
                }
            }
        }
        System.out.println("Por la compra de más de 1000 pesos en productos, se le hace un 20% de descuento.");
        System.out.println("Costo total de la compra  descuento: " + cuentaTotal);
        cuentaTotal =  cuentaTotal* descuento;
        System.out.println("Costo total de la compra con descuento: " + cuentaTotal);
            do {
                try {
                    System.out.println("Ingrese su efectivo:");
                    pago = scanner.nextFloat();
                    if (pago < cuentaTotal) {
                        throw new InputMismatchException("El pago debe cubrir el costo total.");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.err.println("Por favor, ingrese un monto válido en efectivo que cubra el costo total.");
                    scanner.nextLine();
                }
            } while (true);
        float cambio = pago - cuentaTotal;
        System.out.println("La compra fue realizada con éxito. Su cambio es de: " + cambio);
        tipoVenta= ("Venta con descuento");
        registroDeVenta venta = new registroDeVenta(listaCarrito, cuentaTotal, tipoVenta);
            listaVentas.add(venta);
        for (Productos productoCarrito : listaCarrito) {
            for (Productos productoInventario : listaProductos) {
                if (productoCarrito.getnombreProducto().equalsIgnoreCase(productoInventario.getnombreProducto())) {
                    System.out.println("----------------------");
                    System.out.println("Nombre: " + productoCarrito.getnombreProducto());
                    System.out.println("Precio unitario: " + productoInventario.getcostoProducto());
                    System.out.println("Cantidad: " + productoCarrito.getcantidadProducto());
                    break; 
                }
            }
        }
        System.out.println("Costo total de la compra: " + cuentaTotal);
    }

    public void imprimirVentas(){
        registroDeVenta ventas = new registroDeVenta(listaCarrito, cuentaTotal, tipoVenta);
        ventas.imprimirRegistrosVenta(listaVentas);
    }
}