//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.*;
import java.net.*;
import java.util.Random;


//ProcesadorYodafy
public class ProcesadorYodafy {
	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private DatagramSocket socketUDPservidor;
	
	// Para que la respuesta sea siempre diferente, usamos un generador de números aleatorios.
	private Random random;
	
	// Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public ProcesadorYodafy(DatagramSocket socketServicio) {
		this.socketUDPservidor=socketServicio;
		random=new Random();
	}
	
	
	// Aquí es donde se realiza el procesamiento realmente:
	void procesa(){
		int port;
		InetAddress direccion;

		// Como máximo leeremos un bloque de 1024 bytes. Esto se puede modificar.
		byte [] datosRecibidos=new byte[1024];
		
		// Array de bytes para enviar la respuesta. Podemos reservar memoria cuando vayamos a enviarka:
		byte [] datosEnviar;
    
			// Recepcion del paquete enviado por el cliente 

			DatagramPacket paquete;
			paquete = new DatagramPacket(datosRecibidos, datosRecibidos.length);
			socketUDPservidor.receive(paquete);
			datosRecibidos=paquete.getData();
			direccion=paquete.getAddress();
			port=paquete.getPort();

			// Yoda hace su magia:
			// Creamos un String a partir de un array de bytes de tamaño "bytesRecibidos":
			String peticion=new String(datosRecibidos);
			// Yoda reinterpreta el mensaje:
			String respuesta=yodaDo(peticion);
			System.out.print(respuesta+"\n");
			// Convertimos el String de respuesta en una array de bytes:
			datosEnviar=respuesta.getBytes();

			// Envio a partir de los datos recibidos del cliente de la respuesta

			paquete = new DatagramPacket(datosEnviar, datosEnviar.length, direccion, port);
			socketUDPservidor.send(paquete);
			socketUDPservidor.close();

	}

	// Yoda interpreta una frase y la devuelve en su "dialecto":
	private String yodaDo(String peticion) {
		// Desordenamos las palabras:
		String[] s = peticion.split(" ");
		String resultado="";
		
		for(int i=0;i<s.length;i++){
			int j=random.nextInt(s.length);
			int k=random.nextInt(s.length);
			String tmp=s[j];
			
			s[j]=s[k];
			s[k]=tmp;
		}
		
		resultado=s[0];
		for(int i=1;i<s.length;i++){
		  resultado+=" "+s[i];
		}
		
		return resultado;
	}
}
