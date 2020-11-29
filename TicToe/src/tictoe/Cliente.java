package tictoe;

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
 *
 * @author Pablo Jiménez Jiménez (pablojj1808@correo.ugr.es)
 * @author Sergio Fernández Vela (sergiofern@correo.ugr.es)
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
            String cond;
            
            //obtener los flujos en modo texto
            inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
            outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);

            leerServidor(); // mando la ficha
            do {
                
                leerTablero();
                leerServidor();
                String ficha = in.nextLine();
                mandarServidor(ficha);
                cond = leerServidorR(); // leo si fin
                System.out.println("he leido del supuesto final2: ." + cond + ".");

            } while (cond != "FIN_DEL_JUEGO");
            in.close();

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

    private static void leerTablero() throws IOException {
        System.out.println(inReader.readLine());
        System.out.println(inReader.readLine());
        System.out.println(inReader.readLine());
        System.out.println(inReader.readLine());
    }

    private static void mandarServidor(String s) {
        outPrinter.println(s);
        outPrinter.flush();
    }

    private static void leerServidor() throws IOException {
        System.out.println(inReader.readLine());
    }
    
    private static String leerServidorR() throws IOException {
        return inReader.readLine();
    }
}
