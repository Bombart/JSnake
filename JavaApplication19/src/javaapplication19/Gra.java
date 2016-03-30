package javaapplication19;

import java.awt.Rectangle;


public class Gra implements Runnable {

	private boolean start = false;
	private boolean migawka1 = false;
	private boolean migawka2 = false;
	private boolean skonczona = false;														
	private int wynik = 0;
	private int pozostalyWynik = 0;
	private int sekundy = 0;
	private int minuty = 0;
        private int poziomTrudnosci=0;	//0=łatwy
	private  int poziomTrudnosci_thread;
	private boolean pauza = false;
	private boolean restart = false;
	private boolean startMenu = true;

	
	private String menuWybor = "Start";

	private Snake snake;
	private Gra Gra;
	private Ciastko Ciastko;


	public void startGame(Gra Gra) {										
		start = true;//gra wystartowala
		Thread t = new Thread(Gra);
		menuWybor = "Zrestartuj gre";

		switch(Gra.pobierzPoziomTrudnosci()) {
			case 0:
				Gra.ustalPoziomTrudnosci_thread(300);
				break;
			case 1:
				Gra.ustalPoziomTrudnosci_thread(200);
				break;
			case 2:
				Gra.ustalPoziomTrudnosci_thread(100);
				break;		
		}
		
		t.start();
	}
	
	public void restartGame() {
		snake.ustalSnakeX(100);
		snake.ustalSnakeY(100);
		snake.ustalWLewo(false);
		snake.ustalDoGory(false);
		snake.ustalNaDol(false);
		snake.ustalWPrawo(true);
		Gra.ustalSkonczona(false);
		Gra.ustalWynik(0);
		Gra.ustalSekundy(0);
		Gra.ustalMinuty(0);
		Gra.ustalPozostalyWynik(100);
		Gra.ustalPauza(false);
		snake.pobierzListe().clear();
		snake.pobierzListe().add(new Rectangle(snake.pobierzSnakeX(), snake.pobierzSnakeY(), 10, 10));
	}


	public void zakonczGre() {															
		start = false;

		//Tworzę migawke
		for(int i=0; i<4; i++) {			
			migawka1 = true;
			Interfejs.f1.repaint();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			migawka1 = false;
			migawka2 = true;
			Interfejs.f1.repaint();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			migawka2 = false;
		}
		
		//Wyświetl koniec gry i wynik
		migawka1 = false;
		migawka2 = false;
		skonczona = true;
		menuWybor = "Zrestartuj gre";											
		Interfejs.f1.repaint();
		
	}
	

	
	public void run() {
		
		snake = new Snake();
		Ciastko = new Ciastko();
		Ciastko.ustalCiastko(Ciastko);
		Projekt.interfejs.ustalCiastko(Ciastko);							
		Projekt.interfejs.ustalSnake(snake);
		
		snake.pobierzListe().add(new Rectangle(snake.pobierzSnakeX(), snake.pobierzSnakeY(), 10, 10));

		long czasStart = System.currentTimeMillis();
		long aktualnyCzas = 0;

		//logika gry
		while(Gra.start==true) {
			
				if(Gra.czyPauza()==false) {

					
					zderzenieSciana();
					
					
					stworzCiastko();
					
					
					if(Gra.pobierzPozostalyWynik()>0) {
						Gra.ustalPozostalyWynik(Gra.pobierzPozostalyWynik()-1);
					}
				
					//sprawdzenie zderzen
					zderzenieCiastko();
						
					
					zderzenieSnake();
					
					//rysowanie snake'a
					snake.pobierzListe().add(new Rectangle(snake.pobierzSnakeX(), snake.pobierzSnakeY(), 10, 10));
					snake.pobierzListe().remove(0);
					
					
					aktualnyCzas = System.currentTimeMillis();
					if(aktualnyCzas - czasStart >= 901) {						
						if(Gra.pobierzSekundy()<59) {
							Gra.ustalSekundy(Gra.pobierzSekundy()+1);
						}else if(Gra.pobierzSekundy()>=59) {
							Gra.ustalSekundy(0);
							Gra.ustalMinuty(Gra.pobierzMinuty()+1);
						}	
						czasStart += aktualnyCzas - czasStart + 100;
					}
				}
			
			Interfejs.f1.repaint();
			

			try {
				Thread.sleep(Gra.pobierzPoziomTrudnosci_thread());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void zderzenieSciana() {
		if(snake.czyDoGory()==true) {
			if(snake.pobierzSnakeY()<20) {
				zakonczGre();
			}else{
				snake.ustalSnakeY(snake.pobierzSnakeY() - 15);
			}
			
		}else if(snake.czyNaDol()==true) {
			if(snake.pobierzSnakeY()>509) {
				zakonczGre();
			}else {
				snake.ustalSnakeY(snake.pobierzSnakeY() + 15);
			}
		}else if(snake.czyWLewo()==true) {
			if(snake.pobierzSnakeX()<20) {
				zakonczGre();
			}else {
				snake.ustalSnakeX(snake.pobierzSnakeX() - 15);
			}
		}else if(snake.czyWPrawo()==true) {
			if(snake.pobierzSnakeX()>570) {
				zakonczGre();
			}else {
				snake.ustalSnakeX(snake.pobierzSnakeX() + 15);
			}
		}
	}
	
	
	
	public void zderzenieSnake() {
		for(int i=1; i<snake.pobierzListe().size()-1; i++) {
			
			if(i+1<snake.pobierzListe().size()) {
				if(snake.pobierzListe().get(0).intersects(snake.pobierzListe().get(i+1))) {//czy nachodzi na siebie
					zakonczGre();
				}
			}
		}
	}
	
	public void zderzenieCiastko() {
		if(Math.abs(Ciastko.pobierzCiastkoX()-snake.pobierzSnakeX())<=8  &&  Math.abs(Ciastko.pobierzCiastkoY()-snake.pobierzSnakeY())<=8) {
			Ciastko.ustalCiastkoLezy(false);

			snake.pobierzListe().add(new Rectangle(snake.pobierzSnakeX(), snake.pobierzSnakeY(), 10, 10));

			Gra.wynik += Gra.pobierzPozostalyWynik();
		}
	}
	
	
	public void stworzCiastko() {
		if(Ciastko.czyCiastkoLezy() == false) {
			Ciastko.ustalCiastkoX((int) (35+Math.random()*502));
			Ciastko.ustalCiastkoY((int) (35+Math.random()*472));
			Ciastko.ustalCiastkoLezy(true);
			Gra.pozostalyWynik = 100;
		}
	}
	
	
	
	public Snake pobierzSnake() {
		return snake;
	}


	public void ustalSnake(Snake snake) {
		this.snake = snake;
	}

	public boolean czyStart() {
		return start;
	}

	public void ustalStart(boolean start) {
		this.start = start;
	}

	public boolean czySkonczona() {
		return skonczona;
	}

	public void ustalSkonczona(boolean skonczona) {
		this.skonczona = skonczona;
	}

	public int pobierzWynik() {
		return wynik;
	}

	public void ustalWynik(int wynik) {
		this.wynik = wynik;
	}

	public Gra pobierzGra() {
		return Gra;
	}


	public void ustalGra(Gra Gra) {
		this.Gra = Gra;
	}

	public boolean czyMigawka1() {
		return migawka1;
	}


	public void ustalMigawka1(boolean migawka1) {
		this.migawka1 = migawka1;
	}


	public boolean czyMigawka2() {
		return migawka2;
	}


	public void ustalMigawka2(boolean migawka2) {
		this.migawka2 = migawka2;
	}


	public boolean czyPauza() {
		return pauza;
	}


	public void ustalPauza(boolean pauza) {
		this.pauza = pauza;
	}


	public boolean czyRestart() {
		return restart;
	}


	public void ustalRestart(boolean restart) {
		this.restart = restart;
	}


	public String pobierzMenuWybor() {
		return menuWybor;
	}


	public void ustalMenuWybor(String menuWybor) {
		this.menuWybor = menuWybor;
	}

	public int pobierzPozostalyWynik() {
		return pozostalyWynik;
	}

	public void ustalPozostalyWynik(int PozostalyWynik) {
		this.pozostalyWynik = PozostalyWynik;
	}



	public int pobierzMinuty() {
		return minuty;
	}
	
		public void ustalPoziomTrudnosci(int poziomTrudnosci) {
		this.poziomTrudnosci = poziomTrudnosci;
	}

	public int pobierzPoziomTrudnosci_thread() {
		return poziomTrudnosci_thread;
	}

	public void ustalPoziomTrudnosci_thread(int poziomTrudnosci_thread) {
		this.poziomTrudnosci_thread = poziomTrudnosci_thread;
	}

	public void ustalMinuty(int minuty) {
		this.minuty = minuty;
	}


	public boolean CzyStartMenu() {
		return startMenu;
	}

	public void setStartmenu(boolean startmenu) {
		this.startMenu = startmenu;
	}
		public int pobierzSekundy() {
		return sekundy;
	}

	public void ustalSekundy(int sekundy) {
		this.sekundy = sekundy;
	}

	public int pobierzPoziomTrudnosci() {
		return poziomTrudnosci;
	}



}
