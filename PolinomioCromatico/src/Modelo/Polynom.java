/*Elaborado por: Juan Pablo Gómez Restrepo
*juan.gomez125@udea.edu.co
*Abril 1 de 2017
*Universidad de Antioquia
*Medellín
*Version 1.0
*/
package modelo;

import java.math.BigInteger;

 /*Polinomios: Listas doblemente ligadas con funciones extendidas 
 *             Los nodos de la lista tienen un monomio en su campo dato 
 *             Los monomios tienen un coeficiente y un exponente     
 */
public class Polynom extends DoubleList {
         
    /*Constructor por defecto
    *construye el polinomio nulo 0x0
    */
    public Polynom() { 
        Monomio m = new Monomio(BigInteger.ZERO, 0);
        insert(m, lastNode());        
    }
     
    //Constructor con parametrio String de entrada
    public  Polynom(String p){ 
      p = deleteSpaces(p);
        
        for (int j = 0; j <p.length(); j++) {                       
            
            char a=p.charAt(j);
            String c="";
            String e="";
            BigInteger coef;
            int exp;
            Monomio m;
            boolean continuar= true;
            
      //////SE COLECTA UN COEFICIENTE
            if(a=='-' || a=='+'){
                c=c+a;
                j++;
                a=p.charAt(j);
            }            
            
            while(a!='-' && a!='+'){
                if(a!='x' && a!= 'X'){
                    c= c+a;
                    j++;
                    if(j<p.length()){
                        a=p.charAt(j);
                    }else break;
                }else break;
            }      
            
            if (c.equals("") || c.equals("-") || c.equals("+")){ 
                c=c+"1"; 
            }
      ////SE COLECTA UN EXPONENTE     
        if((a=='x' || a=='X')){
          continuar= true;
        }else {
            e="0";
            continuar = false;
        }        
            if(continuar){
                j++;
                while(j<p.length()){
                        a=p.charAt(j);

                        if(!stop(a)){
                            e=e+a;
                            j++;
                        }else{
                            break;
                        }
                }
                if(e.equals("")){
                    e="1";                           
                }
            }
        
         ////SE CREA UN MONOMIO NUEVO Y SE INSERTA EN LA LISTA
            coef = BigInteger.valueOf(Long.parseLong(c));
            exp = Integer.parseInt(e);
            m = new  Monomio(coef, exp);
            insert(m, lastNode());
            j--;          
        }
       simplificar();
    }
    
    /*Este método solo es usado por el constructor
    *Se encarga de eliminar todos los espacios blanco de la cadena (String) con la cual se construye el polinomio      
    *Simplifica el proceso de costruccion del polinomio al eliminar los espacios en blanco
    */
    private String deleteSpaces(String p) { // elimina los espacios en el string de entrada para ayudar a la correcta construcción de la lista
       String aux="";
       char c;
       for (int i = 0; i < p.length(); i++) {
           c= p.charAt(i);
           if(c!=' '){
               aux= aux+c;
           }
       }
     return aux;
   }

    /*Metodo que usa el constructor para validar los casos de parada de la variable que recoge cada caracter de la expresion (string) de entrada
    *se usa para separar los coeficientes y exponentes 
    */
    private boolean stop(char a){
        return a=='-' || a=='+';                   
    }

    /*El método simplificar es usado unicamente por el constructor
    *Se encarga de simplificar el polinómio
    *suma los terminos con exponentes iguales y reduce la expresion
    */
    private void simplificar(){

        DoubleNode x;
        DoubleNode y;
        DoubleNode w;
        x= firstNode();

        while(!isTheEnd(x)){
            y= firstNode();

            while (!isTheEnd(y)){
            if(x.getData().getExp()== y.getData().getExp() && x!=y){
                x.getData().setCoef(x.getData().getCoef().add(y.getData().getCoef()));
                w = y;
                y= y.getRight();
                delete(w);
                }else y= y.getRight();                          
            }
            x= x.getRight();
        }            
    }  

    /*El método writePoly se encarga de convertir a tipo String un objeto de tipo Polynom
    *Imprime de forma amigable para el usuario el polinómio (this) recorriendo la lista nodo a nodo
    */
    public  String writePoly(){

       DoubleNode y = firstNode();
       Monomio mono;
       String r="";

       while(!this.isTheEnd(y)){
            mono= y.getData();

        if(mono.getCoef()==BigInteger.ZERO){
            y=y.getRight();
        }else{
            if(mono.getCoef()==(BigInteger.ONE) || mono.getCoef()==BigInteger.valueOf(Long.parseLong("-1"))){
                if(mono.getExp()!=0){
                     if(mono.getExp()==1){
                        r=r+getSymbol(mono.getCoef())+"x";
                        y=y.getRight();
                    }else{
                        r=r+getSymbol(mono.getCoef())+"x"+mono.getExp();
                        y=y.getRight();
                    }   
                }else{
                    r=r+getSymbol(mono.getCoef())+abs(mono.getCoef());
                    y=y.getRight();                     
                }
            }else{                    
                if(mono.getExp()==0){
                    r=r+getSymbol(mono.getCoef())+abs(mono.getCoef());
                    y=y.getRight();
                }else{
                    if(mono.getExp()==1){
                        r=r+getSymbol(mono.getCoef())+abs(mono.getCoef())+"x";
                        y=y.getRight();
                    }else{
                        r=r+getSymbol(mono.getCoef())+abs(mono.getCoef())+"x"+mono.getExp();
                        y=y.getRight();
                    }
                }                                
            }
        }  
        
       }
       
       if(r!="" && r.charAt(0)=='+'){
           r= r.substring(1);
           return r;
       }
       else{      
       if(r.equals("")) return "0x0";
       else return r;
    }
    }
    
    /*Es un método que solo usa el método writePoly
    *se encarga de idenficar el signo de un numero real  (double) para poder imprimerlo adecuadamente
    *Es útil para los casos en los que el número es positivo porque el simbolo '+' se pierde a la hora de imprimirlo
    */
    private String getSymbol(BigInteger s){
        String symbol="";
        if (s.signum()==1){
            symbol="+";        
        }else symbol ="-";        
        return symbol;
    }
    
    /*Este método solo es llamado por el método writePoly
    *sirve para obtener el valor absoluto de un número real
    *facilita el proceso de impresión de polinomios
    */
    private double abs(double  c){
        if(c>0){
            return c;
            }else{
        return c*(-1);
        }
    }
        
   /*Este método evalua un polinómio en un numero real a, 
   *retorna falso si a no hace cero el polinomio (this)
   *retorna true si hace hace cero el polinomio (this)
   */    
    public boolean esFactor(double a){
          return evaluatePolynomAt(a)==0;
    }
  
    /*Este método se encarga de sumar dos polinomios
    * El polinomio (this) es sumado con otro polinomio que entra como parametro
    */
    public void sumar(Polynom p){
       DoubleNode nt = firstNode();
       DoubleNode np = p.firstNode();
       Monomio m,mp;

       while(!isTheEnd(nt)&&!p.isTheEnd(np)){
           m = nt.getData();
           mp = np.getData();
           if(m.getExp()== mp.getExp()){
               m.setCoef((m.getCoef()+mp.getCoef()));
               nt = nt.getRight();
               np = np.getRight();
           }
           else if(nt.getData().getExp()>np.getData().getExp()) nt = nt.getRight();
           else if(nt.getData().getExp()<np.getData().getExp()){                
               Monomio i = new Monomio(np.getData().getCoef(), np.getData().getExp());
               DoubleNode d = new DoubleNode(i);
               conect(d, nt.getLeft());  
               nt = nt.getRight();
               np = np.getRight();                
           }              
       }        
       while(!p.isTheEnd(np)){
           Monomio i = new Monomio(np.getData().getCoef(), np.getData().getExp());
           DoubleNode d = new DoubleNode(i);
           conect(d, lastNode());
           np = np.getRight();
       }
    } 
    
    /*Este método solo es usado por el método multiplicar
    *reduce la complejidad del proceso de multiplicacion de 2 polinomios
    *Se crea un nuevo polinomio que es el resultado de recorrer el polinomio que invocó el método multiplicando cada término de él por el termino enviado como parámetro 
    *se almacena el resultado en la nueva lista
    */
    private Polynom  smult(Monomio  t){
        int  et,ei,ek;
        double  ct, ci, ck;
        Polynom  c;
        Monomio  ti, tk;
        DoubleNode  p;
        ct = t.getCoef();
        et = t.getExp();
        c = new  Polynom();
        p = firstNode();

        while (!isTheEnd(p)){  
                ti = p.getData();
                ci = ti.getCoef();
                ei = ti.getExp();
                ck = ci * ct;
                ek = ei + et;
                tk = new Monomio(ck, ek);
                c.insert(tk, c.lastNode());
                p = p.getRight();
            }
       return  c;
    }         
    
    /*Se encarga de multiplicar 2 polinómios
    *el polinomio (this) se multiplica con el polinomio que entra como parametro 
    *se devuelve un nuevo polinómio y no se módifica el polinomio que invoca el método
    */
    public Polynom multiplicar(Polynom  b){
            Polynom  c, aux;
            DoubleNode  p;
            Monomio  t;
            p = b.firstNode();
            c = new Polynom();

            while (!isTheEnd(p)){
                t = p.getData();
                aux = smult(t);
                c.sumar(aux);
                p = p.getRight();
            }
        return  c;
    }     
    
   /*Este método se encarga de restar dos polinomios
    * El polinomio (this) es restado con otro polinomio que entra como parametro
    *este metodo usa los métodos multiplicarPorEscalar() y sumar() para simplificar su implementación  
    */
    public void restar(Polynom p){
        p.multiplicarPorEscalar(-1);
        sumar(p);
    }
    
    /*Este método se encarga de obtener la derivada del polinomio que lo invoca
    *modifica el this
    */
    public void derivate(){
        
        DoubleNode x = firstNode();
        Monomio m;
        Monomio n;
        double c;
        int e;
                
        while(!isTheEnd(x)){              
            m =x.getData();
            m.setCoef(m.getCoef()*m.getExp());
            m.setExp(m.getExp()-1);
            x = x.getRight();
        }        
    }
    
    /*Este método se encarga de obtener la derivada n-esima del polinomio que lo invoca
    *Modifica el this
    *El parametro de entrada es el numero de derivadas 
    */
    public Polynom derivate(int n){
        int i=0;
        while(i<n){              
            derivate();
            i++;
        }
            return this;
    }
    
    /*Método que multiplica a un polinomio por un escalar real (double)
    *no modifica el this
    *es usado por otros métodos de la clase polynom
    */
    public Polynom multiplicarPorEscalar(double e){
        
        DoubleNode x= firstNode();
        while(!isTheEnd(x)){
            double n= x.getData().getCoef()*e;
            x.getData().setCoef(n);
            x=x.getRight();           
        }
        return this;
    }
    
    
    //Método que evalúa el polinomio (this) que lo invoca, en un número real (double) x que entra como parámetro
    public double evaluatePolynomAt(double x){
        
        DoubleNode y = this.firstNode();
        double r=0;
            while(!this.isTheEnd(y)){
                r=r+((Math.pow(x, y.getData().getExp()))*(y.getData().getCoef()));
                y=y.getRight();
            }
            return r;  
    }
    
    //Método que que obtiene la integral indefinida del polinomio que lo invoca (this)
    public Polynom integrate(){
        
        DoubleNode x= firstNode();
        Monomio m;
        Polynom r= new Polynom();
        
        while (!isTheEnd(x)){
            int e = x.getData().getExp()+1;
            double c = x.getData().getCoef()/e;
            m = new Monomio (c,e);
            r.insert(m, r.lastNode());
            x=x.getRight();
        }    
        return r;
    }
    
    /*Método que obtiene la integral definida del polinomio que lo invoca (this)
    *evalúa la integral entre los límites de integración que entan como parámetro
    */
    public double integrate(double a, double b){
        DoubleNode x= firstNode();
        double r = 0;
        while (!this.isTheEnd(x)){
            int e = x.getData().getExp()+1;
            double c = x.getData().getCoef()/e;
            r = r+c*(Math.pow(b, e)-Math.pow(a, e)); 
            x=x.getRight();
        } 
        return r;    
    }
    
    /*Método que elimina el polinomio (this) que lo invoca
    *borra el polinomio nodo a nodo y devuelve el polinomio nulo
    */
    public void deletePolynom(){
        DoubleNode x= firstNode();
        DoubleNode y = x;
        while (!isTheEnd(y)){
            y=y.getRight();
            delete(x);
            x=y;           
        }
        Monomio m = new Monomio(0,0);
        insert(m, lastNode() );
    }    
}
