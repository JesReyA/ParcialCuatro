package unam.fes.aragon.logicaGrafos;

import unam.fes.aragon.dinamicas.listasimple.ListaSimple;
import unam.fes.aragon.logicaGrafos.clasesAuxiliares.Vertice;

import java.io.File;
import java.util.Scanner;

public class Grafo <E> {
    private int verticesMaximos;
    private ListaSimple<Vertice<String>> listaVertices;
    private int[][] matrizAdyacencia;


    /**
     * Recibe la ruta relativa de un archivo con el formato necesario para crear un grafo
     *
     * @param rutaRelativaArchivo
     */
    public Grafo(String rutaRelativaArchivo){
        try{
            File archivo = new File(rutaRelativaArchivo);
            Scanner scanner = new Scanner(archivo);
            listaVertices = new ListaSimple<>();
            int contadorInterno = 0;
            while(scanner.hasNextLine()){
                if(contadorInterno < 1){
                    String linea = scanner.nextLine();
                    verticesMaximos = Integer.parseInt(linea);
                    contadorInterno++;
                } else if (contadorInterno == 1) {
                    String linea = scanner.nextLine();
                    String [] lineaSeparada = linea.split(",");
                    for (String s : lineaSeparada) {
                        Vertice<String> verticeTmp = new Vertice<>(String.valueOf(s));
                        listaVertices.agregarEnCola(verticeTmp);
                    }
                    contadorInterno++;
                }else{
                    matrizAdyacencia = new int[verticesMaximos][verticesMaximos];
                    for (int i = 0; i < matrizAdyacencia.length; i++) {
                        String linea = scanner.nextLine();
                        String [] lineaSeparada = linea.split(",");
                        for (int j = 0; j < matrizAdyacencia[i].length; j++) {
                            int valorPeso = Integer.parseInt(lineaSeparada[j]);
                            matrizAdyacencia[i][j] = valorPeso;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void imprimirMatrizAdyacencia(){
        for (int i = 0; i < matrizAdyacencia.length; i++) { // Iterate through rows
            for (int j = 0; j < matrizAdyacencia[i].length; j++) { // Iterate through columns in the current row
                System.out.print(matrizAdyacencia[i][j] + " ");
            }
            System.out.println(); // New line after each row
        }
    }

    public int[][] obtenerMatrizAdyacencia(){
        return matrizAdyacencia;
    }
}
