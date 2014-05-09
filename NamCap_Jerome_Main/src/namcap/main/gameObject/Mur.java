package namcap.main.gameObject;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Mur {
	
	private Rectangle boundingRectangle;

	
	public Mur(Rectangle boundingRectangle) {
		this.boundingRectangle = boundingRectangle;
	}
	
	public boolean collision(NamCap namCap) {
		return Intersector.overlaps(namCap.getBoundingCircle(), boundingRectangle);
	}
	
	
	
//	private Rectangle boundingRectangle;
//	private Vector2 position;
//	private float largeurBlock, hauteurBlock;
//
//	/**
//	 * Le constructeur de cette classe permet de choisir le nombre
//	 * de blocks (32x32) que contient le mur. Il permet de créer selon
//	 * la position voulue (origine du groupe de blocks en haut à gauche).
//	 * des murs horizontaux ou verticaux.
//	 * @param position
//	 * @param nombreDeBlock
//	 * @param isVertical
//	 */
//	public Mur(Vector2 position, int nombreDeBlock, boolean isVertical) {
//		
//		if(isVertical){
//			this.largeurBlock = 32;
//			this.hauteurBlock = 32 * nombreDeBlock;
//		}
//		else {
//			this.largeurBlock = 32 *nombreDeBlock;
//			this.hauteurBlock = 32;
//		}
//		
//		
//		this.position = position;
//
//		boundingRectangle = new Rectangle(position.x, position.y, largeurBlock, hauteurBlock);
//	}
//	
//	
//	
//	public boolean collision(NamCap namCap) {
//		return Intersector.overlaps(namCap.getBoundingCircle(), boundingRectangle);
//	}
//
//
//	public Vector2 getPosition() {
//		return position;
//	}
//
//
//	public float getLargeur() {
//		return largeurBlock;
//	}
//
//
//	public float getHauteur() {
//		return hauteurBlock;
//	}
}
