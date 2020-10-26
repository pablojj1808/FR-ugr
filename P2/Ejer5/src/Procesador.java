
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            // sem, mantengo el hilo en un bucle hasta ue se cumpla confición.
            // paro el hilo hasta que se cumpla condición (var condition?)
            // notify() and notifyAll()
            // -> wait(), para sacar a un hilo del wait
            // desde el hilo principal 
            if (juego.getNumJugadores() != MAX_JUGADORES) {                
                try {
                    this.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Procesador.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.notifyAll();
            }
            
                do {
                    
                    hablarCliente(Color.YELLOW + juego.sigPregunta() + Color.RST);
                    
                    // lanzo contador, si se cumple condicion de tiempo ~currentTime~
                    // nada thread
                    String respuesta = inReader.readLine();
                    juego.registrarRespuesta(respuesta, jugActual);
                    System.out.println(juego.finalJuego());
                } while (!juego.finalJuego());
                hablarCliente("FIN DEL JUEGO");
            

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
