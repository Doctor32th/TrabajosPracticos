module com.mycompany.gestorpracticas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.gestorpracticas to javafx.fxml;
    opens models;
    
    exports com.mycompany.gestorpracticas;
}
