/*Elaborado por: Juan Pablo Gómez Restrepo
*juan.gomez125@udea.edu.co
*Abril 1 de 2017
*Universidad de Antioquia
*Medellín
*Version 1.0
*/
package Modelo.Listas;
import java.math.BigInteger;
//Los nodeos de la clase Polynom tienen en su campo dato un Monomio
public class Monomio{
    
    private BigInteger coef;
    private int exp;
        
    //Constructor por defecto: construye el polinomio 0x0 (nulo)
    public Monomio(){
    
        this.coef=(BigInteger.ZERO);
        this.exp=0;
    }
    
  //Constructor de monomios cuyos coeficientes y exponentes entran como parámetro     
    public Monomio(BigInteger c, int e){
        this.coef=c;
        this.exp=e;    
    }    
  ////GETTERS AND SETTERS

    public BigInteger getCoef() {
        return coef;
    }

    public void setCoef(BigInteger coef) {
        this.coef = coef;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }  
}
