
package Modelo.grafos;
import Modelo.Archivo;
import Modelo.Archivo;
import java.io.File;
import Modelo.Listas.SimpleNode;

public class LinkedAdyListG {
    private int [] vec; // es un vector en teoría, pero si es un grafo con mucjhas aristas no se si sea bueno trabajar con un vector
    private SimpleNode v;  // agregué la listas simples
    private Archivo archivo;
    private File file;
    
    public LinkedAdyListG(){// constructor
                 
    
    }
    
    public void setFile(){ // configura el archivo dfe donde se va a sacar el grafo
        file= archivo.getFile();  
    }  
}
