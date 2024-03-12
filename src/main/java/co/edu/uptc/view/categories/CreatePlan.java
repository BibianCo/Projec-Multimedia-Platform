package co.edu.uptc.view.categories;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.Main;
import co.edu.uptc.controller.PlanController;
import co.edu.uptc.model.Plan;
import co.edu.uptc.persistence.FilePersistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreatePlan implements Initializable {
    @FXML
    private TextField namePlan;
    @FXML
    private TextField descriptionName;
    @FXML
    private TextField priceName;
    @FXML
    private TextField daysName;

    @FXML
    private TableView<Plan> tableView;

    @FXML
    private TableColumn<Plan, Integer> idColumn, priceColumn, durationColumn;

    @FXML
    private TableColumn<Plan, String> nameColumn, descriptionColumn;

    @FXML
    private Label messageError1, messageError2, messageError3, messageError4;

    private PlanController planController;
    private FilePersistence<Plan> filePersistence;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("namePlan"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Type type = new TypeToken<ArrayList<Plan>>() {
        }.getType();
        filePersistence = new FilePersistence<>(type, "plans");
        planController = new PlanController(filePersistence);
        filePersistence.createFile();
        loadItems();
    }

    private void loadItems() {
        tableView.getItems().setAll(new ArrayList<>());
        tableView.getItems().addAll(planController.getAll());
    }

    @FXML
    private void createPlan() throws IOException {
        if (validarString(namePlan.getText(), messageError1) || validarString(descriptionName.getText(), messageError2)
                || validarNumber(priceName.getText(), messageError3)
                || validarNumber(daysName.getText(), messageError4)) {

        } else {

            Plan plan = new Plan(setId(), namePlan.getText(), descriptionName.getText(),
                    Integer.parseInt(priceName.getText()), Integer.parseInt(daysName.getText()));

            if (planController.add(plan)) {
                namePlan.clear();
                descriptionName.clear();
                priceName.clear();
                daysName.clear();
                messageError1.setText("");
                messageError2.setText("");
                messageError3.setText("");
                messageError4.setText("");
                loadItems();
            } else {
                messageError4.setText("Cannot create plan");
            }

        }

    }

    private boolean validarString(String valor, Label mensajeError) {
        if (valor.isEmpty() || valor.trim().isEmpty()) {
            mensajeError.setText("Empty string error, fill the boxes");
            return true;
        } else if (!valor.matches("\\b[a-zA-Z]+(\\s+[a-zA-Z]+)*\\b")) {
            mensajeError.setText("Only words are accepted.");
            return true;
        } else {
            mensajeError.setText("");
            return false;
        }

    }

    private boolean validarNumber(String valor, Label mensajeError) {
        if (valor.isEmpty() || valor.trim().isEmpty()) {
            mensajeError.setText("Empty string error, fill the boxes");
            return true;
        } else if (valor.length() > 6) {
            mensajeError.setText("Enter a valid value. It must be up to 6 digits.");
            return true;
        } else if (!valor.matches("\\d*")) {
            mensajeError.setText("Only numbers are accepted.");
            return true;
        } else {
            mensajeError.setText("");
            return false;
        }

    }

    public int setId() {
        if (planController.getAll().isEmpty()) {
            return 1;
        } else {
            return planController.getAll().get(planController.getAll().size() - 1).getId() + 1;
        }

    }

    @FXML
    private void salir() throws IOException {
        Main.setRoot("menuadmi-view");
    }

}
