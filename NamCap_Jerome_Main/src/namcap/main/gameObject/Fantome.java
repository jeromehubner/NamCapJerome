package namcap.main.gameObject;

import java.util.Random;


public class Fantome extends NamCap {

	public Fantome(int x, int y, float largeur, float hauteur) {
		super(x, y, largeur, hauteur);
		vitesseDeplacement = 4;
		memorizeGoRight();
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		moveRandom();
		//		if(isGoingUp() || isGoingDown() || vitesse.x == 0){		// vitesse.x == 0 car au démarrage il est arrêté
		//			moveRandomX();
		//
		//			if(isGoingLeft() || isGoingRight() || vitesse.y == 0)	// vitesse.y == 0 car au démarrage il est arrêté
		//				moveRandomY();
		//		}
		//		
		//		
		//		if(isGoingLeft() || isGoingRight() || vitesse.y == 0){	// vitesse.y == 0 car au démarrage il est arrêté
		//			moveRandomY();
		//
		//			if(isGoingUp() || isGoingDown() || vitesse.x == 0)		// vitesse.x == 0 car au démarrage il est arrêté
		//				moveRandomX();
		//		}
	}


	private void moveRandomY(){
		int random = new Random().nextInt(2);

		switch(random){
		case 0:
			if(canGoingUp())
				memorizeGoUp();
			break;

		case 1:
			if(canGoingDown())
				memorizeGoDown();
			break;
		}
	}


	private void moveRandomX(){
		int random = new Random().nextInt(2);

		switch(random){
		case 0:
			if(canGoingLeft())
				memorizeGoLeft();
			break;

		case 1:
			if(canGoingRight())
				memorizeGoRight();
			break;
		}
	}


	private void moveRandom(){
		int random = new Random().nextInt(2);

		switch(random){
		case 0:
			if(isGoingUp() || isGoingDown() || vitesse.x == 0){		// vitesse.x == 0 car au démarrage il est arrêté
				moveRandomX();

				if(isGoingLeft() || isGoingRight() || vitesse.y == 0)	// vitesse.y == 0 car au démarrage il est arrêté
					moveRandomY();
			}
			break;

		case 1:
			if(isGoingLeft() || isGoingRight() || vitesse.y == 0){	// vitesse.y == 0 car au démarrage il est arrêté
				moveRandomY();

				if(isGoingUp() || isGoingDown() || vitesse.x == 0)		// vitesse.x == 0 car au démarrage il est arrêté
					moveRandomX();
			}
			break;
		}
	}
}
