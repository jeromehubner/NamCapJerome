package namcap.main.gameObject;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class Fantome extends NamCap {

	public Fantome(Vector2 position) {
		super(position);
		// memorizeGoRight();
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		moveRandom();
	}

	/**
	 * Méthode qui comprend les 2 méthodes randomX() et randomY() alternées.
	 */
	private void moveRandom() {
		int random = new Random().nextInt(2);

		switch (random) {
		case 0:
			if (isGoingUp() || isGoingDown() || vitesse.x == 0) { // vitesse.x
																	// == 0 car
																	// au
																	// démarrage
																	// il est
																	// arrêté
				moveRandomX();

				if (isGoingLeft() || isGoingRight() || vitesse.y == 0) // vitesse.y
																		// == 0
																		// car
																		// au
																		// démarrage
																		// il
																		// est
																		// arrêté
					moveRandomY();
			}
			break;

		case 1:
			if (isGoingLeft() || isGoingRight() || vitesse.y == 0) { // vitesse.y
																		// == 0
																		// car
																		// au
																		// démarrage
																		// il
																		// est
																		// arrêté
				moveRandomY();

				if (isGoingUp() || isGoingDown() || vitesse.x == 0) // vitesse.x
																	// == 0 car
																	// au
																	// démarrage
																	// il est
																	// arrêté
					moveRandomX();
			}
			break;
		}
	}

	private void moveRandomY() {
		int random = new Random().nextInt(2);

		switch (random) {
		case 0:
			if (canGoingUp())
				memorizeGoUp();
			break;

		case 1:
			if (canGoingDown())
				memorizeGoDown();
			break;
		}
	}

	private void moveRandomX() {
		int random = new Random().nextInt(2);

		switch (random) {
		case 0:
			if (canGoingLeft())
				memorizeGoLeft();
			break;

		case 1:
			if (canGoingRight())
				memorizeGoRight();
			break;
		}
	}

	/**
	 * Méthode utilisée pour réagir au collision avec le namCap.
	 * 
	 * @param namCap
	 * @return
	 */
	public boolean collision(NamCap namCap) {
		if (Intersector.overlaps(namCap.getBoundingCircle(),
				getBoundingCircle()))
			return true;
		return false;
	}

	/**
	 * Méthode utilisée pour réagir au collision avec une surprise.
	 * 
	 * @param surprise
	 * @return
	 */
	public boolean collision(Surprise surprise) {
		if (Intersector.overlaps(surprise.getBoundingCircle(),
				getBoundingCircle()))
			return true;
		return false;
	}
}
