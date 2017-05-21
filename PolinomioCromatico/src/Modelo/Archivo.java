package Modelo;

import java.awt.Component;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Archivo {

    private File file;
    private JFileChooser fileC;

//Método que obtiene, con un buscador de archivos grafico (foilechooser), el archivo del grafo a trabajar    
    public void setFile(Component c) {
        fileC = new JFileChooser();
        fileC.showOpenDialog(c);
        file = fileC.getSelectedFile();
    }

    public File getFile() {
        return file;
    }

}