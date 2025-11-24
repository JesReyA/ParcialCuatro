package unam.fes.aragon.logicaGrafos.clasesAuxiliares;

import unam.fes.aragon.logicaGrafos.Grafo;

public class pruebaGrafos {
    public static void main(String[] args) {
        Grafo<Integer> testGrafo =new Grafo<>("DatosGrafo.txt");
        testGrafo.imprimirMatrizAdyacencia();
        testGrafo.path();
    }
}
