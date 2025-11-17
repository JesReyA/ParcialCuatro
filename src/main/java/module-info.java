module org.example.grafos {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.grafos to javafx.fxml;
    exports org.example.grafos;
}