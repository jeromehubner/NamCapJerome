package namcap.main.gameObject;

import java.util.Collection;
import java.util.HashMap;

import namcap.main.gameWorld.GameRenderer;
import namcap.main.helpers.AssetLoaderMap;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Points {

	private int valeur = 10;
	private HashMap<Vector2, Point> hashMapPoints;

	public Points() {
		// Initialisation du MapObjets depuis la Classe AssetLoaderMap
		MapObjects mapObjects = AssetLoaderMap.layerObjectPoints.getObjects();
		
		hashMapPoints = new HashMap<Vector2, Point>(mapObjects.getCount());

		Rectangle boundingMapObject;
		Vector2 positionObject;

		for (MapObject m : mapObjects) {
			boundingMapObject = ((RectangleMapObject) m).getRectangle();

			positionObject = new Vector2(boundingMapObject.x
					* GameRenderer.unitScale, boundingMapObject.y
					* GameRenderer.unitScale);

			hashMapPoints.put(positionObject, new Point(positionObject));
		}
	}

	/**
	 * Méthode qui vérifie si le NamCap entre en collision avec un des points
	 * contenu dans la hashMapPoints de cette classe.
	 * 
	 * @param namCap
	 * @return true si le NamCap est en collision avec un des points.
	 */
	public boolean collision(NamCap namCap) {
		/*
		 * La position du NamCap est arrondie à l'entier le plus proche pour
		 * récupérer la cellule du NamCap et vérifier s'il elle contient un
		 * point.
		 */
		Vector2 positionDuNamCap = new Vector2(
				Math.round(namCap.getPosition().x), Math.round(namCap
						.getPosition().y));

		if (hashMapPoints.containsKey(positionDuNamCap))
			if (hashMapPoints.get(positionDuNamCap).collision(namCap)) {
				hashMapPoints.remove(positionDuNamCap);
				return true;
			}
		return false;
	}

	/**
	 * 
	 * @return Une collection des points contenus dans la map.
	 */
	public Collection<Point> getCollectionPoints() {
		return hashMapPoints.values();
	}

	public HashMap<Vector2, Point> getHashMapPoints() {
		return hashMapPoints;
	}

	/**
	 * 
	 * @return La valeur d'un point.
	 */
	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
}
