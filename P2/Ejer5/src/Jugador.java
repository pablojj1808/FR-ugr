/**
 *
 * @author pablojj
 */
public class Jugador {
    
    private String nombre;
    private int acertadnombre;
    private int acertadas;
    private int num;

    public Jugador(String nombre, int acertadas, int num) {
        this.nombre = nombre;
        this.acertadas = acertadas;
        this.num = num;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAcertadas() {
        return acertadas;
    }

    public int getNum() {
        return num;
    }
    
    
}
