package unam.fes.aragon.logicaGrafos.clasesAuxiliares;

public class Vertice<E> {
    public E valor;
    public boolean estaEnArbol;

    public Vertice(E valor){
        this.valor = valor;
        this.estaEnArbol = false;
    }


}
