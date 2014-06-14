package namcap.main.gameObject;

import namcap.main.gameWorld.GameRenderer;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Class utilisée pour récupérer toutes les positions de tous les objets 
 * dynamiques du terrain (startPositionOfNamCap, startPositionOfFantomes,
 * positionsOfSurprises). 
 * @author jerome
 *
 */
public class ObjectPositions {
	
	private Array<Vector2> namCapStartPositions;
	private Array<Vector2> fantomeStartPositions;
	private Array<Vector2> surprisePositions;
	
	
	public ObjectPositions(MapLayer mapLayer) {
		
		namCapStartPositions = new Array<Vector2>();
		fantomeStartPositions = new Array<Vector2>();
		surprisePositions = new Array<Vector2>();
		
		
		MapObjects mapObjects = mapLayer.getObjects();
		Rectangle rectangle;
		
		
		for(MapObject m : mapObjects){
			rectangle = ((RectangleMapObject)m).getRectangle();
		
			if(m.getName().equals("namCapStart")){
				namCapStartPositions.add(new Vector2(rectangle.getX() *GameRenderer.unitScale, rectangle.getY()*GameRenderer.unitScale));
		
			}
			else if(m.getName().equals("fantomeStart")){
				fantomeStartPositions.add(new Vector2(rectangle.getX() *GameRenderer.unitScale, rectangle.getY() *GameRenderer.unitScale));
		
			}
			else if(m.getName().equals("surprise")){
				surprisePositions.add(new Vector2(rectangle.getX() *GameRenderer.unitScale, rectangle.getY() *GameRenderer.unitScale));
				
			}
		}
	}
	
	
	public Vector2 getNamCapStartPosition(){
		int randomPosition = MathUtils.random(namCapStartPositions.size -1);
		
		return namCapStartPositions.get(randomPosition);
	}
	
	
	public Vector2 getFantomeStartPosition(){
		int randomPosition = MathUtils.random(fantomeStartPositions.size -1);
		
		return fantomeStartPositions.get(randomPosition);
	}
	
	
	public Vector2 getSurprisePosition(){
		int randomPosition = MathUtils.random(surprisePositions.size -1);
		
		return surprisePositions.get(randomPosition);
	}
}
