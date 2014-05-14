package namcap.main.gameObject;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Mur {
	
	private Rectangle boundingRectangle;
	
	public Mur(Rectangle boundingRectangle) {
		this.boundingRectangle = boundingRectangle;
	}
	
	public void testCollision(NamCap namCap) {
		if(Intersector.overlaps(namCap.getBoundingCircle(), this.boundingRectangle))
			if(namCap.isGoingLeft())
				namCap.reactToLeftCollision();
			else if (namCap.isGoingRight())
				namCap.reactToRightCollision();
			else if (namCap.isGoingUp())
				namCap.reactToUpCollision();
			else if (namCap.isGoingDown())
				namCap.reactToDownCollision();
	}
	

	private Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}
	
}
