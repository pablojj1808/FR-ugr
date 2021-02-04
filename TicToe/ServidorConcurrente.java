import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Implementación de un servidor concurrente
 *
 * @author Pablo Jiménez Jiménez 
 *         (pablojj1808@correo.ugr.es)
 * @author Sergio Fernández Vela
 *         (sergiofern@correo.ugr.es)
 */

public class ServidorConcurrente {

    private static final int MAX_CONEX = 2;
    private static int conexiones = 0;
    
                                            
    public static void main(String[] args) {

        Socket socketCliente;
        ServerSocket serverSocket;
        final int port = 9909;

        try {
            serverSocket = new ServerSocket(port);

            do {
                // Aceptamos conexiones de los clientes
                if(conexiones != MAX_CONEX) {
                    socketCliente = serverSocket.accept();
                    System.out.println("Cliente conectado");
                    conexiones++;
                    
                    // Creamos un objeto de la clase ProcesadorPasa, pasándole como 
                    // argumento el nuevo socket, para que realice el procesamiento
                    // Este esquema permite que se puedan usar hebras más fácilmente.
                    Procesador procesador = new Procesador(socketCliente);
                    procesador.start();
                }
            } while (!Juego.getInstance().algunGanador() && !Juego.getInstance().empate());
            
           serverSocket.close();
           if(Juego.getInstance().empate()) System.out.println("El juego ha finalizado con un empate.");
           if(Juego.getInstance().algunGanador()) System.out.println("El juego ha finalizado");
            
        } catch (IOException ex) {
            System.err.println("No se ha podido conectar en el puerto = " + port);
        }
    }
}
