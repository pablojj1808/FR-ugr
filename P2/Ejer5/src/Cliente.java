import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author pablojj
 */
public class Cliente {

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
            BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
            PrintWriter outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);

            // enviamos el codigo play para indicar que deseamos jugar y que se inicie el servicio
            outPrinter.println("preparado?: ");
            outPrinter.flush();

            // recibimos el mensaje de respuesta del servidor indicando que atiende nuestra peticion
            String recibido = inReader.readLine();
            //System.out.println(recibido);
            //el mensaje recibido debe ser comenzemos
            if (recibido.equals("yes")) {
                
                System.out.println("Preparados para empezar a jugar");
                
                // 1.imprimir las letras a jugar
                
                // 2. imprimir la pregunta
                
                // 3. captar y enviar respuesta
                
                
                // 4. imprimir resultado
                
                

            }
        } catch (IOException ex) {
            System.err.println("Error de entrada/salida al abrir el socket.");
        } 
    }
}