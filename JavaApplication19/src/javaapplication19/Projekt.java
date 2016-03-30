package javaapplication19;

import java.awt.EventQueue;


public class Projekt {	
	
	public static Interfejs interfejs;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfejs = new Interfejs();
					interfejs.createGameWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}




