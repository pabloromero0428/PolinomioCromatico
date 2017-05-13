package Modelo;
import Modelo.grafos.LinkedAdyListG;
import java.awt.Component;
import java.io.File;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import modelo.Polynom;

public class PolyC{
    private LinkedAdyListG grafo;
    private Polynom polynom;         

    //Contructor del polinomio G
    public PolyC(){
        
        
    
    } 
    //prueba
    public Polynom calcularPoly(){
  
       return  (new Polynom());
    }

    public void setGrafo(File file) {
        grafo = new  LinkedAdyListG(file);
    }
    
       
  
    
    
}
