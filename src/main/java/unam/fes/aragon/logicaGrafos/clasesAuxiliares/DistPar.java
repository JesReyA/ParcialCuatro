package unam.fes.aragon.logicaGrafos.clasesAuxiliares;


/**
 * Esta enfermedad mental de clase guarda la distancia entre
 * un vertice de inicio y el vertice padre de un
 * vertice dado
 */

public class DistPar {
    public int distancia;
    public int verticePadre;

    public DistPar(int distancia, int verticePadre) {
        this.distancia = distancia;
        this.verticePadre = verticePadre;
    }

    public int getVerticePadre() {
        return verticePadre;
    }

    public void setVerticePadre(int verticePadre) {
        this.verticePadre = verticePadre;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
}
