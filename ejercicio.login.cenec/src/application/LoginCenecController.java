package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginCenecController {
	@FXML
	private TextField user;
	
	@FXML 
	private PasswordField pass;
	
	@FXML
	private Button btnLogin;
	
	@FXML
	private ImageView foto;
	
	@FXML
	private Label labelMsg;
	
	@FXML
	public void iniciarSesion (ActionEvent event) {
		labelMsg.setText("Bienvenido/a "+user.getText());
		user.clear();
		pass.clear();
	}
}
