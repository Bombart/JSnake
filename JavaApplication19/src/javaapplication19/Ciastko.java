package javaapplication19;


public class Ciastko {
	
	private int ciastkoX = 0;												
	private int ciastkoY = 0;
	private boolean ciastkoLezy = false;
	private Ciastko ciastko;
	


	public boolean czyCiastkoLezy() {
		return ciastkoLezy;
	}
	public void ustalCiastkoLezy(boolean ciastkoLezy) {
		this.ciastkoLezy = ciastkoLezy;
	}
	public Ciastko pobierzCiastko() {
		return ciastko;
	}
	public void ustalCiastko(Ciastko ciastko) {
		this.ciastko = ciastko;
	}
        	public int pobierzCiastkoX() {
		return ciastkoX;
	}
	public void ustalCiastkoX(int toX) {
		this.ciastkoX = toX;
	}
	public int pobierzCiastkoY() {
		return ciastkoY;
	}
	public void ustalCiastkoY(int toY) {
		this.ciastkoY = toY;
	}

}
