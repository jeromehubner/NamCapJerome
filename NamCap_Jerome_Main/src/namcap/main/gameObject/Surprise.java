package namcap.main.gameObject;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Surprise {
	
	private Vector2 position;
	private float largeur, hauteur;
	private Circle boundingCircle;
	
	
	public Surprise(Vector2 position) {
		this.position = position;
		boundingCircle = new Circle(position, 1);
		largeur = 1;
		hauteur = 1;
	}


	public void collision(){
		
	}
	
	
	public float getLargeur() {
		return largeur;
	}


	public float getHauteur() {
		return hauteur;
	}


	public Vector2 getPosition() {
		return position;
	}
}