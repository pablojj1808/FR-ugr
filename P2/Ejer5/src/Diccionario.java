
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author pablojj
 */
public class Diccionario {
    
    private Map contenedor;
    private List letrasElegidas;

    public Diccionario() {
        contenedor = new HashMap<String, Preguntas>();
        contenedor.put("A", new Preguntas("Planta perenne, de tronco leñoso y elevado, que se ramifica a cierta altura del suelo.", "arbol"));
        contenedor.put("B", new Preguntas("Embarcación de estructura cóncava y, generalmente, de grandes dimensiones.", "barco"));
        contenedor.put("C", new Preguntas("Edificio para habitar.", "casa"));
        contenedor.put("D", new Preguntas("Moneda corriente.", "dinero"));
    }
    
    public List letrasSeleccionadas() {
        List salida = new ArrayList<String>();
        salida.add("A");
        salida.add("C");
        salida.add("D");
        return salida;
    }
    
    public String def(String letra) {
        Preguntas p = (Preguntas) contenedor.get(letra);
        return p.pregunta;
    }
    
    public String respuesta(String letra) {
        Preguntas p = (Preguntas) contenedor.get(letra);
        return p.respuesta;
    }
}
