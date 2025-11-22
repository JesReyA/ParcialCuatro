package unam.fes.aragon.inicio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Ventana extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Ventana.class.getResource("/unam/fes/aragon/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("GRAFOS");
        stage.setResizable(false);
        scene.getStylesheets().add(getClass().getResource("/unam/fes/aragon/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
