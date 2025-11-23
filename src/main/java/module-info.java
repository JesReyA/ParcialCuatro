module org.example.grafos {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.xml;
    requires Herramientas;


    //requires org.example.grafos;
    opens unam.fes.aragon to javafx.fxml;
    exports unam.fes.aragon;
    exports unam.fes.aragon.controller;
    opens unam.fes.aragon.controller to javafx.fxml;
    exports unam.fes.aragon.inicio;
    opens unam.fes.aragon.inicio to javafx.fxml;
}