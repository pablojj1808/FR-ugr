package tictoe;



// follow Singleton pattern
/*
* @author Pablo Jiménez Jiménez 
*         (pablojj1808@correo.ugr.es)
* @author Sergio Fernández Vela
*         (sergiofern@correo.ugr.es)
*/


public class Juego {

    private static Juego juego;
    private char[][] tablero;
    private static final char INICIO = '_';
    private static  char[] fichas;
    private static int jugActual = 0;
    private static boolean consumidoJug1 = false;
    
    static Juego getInstance() {
        if(juego == null) juego = new Juego();
        return juego;
    }
    
    public Juego() {
        fichas = new char[2];
        fichas[0] = 'X';
        fichas[1] = 'O';
        tablero = new char[3][3];
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                tablero[i][j] = INICIO;
    }
    
    public String pintarTab() {
        String tab = "";
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                tab += "|" + tablero[i][j];
            }
            tab+= "|\n";
        }

        return tab;
    }

    public char asignarFichas() {
        if(consumidoJug1) return fichas[1];
        else {
            consumidoJug1 = true;
            return fichas[0];
        }
    }

    public void putFicha(byte f, byte c) {
        tablero[f - 1][c - 1] = fichas[jugActual];
        cambiarJugador();
    }


    public void putFicha(String s) {
        putFicha(
                (byte) Integer.parseInt(Character.toString(s.toCharArray()[0])),
                (byte) Integer.parseInt(Character.toString(s.toCharArray()[1]))
        );
    }

    private void cambiarJugador() {
        jugActual++;
        jugActual = jugActual % 2;
    }

    public boolean algunGanador() {
        return filas() || cols() || diagonal1() || diagonal2();
    }

    private boolean filas() {
        boolean salida = false;
        int x = 0;
        int o = 0;
        for (int j = 0; j < 3; j++)
            if (tablero[j][j] == 'X')
                x++;
            else if (tablero[j][j] == 'O')
                o++;
        if(x == 3 || o == 3) salida = true;
        return salida;
    }

    private boolean cols() {
        boolean salida = false;
        int x = 0;
        int o = 0;
        for (int i = 2, j=0; i >=0; i--, j++) {
            if (tablero[i][j] == 'X')
                x++;
            else if (tablero[i][j] == 'O')
                o++;

        }
        if (x == 3 || o == 3) salida = true;
        return salida;
    }

    private boolean diagonal1() {
        boolean salida = false;
        int x = 0;
        int o = 0;
        for (int j = 0; j < 3; j++)
            if (tablero[j][j] == 'X')
                x++;
            else if (tablero[j][j] == 'O')
                o++;
        if (x == 3 || o==3)     salida = true;
        return salida;
    }

    private boolean diagonal2() {
        boolean salida = false;
        int x = 0;
        int o = 0;
        for (int i = 2, j=0; i >=0; i--, j++) {
            if (tablero[i][j] == 'X')
                x++;
            else if (tablero[i][j] == 'O')
                o++;
        }
        if (x == 3 || o==3)  salida = true;
        return salida;
    }
}
