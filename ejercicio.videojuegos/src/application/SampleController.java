package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class SampleController {
	@FXML
	private TextField txtNombre;
	@FXML
	private ChoiceBox chbConsola;
	@FXML
	private TextField txtPrecio;
	@FXML
	private ChoiceBox chbPEGI;
	@FXML
	private TableView<Juego> tableJuego;
	@FXML
	private TableColumn<Juego, String> columnNombre;
	@FXML
	private TableColumn<Juego, String> columnConsola;
	@FXML
	private TableColumn<Juego, String> columnPrecio;
	@FXML
	private TableColumn<Juego, Integer> columnPEGI;
	@FXML
	private Button btnAnadir;
	@FXML
	private Button btnBorrar;

	private ObservableList<Juego> listaJuegos = this.getJuegosBD();
	public ObservableList<String> listaConsolas = FXCollections.observableArrayList("PS4", "PS5", "Xbox One","Nintendo Switch",
			"PC");
	public ObservableList<String> listaPEGI = FXCollections.observableArrayList("3", "7", "12",
			"16","18");

	@FXML
	private void initialize() {
		chbConsola.setItems(listaConsolas);
		chbPEGI.setItems(listaPEGI);
		columnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		columnConsola.setCellValueFactory(new PropertyValueFactory<>("Consola"));
		columnPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
		columnPEGI.setCellValueFactory(new PropertyValueFactory<>("PEGI"));
		tableJuego.setItems(listaJuegos);
	}

	@FXML
	public void anadirJuego(ActionEvent e) {
		if (txtNombre.getText().isBlank() || chbConsola.getValue() == null || txtPrecio.getText().isBlank()
				|| chbPEGI.getValue() == null) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Error al insertar");
			alerta.setHeaderText("Datos introducidos erroneos");
			alerta.setContentText("Por favor, introduzca los datos");
			alerta.showAndWait();
		} else {
			if (!esNumero(txtPrecio.getText())) {
				Alert alerta2 = new Alert(AlertType.ERROR);
				alerta2.setTitle("Error al insertar");
				alerta2.setHeaderText("Cantidad introducida erronea");
				alerta2.setContentText("Por favor, introduzca un número válido");
				alerta2.showAndWait();
			} else {
				try {
				Juego l = new Juego(txtNombre.getText(), Float.parseFloat(txtPrecio.getText()), chbConsola.getValue().toString(),
						Integer.parseInt(chbPEGI.getValue().toString()));
				/**
				 * Introducimos en bd
				 */
				insertarJuego(l);
				listaJuegos.add(l);
				txtNombre.clear();
				chbConsola.getSelectionModel().clearSelection();
				txtPrecio.clear();
				chbPEGI.getSelectionModel().clearSelection();
				}catch(Exception e1){
					Alert alerta3 = new Alert(AlertType.ERROR);
					alerta3.setTitle("Error al insertar");
					alerta3.setHeaderText("Ese nombre ya existe en la base de datos");
					alerta3.setContentText("Por favor, introduzca un nombre válido");
					alerta3.showAndWait();
				}
				
			}
		}

	}

	@FXML
	public void borrarJuego(ActionEvent e) {

		int seleccionTabla = tableJuego.getSelectionModel().getSelectedIndex();
		if (seleccionTabla <= -1) {
			Alert alerta3 = new Alert(AlertType.ERROR);
			alerta3.setTitle("Error al borrar");
			alerta3.setHeaderText("Fila no seleccionada");
			alerta3.setContentText("Por favor, seleccione una fila antes de borrar");
			alerta3.showAndWait();
		} else {
			Juego l=tableJuego.getSelectionModel().getSelectedItem();
			/**
			 * Borrar de bd
			 */
			borrarJuego(l);
			tableJuego.getItems().remove(seleccionTabla);
			tableJuego.getSelectionModel().clearSelection();
		}
	}

	private boolean esNumero(String n) {

		try {
			Float.parseFloat(n);
			return true;
		} catch (NumberFormatException exc) {
			return false;
		}

	}

	private ObservableList<Juego> getJuegosBD() {
		ObservableList<Juego> listaJuegosBD=null;
		try {
			listaJuegosBD = FXCollections.observableArrayList();
			DatabaseConnection db = new DatabaseConnection();
			String query="select * from juegos";
			Connection conect = db.getConnection();
			PreparedStatement ps=conect.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Juego lib=new Juego(rs.getInt("id"),rs.getString("nombre"),rs.getFloat("precio"),rs.getString("consola"),rs.getInt("PEGI"));
				listaJuegosBD.add(lib);
			}
			conect.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaJuegosBD;

	}
	private void insertarJuego(Juego l) throws SQLException  {
		
		DatabaseConnection db = new DatabaseConnection();
		Connection c=db.getConnection();
		String query="INSERT INTO juegos (nombre,precio,consola,PEGI) VALUES (?,?,?,?)";
		try {
			PreparedStatement ps=c.prepareStatement(query);
			ps.setString(1, l.getNombre());
			ps.setFloat(2, l.getPrecio());
			ps.setString(3, l.getConsola());
			ps.setInt(4, l.getPEGI());
			ps.execute();
		
		}finally {
			c.close();
		}
		
		

		
	}
	
	private void borrarJuego(Juego l) {
		DatabaseConnection db = new DatabaseConnection();
		Connection c=db.getConnection();
		String query="DELETE FROM juegos WHERE nombre=?";
		
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(query);
			ps.setString(1, l.getNombre());
			ps.execute();
		} catch (SQLException e) {
			try {
				c.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
