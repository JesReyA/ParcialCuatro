package unam.fes.aragon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;

import unam.fes.aragon.dinamicas.listasimple.ListaSimple;
import unam.fes.aragon.logicaGrafos.Grafo;
import unam.fes.aragon.logicaGrafos.clasesAuxiliares.Vertice;


import java.io.File;

public class Controller {

    @FXML
    Button buttonElegirArchivo;

    @FXML
    Pane grafoConstruido;

    @FXML
    TextArea matrizAdyacenciaDijkstra;

    private FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("TXT", "*.txt");
    private FileChooser.ExtensionFilter ex2 = new FileChooser.ExtensionFilter("CSV", "*.csv");
    private File archivoElegido;


    public void handleElegirArchivo(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(ex1, ex2);
        archivoElegido= fileChooser.showOpenDialog(stage);

        try{
            if(archivoElegido != null){
                mostrarAlerta(Alert.AlertType.INFORMATION, "EXITO",  "Se adjuntó con éxito el archivo" + "\n" + "El grafo se mostrará a continuación junto con su ruta más corta");
                Grafo<Integer> grafoNuevo =new Grafo<>(archivoElegido.getAbsolutePath());

                int[][] nuevaMatriz = grafoNuevo.obtenerMatrizAdyacencia();
                matrizAdyacenciaDijkstra.appendText("\nMATRIZ ADYACENCIA GRAFO\n\n");
                for(int i=0; i< nuevaMatriz.length; i++){
                    for(int j=0; j< nuevaMatriz.length; j++){
                        matrizAdyacenciaDijkstra.appendText(String.valueOf(nuevaMatriz[i][j] + " "));
                    }
                    matrizAdyacenciaDijkstra.appendText(String.valueOf("\n"));
                }
                ListaSimple<Vertice<String>> nombres = grafoNuevo.obtenerNombres();
                dibujarGrafoVisual(nuevaMatriz, nombres);

                matrizAdyacenciaDijkstra.appendText("\nDIJKSTRA\n");
                grafoNuevo.path();
                matrizAdyacenciaDijkstra.appendText(String.valueOf(grafoNuevo.imprimirRecorridos()));
            }
        }catch (Exception e){
            mostrarAlerta(Alert.AlertType.ERROR, "ERROR", e.getMessage() + "Revise el archivo ingresado y reintente");
        }
    }

    private void dibujarGrafoVisual(int[][] matriz, ListaSimple<Vertice<String>> listaVertices) {
        grafoConstruido.getChildren().clear();

        int numNodos = matriz.length;
        double paneWidth = grafoConstruido.getWidth();
        double paneHeight = grafoConstruido.getHeight();

        double centerX = paneWidth / 2;
        double centerY = paneHeight / 2;
        double radioLayout = Math.min(paneWidth, paneHeight) / 3;

        String[] arregloNombres = new String[numNodos];

        if (listaVertices != null) {
            for (int k = 0; k < numNodos; k++) {
                Vertice<String> vertice = listaVertices.obtenerNodo(k);
                if (vertice != null && vertice.valor != null) {
                    arregloNombres[k] = vertice.valor;
                } else {
                    arregloNombres[k] = "V" + k;
                }
            }
        } else {
            for (int k = 0; k < numNodos; k++) arregloNombres[k] = "V" + k;
        }

        double[][] pos = new double[numNodos][2];

        for (int i = 0; i < numNodos; i++) {
            double angulo = 2 * Math.PI * i / numNodos;
            pos[i][0] = centerX + radioLayout * Math.cos(angulo);
            pos[i][1] = centerY + radioLayout * Math.sin(angulo);
        }

        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                if (matriz[i][j] > 0 && i < j) {
                    Line linea = new Line(pos[i][0], pos[i][1], pos[j][0], pos[j][1]);
                    linea.setStroke(Color.BLACK);
                    linea.setStrokeWidth(1.5);
                    grafoConstruido.getChildren().add(linea);

                    double factorPosicion = 0.35;
                    double labelX = pos[i][0] + (pos[j][0] - pos[i][0]) * factorPosicion;
                    double labelY = pos[i][1] + (pos[j][1] - pos[i][1]) * factorPosicion;

                    StackPane etiquetaPeso = new StackPane();

                    Text textoPeso = new Text(String.valueOf(matriz[i][j]));
                    textoPeso.setFont(Font.font("Arial", 14));
                    textoPeso.setFill(Color.BLUE);
                    textoPeso.setStyle("-fx-font-weight: bold;");

                    javafx.scene.shape.Rectangle fondoTexto = new javafx.scene.shape.Rectangle(20, 15);
                    fondoTexto.setFill(Color.rgb(230, 230, 230));

                    etiquetaPeso.getChildren().addAll(fondoTexto, textoPeso);

                    etiquetaPeso.setLayoutX(labelX - 10);
                    etiquetaPeso.setLayoutY(labelY - 7.5);

                    grafoConstruido.getChildren().add(etiquetaPeso);
                }
            }
        }

        for (int i = 0; i < numNodos; i++) {
            StackPane nodoVisual = new StackPane();
            nodoVisual.setLayoutX(pos[i][0] - 20);
            nodoVisual.setLayoutY(pos[i][1] - 20);

            Circle circulo = new Circle(20);
            circulo.setFill(Color.LIGHTGRAY);
            circulo.setStroke(Color.BLACK);

            Text textoNombre = new Text(arregloNombres[i]);
            textoNombre.setBoundsType(TextBoundsType.VISUAL);
            textoNombre.setFont(Font.font("Arial", 16));

            nodoVisual.getChildren().addAll(circulo, textoNombre);
            grafoConstruido.getChildren().add(nodoVisual);
        }
    }

    public void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje ){
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
