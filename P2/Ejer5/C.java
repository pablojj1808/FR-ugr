
/**
 * Clase para imprimir colores por la consola de
 * sistemas UNIX
 *
 * @author pablojj
 */
public class C {
    
    public static  String black(String x) {
        return BLACK + x + RST;
    }
    public static final String BLACK = "\u001B[31m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN  = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String RST  = "\\x1B[0m";
    
    public static final String BOLD = "\\x1B[1m";
    public static final String UND  = "\\x1B[4m";
    public static final String ITAL ="\\x1B[3m";
}
