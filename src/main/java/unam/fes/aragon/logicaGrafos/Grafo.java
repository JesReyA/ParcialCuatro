package unam.fes.aragon.logicaGrafos;

import unam.fes.aragon.dinamicas.listasimple.ListaSimple;
import unam.fes.aragon.logicaGrafos.clasesAuxiliares.DistPar;
import unam.fes.aragon.logicaGrafos.clasesAuxiliares.Vertice;

import java.io.File;
import java.util.Scanner;

public class Grafo <E> {
    private int valorInfinito = Integer.MAX_VALUE;
    private int verticesMaximos;
    private ListaSimple<Vertice<String>> listaVertices;
    private int[][] matrizAdyacencia;

    //Atributos utilizados en dijkstra
    private int nVerts; //numero actual de vertices en el grafo
    private int nTree;
    private DistPar recorridos[]; //almacena los vertices junto el camino mas corto para llegar a ellos

    private int verticeActual;
    private int distanciaAlVerticeActual;



    /**
     *
     * Recibe la ruta relativa de un archivo con el formato necesario para crear un grafo
     *
     * @param rutaRelativaArchivo
     */
    public Grafo(String rutaRelativaArchivo) {
        try {
            File archivo = new File(rutaRelativaArchivo);
            Scanner scanner = new Scanner(archivo);

            listaVertices = new ListaSimple<>();
            nVerts = 0;
            nTree = 0;
            int contadorInterno = 0;

            while (scanner.hasNextLine()) {
                if (contadorInterno < 1) {
                    String linea = scanner.nextLine();
                    verticesMaximos = Integer.parseInt(linea);
                    contadorInterno++;
                } else if (contadorInterno == 1) {
                    String linea = scanner.nextLine();
                    String[] lineaSeparada = linea.split(",");
                    for (String s : lineaSeparada) {
                        Vertice<String> verticeTmp = new Vertice<>(String.valueOf(s));
                        listaVertices.agregarEnCola(verticeTmp);
                    }
                    contadorInterno++;
                } else {
                    matrizAdyacencia = new int[verticesMaximos][verticesMaximos];
                    for (int i = 0; i < matrizAdyacencia.length; i++) {
                        String linea = scanner.nextLine();
                        String[] lineaSeparada = linea.split(",");
                        for (int j = 0; j < matrizAdyacencia[i].length; j++) {
                            int valorPeso = Integer.parseInt(lineaSeparada[j]);
                            matrizAdyacencia[i][j] = valorPeso;
                        }
                    }
                }
            }
            nVerts = verticesMaximos;
            recorridos = new DistPar[verticesMaximos];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void imprimirMatrizAdyacencia() {
        for (int i = 0; i < matrizAdyacencia.length; i++) { // Iterate through rows
            for (int j = 0; j < matrizAdyacencia[i].length; j++) { // Iterate through columns in the current row
                System.out.print(matrizAdyacencia[i][j] + " ");
            }
            System.out.println(); // New line after each row
        }
    }

    public int[][] obtenerMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public ListaSimple<Vertice<String>> obtenerNombres() {
        return listaVertices;
    }

    public void path() {
        int inicioArbol = 0;
        listaVertices.obtenerNodo(inicioArbol).estaEnArbol = true;
        nTree = 1;


        //Trasferir la primera fila de la matriz de adyacencia a sPath
        for (int j = 0; j < nVerts; j++) {
            int tempDist = matrizAdyacencia[inicioArbol][j];

            if (tempDist == 0 && j != inicioArbol) {
                tempDist = valorInfinito;
            }

            recorridos[j] = new DistPar(tempDist, inicioArbol);
        }

        while (nTree < nVerts) {
            int indexMin = getMin();
            int minDist = recorridos[indexMin].distancia;
            if (minDist == valorInfinito) {
                System.out.println("no hay vertices alcanzables");
                break;
            } else {
                verticeActual = indexMin;
                distanciaAlVerticeActual = recorridos[indexMin].distancia;
            }
            listaVertices.obtenerNodo(verticeActual).estaEnArbol = true;
            nTree++;
            adjustPath();
        }
        imprimirRecorridos(); //Faltaba esta linea (lol)
        nTree = 0;
        for (int j = 0; j < nVerts; j++)
            listaVertices.obtenerNodo(j).estaEnArbol = false;
    }

    public int getMin(){
        int minDist = valorInfinito;
        int indexMin = 0;

        for(int j = 1; j<nVerts; j++){
            if(!listaVertices.obtenerNodo(j).estaEnArbol && recorridos[j].distancia < minDist){
              minDist = recorridos[j].distancia;
              indexMin = j;
            }
        }
        return indexMin;
    }
    public void adjustPath() {
        int column = 1;

        while (column < nVerts) {
            if (listaVertices.obtenerNodo(column).estaEnArbol) {
                column++;
                continue;
            }

            int currentToFringe = matrizAdyacencia[verticeActual][column];
            if (currentToFringe > 0) {
                int startToFringe = distanciaAlVerticeActual + currentToFringe;
                int sPathDist = recorridos[column].distancia;

                if (startToFringe < sPathDist) {
                    recorridos[column].verticePadre = verticeActual;
                    recorridos[column].distancia = startToFringe;
                }
            }
            column++;
        }
    }

    public StringBuilder imprimirRecorridos(){
        StringBuilder recorridosDijkstra= new StringBuilder("");
        for(int j=0; j<nVerts; j++){
            recorridosDijkstra.append((listaVertices.obtenerNodo(j).valor + "="));
            if(recorridos[j].distancia == valorInfinito){
                recorridosDijkstra.append("inf");
            }else {
                recorridosDijkstra.append(recorridos[j].distancia);
            }
            String parent = listaVertices.obtenerNodo(recorridos[j].verticePadre).valor;
            recorridosDijkstra.append("(" + parent + ")\n");
        }
        return recorridosDijkstra;
    }
}

