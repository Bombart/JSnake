package javaapplication19;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Interfejs {
	
	static JFrame f1;
	static JPanel p1;
        
	private Snake snake;
	private Gra Gra;
	private Ciastko Ciastko;
	
	public Interfejs() {
    	Gra = new Gra();
    	Gra.ustalGra(Gra);
	}
	
        private int fontSize=25;
	private int fontSize1=12;
        public Font font=new Font("Helvetica", Font.BOLD, fontSize);
        public Font font1=new Font("Helvetica", Font.BOLD, fontSize1);

	public void createGameWindow() {
		
		f1 = new JFrame("Snake");
		f1.setSize(1000, 578);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
		p1 = new Draw();
		f1.add(p1);

		f1.setVisible(true);

		f1.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(Gra.czyStart()==true) {
					

					if(Gra.czyPauza()==true){
						
						MenuPauza(e);
						
						
					}else if(Gra.czyPauza()==false) {

						MenuGra(e);
						
					}
					
					
				}else if(Gra.czySkonczona()==true) {
					
					MenuKoniecGry(e);
					
				
				}else if(Gra.CzyStartMenu()==true) {

					MenuNormalne(e);
					
				}
			}

                        
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
                        
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
		});	

	}
	

	class Draw extends JPanel {

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			if(Gra.czyStart() == true) {
				
				if(Gra.czyPauza()==true) {
					
					stworzMenuPauza(g2);
					
				}else if(Gra.czyPauza()==false) {

					stworzGre(g2);
					
				}
				
			}else if(Gra.czyMigawka1() == true){

				stworzMigawke1(g2);
				
			}else if(Gra.czyMigawka2()==true) {

				stworzMigawke2(g2);
				
			}else if(Gra.czySkonczona() == true) {
				
				stworzKoniecGry(g2);
				
			}else if(Gra.CzyStartMenu()==true) {

				stworzMenuStart(g2);
				
			}
				
		}
		
	}
	
	// Key event'y
	
	public void MenuPauza(KeyEvent e) {
		//ESC
		if(e.getKeyCode()==27) {
			Gra.ustalPauza(false);
			
		//Gora	
		}else if(e.getKeyCode()==38) {
			if(Gra.pobierzMenuWybor().equals("Wyjdź")) {
				Gra.ustalMenuWybor("Powrót do menu");
				Interfejs.f1.repaint();
			}else if(Gra.pobierzMenuWybor().equals("Powrót do menu")) {
				Gra.ustalMenuWybor("Zrestartuj gre");
				Interfejs.f1.repaint();
			}
			
		//Dol	
		}else if(e.getKeyCode()==40) {
			if(Gra.pobierzMenuWybor().equals("Zrestartuj gre")) {
				Gra.ustalMenuWybor("Powrót do menu");
				Interfejs.f1.repaint();
			}else if(Gra.pobierzMenuWybor().equals("Powrót do menu")) {
				Gra.ustalMenuWybor("Wyjdź");
				Interfejs.f1.repaint();
			}	
		//Enter	
		}else if(e.getKeyCode()==10) {
			switch(Gra.pobierzMenuWybor()) {
			case "Zrestartuj gre":
				Gra.restartGame();
				break;
			case "Wyjdź":
				System.exit(0);
				break;
			case "Powrót do menu":
				Gra.ustalPauza(false);
				Gra.ustalStart(false);
				Gra.restartGame();
				Gra.setStartmenu(true);
				Gra.ustalMenuWybor("Start");
				Interfejs.f1.repaint();
				break;
			}
		}
	}
	
	public void MenuGra(KeyEvent e) {
		if(e.getKeyCode()==38) {
			if(snake.czyNaDol()==false) {
				snake.ustalNaDol(false);
				snake.ustalWPrawo(false);
				snake.ustalWLewo(false);
				snake.ustalDoGory(true);
			}
		}else if(e.getKeyCode()==40) {
			if(snake.czyDoGory()==false) {
				snake.ustalWPrawo(false);
				snake.ustalWLewo(false);
				snake.ustalDoGory(false);
                snake.ustalNaDol(true);
			}
		}else if(e.getKeyCode()==37) {
			if(snake.czyWPrawo()==false) {
				snake.ustalNaDol(false);
				snake.ustalWPrawo(false);
				snake.ustalDoGory(false);
				snake.ustalWLewo(true);
			}
		}else if(e.getKeyCode()==39) {
			if(snake.czyWLewo()==false) {
				snake.ustalNaDol(false);
				snake.ustalWLewo(false);
				snake.ustalDoGory(false);
				snake.ustalWPrawo(true);
			}
		}else if(e.getKeyCode()==27) {
				Gra.ustalPauza(true);	
		}
	}
	
	public void MenuKoniecGry(KeyEvent e) {
		//Up
		if(e.getKeyCode()==38) {
			if(Gra.pobierzMenuWybor().equals("Wyjdź")) {
				Gra.ustalMenuWybor("Zrestartuj gre");
				Interfejs.f1.repaint();
			}	
		//Down	
		}else if(e.getKeyCode()==40) {
			if(Gra.pobierzMenuWybor().equals("Zrestartuj gre")) {
				Gra.ustalMenuWybor("Wyjdź");
				Interfejs.f1.repaint();
			}	
		//Enter
		}else if(e.getKeyCode()==10) {
			switch(Gra.pobierzMenuWybor()) {
			case "Zrestartuj gre":
				Gra.restartGame();
				Gra.startGame(Gra);
				break;
			case "Wyjdź":
				System.exit(0);
				break;
			}
		}
	}
	
	public void MenuNormalne(KeyEvent e) {
		
		if(e.getKeyCode()==38) {
			if(Gra.pobierzMenuWybor().equals("Wyjdź")) {
				Gra.ustalMenuWybor("Trudność");
				Interfejs.f1.repaint();
			}else if(Gra.pobierzMenuWybor().equals("Trudność")) {
				Gra.ustalMenuWybor("Start");
				Interfejs.f1.repaint();
			}		
			
		}else if(e.getKeyCode()==40) {
			if(Gra.pobierzMenuWybor().equals("Start")) {
				Gra.ustalMenuWybor("Trudność");
				Interfejs.f1.repaint();
			}else if(Gra.pobierzMenuWybor().equals("Trudność")) {
				Gra.ustalMenuWybor("Wyjdź");
				Interfejs.f1.repaint();
			}	
	
		}else if(e.getKeyCode()==39) {
			if(Gra.pobierzMenuWybor().equals("Trudność") && Gra.pobierzPoziomTrudnosci()!=2) {
				Gra.ustalPoziomTrudnosci(Gra.pobierzPoziomTrudnosci()+1);
				Interfejs.f1.repaint();
			}
	
		}else if(e.getKeyCode()==37) {
			if(Gra.pobierzMenuWybor().equals("Trudność") && Gra.pobierzPoziomTrudnosci()!=0) {
				Gra.ustalPoziomTrudnosci(Gra.pobierzPoziomTrudnosci()-1);
				Interfejs.f1.repaint();
			}
		}
		
		else if(e.getKeyCode()==10) {
		switch(Gra.pobierzMenuWybor()) {
		case "Start":
			Gra.startGame(Gra);
			break;
		case "Wyjdź":
			System.exit(0);
			break;
		}
	}
	}
	
	// rysowanie

	public void stworzMenuPauza(Graphics2D g2) {
		g2.setColor(Color.green);
		g2.fillRect(0, 0, 600, 600);
		g2.setColor(Color.BLUE);
                g2.setFont(font);
		g2.drawString("Pause menu", 105, 150);
                g2.setFont(font1);
		g2.drawString("Zrestartuj gre", 105, 285);
		g2.drawString("Powrót do menu", 105, 360);
		g2.drawString("Wyjdź", 105, 435);
		
		switch (Gra.pobierzMenuWybor()) {
		  case "Zrestartuj gre":
			g2.fillOval(85, 275, 10, 10);
			break; 
		  case "Powrót do menu":
			g2.fillOval(85, 350, 10, 10);
			break; 
		  case "Wyjdź":
				g2.fillOval(85, 425, 10, 10);
				break; 	
		}	
	}
	
	public void stworzGre(Graphics2D g2) {

		g2.setColor(Color.cyan);
		g2.fillRect(0, 0, 600, 600);

		g2.setColor(Color.BLACK);
		g2.fillRect(590, 0, 15, 600);
		g2.fillRect(0, 0, 15, 600);		
		g2.fillRect(0, 0, 600, 15);
		g2.fillRect(0, 525, 600, 15);
		
	
		//stworz snake'a
		for(int i=1; i<=snake.pobierzListe().size(); i++) { 
			g2.fillRect(snake.pobierzListe().get(i-1).x,snake.pobierzListe().get(i-1).y, 10, 10);
		}
		
		
		
		if(Ciastko.czyCiastkoLezy() == true) {
			g2.setColor(Color.RED);
			g2.fillRect(Ciastko.pobierzCiastkoX(), Ciastko.pobierzCiastkoY(), 10, 10);
		}
		
		
// rysowanie informacji o  grze 
		
		
		g2.setColor(Color.BLACK);
		if(Gra.pobierzMinuty()<10 && Gra.pobierzSekundy()<10) {								
			
			g2.drawString("0"+Gra.pobierzMinuty()+":0"+Gra.pobierzSekundy(), 630, 75);
			
		}else if(Gra.pobierzMinuty()<10 && Gra.pobierzSekundy()>9) {							
			
			g2.drawString("0"+Gra.pobierzMinuty()+":"+Gra.pobierzSekundy(), 630, 75);
			
		}else if(Gra.pobierzMinuty()>9 && Gra.pobierzSekundy()>9) {							
			
			g2.drawString(Gra.pobierzMinuty()+":"+Gra.pobierzSekundy(), 630, 75);
			
		}else if(Gra.pobierzMinuty()>9 && Gra.pobierzSekundy()<10) {							
			
			g2.drawString(Gra.pobierzMinuty()+":0"+Gra.pobierzSekundy(), 630, 75);
			
		}
		
		g2.drawString("Wynik: "+Gra.pobierzWynik(), 630, 150);
		g2.drawString("Pozostało: "+Gra.pobierzPozostalyWynik(), 630, 180);
		
		
		// -------------------------------------------------------------------------------------------
	}
	
	public void stworzMigawke1(Graphics2D g2) {
		g2.setColor(Color.cyan);
		g2.fillRect(0, 0, 600, 600);
		g2.setColor(Color.BLACK);


		g2.setColor(Color.BLACK);
		g2.fillRect(590, 0, 15, 600);
		g2.fillRect(0, 0, 15, 600);		
		g2.fillRect(0, 0, 600, 15);
		g2.fillRect(0, 525, 600, 15);
	

		//snake
		for(int i=1; i<=snake.pobierzListe().size(); i++) { 
			g2.fillRect(snake.pobierzListe().get(i-1).x,snake.pobierzListe().get(i-1).y, 10, 10);
		}
		

		if(Ciastko.czyCiastkoLezy() == true) {
			g2.setColor(Color.red);
			g2.fillRect(Ciastko.pobierzCiastkoX(), Ciastko.pobierzCiastkoY(), 10, 10);
		}
	}
	
	public void stworzMigawke2(Graphics2D g2) {

		g2.setColor(Color.cyan);
		g2.fillRect(590, 0, 15, 600);
		g2.fillRect(0, 0, 15, 600);		
		g2.fillRect(0, 0, 600, 15);
		g2.fillRect(0, 525, 600, 15);
		

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 600, 600);
		

		g2.setColor(Color.cyan);
		g2.fillRect(590, 0, 15, 600);
		g2.fillRect(0, 0, 15, 600);		
		g2.fillRect(0, 0, 600, 15);
		g2.fillRect(0, 525, 600, 15);
	}
	
	public void stworzKoniecGry(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.fillRect(0, 0, 600, 600);
		g2.setColor(Color.WHITE);
                g2.setFont(font);
		g2.drawString("Gra skończona! \n Twój wynik: "+Gra.pobierzWynik(), 100, 100);
                g2.setFont(font1);
		g2.drawString("Zrestartuj gre", 150, 300);
		g2.drawString("Wyjdź", 150, 375);
		
		switch (Gra.pobierzMenuWybor()) {

		  case "Zrestartuj gre":
			g2.fillOval(130, 289, 10, 10);
			break; 
		  case "Wyjdź":
			g2.fillOval(130, 364, 10, 10);
			break;
		  case "Trudność":
			  g2.fillOval(130, 439, 10, 10);
			  break;
		}	
	}

            
            	public void stworzMenuStart(Graphics2D g2) {
		g2.setColor(Color.green);
		g2.fillRect(0, 0, 600, 600);
		g2.setColor(Color.BLUE);
                g2.setFont(font);
		g2.drawString("Snake", 150, 150);
                 g2.setFont(font1);
		g2.drawString("Start", 150, 300);
		g2.drawString("Trudność:", 150, 375);
		g2.drawString("Wyjdź", 150, 450);
		g2.drawString("Łatwy", 307, 375);
		g2.drawString("Średni", 382, 375);
		g2.drawString("Trudny", 457, 375);

		
		switch (Gra.pobierzMenuWybor()) {

		  case "Start":
			g2.fillOval(130, 289, 10, 10);
			break; 
		  case "Trudność":
			g2.fillOval(130, 364, 10, 10);
			break; 
		  case "Wyjdź":
				g2.fillOval(130, 439, 10, 10);
				break; 
		}	
		
		
		switch(Gra.pobierzPoziomTrudnosci()) {
		case 0:
			g2.drawRect(305, 360, 35, 17);		
			break;
		case 1:
			g2.drawRect(378, 360, 43, 17);
			break;
		case 2:
			g2.drawRect(455, 360, 41, 17);
			break;

		}
	}

	public Gra pobierzGra() {
		return Gra;
	}


	public void ustalGra(Gra Gra) {
		this.Gra = Gra;
	}


	public Ciastko pobierzCiastko() {
		return Ciastko;
	}


	public void ustalCiastko(Ciastko Ciastko) {
		this.Ciastko = Ciastko;
	}
	
	public Snake pobierzSnake() {
		return snake;
	}


	public void ustalSnake(Snake snake) {
		this.snake = snake;
	}
	

}
