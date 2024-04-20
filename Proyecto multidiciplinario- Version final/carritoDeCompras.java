import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class carritoDeCompras {
    static Inventario objInventario = new Inventario();
    private static ArrayList<Productos> listaCarrito = new ArrayList<>();

    public carritoDeCompras() {
    }

    public ArrayList<Productos> agregarProducto(ArrayList<Productos> listaProductos) {
        listaCarrito.clear();
        Scanner scanner = new Scanner(System.in);
        String respuesta = "", nombreProducto;
        boolean encontrado = false, bandera = false;
        int cantidadDisponible = 0, cantidadProducto = 0;
        imprimirInventario(listaProductos);
        do {
            do {
                System.out.print("\nIngrese el nombre del producto: ");
                nombreProducto = scanner.nextLine();
                for (Productos producto : listaProductos) {
                    if (producto.getnombreProducto().equalsIgnoreCase(nombreProducto)) {
                        encontrado = true;
                        cantidadDisponible = producto.getcantidadProducto();
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("\n---- El producto no existe en el inventario. ----");
                }
            } while (!encontrado);

            do {
                System.out.println(
                        "\n----Ingrese la cantidad del producto" + " (disponible: " + cantidadDisponible + ") : ");
                System.out.print(" ---Cantidad: ");
                while (bandera == false) {
                    try {
                        cantidadProducto = scanner.nextInt();
                        while (cantidadProducto <= 0 || cantidadProducto > cantidadDisponible) {
                            System.out.print("\n----Ingrese una cantidad valida: ");
                            cantidadProducto = scanner.nextInt();
                        }
                        bandera = true;
                    } catch (InputMismatchException e) {
                        System.out.print("oh oh, a ingresado un caracter invalido intentelo de nuevo: ");
                        scanner.nextLine();
                    }
                }
                bandera = false;
            } while (cantidadProducto > cantidadDisponible);
            for (Productos producto : listaProductos) {
                if (producto.getnombreProducto().equalsIgnoreCase(nombreProducto)) {
                    producto.setcantidadProducto(producto.getcantidadProducto() - cantidadProducto);
                    break;
                }
            }

            Productos producto = new Productos(nombreProducto, cantidadProducto);
            listaCarrito.add(producto);
            System.out.println("\n----Producto agregado al carrito.----");
            scanner.nextLine();

            System.out.println("\n-----¿Desea agregar otro producto? (S/N)----");
            System.out.print("desicion: ");
            while (bandera == false) {

                respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("N")) {
                    if (respuesta.equalsIgnoreCase("S")) {
                        imprimirInventario(listaProductos);
                    }
                } else {
                    System.out.print("Opcion incorrecta intente de nuevo...");
                    respuesta = scanner.nextLine();
                }
                bandera = true;

            }
            bandera = false;
        } while (respuesta.equalsIgnoreCase("S"));

        System.out.println("\n-----Productos en el carrito:-----\n");
        for (Productos producto : listaCarrito) {
            System.out.println(
                    "Nombre: " + producto.getnombreProducto() + " Cantidad: " + producto.getcantidadProducto());
        }
        System.out.println("----------------------");
        System.out.println("\n¿Desea eliminar algún producto del carrito? (S/N) \n");
        while (bandera == false) {
            respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("N")) {
                if (respuesta.equalsIgnoreCase("S")) {
                    modificarProducto();
                    
                }

            } else {
                System.out.print("Opcion incorrecta intente de nuevo: ");
                respuesta = scanner.nextLine();
            }
            ventaProducto venta = new ventaProducto(listaCarrito);
            venta.realizarPago(listaProductos);
            bandera = true;
        }

        return listaCarrito;
        
    }

    public static void modificarProducto() {
        Scanner scanner = new Scanner(System.in);
        boolean bandera = false;
        int numeroProducto = 0, indice = 1, eliminarProductosCarrito = 0;
        System.out.println("\n-----------Este es tu carrito-------\n");
        for (Productos producto : listaCarrito) {
            System.out.println(indice + ") Nombre: " + producto.getnombreProducto() + " Cantidad: "
                    + producto.getcantidadProducto());
            indice++;
        }
        System.out.print("Seleccione el número del producto que desea eliminar: ");
        while (bandera == false) {
            try {
                numeroProducto = scanner.nextInt();
                while (numeroProducto <= 0 || numeroProducto > listaCarrito.size()) {
                    System.out.println("Intente de nuevo: ");
                    numeroProducto = scanner.nextInt();
                    eliminarProductosCarrito = scanner.nextInt();
                }
                bandera = true;
            } catch (InputMismatchException e) {
                System.out.println("Intente de nuevo: ");
                scanner.nextLine();
                bandera = false;
            }

        }

        bandera = false;
        while (bandera == false) {
            System.out.print("Cuantos articulos desea eliminar: ");

            try {
                eliminarProductosCarrito = scanner.nextInt();
                if (eliminarProductosCarrito >= 1
                        && eliminarProductosCarrito <= listaCarrito.get(numeroProducto - 1).cantidadProducto()) {
                    System.out.println("Producto eliminado exitosamente del carrito.");

                    int newCantidadProducto = listaCarrito.get(numeroProducto - 1).cantidadProducto()
                            - eliminarProductosCarrito;
                    listaCarrito.get(numeroProducto - 1).setcantidadProducto(newCantidadProducto);

                    ventaProducto venta = new ventaProducto(listaCarrito);
                    if (listaCarrito.get(numeroProducto - 1).cantidadProducto() >= 1) {
                        venta.realizarPago(listaCarrito);
                    }
                    bandera = true;
                } else {
                    System.out.println("Número de producto no válido. Por favor, seleccione un número válido.");
                    eliminarProductosCarrito = scanner.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println("Intente de nuevo: ");
                scanner.nextLine();
                bandera = false;
            }
        }

    }

    private void imprimirInventario(ArrayList<Productos> listaProductos) {
        System.out.println("\n -----Productos disponibles en el inventario:----\n");
        for (int i = 0; i < objInventario.getListaProductos().size(); i++) {
            System.out.println(i + 1 + " )Nombre del producto:  " + listaProductos.get(i).getnombreProducto()
                    + ", cantidad: " + listaProductos.get(i).getcantidadProducto());
        }
    }
}
