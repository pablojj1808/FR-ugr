
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author pablojj
 */
class Procesador extends Thread {

    private static Juego juego;
    private Socket socketCliente;

    BufferedReader inReader = null;
    PrintWriter outPrinter = null;
    
     
    Procesador(Socket socketServicio) {
        this.socketCliente = socketServicio;
        juego =Juego.getInstance();
    }

    @Override
    public void run() {
        try {

            // Obtiene los flujos de escritura/lectura
            inReader = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            outPrinter = new PrintWriter(socketCliente.getOutputStream(), true);

            hablarCliente(juego.pintarTab());
            
            
            socketCliente.close();
        } catch (IOException e) {
            System.err.println("Error al obtener los flujso de entrada/salida.");
        } 
    }

    private void hablarCliente(String s) {
        System.out.println(s);
        outPrinter.println(s);
        outPrinter.flush();
    }

}
