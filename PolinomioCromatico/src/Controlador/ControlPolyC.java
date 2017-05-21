package Controlador;

import Modelo.PolyC;
import Modelo.grafos.LinkedAdyListG;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import Modelo.Polynom;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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

    public void guardarResultados(String path) {
        try {
            long time_end;
            time_end = System.currentTimeMillis();
            //String polinomio = polynom.writePoly();
            
            File f = new File("ResultadosColoraciónGrafos(JPs).txt");
            FileWriter fw = new FileWriter(f, true); // abro el archivo de lectura
            PrintWriter pw = new PrintWriter(fw);   // abro el archivo de escritura
            //separo los campos dentro del archivo
            pw.println("T: " + time_end + " P(G,x): " + polynom.writePoly());
            //cierro los archivos de lectura y escritura
            pw.close();
            fw.close();
            fileMove(f.getPath(), path);          
            
        } catch (IOException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
        }
    }
    
    public static void fileMove(String sourceFile, String destinationFile) {
     try {
        File inFile = new File(sourceFile);
        File outFile = new File(destinationFile);

        FileInputStream in = new FileInputStream(inFile);
        FileOutputStream out = new FileOutputStream(outFile);

            int c;
                while ((c = in.read()) != -1)
                    out.write(c);
                    in.close();
                    out.close();

            File file = new File(sourceFile);
                if (file.exists()) {
                    file.delete();
            }

        } catch (IOException e) {System.err.println("Hubo un error de entrada/salida!!!");}
}

}
