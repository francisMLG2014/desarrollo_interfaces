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

public class IndexController {
	@FXML
	private TextField txtTitulo;
	@FXML
	private ChoiceBox chbEditorial;
	@FXML
	private TextField txtAutor;
	@FXML
	private TextField txtPaginas;
	@FXML
	private TableView<Libro> tableLibro;
	@FXML
	private TableColumn<Libro, String> columnTitulo;
	@FXML
	private TableColumn<Libro, String> columnEditorial;
	@FXML
	private TableColumn<Libro, String> columnAutor;
	@FXML
	private TableColumn<Libro, Integer> columnPaginas;
	@FXML
	private Button btnAnadir;
	@FXML
	private Button btnBorrar;

	private ObservableList<Libro> listaLibros = this.getLibrosBD();
	public ObservableList<String> listaEditoriales = FXCollections.observableArrayList("Planeta", "Altaya", "Kadokawa",
			"Penguin Libros");

	@FXML
	private void initialize() {
		chbEditorial.setItems(listaEditoriales);

		columnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		columnEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
		columnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		columnPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		tableLibro.setItems(listaLibros);
	}

	@FXML
	public void anadirLibro(ActionEvent e) {
		if (txtTitulo.getText().isBlank() || chbEditorial.getValue() == null || txtAutor.getText().isBlank()
				|| txtPaginas.getText().isBlank()) {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Error al insertar");
			alerta.setHeaderText("Datos introducidos erroneos");
			alerta.setContentText("Por favor, introduzca los datos");
			alerta.showAndWait();
		} else {
			if (!esNumero(txtPaginas.getText())) {
				Alert alerta2 = new Alert(AlertType.ERROR);
				alerta2.setTitle("Error al insertar");
				alerta2.setHeaderText("Cantidad introducida erronea");
				alerta2.setContentText("Por favor, introduzca un número válido");
				alerta2.showAndWait();
			} else {
				Libro l = new Libro(txtTitulo.getText(), chbEditorial.getValue().toString(), txtAutor.getText(),
						Integer.parseInt(txtPaginas.getText()));
				/**
				 * Introducimos en bd
				 */
				insertarLibro(l);
				listaLibros.add(l);
				txtTitulo.clear();
				chbEditorial.getSelectionModel().clearSelection();
				txtAutor.clear();
				txtPaginas.clear();
			}
		}

	}

	@FXML
	public void borrarLibro(ActionEvent e) {

		int seleccionTabla = tableLibro.getSelectionModel().getSelectedIndex();
		if (seleccionTabla <= -1) {
			Alert alerta3 = new Alert(AlertType.ERROR);
			alerta3.setTitle("Error al borrar");
			alerta3.setHeaderText("Fila no seleccionada");
			alerta3.setContentText("Por favor, seleccione una fila antes de borrar");
			alerta3.showAndWait();
		} else {
			Libro l=tableLibro.getSelectionModel().getSelectedItem();
			/**
			 * Borrar de bd
			 */
			borrarLibro(l);
			tableLibro.getItems().remove(seleccionTabla);
			tableLibro.getSelectionModel().clearSelection();
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

	private ObservableList<Libro> getLibrosBD() {
		ObservableList<Libro> listaLibrosBD=null;
		try {
			listaLibrosBD = FXCollections.observableArrayList();
			DatabaseConnection db = new DatabaseConnection();
			Connection conect = db.getConnection();
			String query="select * from libros";
			PreparedStatement ps=conect.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Libro lib=new Libro(rs.getInt("id"),rs.getString("titulo"),rs.getString("editorial"),rs.getString("autor"),rs.getInt("paginas"));
				listaLibrosBD.add(lib);
			}
			conect.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaLibrosBD;

	}
	private void insertarLibro(Libro l) {
		DatabaseConnection db = new DatabaseConnection();
		Connection c=db.getConnection();
		String query="INSERT INTO libros (titulo,editorial,autor,paginas) VALUES (?,?,?,?)";
		try {
			PreparedStatement ps=c.prepareStatement(query);
			ps.setString(1, l.getTitulo());
			ps.setString(2, l.getEditorial());
			ps.setString(3, l.getAutor());
			ps.setInt(4, l.getPaginas());
			ps.execute();
		} catch (SQLException e) {
			try {
				c.close();
			} catch (SQLException e1) {
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
	
	private void borrarLibro(Libro l) {
		DatabaseConnection db = new DatabaseConnection();
		Connection c=db.getConnection();
		String query="DELETE FROM libros WHERE titulo=?";
		
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(query);
			ps.setString(1, l.getTitulo());
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
