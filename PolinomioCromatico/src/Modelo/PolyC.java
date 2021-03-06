package Modelo;
import Modelo.grafos.LinkedAdyListG;


public class PolyC{
    private LinkedAdyListG grafo;
    private Polynom polynom;         

        
    public Polynom calcularPoly(LinkedAdyListG g) {  //P(G)

       
        // caso base grafo completo
        if (g.completo()) {
            
            return g.calcularCompleto();

        } //caso base grafo disperso
        if (g.isDisperso()) {
            
            return g.calcularDisperso();
        }

        
            if (g.isDenso()) { //P(G) = P(G+e) - P(G/e)                
                Polynom p= calcularPoly(g.AgreararistaGrafo());
                Polynom q =(calcularPoly(g.fusionarArista(g.getActualv1(),g.getActualv2())));                
                polynom = p.sumar(q);                
                
            } else {//P(G) = P(G-e) - P(G/e)                        
                Polynom p= calcularPoly(g.quitararistaGrafo());
                Polynom q =(calcularPoly(g.fusionarArista(g.getActualv1(),g.getActualv2())));                
                polynom = p.restar(q);
            }
        
        return polynom;
    } 
}
