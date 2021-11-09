package com.mycompany.gestorpracticas;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Actividad;


/**
 * En la fecha lo suyo es poner Date. Pero poniendo String no cambiaria nada
 * @author LuisRobbeToichoaSaut
 */
public class PrimaryController implements Initializable{

    @FXML
    private TextField txtactividad;
    @FXML
    private Spinner<Double> duracion;
    @FXML
    private DatePicker fecha;
    @FXML
    private TableView<Actividad> tabla;
    @FXML
    private TableColumn<Actividad, Long> cid;
    @FXML
    private TableColumn<Actividad, String> cactividad;
    @FXML
    private TableColumn<Actividad, Double> cduracion;
    @FXML
    private TableColumn<Actividad, Date> cfecha;
    @FXML
    private Button btnañadir;

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void añadiractividad(ActionEvent event) {
        Actividad a = new Actividad();
        a.setId(0L);
        a.setNombre(txtactividad.getText());
        a.setDuracion(duracion.getValue());
                
        LocalDate localDate = fecha.getValue();
        Date fechaConvertida = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println( fechaConvertida );
        a.setFecha(fechaConvertida);
        
        tabla.getItems().add(a);
    }

    //Este es el spinner. Límite inferior, superior, inicial y frecuencia
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory svf = new DoubleSpinnerValueFactory(0,8,1,0.5);
        duracion.setValueFactory(svf);
        
        cfecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        cactividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cduracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        
    }
}
