import java.util.ArrayList;

public class Cliente {
    private String nombreCliente;
    private int numTelefono;
    private ArrayList<Productos> apartados;
    private float saldoPendiente;

    public Cliente(String nombreCliente, int numTelefono, ArrayList<Productos> apartados, float saldoPendiente) {
        this.nombreCliente = nombreCliente;
        this.numTelefono = numTelefono;
        this.apartados = apartados;
        this.saldoPendiente = saldoPendiente;
    }

    public  Cliente() {
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNumTelefono(int numTelefono) {
        this.numTelefono = numTelefono;
    }
    public int getNumTelefono() {
        return numTelefono;
    }
    public void setApartados(ArrayList<Productos> apartados) {
       this.apartados = apartados;
    }
    public ArrayList<Productos> getaApartados() {
        return apartados;
    }
    public float getSaldoPendiente() {
        return saldoPendiente;
    }
    public void setSaldoPendiente(float saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }



}
