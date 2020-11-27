package tictoe;



// CLase singleton
public class Juego {

    private static Juego juego;
    private char[][] tablero;
    private static final char INICIO = '_';
    private static final char JUG1 = 'X';
    private static final char JUG2 = 'O';
    private static boolean consumidoJug1 = false;
    
    static Juego getInstance() {
        if(juego == null) juego = new Juego();
        return juego;
    }
    
    public Juego() {
        tablero = new char[4][4];
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++)
                tablero[i][j] = INICIO;
    }
    
    public String pintarTab() {
        /*String tab = """
                    |_|_|_|_|
                    |_|_|_|_|
                    |_|_|_|_|
                    |_|_|_|_|
                    """;*/
        String tab = "";
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++)
                tab += "|" + tablero[i][j] + "|" + "|" + tablero[i][j] + "|" + "|" + tablero[i][j] + "|" + "|" + tablero[i][j] + "|";
                   
        System.out.println(tab);
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
