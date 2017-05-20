package Controlador;
import Modelo.PolyC;
import Modelo.grafos.LinkedAdyListG;
import java.io.File;
import java.io.IOException;
import modelo.Polynom;



public class ControlPolyC {
    PolyC  polyC;
    LinkedAdyListG grafo;
    Polynom polynom;
    
    public void costruirGrafo(File file) throws IOException {
        
       grafo = new LinkedAdyListG(file);
              
   
    }

    public void calcularPolinomioCromatico() {
        
        polynom = polyC.calcularPoly(grafo);
        
    
    }

    public void guardarResultados(String path) {
  
    
    }

    public void calcularCPU() {

    }
    
}