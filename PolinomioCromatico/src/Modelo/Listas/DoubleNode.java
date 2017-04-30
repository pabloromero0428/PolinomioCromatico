/*Elaborado por: Juan Pablo Gómez Restrepo
*juan.gomez125@udea.edu.co
*Abril 1 de 2017
*Universidad de Antioquia
*Medellín
*Version 1.0
*/
package Modelo.Listas;

//Tomado del libro Algoritmia II, Roberto Florez - Editorial U de A
//Para una documentación mas detallada, remitase al texto de referencia
public class DoubleNode {
    private Monomio data;
    private DoubleNode left, right;

    //Constructor de un nodoDoble por defecto
    public DoubleNode() {
        this.right= null;
        this.left=null;
        this.data=null;
    }
    
    //Constructor de un nuevo nodoDoble con un objeto de tipo Monomio pasado por parámetro
    public DoubleNode(Monomio m){
        this.right= null;
        this.left=null;
        this.data=m;    
    }
    
    ////GETTERS AND SETTERS

    public Monomio getData() {
        return data;
    }

    public void setData(Monomio data) {
        this.data = data;
    }

    public DoubleNode getLeft() {
        return left;
    }

    public void setLeft(DoubleNode lL) {
        this.left = lL;
    }

    public DoubleNode getRight() {
        return right;
    }

    public void setRight(DoubleNode rl) {
        this.right = rl;
    }    
}
