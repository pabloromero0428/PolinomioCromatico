package Modelo.grafos;

import Modelo.Archivo;
import Modelo.Archivo;
import Modelo.Listas.DoubleNode;
import Modelo.Listas.SList;
import java.io.File;
import Modelo.Listas.SimpleNode;

/*
*REPRESENTACIÓN SUGERIDA POR ROBERTO
clase  grafoComoListasLigadasDeAdyacencia 
	private
		int  n			// n es el número de vértices del grafo
		nodoSimple  V[]
Fin(grafoComoListasLigadasDeAdyacencia)
 */
public class LinkedAdyListG extends SList {

    private SimpleNode[] vec; // es un vector en teoría, pero si es un grafo con mucjhas aristas no se si sea bueno trabajar con un vector
    private Archivo archivo;
    private File file;
    int maxNodos;
    boolean dirigido; // Indica si es dirigido o no.
    int numVertices; // Número de vértices del grafo    
    //SList listaAdy[];        // Vector de listas de adyacencias del grafo.

    public void setFile() { // configura el archivo dfe donde se va a sacar el grafo
        file = archivo.getFile();
    }

    //constructor vacío
    public LinkedAdyListG() {// constructor vacio
        //maxNodos = numVertices = 0;
    }

    /**
     * Constructor que recibe como parametro la cantidad de vertices que tiene
     * el grafo
     *
     * @param n
     */
    public LinkedAdyListG(int n) {
        //maxNodos = n;
        numVertices = n;
        vec = new SimpleNode[n]; // de esta forma se agregan directamente los vertices necesarios al vector directamente
    }

    /**
     * YA NO SERIA NECESARIO ESTE MÉTODO YA QUE LOS VERTICES, EN LA REPRESENTACIÓN ACTUAL SE AGREGAN AL VECTOR INMEDIATAMENTE
     * Agrega los vertices al contructor
     *
     * @param n
     */
    /*public void insertaVertice(int n) {
        if (n > maxNodos - numVertices) {
            System.out.println("Error, se supera el número de nodos máximodel grafo\");\n");

        } else {
            for (int i = numVertices; i < numVertices + n; i++) {
                listaAdy[i] = new SList();
            }
        }
        numVertices += n;
    }*/
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
            SimpleNode x = new SimpleNode(j);
            vec[i].setLink(x);
        }
    }

//    public void eliminaArista(int i, int j) {
//        if (j >= numVertices) {
//            System.out.println("Error, no existe el vértice en el grafo");
//        } else {
//            listaAdy[i].delete(j, );
//            
//        }
//    }
    
    // Imprime el grafo como lista de adyacencia
        public void imprimirGrafo() {
        System.out.println("El grafo contiene " + numVertices + " vértices: \n");

        for (int i = 0; i < numVertices; i++) {
            SimpleNode x = vec[i];
            System.out.println("Vertice" + vec[i]);
            while (!isTheEnd(x)) {
                System.out.println("Vertice" + x.getData());
                x = x.getLink();
            }

        }
    }

    /**
     * Este metodo escribe los datos que se encuentran en la lista de cada
     * espacio del vector
     *
     * @param lista
     */
    static void escribir(SList lista) {
        SimpleNode aux;
        aux = lista.firstNode();
        while (aux != null) {
            System.out.println(aux.getData());
            aux = aux.getLink();
        }
        System.out.println("FIN");
    }

    public static void main(String[] args) {

    }

}
