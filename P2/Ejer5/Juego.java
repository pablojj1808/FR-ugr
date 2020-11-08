

// CLase singleton
public class Juego {

    private static Juego juego;
    private char[][] tablero;
    private static final char INICIO = ' ';
    
    static Juego getInstance() {
        if(juego == null) juego = new Juego();
        return juego;
    }
    
    public Juego() {
        tablero = new char[3][3];
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                tablero[i][j] = INICIO;
    }
    
    public String pintarTab() {
        String tab = "|  |  |  |\n"
                   + "----------\n"
                   + "|  |  |  |\n"
                   + "----------\n"
                   + "|  |  |  |\n";
        return tab;
    }
    
    public char getJugada(byte f, byte c) {
        return tablero[f][c];
    }
    
}
