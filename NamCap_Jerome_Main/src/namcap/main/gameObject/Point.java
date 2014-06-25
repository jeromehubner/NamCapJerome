package namcap.main.gameObject;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Point {

	private Vector2 position;
	private float largeur, hauteur;
	private Circle boundingCircle;

	public Point(Vector2 position) {
		// TODO : Attention aux valeurs en dur ???
		this.position = position;
		largeur = 1;
		hauteur = 1;
		boundingCircle = new Circle(position.x + largeur / 2, position.y
				+ hauteur / 2, largeur / 2);
	}

	/**
	 * Verifie si le namCpa entre en collision avec le point.
	 * 
	 * @param namCap
	 * @return
	 */
	public boolean collision(NamCap namCap) {
		if (namCap.getBoundingCircle().overlaps(getBoundingCircle()))
			return true;
		return false;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	public float getLargeur() {
		return largeur;
	}

	public float getHauteur() {
		return hauteur;
	}
}
