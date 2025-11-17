package unam.fes.aragon.logicaGrafos;

import unam.fes.aragon.logicaGrafos.clasesAuxiliares.Vertice;

import java.io.File;
import java.util.Scanner;

public class Grafo <E> {
    private int verticesMaximos;
    private Vertice<E>[] listaVertices;
    private int[][] matrizAdyacencia;
    private int numeroVertices;


    /**
     * Recibe la ruta relativa de un archivo con el formato necesario para crear un grafo
     *
     *
     * Metodo incompleto
     * @param rutaRelativaArchivo
     */
    public Grafo(String rutaRelativaArchivo){
        this.numeroVertices = 0;
        try{
            File archivo = new File(rutaRelativaArchivo);
            Scanner scanner = new Scanner(archivo);
            int contadorInterno = 0;
            while(scanner.hasNextLine()){
                if(contadorInterno < 1){
                    String linea = scanner.nextLine();
                    verticesMaximos = Integer.parseInt(linea);
                    contadorInterno++;
                } else if (contadorInterno == 2) {
                    listaVertices = new Vertice[verticesMaximos];
                    String linea = scanner.nextLine();
                    String [] lineaSeparada = linea.split(",");
                    for(int i = 0; i < lineaSeparada.length; i++){

                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void añadirVertice(E valor){
        listaVertices[numeroVertices++] = new Vertice<>(valor);
    }


}
