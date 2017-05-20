package Modelo;
import Modelo.grafos.LinkedAdyListG;
import modelo.Polynom;

public class PolyC{
    private LinkedAdyListG grafo;
    private Polynom polynom;         

        
    public Polynom calcularPoly(LinkedAdyListG g) {

        boolean continuar = true;
        // caso base grafo completo
        if (g.completo()) {
            continuar = false;
            return g.calcularCompleto();

        } //caso base grafo disperso
        if (g.isDisperso()) {
            continuar = false;
            return g.calcularDisperso();
        }
/*
        if (continuar) {
            if (g.isDenso()) {
                polynom = calcularPoly(g.AgreararistaGrafo()).sumar(calcularPoly(g.fusionarArista(g.getActualv1(),g.getActualv2())));
            } else {
                polynom = calcularPoly(g.quitararistaGrafo()).sumar(calcularPoly(g.fusionarArista(g.getActualv1(),g.getActualv2()))));
            }
        }
*/
        return polynom;
    } 
}
