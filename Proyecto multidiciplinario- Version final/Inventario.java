import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Inventario {
    private ArrayList<Productos> listaProductos = new ArrayList<>();

    public Inventario() {
        listaProductos.add(new Productos("Labial", 100, "Avon", "sadas",
                15));

        listaProductos.add(new Productos("rubor", 15, "Avon", "sadas",
                20));

        listaProductos.add(new Productos("delineador", 55, "Avon", "sadas",
                6));

        listaProductos.add(new Productos("sombra", 45, "Avon", "sadas",
                4));

        listaProductos.add(new Productos("jabones", 15, "Avvo", "sadas",
                22));
    }

    public void verMenu() {
        Scanner sc = new Scanner(System.in);
        int desicion = 0;
        boolean bander = false;

        System.out.println("Que operacion desea realizar? \n");
        System.out.println("1) Ingresar articulos 2) Modificar articulos \n");
        System.out.println("3) Eliminar Productos 4) Ver Inventario\n");
        while (bander == false) {
            try {
                desicion = sc.nextInt();
                while (desicion <= 0 && desicion > 4) {
                    System.out.print("Intente de nuevo: ");
                    desicion = sc.nextInt();
                }
                bander = true;
            } catch (InputMismatchException e) {
                System.out.print("Intente de nuevo: ");
                sc.nextLine();
            }
        }
        bander = false;

        switch (desicion) {
            case 1:
                IngresarProductos();
                break;

            case 2:
                modificarListaProductos();
                break;

            case 3:
                eliminarProductos();
                break;

            case 4:
                imprimirListaProducto();
                break;

            default:
                break;
        }
    }

    public void IngresarProductos() throws InputMismatchException {
        Scanner sc = new Scanner(System.in);
        String nombreProducto, marcaProducto, descripcionProducto;
        float costoProductos = 0;
        int cantidadProductos = 0;
        int desicion = 0;
        boolean valida = false;

        do {
            System.out.println("\n----------Ingrese los datos del Producto-------");
            System.out.print("------Ingrese el nombre del producto: ");
            nombreProducto = sc.nextLine();

            System.out.print("Ingrese el costo del producto: ");
            while (valida == false) {
                try {
                    costoProductos = sc.nextFloat();
                    while (costoProductos <= 0) {
                        System.out.print("No es una cantidad valida, intente de nuevo: ");
                        costoProductos = sc.nextFloat();
                    }
                    valida = true;
                } catch (InputMismatchException e) {
                    System.out.print("Oh oh, a ingresado un caracter invalido intentelo de nuevo: ");
                    sc.nextLine();
                }
            }
            valida = false;
            sc.nextLine();

            System.out.print("Ingrese la cantidad de productos: ");
            while (valida == false) {
                try {
                    cantidadProductos = sc.nextInt();
                    while (cantidadProductos <= 0) {
                        System.out.print("No es una cantidad valida, Ingrese de nuevo: ");
                        cantidadProductos = sc.nextInt();
                    }
                    valida = true;
                } catch (InputMismatchException e) {
                    System.err.print("Oh no, ah ingresado un caracter invalido intente de nuevo: ");
                    sc.nextLine();
                }
            }
            valida = false;
            sc.nextLine();
            System.out.print("\nIngrese la marca del producto: ");
            marcaProducto = sc.nextLine();

            System.out.print("\nIngrese la descripcion del producto: ");
            descripcionProducto = sc.nextLine();
            System.out.println("---------------------");

            System.out.println("\n------Desea agregar otro producto?--------\n");
            System.out.println("1) SI      2) NO");
            System.out.print("Opcion: ");
            while (valida == false) {
                try {
                    desicion = sc.nextInt();
                    while (desicion <= 0 && desicion > 2) {
                        System.out.print("\nIntentelo de nuevo: ");
                        desicion = sc.nextInt();
                    }
                    valida = true;
                } catch (InputMismatchException e) {
                    System.err.print("\nIntentelo de nuevo: ");
                    sc.nextLine();
                }
            }
            valida = false;
            sc.nextLine();

            Productos producto = new Productos(nombreProducto, costoProductos, marcaProducto, descripcionProducto,
                    cantidadProductos);
            listaProductos.add(producto);

        } while (desicion != 2);
    }

    public void modificarListaProductos() {

        Scanner sc = new Scanner(System.in);
        String nuevoNombre, nuevaDescripcion;
        int nuevoPrecio, nuevaCantidad, modificar = 0, opcion = 0;
        boolean valida = false;

        System.out.println("\n---------Indique que articulo quiere modificar-------\n");
        for (int i = 0; i < listaProductos.size(); i++) {
            System.out.println(i + 1 + ")nombre del producto: " + listaProductos.get(i).getnombreProducto());
        }
        System.out.print("\nOpcion: ");
        while (valida == false) {
            try {
                opcion = sc.nextInt();
                while (opcion <= 0 && opcion > listaProductos.size()) {
                    System.out.print("\nNo existe elemento intente de nuevo: ");
                    opcion = sc.nextInt();

                }
                valida = true; // se necesita para validar esta instruccion
            } catch (InputMismatchException e) {
                System.err.print("\nA ingresado un caracter invalido intente de nuevo: ");
                sc.nextLine();
            }
        }
        valida = false;
        sc.nextLine(); // lo utilize por que tenia un bug el cual a veces se saltaba la sig parte de
                       // ingresar los datos

        System.out.println("\nDeseas modificar el nombre? 1.-si 2.-no");
        System.out.print("\nOpcion: ");
        // este ciclo sirve para las excepciones que accidentalmente ingrese el usuario
        while (valida == false) {
            // la excepcion como tal
            try {
                modificar = sc.nextInt();
                while (modificar !=1 && modificar !=2) {
                    System.out.print("Ingrese correctamente la opcion: ");
                    modificar = sc.nextInt();
                }
                if (modificar == 1) {
                    System.out.print("\n------Modificar nombre: ");
                    sc.nextLine();
                    nuevoNombre = sc.nextLine();
                    listaProductos.get(opcion - 1).setnombreProducto(nuevoNombre);
                    
                }
                valida = true;
            } catch (InputMismatchException e) {
                System.err.print("A ingresado un caracter, intente de nuevo: ");
                sc.nextLine(); // para las excepciones de ley de debe poner esta instruccion
            }
        }
        valida = false; // se necesita para que se pueda seguir utilizando esta variable con los ciclos
                        // while

        System.out.println("\n-----Deseas modificar el precio? 1.-si 2.-no");
        System.out.print("Opcion: ");
        while (valida == false) {
            try {
                modificar = sc.nextInt();
                while (modificar !=1 && modificar !=2) {
                    System.out.print("Ingrese correctamente la opcion: ");
                    System.out.println("\n-----Deseas modificar el precio? 1.-si 2.-no");
                    modificar = sc.nextInt();
                }
                if (modificar == 1) {
                    System.out.print("\n-- Modificar precio unitario: ");
                    nuevoPrecio = sc.nextInt();
                    while (nuevoPrecio<=0) {
                        System.out.print("\nIngrese una cantidad valida: ");
                        nuevoPrecio = sc.nextInt();
                    }
                    listaProductos.get(opcion - 1).setcostoProducto(nuevoPrecio);
                    
                }
                valida = true;
            } catch (InputMismatchException e) {
                System.out.print("\nA ingresado un caracter invalido, intente de nuevo: ");
                sc.nextLine();
            }
        }
        valida = false;
        System.out.println("\nDeseas modificar la cantidad ? 1.-si 2.-no");
        System.out.print("\nOpcion: ");

        while (valida == false) {
            try {
                modificar = sc.nextInt();
                while (modificar !=1 && modificar !=2) {
                    System.out.print("\nIngrese bien la opcion: ");
                    modificar = sc.nextInt();
                }
                if (modificar == 1) {
                    System.out.print("\nModificar cantidad: ");
                    nuevaCantidad = sc.nextInt();
                    System.out.println(nuevaCantidad);
                    listaProductos.get(opcion - 1).setcantidadProducto(nuevaCantidad);
                }
                valida = true;
            } catch (Exception e) {
                System.err.print("\nA ingresado un caracter invalido, intente de nuevo: ");
                sc.nextLine();
            }
        }

        valida = false;
        System.out.println("Deseas modificar la descripcion? 1.-si 2.-no");
        System.out.print("Opcion: ");
        while (valida == false) {
            try {
                modificar = sc.nextInt();
                while (modificar !=1 && modificar !=2) {
                    System.out.print("\nIngrese bien la opcion: ");
                    modificar = sc.nextInt();
                }
                if (modificar == 1) {
                    System.out.print(" Modificar la descripcion: ");
                    sc.nextLine();
                    nuevaDescripcion = sc.nextLine();
                    listaProductos.get(opcion - 1).setdescripcionProducto(nuevaDescripcion);
                }
                valida = true;
            } catch (InputMismatchException e) {
                System.err.print("\nA ingresado un caracter invalido, intente de nuevo: ");
                sc.nextLine();
            }
        }

    }

    public void imprimirListaProducto() {
        System.out.println("\n--------Este es el inventario actual---------\n");
        for (int i = 0; i < listaProductos.size(); i++) {
            System.out.println("  ");
            System.out.println(i + 1 + ")nombre del producto: " + listaProductos.get(i).getnombreProducto() + "  "
                    + "Marca del producto): " + listaProductos.get(i).getmarcaProducto() + " Costo unitario): " + "  $ "
                    + listaProductos.get(i).getcostoProducto() + "  " + " Cantidad disponible): "
                    + listaProductos.get(i).getcantidadProducto() + "  " + "Descripcion del producto):"
                    + listaProductos.get(i).getdescripcionProducto());
            System.out.println(" ");
        }
        System.out.println("--------------------------");
    }

    public void eliminarProductos() {
        int opcion = 0;
        boolean validar = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n-----------Indique que articulo quiere eliminar----------------\n");
        System.out.print("Opcion: ");
        for (int i = 0; i < listaProductos.size(); i++) {
            System.out.println(i + 1 + ")nombre del producto: " + listaProductos.get(i).getnombreProducto());
        }
        System.out.print("\nOpcion: ");
        while (validar == false) {
            try {
                opcion = sc.nextInt();
                while (opcion <= 0 && opcion > listaProductos.size()) {
                    System.out.print("\nNo existe elemento intente de nuevo: ");
                    opcion = sc.nextInt();
                }
                validar = true;
            } catch (InputMismatchException e) {
                System.out.print("\nA ingresado un caracter invalido, intente de nuevo: \n");
                sc.nextLine();
            }
        }
        validar = false;
        listaProductos.remove(opcion - 1);

    }

    public ArrayList<Productos> getListaProductos() {
        return listaProductos;
    }

}
