//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class YodafyClienteUDP {

    public static void main(String[] args) {

        byte [] buferEnvio = new byte[256];
        byte [] buferRecepcion = new byte[256];
        
        int port = 8989;

        DatagramSocket socket = null;
        DatagramSocket paquete = null;
        DatagramSocket paqueteModificado = null;
        InetAddress direccion = null;

        String fraseModificada


        // se crea el socket para la comunicacion mediante datagramas
        try {
            socket = DatagramSocket();
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al abrir el socket.");
        }

        // Obtenemos la dirección con la que se quiere realizar la comunicación
        try {
            direccion = InetAddress("localhost");
        } catch (UnknownHostException e) {
            System.err.println("Error al recuperar la direccion.");
        }

        buferEnvio = "Al monte del volcan debes ir sin demora".getBytes();

        // Se envía el datagramPacket con el mensaje original y se recibe 
        // uno con el mensaje yodificado.
        try {
            paquete = new DatagramPacket(buferEnvio, buferEnvio.length, direccion, port);
            socket.send(paquete);

            paqueteModificado = new DatagramPacket(buferRecepcion, buferRecepcion.length);
            socket.receive(paqueteModificado);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al abrir el socket.");
        }

        fraseYodificada = new String(paqueteModificado.getData());

        System.out.println("\nRecibido: " + fraseYodificada);
        
        socket.close();

    }
}
