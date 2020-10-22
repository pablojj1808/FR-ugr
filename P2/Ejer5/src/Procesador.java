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

    private Socket socketServicio;
    private int palabrasMostradas;

    private List definiciones;
    private List respuestas;

    Procesador(Socket socketServicio) {
        this.socketServicio = socketServicio;
        definiciones = new ArrayList<String>();
        respuestas = new ArrayList<String>();
        definiciones.add("A: naturaleza, alta, de color verde");
        respuestas.add("arbol");
        palabrasMostradas = 0;
    }

    @Override
    public void run() {
        try {

            // Obtiene los flujos de escritura/lectura
            BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
            PrintWriter outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);

            //recibimos le mensaje inicial que nos da el cliente, debera ser play 
            //para poder comenzar el juego
            String peticion = inReader.readLine();
            outPrinter.println(letrasAJugar());
            System.out.println("primera peticion = " + peticion);

            if (peticion.equals("yes")) {
                //mandamos el mensaje de comenzamos para confirmar que atendemos la peticion
                outPrinter.println("comenzemos");

                //bucle que solo acabara cuando se hayan respondido todas
                while (palabrasMostradas < 1) {
                    //imprimir el rosco
                    
                    // letra por la que vamos a jugar
                    char letra = 'a';

                    //mandamos el mensaje de la pregunta
                    outPrinter.println(letra + ": " + definiciones.get(0));

                    //leemos la respuesta
                    peticion = inReader.readLine();

                    //comprobacion de si la respuesta es correcta o pasapalabra
                    if (peticion.equals(respuestas.get(0))) {
                        palabrasMostradas++;
                        
                        /*
                        if (contar_completas == 25) {
                            outPrinter.println("FIN DEL JUEGO");
                        } else {
                            outPrinter.println("CORRECTO!!!!!!!!!!");
                        }*/
                    } else if (!peticion.equals("pasapalabra")) {
//                        contestadas.set(puntero, 2);
//                        contar_completas++;
//                        if (contar_completas == 25) {
//                            outPrinter.println("FIN DEL JUEGO");
//                        } else {
//                            outPrinter.println("OHHHHH ERRORR!! La respuesta correcta era " + respuestas.get(puntero));
//                        }

                    } else {
                        outPrinter.println("CONTINUAMOS CON LA SIGUIENTE");
                    }
                    

                }

            }
            

        } catch (IOException e) {
            System.err.println("Error al obtener los flujso de entrada/salida.");
        }
    }

    private String letrasAJugar() {
        return "[ a ]";
    }

}
