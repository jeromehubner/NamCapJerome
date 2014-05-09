package namcap.main.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;


/**
 * Classe qui permet le chargement de fichier .tmx généré à partir du logiciel Tiled.
 * @author jerome
 *
 */
public class AssetLoaderTiled {
	// Permet de récupérer le fichier .tmx
	public static TiledMap tiledMap;	


	// MapLayers => tableau de tous les calques du fichier .tmx
	public static MapLayers mapLayers;


	// TiledMapTileLayer => un calque de tile
	public static TiledMapTileLayer layerTileTerrain;
	public static TiledMapTileLayer layerTileMurs;


	// MapLayer => calque d'objets
	public static MapLayer layerObjetMurs;


	// TiledMapTileSets => tableau de tous les tileSets du fichier .tmx
	public static TiledMapTileSets tiledMapTileSets;

	// TiledMapTileSet => un tileSet
	public static TiledMapTileSet tiledMapTileSet;


	//------- Création du NamCap Animé -------*/ 
	public static Texture texture;

	public static Animation namCapAnimation;



	public static void load() {

		// On récupère le fichier .tmx
		tiledMap = new TmxMapLoader().load("images/terrain_foot.tmx");

		// On instancie les calques de tiles et d'objets contenus dans le mapLayers
		mapLayers = tiledMap.getLayers();


		/*------- Calques de tiles -------*/
		layerTileTerrain = (TiledMapTileLayer) mapLayers.get("tileTerrain");
		layerTileMurs = (TiledMapTileLayer) mapLayers.get("tileMurs");
		/*--------------------------------*/


		/*------- Calques d'objets-------*/
		layerObjetMurs = mapLayers.get("objetMurs");
		/*--------------------------------*/


		loadNamCapAnimation();

	}

	private static void loadNamCapAnimation(){
		texture = new Texture(Gdx.files.internal("images/texture.png"));


		TextureRegion namCap0 = new TextureRegion(texture, 0, 641, 128, 128);
		// Cette méthode .flip() crée un miroir vertical ou horizontal ou les 2
		// de la TextureRegion
		namCap0.flip(false, true);
		
		TextureRegion namCap1 = new TextureRegion(texture, 128, 641, 128, 128);
		namCap1.flip(false, true);
		
		TextureRegion namCap2 = new TextureRegion(texture, 256, 641, 128, 128);
		namCap2.flip(false, true);

		/*
		 * On crée un tableau de textureRegion pour générer l'animation
		 */
		TextureRegion[] namCap012 = {namCap0, namCap1, namCap2};

		/*
		 * L'animation est créée avec le tableau de textureRegion.
		 * 0.1 est la durée d'une frame.
		 */
		namCapAnimation = new Animation(0.1f, namCap012);
		namCapAnimation.setPlayMode(Animation.LOOP_PINGPONG);
	}

	public static void dispose(){
		tiledMap.dispose();
	}
}
