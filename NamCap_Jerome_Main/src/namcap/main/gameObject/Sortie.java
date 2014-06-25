package namcap.main.gameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

/**
 * Classe permettant d'instancier un objet de type Sortie pour que le NamCap
 * devra récupérer pour terminer le jeu. Celle-ci pourra être affichée lorsque
 * tous les points auront été capturés.
 * 
 * @author jerome
 * 
 */
public class Sortie {

	private Vector2 position;
	private float largeur, hauteur;
	private Circle boundingCircle;

	public Sortie(Vector2 position) {
		this.position = position;
		largeur = 1;
		hauteur = 1;

		boundingCircle = new Circle(position.x + largeur / 2, position.y
				+ hauteur / 2, largeur / 4);
	}

	public boolean collision(NamCap namCap) {
		if (Intersector.overlaps(namCap.getBoundingCircle(),
				getBoundingCircle())) {
			Gdx.input.vibrate(200);
			return true;
		}
		return false;
	}

	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	public Vector2 getPosition() {
		return position;
	}

	public float getLargeur() {
		return largeur;
	}

	public float getHauteur() {
		return hauteur;
	}
}
