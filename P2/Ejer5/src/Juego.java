
import java.util.ArrayList;
import java.util.List;


// CLase singleton
public class Juego {

    private List<Jugador> jugadores;
    private int jugadorActual; //Numero a adivinar
    private Diccionario dico;

    private static Juego juego = null;
    private static int numJugadoresCreados = 0;

    public Juego() {
        jugadorActual = 0;
        jugadores = new ArrayList<>();
    }
    
    public void aniadirJugador(String nombre) {
        jugadores.add(new Jugador(nombre, 0, jugadores.size()));
    }

    public static Juego getInstanceJuego() {
        if (juego == null) {
            juego = new Juego();
        }
        
        return juego;
    }
    
    public int getJugadorActual() {
        return jugadorActual;
    }
    
    public void cambiarJugador() {
        if(jugadorActual > jugadores.size()) {
            jugadorActual = jugadorActual % jugadores.size();
        } else {
            jugadorActual++;
        }
    }
    
}
