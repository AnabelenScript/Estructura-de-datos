import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {public static void main(String[] args) {
        verMenu();
    }

    public static void verMenu() {
        Scanner sc = new Scanner(System.in);
        carritoDeCompras objCarritoDeCompras = new carritoDeCompras();
        ArrayList<Productos> listaProductos = new ArrayList<>();
        Inventario objInventario = new Inventario();
        Apartado objApartado = new Apartado(objInventario);
        registroDeVenta obRegistroVenta = new registroDeVenta();
        ventaProducto objVentaProducto = new ventaProducto();
        objVentaProducto.setListaApartadosCliente(objApartado.getCuentaApartadoClientes());
         ArrayList<Cliente> listaClientes = new ArrayList<>();

        
        int menu = 0;
        boolean valida = false;
        do {
            System.out.println("-----------------Bienvenida Patricia------------------------");
            System.out.println("Que desea realizar? \n");
            System.out.println("1) Registro de ventas 2) Realiza Pagos\n");
            System.out.println("3) Realizar un apartado  4) Inventario \n");
            System.out.println("5) Salir");
            System.out.print("\n---Opcion: ");
            while (valida == false) {
                try {
                    menu = sc.nextInt();
                    valida = true;
                } catch (InputMismatchException e) {
                    System.err.println("Intentelo de nuevo");
                    sc.nextLine();
                }
            }
            valida = false;
            switch (menu) {
                case 1:
                    obRegistroVenta.imprimirRegistrosVenta(ventaProducto.getlistaVentas());
                    break;
                case 2:
                    objVentaProducto.verMenu(objInventario.getListaProductos());
                    break;
                case 3:
                objApartado.crearCuentaPendiente(listaProductos);
                    break;

                case 4:
                    objInventario.verMenu();
                    break;

                default:
                    break;
            }
        } while (menu != 5);
    }
}
