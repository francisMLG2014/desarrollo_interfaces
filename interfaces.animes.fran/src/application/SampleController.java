package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	private ChoiceBox chbEstudio;
	@FXML
	private TextField txtTemporadas;
	@FXML
	private ChoiceBox chbEn_emision;
	@FXML
	private TableView<Anime> tableAnime;
	@FXML
	private TableColumn<Anime, String> columnNombre;
	@FXML
	private TableColumn<Anime, String> columnEstudio;
	@FXML
	private TableColumn<Anime, Integer> columnTemporadas;
	@FXML
	private TableColumn<Anime, Boolean> columnEn_emision;
	@FXML
	private Button btnAnadir;
	@FXML
	private Button btnBorrar;

	private ObservableList<Anime> listaAnimes = this.getAnimesBD();
	public ObservableList<String> listaEstudio = FXCollections.observableArrayList("Mappa","Kyoto Animation","Wit","Toei Animation");
	public ObservableList<Boolean> listaEn_emision = FXCollections.observableArrayList(true,false);

	@FXML
	private void initialize() {
		chbEstudio.setItems(listaEstudio);
		chbEn_emision.setItems(listaEn_emision);
		
		
		
		columnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		columnEstudio.setCellValueFactory(new PropertyValueFactory<>("Estudio"));
		columnTemporadas.setCellValueFactory(new PropertyValueFactory<>("Temporadas"));
		columnEn_emision.setCellValueFactory(new PropertyValueFactory<>("En_emision"));
		tableAnime.setItems(listaAnimes);
	}

	@FXML
	public void anadirAnime(ActionEvent e) {
		if (txtNombre.getText().isBlank() || chbEstudio.getValue() == null ||  txtTemporadas.getText().isBlank()
				|| chbEn_emision.getValue() == null) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Error al insertar");
			alerta.setHeaderText("Datos introducidos erroneos");
			alerta.setContentText("Por favor, introduzca los datos");
			alerta.showAndWait();
		} else {
			if(esNumero( txtTemporadas.getText())) {
			 try {
				Anime l = new Anime(txtNombre.getText(), chbEstudio.getValue().toString(), Integer.parseInt(txtTemporadas.getText()),
						(boolean) chbEn_emision.getValue());
				/**
				 * Introducimos en bd
				 */
				insertarAnime(l);
				listaAnimes.add(l);
				txtNombre.clear();
				chbEstudio.getSelectionModel().clearSelection();
				txtTemporadas.clear();
				chbEn_emision.getSelectionModel().clearSelection();
			 }catch(Exception ex){
				 Alert alerta3 = new Alert(AlertType.ERROR);
					alerta3.setTitle("Error al insertar");
					alerta3.setHeaderText("Ese nombre ya existe en la base de datos");
					alerta3.setContentText("Por favor, introduzca un nombre válido");
					alerta3.showAndWait();
			 }
		}else {
			Alert alerta2 = new Alert(AlertType.ERROR);
			alerta2.setTitle("Error al insertar");
			alerta2.setHeaderText("Cantidad introducida erronea");
			alerta2.setContentText("Por favor, introduzca un número válido");
			alerta2.showAndWait();
			
		}
			
		}

	}

	@FXML
	public void borrarAnime(ActionEvent e) {

		int seleccionTabla = tableAnime.getSelectionModel().getSelectedIndex();
		if (seleccionTabla <= -1) {
			Alert alerta3 = new Alert(AlertType.ERROR);
			alerta3.setTitle("Error al borrar");
			alerta3.setHeaderText("Fila no seleccionada");
			alerta3.setContentText("Por favor, seleccione una fila antes de borrar");
			alerta3.showAndWait();
		} else {
			Anime l=tableAnime.getSelectionModel().getSelectedItem();
			/**
			 * Borrar de bd
			 */
			borrarAnime(l);
			tableAnime.getItems().remove(seleccionTabla);
			tableAnime.getSelectionModel().clearSelection();
		}
	}

	

	private ObservableList<Anime> getAnimesBD() {
		ObservableList<Anime> listaAnimesBD=null;
		try {
			listaAnimesBD = FXCollections.observableArrayList();
			DatabaseConnection db = new DatabaseConnection();
			String query="select * from animes";
			Connection conect = db.getConnection();
			PreparedStatement ps=conect.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Anime lib=new Anime(rs.getInt("id"),rs.getString("nombre"),rs.getString("estudio"),rs.getInt("temporadas"),rs.getBoolean("en_emision"));
				listaAnimesBD.add(lib);
			}
			conect.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAnimesBD;

	}
	private void insertarAnime(Anime l) throws SQLException{
		DatabaseConnection db = new DatabaseConnection();
		Connection c=db.getConnection();
		String query="INSERT INTO animes (nombre,estudio,temporadas,en_emision) VALUES (?,?,?,?)";
		try {
			PreparedStatement ps=c.prepareStatement(query);
			ps.setString(1, l.getNombre());
			ps.setString(2, l.getEstudio());
			ps.setInt(3, l.getTemporadas());
			ps.setBoolean(4, l.getEn_emision());
			ps.execute();
		}finally {
			c.close();
		}
	}
	
	private void borrarAnime(Anime l) {
		DatabaseConnection db = new DatabaseConnection();
		Connection c=db.getConnection();
		String query="DELETE FROM animes WHERE nombre=?";
		
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
	private boolean esNumero(String n) {

		try {
			Integer.parseInt(n);
			return true;
		} catch (NumberFormatException exc) {
			return false;
		}

	}

}