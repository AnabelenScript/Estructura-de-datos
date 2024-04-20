import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ventaConPromocion extends ventaProducto {
    private float precioPromocion= 100;

    public ventaConPromocion(ArrayList<Productos> listaCarrito) {
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
        System.out.println("Por promocion de apertura al hacer una compra minima de 800 se le bonifican una promocion de 100 pesos frente a su compra total");
        System.out.println("Costo total sin promoción: " + cuentaTotal);
        cuentaTotal= cuentaTotal-precioPromocion;
        System.out.println("Costo total con promoción: " + cuentaTotal);
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
        tipoVenta=("Venta con promocion");
        System.out.println("La compra fue realizada con éxito. Su cambio es de: " + cambio);
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
        System.out.println("Costo total de la compra: " +cuentaTotal);
        scanner.close();
    }
}

