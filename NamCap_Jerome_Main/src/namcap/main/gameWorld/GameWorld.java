package namcap.main.gameWorld;

import namcap.main.gameObject.Mur;
import namcap.main.gameObject.NamCap;
import namcap.main.helpers.AssetLoaderTiled;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;


/**
 * Classe permettant la 'Updating task'.
 * Cette classe a besoin de savoir quand une collision a lieu pour
 * réagir en conséquence (récupérer le score, stopper le NamCap, Jouer un son...) 
 * 
 * @author jerome
 *
 */
public class GameWorld {

	private NamCap namCap;
	private Array<Mur> murs;


	public GameWorld() {
		
		MapObjects mapObjects = AssetLoaderTiled.layerObjetMurs.getObjects();
		RectangleMapObject rectangleMapObject;
		
		murs = new Array<Mur>();
		
		for(MapObject m : mapObjects){
			rectangleMapObject = (RectangleMapObject)m;
			
			murs.add(new Mur(rectangleMapObject.getRectangle()));
		}
		
		
		namCap = new NamCap(4, 4, 24, 24);
	}



	/**
	 * Cette méthode met à jour les objets du jeu en utilisant 
	 * la méthode update() de chacun d'eux (s'ils sont animés...).
	 * Les murs n'ont pas besoin d'être mis à jour car ils ne bougent pas
	 * sur le terrain.
	 * @param delta
	 */
	public void update(float delta) {
		// System.out.println("GameWorld - update");
		
		for(Mur m : murs)
			if(m.collision(namCap))
				namCap.stopNamCap();
			
		
		/*
		 * La méthode update() est définie dans la classe NamCap
		 * Elle contient les changements à apporter au namCap.
		 */
		namCap.update(delta);
	}


	public NamCap getNamCap() {
		return namCap;
	}



	public Array<Mur> getMurs() {
		return murs;
	}
	
}
