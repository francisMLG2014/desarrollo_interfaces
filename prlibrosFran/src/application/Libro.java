package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Libro {

	private int id;
	private SimpleStringProperty titulo;
	private SimpleStringProperty editorial;
	private SimpleStringProperty autor;
	private SimpleIntegerProperty paginas;
	
	public Libro(String titulo,String editorial,String autor,int paginas) {
		this.titulo=new SimpleStringProperty(titulo);
		this.editorial=new SimpleStringProperty(editorial);
		this.autor=new SimpleStringProperty(autor);
		this.paginas= new SimpleIntegerProperty(paginas);
	}
	public Libro(int id,String titulo,String editorial,String autor,int paginas) {
		this.id=id;
		this.titulo=new SimpleStringProperty(titulo);
		this.editorial=new SimpleStringProperty(editorial);
		this.autor=new SimpleStringProperty(autor);
		this.paginas= new SimpleIntegerProperty(paginas);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return this.titulo.get();
	}
	public void setTitulo(String titulo) {
		this.titulo=new SimpleStringProperty(titulo);
	}
	public String getEditorial() {
		return this.editorial.get();
	}
	public void setEditorial(String editorial) {
		this.editorial=new SimpleStringProperty(editorial);
	}
	public String getAutor() {
		return this.autor.get();
	}
	public void setAutor(String autor) {
		this.autor=new SimpleStringProperty(autor);
	}
	public int getPaginas() {
		return this.paginas.get();
	}
	public void setPaginas(int paginas) {
		this.paginas=new SimpleIntegerProperty(paginas);
	}
	
	
}
