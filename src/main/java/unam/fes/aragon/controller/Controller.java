package unam.fes.aragon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import unam.fes.aragon.logicaGrafos.Grafo;

import java.io.File;
import java.nio.channels.FileChannel;

public class Controller {

    @FXML
    Button buttonElegirArchivo;

    @FXML
    TextArea matrizAdyacenciaDijkstra, grafoConstruido;

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

            }
        }catch (Exception e){
            mostrarAlerta(Alert.AlertType.ERROR, "ERROR", e.getMessage() + "Revise el archivo ingresado y reintente");
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
