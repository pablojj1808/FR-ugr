
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablojj
 */
public class Cliente {

    private static BufferedReader inReader;
    private static PrintWriter outPrinter;

    public static void main(String[] args) {
        // Nombre del host donde se ejecuta el servidor y puerto por donde 
        // escucha
        String host = "localhost";
        final int port = 9909;

        Socket socketServicio = null;
        Scanner in = new Scanner(System.in);

        try {
            // Creamos una conexion entre el cliente y el servidor
            socketServicio = new Socket(host, port);

            //obtener los flujos en modo texto
            inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
            outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);
            
            
            
        

                System.out.println(inReader.readLine());

                leerTablero();

            
            
            
            
            
        } catch (UnknownError ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.err.println("Error de entrada/salida al abrir el socket.");
        } finally {
            try {
                socketServicio.close();
            } catch (IOException ex) {
                System.err.println("Fallo al cerrar socket.");
            }
        }

    }

    private static void leerTablero() throws Exception {
        System.out.println(inReader.readLine());
        System.out.println(inReader.readLine());
        System.out.println(inReader.readLine());
        System.out.println(inReader.readLine());
        System.out.println(inReader.readLine());
    }

    private static void mandarServidor(String s) {
        outPrinter.println(s);
        outPrinter.flush();
    }
}
