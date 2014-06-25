package namcap.main.gameObject;

import namcap.main.gameWorld.GameRenderer;
import namcap.main.helpers.AssetLoaderMap;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Classe utilisée pour récupérer toutes les positions de tous les objets
 * dynamiques du terrain (startPositionOfNamCap, startPositionOfFantomes,
 * positionsOfSurprises).
 * 
 * @author jerome
 * 
 */
public class StartObjectPositions {

	private Array<Vector2> namCapStartPositions;
	private Array<Vector2> fantomeStartPositions;
	private Array<Vector2> surprisePositions;
	private Array<Vector2> sortiePositions;

	public StartObjectPositions() {

		/*------- MAPOBJECTS -------*/
		MapObjects mapObjectsNamCapStartPosition = AssetLoaderMap.layerObjectStartNamCap
				.getObjects();
		MapObjects mapObjectsFantomeStartPosition = AssetLoaderMap.layerObjectStartFantome
				.getObjects();
		MapObjects mapObjectsSurpriseStartPosition = AssetLoaderMap.layerObjectStartSurprise
				.getObjects();
		MapObjects mapObjectsSortiePosition = AssetLoaderMap.layerObjectSortie
				.getObjects();

		/*------- TABLEAuX DES START POSITIONS -------*/
		namCapStartPositions = new Array<Vector2>();
		fantomeStartPositions = new Array<Vector2>();
		surprisePositions = new Array<Vector2>();
		sortiePositions = new Array<Vector2>();

		Rectangle rectangle;

		/*------- NAMCAP START -------*/
		for (MapObject m : mapObjectsNamCapStartPosition) {
			rectangle = ((RectangleMapObject) m).getRectangle();
			namCapStartPositions.add(new Vector2(rectangle.getX()
					* GameRenderer.unitScale, rectangle.getY()
					* GameRenderer.unitScale));
		}

		/*------- FANTOME START -------*/
		for (MapObject m : mapObjectsFantomeStartPosition) {
			rectangle = ((RectangleMapObject) m).getRectangle();
			fantomeStartPositions.add(new Vector2(rectangle.getX()
					* GameRenderer.unitScale, rectangle.getY()
					* GameRenderer.unitScale));
		}

		/*------- SURPRISE START -------*/
		for (MapObject m : mapObjectsSurpriseStartPosition) {
			rectangle = ((RectangleMapObject) m).getRectangle();
			surprisePositions.add(new Vector2(rectangle.getX()
					* GameRenderer.unitScale, rectangle.getY()
					* GameRenderer.unitScale));
		}

		/*------- SORTIE -------*/
		for (MapObject m : mapObjectsSortiePosition) {
			rectangle = ((RectangleMapObject) m).getRectangle();
			sortiePositions.add(new Vector2(rectangle.getX()
					* GameRenderer.unitScale, rectangle.getY()
					* GameRenderer.unitScale));
		}

	}

	/**
	 * 
	 * @return Une position aléatoire pour le NamCap.
	 */
	public Vector2 getNamCapStartPosition() {
		int randomPosition = MathUtils.random(namCapStartPositions.size - 1);
		return namCapStartPositions.get(randomPosition);
	}

	/**
	 * 
	 * @return Une position aléatoire pour le fantome.
	 */
	public Vector2 getFantomeStartPosition() {
		int randomPosition = MathUtils.random(fantomeStartPositions.size - 1);
		return fantomeStartPositions.get(randomPosition);
	}

	/**
	 * Méthode qui permet de supprimer une position de départ de la liste
	 * fantomeStartPositions pour éviter que 2 fantômes n'apparaissent au même
	 * endroit.
	 */
	public void removeFantomeStartPosition(Fantome fantome) {
		fantomeStartPositions.removeValue(fantome.getPosition(), true);
	}

	public Vector2 getSurprisePosition() {
		int randomPosition = MathUtils.random(surprisePositions.size - 1);
		return surprisePositions.get(randomPosition);
	}

	/**
	 * Méthode renvoyant une position de sortie. Cette méthode pourra être
	 * utilisée quand tous les points de la map auront été récupérés.
	 * 
	 * @return
	 */
	public Vector2 getSortiePosition() {
		int randomPosition = MathUtils.random(sortiePositions.size - 1);
		return sortiePositions.get(randomPosition);
	}
}
