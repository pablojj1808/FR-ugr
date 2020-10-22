
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
        final int port = 9999;

        Socket socketServicio = null;
        Scanner in = new Scanner(System.in);

        try {
            // Creamos una conexion entre el cliente y el servidor
            socketServicio = new Socket(host, port);

            //obtener los flujos en modo texto
            inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
            outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);

            //System.out.println("preparado?: ");
            // recibimos el mensaje de respuesta del servidor indicando que atiende nuestra peticion
            //String rptas = in.nextLine();
            //System.out.println(recibido);
            //el mensaje recibido debe ser comenzemos
            System.out.println(inReader.readLine());
            System.out.println(inReader.readLine());
            System.out.println(inReader.readLine());
            String recibido = in.nextLine();
            mandarServidor(recibido);
            
            while(!recibido.equals("FIN_JUEGO")) {
                System.out.println(inReader.readLine());
                recibido = in.nextLine();
                mandarServidor(recibido);
            }
//
//            // enviamos el codigo play para indicar que deseamos jugar y que se inicie el servicio
//            outPrinter.println(respuesta);
//            outPrinter.flush();
            //System.out.println(recibido);
            //el mensaje recibido debe ser comenzemos
            //if (recibido.equals("yes")) {
            //  System.out.println("Preparados para empezar a jugar");
            // 1.imprimir las letras a jugar
            // 2. imprimir la pregunta
            // 3. captar y enviar respuesta
            // 4. imprimir resultado
        } catch (UnknownError ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.err.println("Error de entrada/salida al abrir el socket.");
        }

    }

    private static void mandarServidor(String s) {
        outPrinter.println(s);
        outPrinter.flush();
    }
}
