
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Implementación de un servidor concurrente
 *
 * @author Pablo Jiménez Jiménez pablojj1808@correo.ugr.es
 */
public class ServidorConcurrente {
   
    public static void main(String[] args) {

        Socket socketCliente;
        ServerSocket serverSocket;
        final int port = 9909;

        try {
            serverSocket = new ServerSocket(port);

            do {
                // Aceptamos conexiones de los clientes
                socketCliente = serverSocket.accept();
                System.out.println("Cliente conectado");
                
                
                // Creamos un objeto de la clase ProcesadorPasa, pasándole como 
                // argumento el nuevo socket, para que realice el procesamiento
                // Este esquema permite que se puedan usar hebras más fácilmente.
                Procesador procesador = new Procesador(socketCliente);
                procesador.start();

            } while (true);

//            socketCliente.close();
//            serverSocket.close();
            
        } catch (IOException ex) {
            System.err.println("No se ha podido conectar en el puerto = " + port);
        }

    }
}
