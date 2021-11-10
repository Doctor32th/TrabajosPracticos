module com.mycompany.gestorpracticas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javax.persistence;
    requires java.sql;
    requires objectdb;
    requires java.base;
    
    opens com.mycompany.gestorpracticas to javafx.fxml, java.sql;
    opens models;
    
    exports com.mycompany.gestorpracticas;
}
