/*Elaborado por: Juan Pablo Gómez Restrepo
*Abril 1 de 2017
*Universidad de Antioquia
*Medellín
*Version 1.0
*/
package Modelo.Listas;

//Tomado del libro Algoritmia II, Roberto Florez - Editorial U de A
//Para una documentación mas detallada, remitase al texto de referencia
public class DoubleList {
    
    private DoubleNode first;
    private DoubleNode  last;
        
    public DoubleList(){
         first=null;
	 last=null;       
        }
    
    //primer nodo
    public DoubleNode firstNode(){
    return first;
    }
    //ultimo nodo
    public DoubleNode lastNode(){
    return  last;
    }
    
    // fin de recorrido 
    public boolean isTheEnd(DoubleNode x){
        return x == null;
    }
    
    // es vacia        
    public boolean isEmpty(){
        return first == null;
    }
          
     // insertar      
    public void insert (Monomio d, DoubleNode y)
     {
         DoubleNode x;
         DoubleNode p;
         
         x = new DoubleNode(d);
         conect(x,y);
                
     }
     
     //conectar     
    public void conect( DoubleNode x , DoubleNode y)
     {
        if( y == null )
        {
         x.setRight(first);
         if(first != null){
             
             first.setLeft(x);
         }
         else {
             
             last  = x ;
         }
         first = x ;
        }else {
            if(y.getRight()== null){
                y.setRight(x);
                x.setLeft(y);
                last= x;
            }else{
                x.setRight(y.getRight());
                x.setLeft(y);
                x.getRight().setLeft(x);
                y.setRight(x);
            }            
        }
     }
         
    // borrar 
    public void delete(DoubleNode x){
         if(x==null){
            System.out.println("Dato a borrar no existe ");
            return ;
         }
         disconnect(x);
     }
     
     // desconectar     
     public void disconnect(DoubleNode x){
         if(x.getLeft()==null){
             first= x.getRight();
             if(first == null){
                 last = null;
             }else{
                 first.setLeft(null);
             }                
         }
         else {
             if(x.getRight()== null){
                 last = x.getLeft();
                 last.setRight(null);
                 
             }
             else {
                 x.getRight().setLeft(x.getLeft());
                 x.getLeft().setRight(x.getRight());
             }
         }
     }
         
    }
    
    
    
    
    
    

