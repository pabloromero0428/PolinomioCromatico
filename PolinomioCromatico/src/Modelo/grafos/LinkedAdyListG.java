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

    //Atributos necesarios para leer el archivo y construir el grafo
    private Archivo archivo;
    private File file;

    //Atributos propios del grafo
    private SimpleNode[] V; // es un vector en teoría, pero si es un grafo con mucjhas aristas no se si sea bueno trabajar con un vector
    int numVertices; // Número de vértices del grafo  
        
    //constructor por defecto para grafo vacío
    public LinkedAdyListG() {// constructor vacio
        this.V = new SimpleNode[0];
        numVertices = 0;
    }

    //Constructor que recibe como parametro la cantidad de vertices que tiene el grafo
    public LinkedAdyListG(int n) {
        //maxNodos = n;
        numVertices = n;
        V = new SimpleNode[n]; // de esta forma se agregan directamente los vertices necesarios al vector de nosod simple
    }

    public void insertaArista(int i, int j) {
        if (i >= numVertices) {
            System.out.println("Error, no existe el vértice en el grafo");
        } else {
            SimpleNode x = new SimpleNode(j);
            x.setLink(V[i]);
            V[i]=x;         
            
            
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
            SimpleNode x = V[i];
            System.out.println("Vertice" + i);
            while (!isTheEnd(x)) {
                System.out.println("Arista: "+ i+ " " + x.getData());
                x = x.getLink();
            }

        }
    }
 

    public static void main(String[] args) {
        
        LinkedAdyListG g = new LinkedAdyListG(5);
        g.insertaArista(2, 3);
        g.imprimirGrafo();

    }

}
