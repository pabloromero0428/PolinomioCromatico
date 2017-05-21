package Modelo.grafos;

import Modelo.Archivo;
import Modelo.Listas.Monomio;
import Modelo.Listas.SList;
import java.io.File;
import Modelo.Listas.SimpleNode;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import Modelo.Polynom;

public class LinkedAdyListG {

    private int numVertices; // Número de vértices del grafo    
    private SList vec[];        // Vector de listas de adyacencias del grafo.
    private int ver;
    private int arist;
    private boolean[] activo;
    private int actualv1;

    public void setActualv1(int actualv1) {
        this.actualv1 = actualv1;
    }

    public void setActualv2(int actualv2) {
        this.actualv2 = actualv2;
    }
    private int actualv2;

    //Constructor que recibe un vector de listas por parámetro y el vector de activos
    public LinkedAdyListG(SList v[], boolean activos[], int arista) {
        this.vec = v;
        this.activo = activos;
        activos[0] = false;
        this.numVertices = v.length - 1;
        this.arist = arista;
        ver = numVertices;
        int i = v.length;

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
                    activo = new boolean[numVertices + 1];
                    for (int i = 1; i < numVertices + 1; i++) {
                        SList l = new SList();
                        vec[i] = l;
                        activo[i] = true;
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
                if (x.getData() == j) {
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

    public LinkedAdyListG AgreararistaGrafo() {
        boolean existe = false;
        int w = 0;
        for (int i = 1; i <= numVertices; i++) {
            for (int j = 1; j <= numVertices; j++) {
                existe = false;
                SimpleNode x = vec[i].firstNode();
                while (x != null) {
                    if (j == i) {
                        existe = true;
                        break;
                    }
                    if (x.getData() == j) {
                        x = null;
                        existe = true;
                        break;

                    } else {
                        x = x.getLink();
                        w = j;
                    }

                }

                if (existe == false && activo[i] == true) {
                    actualv1 = i;
                    actualv2 = w;
                    SList[] v = new SList[vec.length];
                    boolean[] activoA = new boolean[vec.length];
                    for (int k = 1; k < vec.length; k++) {
                        SList l = new SList();
                        v[k] = l;
                        activoA[k] = activo[k];
                    }
                    for (int p = 1; p <= vec.length - 1; p++) {
                        SimpleNode y = vec[p].firstNode();
                        while (y != null) {
                            v[p].insert(y.getData(), null);
                            y = y.getLink();
                        }

                    }
                    arist = arist + 1;
                    LinkedAdyListG g = new LinkedAdyListG(v, activoA, arist);
                    g.insertaArista(i, w);
                    g.imprimirGrafo();

                    return (g);
                }
            }
        }
        return (null);

    }

    public LinkedAdyListG quitararistaGrafo() {

        for (int i = 1; i < numVertices; i++) {
            SimpleNode x = vec[i].firstNode();
            if (x != null) {
                int v = x.getData();
                actualv1 = i;
                actualv2 = v;
                SList[] ngraf = new SList[vec.length];
                boolean[] activoA = new boolean[vec.length];
                for (int k = 1; k < vec.length; k++) {
                    SList l = new SList();
                    ngraf[k] = l;
                    activoA[k] = true;
                }
                for (int p = 1; p <= vec.length - 1; p++) {
                    SimpleNode y = vec[p].firstNode();
                    while (y != null) {
                        ngraf[p].insert(y.getData(), null);
                        y = y.getLink();
                    }

                }
                arist = arist + 1;
                LinkedAdyListG g = new LinkedAdyListG(ngraf, activoA, arist);
                g.eliminaArista(i, v);

                return (g);
            }
        }
        return (null);

    }

    /**
     * Eliminia la arista v1,v2 de la lista de adyasencia, eliminando los nodos
     * del vector en la posicion v1, y posicion v2.
     *
     * @param v1
     * @param v2
     */
    public void eliminaArista(int v1, int v2) {
        if (v2 < numVertices && v1 < numVertices) {
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

    public LinkedAdyListG fusionarArista(int i, int j) {

        boolean[] activoA = new boolean[vec.length]; // controla si el vertice está borrado o está sin conexiones falso si no está activo, verdadero si es un vértice solo
        activoA[0] = true;
        int d;                                      // recoge el dato del nodo actual
        int c = j;
        SList[] v = new SList[vec.length];
        activoA[i] = false;

        for (int k = 1; k <= vec.length - 1; k++) {

            SList list = vec[k];
            v[k] = new SList();

            if (k != i) { //  si no es el vertice  borrar

                SimpleNode x = list.firstNode();

                while (!list.isTheEnd(x)) {
                    // es un nodo  con dato normal a pasar tan cual
                    d = x.getData();
                    if (d != i) {
                        v[k].insert(d, v[k].lastNode());
                        activoA[k] = true;

                    } else {
                        v[k].insert(c, v[k].lastNode());
                        activoA[k] = true;
                        c = k;
                    }
                    x = x.getLink();
                }
            }
        }

        LinkedAdyListG g = new LinkedAdyListG(v, activoA, arist - 1);
        return g;
    }

    /**
     * Este metodo determina si el grafo denso o no, si es denso el resultado de
     * la operacion alojada debe se >= que 1 de lo contrario es menos denso
     *
     * @return
     */
    public boolean isDenso() {
        boolean esDenso = false;
        int resultado = (2 * arist) / numVertices * (numVertices - 1);
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

        for (int i = 1; i <= numVertices; i++) {
            SimpleNode y = vec[i].firstNode();
            if (y == null) {
                return (false);
            }
            SimpleNode x = vec[i].firstNode();
            contador = 0;
            if (!activo[i]) {
            } 
            else {
                while (x != null) {
                    if (x.getData() == (i)) {
                        x = x.getLink();
                    } else {
                        contador = contador + 1;
                        x = x.getLink();
                    }

                }
                if (contador != numVertices - 1) {
                    escompleto = false;
                    System.out.println("no es completo");
                    return (escompleto);
                }
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
            if (vec[i] != null) {
                aux = vec[i].firstNode();
                while (aux != null) {
                    System.out.println(aux.getData());
                    aux = aux.getLink();
                }
            }

            System.out.println("FIN");
        }
    }

    public int getActualv1() {
        return actualv1;
    }

    public int getActualv2() {
        return actualv2;
    }

    public boolean isDisperso() {

        boolean disperso = true;

        for (int i = 1; i <= vec.length; i++) {
            SimpleNode x = vec[i].firstNode();
            if (x != null) {
                return (false);
            }
        }
        return disperso;
    }

    public Polynom calcularDisperso() {
        int n = numVertices;
        //int n = (vec.length-1);
        Polynom p = new Polynom("x" + n);
        return p;
    }

    public Polynom calcularCompleto() {

        Polynom p = new Polynom("x");
        Monomio m = new Monomio();
        String s;

        for (int i = 0; i < numVertices; i++) {
            s = "x-" + i;
            Polynom q = new Polynom(s);
            p = p.multiplicar(q);
        }
        return p;
    }

//    public static void main(String[] args) throws IOException {
//
//        JFrame j = new JFrame();
//        JFrame.setDefaultLookAndFeelDecorated(true);
//        j.setLayout(new FlowLayout());
//        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Archivo a = new Archivo();
//        a.setFile(j);
//        File f = a.getFile();
//        LinkedAdyListG l = new LinkedAdyListG(f);
//        LinkedAdyListG g;
//        //l.completo();
//        System.out.println("-------------- l ");
//        l.imprimirGrafo();
//        System.out.println("-------------- l ");
//        //l.AgreararistaGrafo();
//        g = l.fusionarArista(1, 9);
//        System.out.println("-------------- g ");
//        g.imprimirGrafo();
//        //l.quitararistaGrafo();
//        System.out.println("-------------- g ");
//
//    }
}
