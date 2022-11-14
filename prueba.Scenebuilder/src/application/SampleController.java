package application;

import java.awt.Button;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SampleController {
	@FXML
	private TextField txtMensaje;
	
	@FXML
	private Button btnMensaje;
	
	@FXML
	private Label labelMensaje;
	
	@FXML
	public void mostrarMensaje(ActionEvent event) {
		labelMensaje.setText(txtMensaje.getText());
	}
}
