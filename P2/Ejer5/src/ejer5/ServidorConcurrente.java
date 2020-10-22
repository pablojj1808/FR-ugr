package ejer5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementación de un servidor concurrente
 *
 * @author Pablo Jiménez Jiménez pablojj1808@correo.ugr.es
 */
public class ServidorConcurrente {

    public static void main(String[] args) {

        ServerSocket serverSocket;
        final int port = 9999;

        try {
            serverSocket = new ServerSocket(port);
            do {
                // Aceptamos conexiones al servidor.
                Socket socketServicio = serverSocket.accept();
                // Creamos un objeto de la clase ProcesadorPasa, pasándole como 
                // argumento el nuevo socket, para que realice el procesamiento
                // Este esquema permite que se puedan usar hebras más fácilmente.
                Procesador procesador = new Procesador(socketServicio);
                procesador.start();
                
            } while (true);

        } catch (IOException ex) {
            System.err.println("No se ha podido conectar en el puerto = " + port);
        }

    }
}
