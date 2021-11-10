package com.mycompany.gestorpracticas;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.Actividad;

/**
 * En la fecha lo suyo es poner Date. Pero poniendo String no cambiaria nada
 *
 * @author LuisRobbeToichoaSaut
 */
public class PrimaryController implements Initializable {

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
    private TableColumn<Actividad, String> cfecha;
    @FXML
    private Button btnañadir;
    @FXML
    private ChoiceBox<String> tipo;
    @FXML
    private TableColumn<Actividad, String> ctipo;

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void añadiractividad(ActionEvent event) {
        Actividad a = new Actividad();
        a.setId(0L);
        a.setNombre(txtactividad.getText());
        a.setDuracion(duracion.getValue());
        a.setTipo(tipo.getValue());

        LocalDate localDate = fecha.getValue();
        Date fechaConvertida = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        a.setFecha(fechaConvertida);

        tabla.getItems().add(a);

        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }

    private void loadAll() {
        EntityManager em = ObjectDBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Actividad> q = em.createQuery("SELECT a FROM Actividad a", Actividad.class);
        tabla.getItems().addAll(q.getResultList());
    }

    //Este es el spinner. Límite inferior, superior, inicial y frecuencia
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory svf = new DoubleSpinnerValueFactory(0, 8, 1, 0.5);
        duracion.setValueFactory(svf);

        tipo.getItems().addAll("FCT", "DUAL");
        tipo.setValue("DUAL");

        //cfecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        cfecha.setCellValueFactory(
                (var row) -> {

                    DateFormat formatoCorto = DateFormat.getDateInstance(DateFormat.SHORT);
                    String fechaCorta = formatoCorto.format(row.getValue().getFecha());
                    return new SimpleStringProperty(fechaCorta);
                }
        );
        cactividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cduracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));

        ctipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        fecha.setValue(LocalDate.now());
        

        loadAll();

    }
}
