package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class IndexController {
	@FXML
	private TextField txtTitulo;
	@FXML
	private ChoiceBox chbEditorial;
	@FXML
	private TextField txtAutor;
	@FXML
	private TextField txtpaginas;
	@FXML
	private TableView <Libro> tableLibro;
	@FXML
	private TableColumn<Libro,String> columnTitulo;
	@FXML
	private TableColumn<Libro,String> columnEditorial;
	@FXML
	private TableColumn<Libro,String> columnAutor;
	@FXML
	private TableColumn<Libro,Integer> columnPaginas;
	
	
	private ObservableList<Libro> listaLibros=FXCollections.observableArrayList(new Libro("La Biblia","Planeta","Jesusito",300));
	public ObservableList<String> listaEditoriales=FXCollections.observableArrayList("Planeta","Altaya","Kadokawa","Penguin Libros");
	
	@FXML
	private void initialize() {
		chbEditorial.setItems(listaEditoriales);
		
		columnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		columnEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
		columnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		columnPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		tableLibro.setItems(listaLibros);
	}
	
	
}
