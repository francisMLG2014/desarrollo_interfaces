package application;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Juego {
	private int id;
	private SimpleStringProperty nombre;
	private SimpleFloatProperty precio;
	private SimpleStringProperty consola;
	private SimpleIntegerProperty PEGI;
	public Juego(String nombre,float precio, String consola, int PEGI) {
		this.nombre=new SimpleStringProperty(nombre);
		this.precio=new SimpleFloatProperty(precio);
		this.consola=new SimpleStringProperty(consola);
		this.PEGI=new SimpleIntegerProperty(PEGI);
	}
	public Juego(int id,String nombre,float precio, String consola, int PEGI) {
		this.id=id;
		this.nombre=new SimpleStringProperty(nombre);
		this.precio=new SimpleFloatProperty(precio);
		this.consola=new SimpleStringProperty(consola);
		this.PEGI=new SimpleIntegerProperty(PEGI);
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
		this.nombre=new SimpleStringProperty(nombre);
	}
	public float getPrecio() {
		return this.precio.get();
	}
	public void setPrecio(float precio) {
		this.precio=new SimpleFloatProperty(precio);
	}
	public String getConsola() {
		return this.consola.get();
	}
	public void setConsola(String consola) {
		this.consola=new SimpleStringProperty(consola);
	}
	public int getPEGI() {
		return this.PEGI.get();
	}
	public void setPaginas(int PEGI) {
		this.PEGI=new SimpleIntegerProperty(PEGI);
	}

}
