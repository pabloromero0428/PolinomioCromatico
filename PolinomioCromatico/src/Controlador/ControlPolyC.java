package Controlador;

import Modelo.PolyC;
import Modelo.grafos.LinkedAdyListG;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import Modelo.Polynom;

public class ControlPolyC {

    PolyC polyC;
    LinkedAdyListG grafo;
    public Polynom polynom;

    public void costruirGrafo(File file) throws IOException {

        grafo = new LinkedAdyListG(file);
        // System.out.println(calcularPoly(grafo).writePoly());
        calcularPoly(grafo).writePoly();
        System.out.println(polynom.writePoly());
    }

    public Polynom calcularPoly(LinkedAdyListG g) {  //P(G)

        boolean continuar = true;
        // caso base grafo completo
        if (g.completo()) {
            continuar = false;
            polynom = g.calcularCompleto();
            return polynom;

        } //caso base grafo disperso
        if (g.isDisperso()) {
            continuar = false;
            polynom = g.calcularDisperso();
            return polynom;
        }

        if (continuar) {
            if (g.isDenso()) { //P(G) = P(G+e) + P(G/e)                
                Polynom p = calcularPoly(g.AgreararistaGrafo());
                Polynom q = (calcularPoly(g.fusionarArista(g.getActualv1(), g.getActualv2())));
                polynom = p.sumar(q);

            } else {//P(G) = P(G-e) - P(G/e)                        
                Polynom p = calcularPoly(g.quitararistaGrafo());
                Polynom q = (calcularPoly(g.fusionarArista(g.getActualv1(), g.getActualv2())));
                polynom = p.restar(q);
            }
        }
        return polynom;
    }

    public void guardarResultados() {
        try {
            long time_end;
            time_end = System.currentTimeMillis();
            //String polinomio = polynom.writePoly();
            FileWriter fw = new FileWriter("Registro Asegurados.txt", true); // abro el archivo de lectura
            PrintWriter pw = new PrintWriter(fw);   // abro el archivo de escritura
            //separo los campos dentro del archivo
            pw.println("T: " + time_end + " P(G,x): " + polynom.writePoly());
            //cierro los archivos de lectura y escritura
            pw.close();
            fw.close();
        } catch (IOException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }

    }

}
