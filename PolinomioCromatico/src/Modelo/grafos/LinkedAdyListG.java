package Modelo.grafos;

import Modelo.Listas.DoubleNode;
import Modelo.Listas.SList;
import java.io.File;
import Modelo.Listas.SimpleNode;
import com.sun.javafx.geom.Vec2d;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class LinkedAdyListG {

    private int numVertices; // Número de vértices del grafo    
    private SList vec[];        // Vector de listas de adyacencias del grafo.
    private int ver;
    private int arist;
    private boolean[] activo;

    /**
     * Constructor que recibe como parametro un archivo para la creacion del
     * grafo
     *
     * @param direccion
     * @param n
     * @throws java.io.FileNotFoundException
     */
     
    //Constructor que recibe un vector de listas por parámetro y el vector de activos
    public LinkedAdyListG(SList v[], boolean activos[]) {
        this.vec = v;
        this.activo = activos;
        //this.numVertices = v.length - 1;

    }
    
    public LinkedAdyListG(File direccion) throws FileNotFoundException, IOException {

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

                    String comentar = comentario;
                    ver = Integer.parseInt(vertices);
                    arist = Integer.parseInt(aristas);
                    numVertices = ver;
                    vec = new SList[numVertices + 1];
                    for (int i = 1; i < numVertices + 1; i++) {
                        SList l = new SList();
                        vec[i] = l;
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
                        boolean existe = false;
                        SimpleNode x = vec[ver1].firstNode();
                        while (x != null) {
                            if (x.getData() == (ver2)) {
                                x = null;
                                existe = true;
                            } else {
                                x = x.getLink();
                            }
                        }
                        if (existe == false) {
                            vec[ver1].insert(ver2, null);
                            vec[ver2].insert(ver1, null);
                        }
                    }
                }
            }
        }
        fr.close();
        br.close();
    }

   

    /**
     * Conecta los vertices
     *
     * @param i
     * @param j
     */
    public void insertaArista(int i, int j) {
        boolean existe = false;
        if (i >= numVertices) {
            System.out.println("Error, no existe el vértice en el grafo");
        } else {
            SimpleNode x = vec[i].firstNode();
            while (x != null) {
                if (x.getData() == (j)) {
                    x = null;
                    existe = true;
                } else {
                    x = x.getLink();
                }
            }
            if (existe == false) {
                vec[i].insert(j, null);
                vec[j].insert(i, null);
            } else {
                System.out.println("La arsita" + i + "," + j + " ya existe");
            }
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
            SimpleNode x = vec[v1].firstNode();
            SimpleNode previo = null;
            int ver1 = (int) x.getData();

            SimpleNode y = vec[v2].firstNode();
            SimpleNode previov2 = null;
            int ver2 = (int) y.getData();
            while (x != null) {
                if (ver1 == v2) {
                    vec[v1].disconnect(x, previo);
                    x = null;
                } else {
                    previo = x;
                    x = x.getLink();
                    ver1 = (int) x.getData();
                }
            }

            while (y != null) {
                if (ver2 == v1) {
                    vec[v2].disconnect(y, previov2);
                    y = null;
                } else {
                    previov2 = y;
                    y = y.getLink();
                    ver2 = (int) y.getData();
                }
            }
        }
    }
              

    public LinkedAdyListG fusionarArista() {
        // variables que se obtienen al seleccionar una arista a borrar, estas variables se obtienen por fuera del método     
        int i = 3; //selecVertceABorrar();  // vector con mas aristas 
        int j = 5; //  el otro vértice de la arista

        boolean[] activoA = new boolean[vec.length]; // controla si el vertice está borrado o está sin conexiones falso si no está activo, verdadero si es un vértice solo
        int d;                                      // recoge el dato del nodo actual
        SList[] v = new SList[vec.length];

        for (int k = 1; k <= vec.length; k++) {

            SList list = vec[k];
            SimpleNode x = list.firstNode();

            if (activo[k] = false) {//si el vértice del grafo a eliminar arista no está activo, se desactiva el vértice del grafo de salida y se pasa al siguiente vertice
                activoA[k] = false;
            } else if (k != i) { //  si no es el vertice  borrar

                while (!list.isTheEnd(x)) {
                    // es un nodo  con dato normal a pasar tan cual
                    d = x.getData();
                    if (d != i) {
                        if (d != j) {
                            v[k].insert(d, v[k].lastNode());
                            activoA[k] = true;
                        }
                    } else if (k != j) {
                        v[k].insert(j, v[k].lastNode());
                        activoA[k] = true;
                    }
                    x = x.getLink();
                }
                // inserta un nodo con dato cambiado a j
            } else { //se encuentra con el vertice a borrar
                while (!list.isTheEnd(x)) {
                    d = x.getData();
                    if (d != i) {
                        v[j].insert(d, v[j].lastNode());
                    }
                    x = x.getLink();
                }
                //v[k]=null;
                activoA[k] = false;
            }
        }
        LinkedAdyListG g = new LinkedAdyListG(v, activoA);
        return g;
    }

    /**
     * Este metodo determina si el grafo denso o no, si es denso el resultado de
     * la operacion alojada debe se >= que 1 de lo contrario es menos denso
     *
     * @param n
     * @param m
     * @return
     */
    public boolean isDenso(int n, int m) {
        boolean esDenso = false;
        int resultado = (2 * m) / n * (n - 1);
        if (resultado >= 1) {
            esDenso = true;
        }
        return (esDenso);
    }

    /**
     *
     * @return
     */
    public boolean completo() {
        boolean escompleto = true;
        int contador;
        for (int i = 1; i < ver; i++) {
            SimpleNode x = vec[i].firstNode();
            contador = 0;
            while (x != null) {
                if (x.getData() == (i)) {
                    x = x.getLink();
                } else {
                    contador = contador + 1;
                    x = x.getLink();
                }

            }
            if (contador != ver - 1) {
                escompleto = false;
                System.out.println("no es completo");
                return (escompleto);
            }
        }
        return (escompleto);
    }

    /**
     * Imprime el grafo como lista de adyacencia
     */
    public void imprimirGrafo() {

        System.out.println("El grafo contiene " + numVertices + " vértices: \n");
        for (int i = 1; i < numVertices + 1; i++) {
            System.out.println("vértice " + i + ": ");
            SimpleNode aux;
            aux = vec[i].firstNode();
            while (aux != null) {
                System.out.println(aux.getData());
                aux = aux.getLink();
            }
            System.out.println("FIN");
        }
    }

    public boolean lineal() {
        for (int i = 1; i < ver; i++) {
            SimpleNode x = vec[i].firstNode();
            if (x != null) {
                return (false);
            }
        }
        return (true);
    }

    public static void main(String[] args) {
        /*
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
      
         */
        
        
        
        
    }
}
