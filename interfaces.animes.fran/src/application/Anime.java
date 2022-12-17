package application;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Anime {
	private int id;
	private SimpleStringProperty nombre;
	private SimpleStringProperty estudio;
	private SimpleIntegerProperty temporadas;
	private SimpleBooleanProperty en_emision;
	
	public Anime(int id, String nombre, String editorial, int temporadas,
			boolean en_emision) {
		super();
		this.id = id;
		this.nombre = new SimpleStringProperty(nombre);
		this.estudio = new SimpleStringProperty(editorial);
		this.temporadas = new SimpleIntegerProperty(temporadas);
		this.en_emision = new SimpleBooleanProperty(en_emision);
	}
	public Anime(String nombre, String editorial, int temporadas,
			boolean en_emision) {
		super();
		this.nombre = new SimpleStringProperty(nombre);
		this.estudio = new SimpleStringProperty(editorial);
		this.temporadas = new SimpleIntegerProperty(temporadas);
		this.en_emision = new SimpleBooleanProperty(en_emision);
		}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return this.nombre.get();
	}
	public void setNombre(String nombre) {
		this.nombre = new SimpleStringProperty(nombre);
	}
	public String getEstudio() {
		return estudio.get();
	}
	public void setEstudio(String editorial) {
		this.estudio = new SimpleStringProperty(editorial);
	}
	public int getTemporadas() {
		return temporadas.get();
	}
	public void setTemporadas(int temporadas) {
		this.temporadas = new SimpleIntegerProperty(temporadas);
	}
	public boolean getEn_emision() {
		return en_emision.get();
	}
	public void setEn_emision(boolean en_emision) {
		this.en_emision = new SimpleBooleanProperty(en_emision);
	}
	
	
	
	

}
