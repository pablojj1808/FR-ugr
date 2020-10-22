
import java.util.ArrayList;
import java.util.List;


// CLase singleton
public class Juego {

    private List<Jugador> jugadores;
    private int jugadorActual; //Numero a adivinar
    private Diccionario dico;
    private boolean finalJuego;
    private int pasos;

    private static Juego juego = null;
    
    private void aumentarPasos() {
        if(pasos == 2) finalJuego= true;
        pasos = 0;
    }

    public Juego() {
        jugadorActual = 0;
        jugadores = new ArrayList<>();
        finalJuego = false;
        pasos = 0;
        dico = new Diccionario();
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
    
    public int getNumJugadores() {
        return jugadores.size();
    }
    
    public boolean finalJuego() {
        return finalJuego;
    }
    
    public List informarLetras() {
        return dico.letrasSeleccionadas();
    }
    
    public String sigPregunta() {
        String algo = dico.def((String)dico.letrasSeleccionadas().get(pasos));
        aumentarPasos();
        return algo;
    }
}
