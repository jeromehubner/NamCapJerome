package namcap.main.gameObject;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;


public class Fantome extends NamCap {

//	public Fantome(int x, int y, float largeur, float hauteur) {
//		super(x, y, largeur, hauteur);
//		vitesseDeplacement = 3;
//		memorizeGoRight();
//	}
	
	
	public Fantome(Vector2 position){
		super(position);
		vitesseDeplacement = 3;
		memorizeGoRight();
	}
	

	@Override
	public void update(float delta) {
		super.update(delta);

		moveRandom();
	}
	
	
	/**
	 * Méthode qui comprend les 2 méthodes randomX() et randomY() alternées.
	 */
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
}
