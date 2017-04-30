package Modelo;

import java.awt.FlowLayout;
import java.io.File;
import javax.swing.JFrame;

public class Pruebas {
    
    //probando el cargador de archivos, funciona!!!
    public static void main(String[] args) {
        JFrame j = new JFrame();
        JFrame.setDefaultLookAndFeelDecorated(true);
        j.setLayout(new FlowLayout());
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Archivo a = new Archivo();
        a.setFile(j);
        File f = a.getFile();

    }

}
