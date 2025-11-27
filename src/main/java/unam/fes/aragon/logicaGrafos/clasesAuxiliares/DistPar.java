package unam.fes.aragon.logicaGrafos.clasesAuxiliares;

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
