package Controlador;
import Modelo.PolyC;
import Modelo.grafos.LinkedAdyListG;
import java.io.File;
import modelo.Polynom;

public class ControlPolyC {
    PolyC  polyC;
    LinkedAdyListG grafo;
    Polynom polynom;
    
    public void costruirGrafo(File file) {
        
        polyC = new PolyC();
        polyC.setGrafo(file);       
   
    }

    public void calcularPolinomioCromatico() {
        
        //polynom = polyC.calcularPoly();
        
    
    }

    public void guardarResultados(String path) {
  
    
    }

    public void calcularCPU() {

    }
    
}
