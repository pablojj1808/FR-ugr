//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.*;
import java.net.*;
 
public class YodafyClienteTCP {

	public static void main(String[] args) {
		
		byte []buferEnvio="Al monte del volcan debes ir sin demora".getBytes();
		byte []buferRecepcion=new byte[1024];
		
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8888;
		
		try {

			//	Creacion del socket udp 
			//
			DatagramSocket socketUDPcliente=null;	
			try {
				socketUDPcliente = new DatagramSocket();
			} catch (IOException e) {
				System.out.println("Error: no se pudo abrir el socket");
			}

			// Creacion del paquete udp de envio con la
			// direccion host y puerto port y el propio envio.

			InetAddress direccion;
			DatagramPacket paquete;
			direccion = InetAddress.getByName(host);
			paquete = new DatagramPacket(buferEnvio, buferEnvio.length, direccion, port);
			socketUDPcliente.send(paquete);

			// Recepcion de la respuesta del servidor

			DatagramPacket respuesta = new DatagramPacket(buferRecepcion, buferRecepcion.length);
			socketUDPcliente.receive(respuesta);
			buferRecepcion=respuesta.getData();
			String peticion=new String(buferRecepcion);

			System.out.println("Recibido: " + peticion); 
			socketUDPcliente.close();
			
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} 
	}
}
