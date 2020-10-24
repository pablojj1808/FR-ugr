
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author pablojj
 */
class Procesador extends Thread {
    
    private Juego juego;
    private Socket socketCliente;
    
    BufferedReader inReader = null;
    PrintWriter outPrinter = null;
    
    private static boolean fin_del_juego;
    private static int numJugadores = 0;
    private static final int MAX_JUGADORES = 2;
    private int jugActual;
    
    Procesador(Socket socketServicio) {
        this.socketCliente = socketServicio;
        this.juego = Juego.getInstanceJuego();
        fin_del_juego = false;
        numJugadores++;
        jugActual = numJugadores; // se empieza a contar en 1
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Hebra " + jugActual + " lanzada");

            // Obtiene los flujos de escritura/lectura
            inReader = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            outPrinter = new PrintWriter(socketCliente.getOutputStream(), true);
            
            hablarCliente("Introduce tu nombre cuando estes listo: ");
            String recibido = inReader.readLine();
            juego.aniadirJugador(recibido);
            
            hablarCliente(juego.informarLetras().toString());
            
            if (juego.getNumJugadores() == MAX_JUGADORES) {                
                
                do {
                    
                    hablarCliente(juego.sigPregunta());
                    String respuesta = inReader.readLine();
                    juego.registrarRespuesta(respuesta, jugActual);
                    System.out.println(juego.finalJuego());
                } while (!juego.finalJuego());
                hablarCliente("FIN DEL JUEGO");
            }

            // Vamos a imprimir resultados
            hablarCliente("Resultados del juego:\n\t" + juego.getResultados());
            
            socketCliente.close();
        } catch (IOException e) {
            System.err.println("Error al obtener los flujso de entrada/salida.");
        }
    }
    
    private void hablarCliente(String s) {
        outPrinter.println(s);
        outPrinter.flush();
    }
    
}
