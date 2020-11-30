package tictoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Pablo Jiménez Jiménez (pablojj1808@correo.ugr.es)
 * @author Sergio Fernández Vela (sergiofern@correo.ugr.es)
 */
class Procesador extends Thread {

    private static Juego juego;
    private Socket socketCliente;
    private static char fichaActual = 'X';
    // private static String condicion = "NADA";
    private ReentrantLock lock = new ReentrantLock();
    private static boolean fin = false;

    BufferedReader inReader = null;
    PrintWriter outPrinter = null;

    Procesador(Socket socketServicio) {
        this.socketCliente = socketServicio;
        System.out.println(socketServicio.getInetAddress());
        juego = Juego.getInstance();
    }

    @Override
    public void run() {
        try {

            // Obtiene los flujos de escritura/lectura
            inReader = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            outPrinter = new PrintWriter(socketCliente.getOutputStream(), true);
            char ficha = juego.asignarFichas();
            hablarCliente("Tu ficha: " + ficha);
            System.out.println("HA PASADO LA ASIGNACION\n" + fichaActual);
            do {
                try 
                {
                    Thread.sleep(1000);
                } 
                catch(InterruptedException e)
                {
                    System.out.println("ERROR: Thread sleep " + e);
                }
                // System.out.println(ficha + "----" + fichaActual);
                if (ficha == fichaActual) {
                    System.out.println("HA PASADO EL LOCK\n");
                    lock.lock();
                    try {
                        if (juego.algunGanador()) {
                            System.out.println("FIIIIIN DEL JUEGOOOO");
                            hablarCliente("FIN");
                            fin = true;
                        } else {
                            System.out.println(">tenfo que seguir<");
                            hablarCliente("_SIGO_");
                        }
                        if(!fin){
                            hablarCliente(juego.pintarTab());
                            hablarCliente("Introduce tu jugada (1-3,1-3): \n");
                            System.out.println("ESTA ESPERANDO A LA RESPUESTA\n");
                            String algo = inReader.readLine();
                            System.out.println("LA HA RECIBIDO\n");
                            juego.putFicha(algo);

                            System.out.println("Condiciones de victoria:\n");
                            System.out.println("Diagonal1:" + juego.diagonal1() + " Diagonal2:" + juego.diagonal2() + " Filas:" + juego.filas() + " Columnas:" + juego.cols() + "\n");
                        }

                    } finally {
                        lock.unlock();
                        // cambio asignacion de fichas
                        if (fichaActual == 'X') {
                            fichaActual = 'O';
                        } else {
                            fichaActual = 'X';
                        }
                    }
                    
                }

            } while (!fin);

            socketCliente.close();

        } catch (IOException e) {
            System.err.println("Error al obtener los flujos de entrada/salida.");
        }
    }

    private void hablarCliente(String s) {
        outPrinter.println(s);
        outPrinter.flush();
    }

}
