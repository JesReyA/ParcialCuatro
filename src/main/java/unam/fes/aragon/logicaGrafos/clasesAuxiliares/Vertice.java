package unam.fes.aragon.logicaGrafos.clasesAuxiliares;

public class Vertice<E> {
    public E valor;
    public boolean visitado;

    public Vertice(E valor){
        this.valor = valor;
        this.visitado = false;
    }


}
