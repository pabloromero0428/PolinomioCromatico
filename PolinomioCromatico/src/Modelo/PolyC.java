package Modelo;
import modelo.Polynom;
import Modelo.grafos.LinkedAdyListG;
import java.awt.Component;
import java.io.File;
import java.util.LinkedList;
import javax.swing.JFileChooser;

public class PolyC extends Polynom{
    private LinkedAdyListG grafo;
         

    //Contructor del polinomio G
    public PolyC(){
        
        
    
    } 
    
    public Polynom calcularPoly(){
    
        
    return  null;
    }

    public void setGrafo(File file) {
        grafo = new  LinkedAdyListG(file);
    }
    
       
  
    
    
}
