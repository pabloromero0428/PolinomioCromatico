
package Modelo;
import Modelo.Archivo;
import java.io.File;

public class LinkedAdyListG {
    private int [] vec; // es un vector en teoría, pero si es un grafo con mucjhas aristas no se si sea bueno trabajar con un vector
    private DoubleNode v;  // hay que implementar la lista simple??
    private Archivo archivo;
    private File file;
    
    public LinkedAdyListG(){// constructor
                 
    
    }
    
    public void setFile(){ //obtiene el archivo donde está el grafo
        file= archivo.getFile();  
    }  
}
