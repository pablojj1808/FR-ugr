
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
    private static numJugadores = 0;
    private static final int MAX_JUGADORES = 1;

    Procesador(Socket socketServicio) {
        this.socketCliente = socketServicio;
        definiciones = new ArrayList<String>();
        respuestas = new ArrayList<String>();
        definiciones.add("A: naturaleza, alta, de color verde");
        respuestas.add("arbol");
        palabrasMostradas = 0;
        pasada = 0;
        correctas = 0;
        fin_del_juego = false;
        numJugadores++;
    }

    @Override
    public void run() {
        try {

            // Obtiene los flujos de escritura/lectura
            inReader = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            outPrinter = new PrintWriter(socketCliente.getOutputStream(), true);

            if(numJugadores == MAX_JUGADORES){  
                System.out.println("¡Bienvenido " + user + " eres " + juego.getJugadorActual() + "!");
                outPrint.println(Integer.toString(juego.getJugadorActualNumero()));
                juego.cambiarJugador();
            }else{
                System.out.println("¡ERROR AL CONECTAR, MAXIMO NUMERO DE JUGADORES: " + juego.getNumMaxJugadores() + "!");
                outPrint.println("maximo");
                socketServicio.close();
            }

            //recibimos le mensaje inicial que nos da el cliente, debera ser play 
            //para poder comenzar el juego
            hablarCliente("Jugaremos con estas letras...");
            ArrayList<String> letras = (ArrayList) dico.letrasSeleccionadas();
            hablarCliente(letras.toString());
            hablarCliente("Preparado?: ");
            String respuesta = inReader.readLine();
            if (respuesta.equals("y")) {
                    
                while (!fin_del_juego) {
                    
                    hablarCliente(dico.def(letras.get(pasada)));
                    respuesta = inReader.readLine();
                    if (respuesta.equals(dico.respuesta(letras.get(pasada)))) {
                        correctas++;
                    }
                    pasada++;
                    if(pasada == 2) fin_del_juego = true;
                }
                
                hablarCliente("FIN_JUEGO");
            }

            
            hablarCliente("\t Has acertado " + pasada + " preguntas");
            System.out.println("terminado");
            
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
