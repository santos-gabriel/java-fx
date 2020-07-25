package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entites.Person;

public class ViewController implements Initializable {
	@FXML
	private TextField txtNumber2;
	@FXML
	private TextField txtNumber1;
	@FXML
	private Button btSum;

	@FXML
	private Label labelResult;

	@FXML
	private ComboBox<Person> comboboxPerson;

	private ObservableList<Person> obsList;
	
	@FXML
	private Button btAll;
	
	@FXML
	public void onBtAllAction() {
		comboboxPerson.getItems().forEach(e -> System.out.println(e));
	}
	
	@FXML
	public void onComboboxPersonAction() {
		Person person = comboboxPerson.getSelectionModel().getSelectedItem();
		System.out.println(person);
	}
	
	public void onBtSumAction() {
		try {

		} catch (NumberFormatException e) {
			Alerts.showAlert("Error", "Parse error", e.getMessage(), AlertType.ERROR);
		}
		Locale.setDefault(Locale.US);
		double number1 = Double.parseDouble(txtNumber1.getText());
		double number2 = Double.parseDouble(txtNumber2.getText());
		double sum = number1 + number2;
		labelResult.setText(String.format("%.2f", sum));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Constraints.setTextFieldDouble(txtNumber1);
		Constraints.setTextFieldDouble(txtNumber2);

		List<Person> list = new ArrayList<>();
		list.add(new Person(1, "Maria", "Maria@gmail.com"));
		list.add(new Person(1, "Alex", "Alex@gmail.com"));
		list.add(new Person(1, "Bob", "Bob@gmail.com"));
		list.add(new Person(1, "Judth", "Judth@gmail.com"));
		obsList = FXCollections.observableArrayList(list);
		comboboxPerson.setItems(obsList);

		Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>() {
			@Override
			protected void updateItem(Person item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};
		comboboxPerson.setCellFactory(factory);
		comboboxPerson.setButtonCell(factory.call(null));

	}

}
