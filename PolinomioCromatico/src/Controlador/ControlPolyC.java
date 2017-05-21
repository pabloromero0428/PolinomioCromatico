package Controlador;
import Modelo.PolyC;
import Modelo.grafos.LinkedAdyListG;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import Modelo.Polynom;

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
      try {
            long time_start, time_end, time_total;
            time_start = System.currentTimeMillis();         
            time_end = System.currentTimeMillis();
            time_total = time_end - time_start;           
            FileWriter fw = new FileWriter("Registro Asegurados.txt", true); // abro el archivo de lectura
            PrintWriter pw = new PrintWriter(fw);   // abro el archivo de escritura
            //separo los campos dentro del archivo
            pw.println("T: "+ time_total + " P(G,x): " + polynom);
            //cierro los archivos de lectura y escritura

            pw.close();
            fw.close();
        } catch (IOException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
    
    }

    public void calcularCPU() {

    }
    
}