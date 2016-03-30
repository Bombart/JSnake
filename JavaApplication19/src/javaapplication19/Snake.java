package javaapplication19;

import java.awt.Rectangle;
import java.util.ArrayList;


public class Snake {
	
	private int snakeX = 100;
	private int snakeY = 300;
        private boolean snakeLewo = false;
	private boolean snakePrawo = true;
	private boolean snakeGora = false;
	private boolean snakeDol = false;
	private  ArrayList<Rectangle> lista = new ArrayList<Rectangle>();
	
	
	public  ArrayList<Rectangle> pobierzListe() {
		return lista;
	}
	public  void ustalListe(ArrayList<Rectangle> lista) {
		this.lista = lista;
	}
	public int pobierzSnakeX() {
		return snakeX;
	}
	public void ustalSnakeX(int snakeX) {
		this.snakeX = snakeX;
	}
	public int pobierzSnakeY() {
		return snakeY;
	}
	public void ustalSnakeY(int snakeY) {
		this.snakeY = snakeY;
	}
	public boolean czyDoGory() {
		return snakeGora;
	}
	public void ustalDoGory(boolean snakeGora) {
		this.snakeGora = snakeGora;
	}
	public boolean czyNaDol() {
		return snakeDol;
	}
	public void ustalNaDol(boolean snakeDol) {
		this.snakeDol = snakeDol;
	}
	public boolean czyWLewo() {
		return snakeLewo;
	}
	public void ustalWLewo(boolean snakeLewo) {
		this.snakeLewo = snakeLewo;
	}
	public boolean czyWPrawo() {
		return snakePrawo;
	}
	public void ustalWPrawo(boolean snakePrawo) {
		this.snakePrawo = snakePrawo;
	}

	
	

}
