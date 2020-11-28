package tictoe;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Pablo Jiménez Jiménez 
 *         (pablojj1808@correo.ugr.es)
 * @author Sergio Fernández Vela
 *         (sergiofern@correo.ugr.es)
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
            hablarCliente("Tu ficha: " + juego.asignarFichas());
            hablarCliente(juego.pintarTab());
            hablarCliente("Introduce tu jugada (1-3,1-3): ");
            var algo = inReader.readLine();
            juego.putFicha(algo);
            hablarCliente(juego.pintarTab());
            
            socketCliente.close();
        } catch (IOException e) {
            System.err.println("Error al obtener los flujso de entrada/salida.");
        } 
    }

    private void hablarCliente(String s) {
        outPrinter.println(s);
        outPrinter.flush();
    }

}
