/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

public class GrafoMAdy {

    private int n;
    private int[][] matriz;

    /**
     * Constructor de clase
     *
     * @param n numero de nodos crea un grafo de n vertices representado como
     * matriz de adyacencia
     */
    public GrafoMAdy(int n) {
        this.n = n;
        matriz = new int[this.n][this.n];
        //se inicializa matriz en 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = 0;
            }
        }
    }

    /**
     * Agrega un vertice i,j al grafo ya creado
     *
     * @param i
     * @param j
     */
    public void agregar(int i, int j) {
        matriz[i][j] = 1;
    }

    /**
     * Elimina un vertice del grafo
     *
     * @param i
     * @param j
     */
    public void remover(int i, int j) {
        if (matriz[i][j] > 0) {
            matriz[i][j] = 1;
        }
    }

    /**
     * Imprime la matriz de adyacensia
     */
    public void imprimirMatriz() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matriz[i][j] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * Imprimre el grafo
     */
    public void imprimiGrafo() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matriz[i][j] > 0) {
                    System.out.println(" (" + i + "," + j + ") ");
                }
            }

        }
    }

//   ----- Fin de Operaciones para obtener Lista de Adyacencia -----   //
    public static void main(String[] args) {
        System.out.println("");
        GrafoMAdy g = new GrafoMAdy(5);
        g.agregar(1, 1);
        g.agregar(2, 3);
        g.agregar(1, 4);
        g.imprimirMatriz();
        g.imprimiGrafo();

    }

}
