import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Apartado {
    private ArrayList<Productos> cuentaApartado;
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private Inventario objInventario;

    public Apartado() {
    }

    public Apartado(Inventario objInventario) {
        this.objInventario = objInventario;
    }

    public void crearCuentaPendiente(ArrayList<Productos> inventarioProductos) {
        cuentaApartado = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int decision = 0, opcion = 0, eliminar = 0, nuevaCantidad = 0, fechaLimiteAnticipo = 15, numCliente = 0,
                canProducto = 0;
        String nombreCliente = "";
        float cuentaTotal = 0, anticipo = 0, costoAnticipo = 0, saldoPendiente = 0;
        boolean bandera = false;

        do {
            System.out.println("Estos son los productos disponibles \n");
            for (int i = 0; i < objInventario.getListaProductos().size(); i++) {
                System.out.println(i + 1 + " )Nombre del producto:  "
                        + objInventario.getListaProductos().get(i).getnombreProducto());
            }
            System.out.println("\n --------Indique la posicion del articulo --------");
            while (bandera == false) {
                try {
                    decision = sc.nextInt();
                    while (decision <= 0 || decision > objInventario.getListaProductos().size()) {
                        System.out.print("Intente de nuevo: ");
                        decision = sc.nextInt();
                    }
                    decision--;
                    bandera = true;
                } catch (InputMismatchException e) {
                    System.out.print("Intente de nuevo: ");
                    sc.nextLine();
                }
            }
            bandera = false;

            System.out.println("\n --------Ingrese la cantidad del producto---------- \n");
            while (bandera == false) {
                try {
                    canProducto = sc.nextInt();
                    while (canProducto <= 0 ) {
                        System.out.print("Intente de nuevo: ");
                        canProducto = sc.nextInt();
                    }
                    bandera = true;
                } catch (Exception e) {
                    System.out.print("Intente de nuevo: ");
                    sc.next();
                }
            }
            bandera = true;
            Productos productoApartado = objInventario.getListaProductos().get(decision);
            productoApartado.setcantidadProducto(canProducto);
            cuentaApartado.add(productoApartado);
            System.out.println("\n -------Desea agregar otro articulo?-------- \n");
            System.out.println("1) Si   2) No");
            while (bandera == false) {
                try {
                    opcion = sc.nextInt();
                    while (opcion < 1 || opcion > 2) {
                        System.out.print("Intente de nuevo: ");
                        opcion = sc.nextInt();
                    }
                    bandera = true;
                } catch (InputMismatchException e) {
                    System.out.print("Intente de nuevo: ");
                    sc.next();
                }
            }
            bandera = false;

            System.out.println("\n ------Productos apartados: ----- \n ");
            for (int i = 0; i < cuentaApartado.size(); i++) {
                System.out.println(
                        cuentaApartado.get(i).getnombreProducto() + ": "
                                + cuentaApartado.get(i).cantidadProducto());
            }

        } while (opcion == 1);

        do {
            System.out.println("\n ----------Desea eliminar algun articulo? -----------\n");
            System.out.println("1) Si  2) NO");
            while (bandera== false) {
                try {
                    decision = sc.nextInt();
                    while (decision <1 || decision > 2) {
                        System.out.println("Intente de nuevo");
                        decision = sc.nextInt();
                    }
                    bandera = true;
                } catch (InputMismatchException e) {
                    System.out.print("Intente de nuevo: ");
                    sc.next();
                }
                
            }
            bandera = false;
            if (decision == 1) {
                System.out.println("\n----Estos son tus productos-----\n");
                for (int i = 0; i < cuentaApartado.size(); i++) {
                    System.out.println(i + 1 + ")Nombre del articulo: " + cuentaApartado.get(i).getnombreProducto());

                }
                
                System.out.println("\n ----Indique la posicion del articulo ----\n");
                while (bandera == false) {
                    try {
                        opcion = sc.nextInt();
                        while (opcion <=0 || opcion > cuentaApartado.size()) {
                            System.out.println("Intentelo de nuevo");
                            opcion = sc.nextInt();
                        }
                        bandera = true;
                    } catch (InputMismatchException e) {
                        System.out.println("intente de nuevo");
                        sc.next();
                    }
                }

                bandera = false;
                sc.nextLine();
                for (int i = 0; i < cuentaApartado.size(); i++) {
                    System.out.println("Estos son tus productos");
                    System.out.println("Nombre: " + cuentaApartado.get(i).getnombreProducto() + " Cantidad: " + cuentaApartado.get(i).getcantidadProducto());
                }
                System.out.println("\n -----Cuantos productos desea eliminar?----- \n");
                eliminar = sc.nextInt();
                nuevaCantidad = cuentaApartado.get((opcion - 1)).getcantidadProducto() - eliminar;
                cuentaApartado.get((opcion - 1)).setcantidadProducto(nuevaCantidad);

            }

        } while (decision == 1);
        for (int i = 0; i < cuentaApartado.size(); i++) {
            cuentaTotal += cuentaApartado.get(i).getcostoProducto() * cuentaApartado.get(i).getcantidadProducto();
        }

        anticipo = cuentaTotal * 0.3f;
        System.out.println("-----Cuenta final: ---- " + " $ " + cuentaTotal);
        sc.nextLine();

        System.out.println("-----Se requiere un anticipo del 30% ----");

        System.out.println("Esta es la cantidad del anticipo: $" + anticipo);
            while (bandera == false) {
                try {
                    System.out.print("Ingrese el anticipo: ");
                    costoAnticipo = sc.nextFloat();
                    if ( costoAnticipo < anticipo ) {
                        System.out.print("Ingrese un anticipo válido. El anticipo debe ser al menos el 30% del costo total: ");
                        costoAnticipo = sc.nextFloat();
                    }
                    bandera = true;
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                    sc.next();
                }
                
            }
            bandera = false;

        saldoPendiente = cuentaTotal - costoAnticipo;
        System.out.print("\nEste es el plazo de dias para poder pagar por completo  " + fechaLimiteAnticipo + " )Dias\n");
            System.out.println("-----Por favor ingrese el nombre del cliente para un mejor registro----");
            sc.nextLine();
            nombreCliente = sc.next();
        
        while (bandera == false) {
            try {
                System.out.println("------ Ingrese el número de teléfono del cliente ----:");
                numCliente = sc.nextInt();
                bandera = true;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número entero válido.");
                sc.next();
            }
            
        }

        Cliente cuentaCliente = new Cliente(nombreCliente, numCliente, cuentaApartado, saldoPendiente);
        listaClientes.add(cuentaCliente);

        System.out.println("---- Informe de Cuenta Pendiente ----");
        System.out.println("Nombre del Cliente: " + nombreCliente);
        System.out.println("Número de Teléfono del Cliente: " + numCliente);
        System.out.println("Anticipo ingresado: " + costoAnticipo);
        System.out.println("Anticipo minimo: " + anticipo);
        System.out.println("Costo Total: " + cuentaTotal);

        System.out.println("Saldo Pendiente: " + saldoPendiente);
        System.out.println("Fecha Límite de Anticipo: " + fechaLimiteAnticipo + "Dias");
        System.out.println("Productos:");
        for (Productos prod : cuentaApartado) {
            System.out.println("- Nombre: " + prod.getnombreProducto() + ", Cantidad: " +
                    prod.getcantidadProducto());
        }
        System.out.println("-------------------------------------");
    }

    public ArrayList<Cliente> getCuentaApartadoClientes() {
        return listaClientes;
    }

    

}
