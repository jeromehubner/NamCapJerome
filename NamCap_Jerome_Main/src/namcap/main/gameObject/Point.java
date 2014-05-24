package namcap.main.gameObject;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Point {

	private int valeur;
	private Vector2 position;
	private float largeur, hauteur;
	private Circle boundingCircle;
	
	
	public Point(Vector2 position) {
		this.position = position;
		valeur = 10;
		boundingCircle = new Circle(position, 1);
		largeur = 1;
		hauteur = 1;
	}
	

	public Vector2 getPosition() {
		return position;
	}


	public Circle getBoundingCircle() {
		return boundingCircle;
	}


	public int getValeur() {
		return valeur;
	}


	public float getLargeur() {
		return largeur;
	}


	public float getHauteur() {
		return hauteur;
	}
}
