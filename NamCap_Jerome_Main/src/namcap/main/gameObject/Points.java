package namcap.main.gameObject;

import java.util.HashMap;

import namcap.main.gameWorld.GameRenderer;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Points {

	private HashMap<Vector2, Point> hashMapPoints;


	public Points(MapObjects mapObjects) {

		hashMapPoints = new HashMap<>(mapObjects.getCount());

		Rectangle boundingMapObject;
		Vector2 positionObject;

		for(MapObject m : mapObjects){
			boundingMapObject = ((RectangleMapObject)m).getRectangle();

			positionObject = new Vector2(boundingMapObject.x * GameRenderer.unitScale,
					boundingMapObject.y * GameRenderer.unitScale);

			hashMapPoints.put(positionObject, new Point(positionObject));
		}
	}


	/**
	 * Méthode qui vérifie si le NamCap entre en collision avec 
	 * un des points.
	 * @param namCap
	 * @return true si le NamCap est en collision avec un des points.
	 */
	public void collision(NamCap namCap){
		Vector2 position = new Vector2(Math.round(namCap.getPosition().x), Math.round(namCap.getPosition().y));

		
		if(hashMapPoints.containsKey(position)){
			
			Point point = hashMapPoints.get(position);
			
			if(namCap.getBoundingCircle().overlaps(
					point.getBoundingCircle())){

				hashMapPoints.remove(position);
				
				namCap.setScore(namCap.getScore() + point.getValeur());
			}
		}
	}


	public HashMap<Vector2, Point> getHashMapPoints() {
		return hashMapPoints;
	}
}
