import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ventaProducto {
    protected float cuentaTotal;
    protected float pago;
    protected Inventario objInventario = new Inventario();
    protected Apartado objApartado = new Apartado();
    protected ArrayList<Productos> listaCarrito = new ArrayList<>();
    protected ArrayList<Cliente> listaApartadosCliente = new ArrayList<>();
    protected static ArrayList<registroDeVenta> listaVentas = new ArrayList<>();
    protected String tipoVenta;

    public ventaProducto(ArrayList<Productos> listaCarrito) {
        for (Productos producto : listaCarrito) {
            this.listaCarrito.add(new Productos(producto.getnombreProducto(), producto.getcantidadProducto()));
        }
    }

    public void setListaApartadosCliente(ArrayList<Cliente> listaApartadosCliente) {
        this.listaApartadosCliente = listaApartadosCliente;
    }

    public void setApartados() {

    }

    public ventaProducto() {

    }

    public static ArrayList<registroDeVenta> getlistaVentas() {
        return listaVentas;
    }

    protected void verMenu(ArrayList<Productos> listaProductos) {
        Scanner sc = new Scanner(System.in);
        int desicion = 0;
        boolean bandera = false;
        carritoDeCompras objCarritoDeCompras = new carritoDeCompras();
        Apartado objApartado = new Apartado();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        System.out.println("\n--Elija la opcion a pagar---\n");
        System.out.println("1) Pagar ahora mismo   2)Pagar productos apartados");
        System.out.print("Opcion: ");
        while (bandera == false) {
            try {
                desicion = sc.nextInt();
                while (desicion < 1 || desicion > 2) {
                    System.out.print("Intente de nuevo: ");
                    desicion = sc.nextInt();
                }

                bandera = true;
            } catch (InputMismatchException e) {
                System.out.println("Intene de nuevo: ");
                sc.nextLine();
            }
        }
        bandera = false;

        switch (desicion) {
            case 1:
                objCarritoDeCompras.agregarProducto(listaProductos);
                break;
            case 2:
                pagarApartado();
                break;

            default:
                break;
        }
    }

    protected void realizarPago(ArrayList<Productos> listaProductos) {
        Scanner scanner = new Scanner(System.in);
        if (listaCarrito.isEmpty() == false) {
            for (Productos productoCarrito : listaCarrito) {
                for (Productos productoInventario : listaProductos) {
                    if (productoCarrito.getnombreProducto().equalsIgnoreCase(productoInventario.getnombreProducto())) {
                        cuentaTotal += productoInventario.getcostoProducto() * productoCarrito.getcantidadProducto();
                        break;
                    }
                }
            }
            if (cuentaTotal >= 1000) {
                ventaConDescuento descuento = new ventaConDescuento(listaCarrito);
                descuento.realizarPago(listaProductos);
            } else if (cuentaTotal >= 800) {
                ventaConPromocion promocion = new ventaConPromocion(listaCarrito);
                promocion.realizarPago(listaProductos);
            } else {

                System.out.println("Costo total de la compra: " + cuentaTotal);
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
                        scanner.next();
                    }
                } while (true);
                float cambio = pago - cuentaTotal;
                tipoVenta = ("Venta normal");
                System.out.println("--------------------------------------------------------");
                System.out.println("La compra fue realizada con éxito. Su cambio es de: " + cambio);
                registroDeVenta venta = new registroDeVenta(listaCarrito, cuentaTotal, tipoVenta);
                listaVentas.add(venta);
                for (Productos productoCarrito : listaCarrito) {
                    for (Productos productoInventario : listaProductos) {
                        if (productoCarrito.getnombreProducto()
                                .equalsIgnoreCase(productoInventario.getnombreProducto())) {
                            System.out.println("Nombre: " + productoCarrito.getnombreProducto());
                            System.out.println("Precio unitario: " + productoInventario.getcostoProducto());
                            System.out.println("Cantidad: " + productoCarrito.getcantidadProducto());
                            System.out.println("Cuenta Total: " + cuentaTotal);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void imprimirVentas() {
        registroDeVenta ventas = new registroDeVenta(listaCarrito, cuentaTotal, tipoVenta);
        ventas.imprimirRegistrosVenta(listaVentas);
    }

    public void pagarApartado() {
        int opcion = 0;
        float pagoFinal = 0, saldoPendiente = 0;
        boolean bander = false, apartadosExsit = false;

        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("-------Estas son las cuentas pendientes-------");
            for (int i = 0; i < listaApartadosCliente.size(); i++) {
                if (listaApartadosCliente.get(i).getSaldoPendiente() != 0) {
                    System.out.println(i + 1 + " )Cliente " + listaApartadosCliente.get(i).getNombreCliente() + "\n");
                    apartadosExsit = true;
                }
            }
            if (apartadosExsit != true) {
                System.out.println("\n----No existen cuentas pendientes----\n");
                return;
            }
            System.out.print("Indique la cuenta que desea pagar: ");
            while (bander == false) {
                try {
                    opcion = sc.nextInt();
                    while (opcion <= 0 || opcion > listaApartadosCliente.size()) {
                        System.out.print("intente de nuevo: ");
                        opcion = sc.nextInt();
                    }
                    bander = true;
                } catch (InputMismatchException e) {
                    System.out.print("intetente de nuevo: ");
                    sc.next();
                }

            }
            bander = false;
            opcion--;
            System.out.println("Saldo pendiente: $" + listaApartadosCliente.get(opcion).getSaldoPendiente());
            System.out.println("Ingrese el saldo restante: ");
            pagoFinal = sc.nextFloat();
            saldoPendiente = listaApartadosCliente.get(opcion).getSaldoPendiente() - pagoFinal;
            if (saldoPendiente <= 0) {
                System.out.println("Su cambio es: " + Math.abs(saldoPendiente));
                listaApartadosCliente.get(opcion).setSaldoPendiente(0);
            } else {
                System.out.println("Su nuevo saldo pendiente es: " + saldoPendiente);
                listaApartadosCliente.get(opcion).setSaldoPendiente(saldoPendiente);
            }
            if (saldoPendiente == 0) {
                System.out.println("Ha liquidado su cuenta con éxito");
            }
            tipoVenta = ("Venta normal");
            registroDeVenta venta = new registroDeVenta(listaApartadosCliente.get(opcion).getaApartados(), pagoFinal, tipoVenta);
            listaVentas.add(venta);
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número válido.");
            sc.next();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
        }
    }

}
