package Modelo;

import Modelo.grafos.LinkedAdyListG;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;

public class Pruebas {
    
    
    public static void main(String[] args) throws IOException {

        JFrame j = new JFrame();
        JFrame.setDefaultLookAndFeelDecorated(true);
        j.setLayout(new FlowLayout());
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Archivo a = new Archivo();
        a.setFile(j);
        
        File f = a.getFile();
        LinkedAdyListG l = new LinkedAdyListG(f);
        l.completo();
        System.out.println("--------------");
        l.imprimirGrafo();       
        System.out.println("-------");
      

    }

}