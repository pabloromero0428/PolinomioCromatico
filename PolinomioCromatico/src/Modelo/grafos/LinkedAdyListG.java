package Modelo.grafos;

import Modelo.Archivo;
import Modelo.Archivo;
import Modelo.Listas.DoubleNode;
import Modelo.Listas.SList;
import java.io.File;
import Modelo.Listas.SimpleNode;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class LinkedAdyListG {

    private int[] vec; // es un vector en teoría, pero si es un grafo con mucjhas aristas no se si sea bueno trabajar con un vector
    private SimpleNode v;  // agregué la listas simples
    private Archivo archivo;
    private File file;     
    private JFileChooser fileC;

    public void setFile() { // configura el archivo dfe donde se va a sacar el grafo
        file = archivo.getFile();
    }
    private DoubleNode M;
    int maxNodos;
    boolean dirigido; // Indica si es dirigido o no.
    int numVertices; // Número de vértices del grafo    
    SList listaAdy[];        // Vector de listas de adyacencias del grafo.
    int ver;
    int arist;
    /**
     * Constructor que recibe como parametro un archivo para la creacion del grafo
     *
     * @param direccion
     * @param n
     */
    public LinkedAdyListG(File direccion) {
        try {
            FileReader fr = new FileReader(direccion);
            BufferedReader br = new BufferedReader(fr);
            String Linea;

            if (br != null) {
                while ((Linea = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(Linea, " ");

                    String tipo = st.nextToken();

                    if (tipo.equalsIgnoreCase("p")) {
                        String comentario = st.nextToken();
                        String vertices = st.nextToken();
                        String aristas = st.nextToken();

                        String T = tipo;
                        String comentar = comentario;
                        ver = Integer.parseInt(vertices);
                        arist = Integer.parseInt(aristas);
                        numVertices = ver;
                        listaAdy = new SList[numVertices + 1];
                        for (int i = 1; i < numVertices + 1; i++) {
                            SList l = new SList();
                            listaAdy[i] = l;
                        }

                    }
                    if (tipo.equalsIgnoreCase("e")) {
                        String v1 = st.nextToken();
                        String v2 = st.nextToken();
                        int ver1 = Integer.parseInt(v1);
                        int ver2 = Integer.parseInt(v2);
                        if (ver1 + 1 >= numVertices + 1) {
                            System.out.println("Error, no existe el vértice en el grafo");
                        } else {
                            listaAdy[ver1].insert(ver2, null);
                            listaAdy[ver2].insert(ver1, null);

                        }

                    }

                }

            }

            fr.close();
            br.close();

        } catch (IOException ex) {
            System.err.println("Surgio un problema!!! " + ex.getMessage());
        }

    }

    /**
     * Conecta los vertices
     *
     * @param i
     * @param j
     */
    public void insertaArista(int i, int j) {
        if (i >= numVertices) {
            System.out.println("Error, no existe el vértice en el grafo");
        } else {
            listaAdy[i].insert(j, null);
            listaAdy[j].insert(i, null);
        }
    }

    /**
     * Eliminia la arista v1,v2 de la lista de adyasencia, eliminando los nodos
     * del vector en la posicion v1, y posicion v2.
     *
     * @param v1
     * @param v2
     */
    public void eliminaArista(int v1, int v2) {
        if (v2 > numVertices && v1 > numVertices) {
            System.out.println("Error, no existe el vértice en el grafo");
        } else if (v1 <= numVertices) {
            SimpleNode x = listaAdy[v1].firstNode();
            SimpleNode previo = null;
            int ver1 = (int) x.getData();

            SimpleNode y = listaAdy[v2].firstNode();
            SimpleNode previov2 = null;
            int ver2 = (int) y.getData();
            while (x != null) {
                if (ver1 == v2) {
                    listaAdy[v1].disconnect(x, previo);
                    x = null;
                } else {
                    previo = x;
                    x = x.getLink();
                    ver1 = (int) x.getData();
                }
            }

            while (y != null) {
                if (ver2 == v1) {
                    listaAdy[v2].disconnect(y, previov2);
                    y = null;
                } else {
                    previov2 = y;
                    y = y.getLink();
                    ver2 = (int) y.getData();
                }
            }
        }
    }
    /**
     * Este metodo determina si el grafo denso o no, si es denso el resultado de la operacion alojada debe se >= que 1 de lo contrario es menos denso
     * @param n
     * @param m
     * @return 
     */
    boolean isDenso (int n, int m){
        boolean esDenso = false;
        int resultado = (2*m)/n*(n-1);
        if(resultado >= 1 ){
            esDenso = true;
        }
        return(esDenso);
    }
    

    /**
     * Imprime el grafo como lista de adyacencia
     */
    public void imprimirGrafo() {
        System.out.println("Tamaño máximo del grafo: " + maxNodos + "\n");
        System.out.println("El grafo contiene " + numVertices + " vértices: \n");
        for (int i = 1; i < numVertices + 1; i++) {
            System.out.println("vértice " + i + ": ");
            SimpleNode aux;
            aux = listaAdy[i].firstNode();
            while (aux != null) {
                System.out.println(aux.getData());
                aux = aux.getLink();
            }
            System.out.println("FIN");
        }
    }
    
    public static void main(String[] args) {
       
        JFrame j = new JFrame();
        JFrame.setDefaultLookAndFeelDecorated(true);
        j.setLayout(new FlowLayout());
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Archivo a = new Archivo();
        a.setFile(j);
        File f = a.getFile();
        LinkedAdyListG l = new LinkedAdyListG(f);  
        l.imprimirGrafo();

    }
}
