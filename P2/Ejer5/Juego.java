

// CLase singleton
public class Juego {

    private static Juego juego;
    private char[][] tablero;
    private static final char INICIO = ' ';
    private static final char JUG1 = 'X';
    private static final char JUG2 = 'O';
    private static boolean consumidoJug1 = false;
    
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
    
    public char asignarFichas() {
        if(consumidoJug1) return JUG2;
        else {
            consumidoJug1 = true;
            return JUG1;
        }
    }
}
