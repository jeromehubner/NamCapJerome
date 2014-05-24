package namcap.main.helpers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
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
public class AssetLoaderMap {
	// Permet de récupérer le fichier .tmx
	public static TiledMap tiledMap;

	/*
	 * Variables utilisées pour placer la camera du renderer
	 * et pour empêcher la sortie du NamCap
	 */
	public static float tiledMapWidth;
	public static float tiledMapHeight;


	// MapLayers => tableau de tous les calques du fichier .tmx
	public static MapLayers mapLayers;


	// TiledMapTileLayer => un calque de tile
	public static TiledMapTileLayer layerBackground;
	public static TiledMapTileLayer layerMurs;
	public static TiledMapTileLayer layerIntersections;
	public static TiledMapTileLayer layerPonts;
	

	// MapLayer => calque d'objets
	public static MapLayer layerObjectPoints;
	public static TextureRegion point;


	// TiledMapTileSets => tableau de tous les tileSets du fichier .tmx
	public static TiledMapTileSets tiledMapTileSets;

	// TiledMapTileSet => un tileSet
	public static TiledMapTileSet tiledMapTileSet;


	/*------- Création du NamCap animé -------*/
	public static Animation namCapAnimation;

	/*------- Création du Fantome animé -------*/
	public static Animation fantomeAnimation;
	
	/*------- Création du point animé -------*/
//	public static Animation pointAnimation;


	public static void load() {
		// On récupère le fichier .tmx
		tiledMap = new TmxMapLoader().load("tmxFiles/sportTheme/foot.tmx");

		// On récupère les largeur et hauteur du tilemap
		tiledMapWidth = ((int)tiledMap.getProperties().get("width"));	// Nombre de tiles (en largeur)

		tiledMapHeight=((int)tiledMap.getProperties().get("height"));	// Nombre de tiles (en hauteur)

		// On instancie les calques de tiles et d'objets contenus dans le mapLayers
		mapLayers = tiledMap.getLayers();


		/*------- Calques de tiles -------*/
		layerBackground = (TiledMapTileLayer) mapLayers.get("layerBackground");
		layerMurs = (TiledMapTileLayer) mapLayers.get("layerMurs");
		layerIntersections = (TiledMapTileLayer) mapLayers.get("layerIntersections");
		layerPonts = (TiledMapTileLayer) mapLayers.get("layerPonts");
//		layerPoints = (TiledMapTileLayer) mapLayers.get("layerPoints");

		

		/*------- Calques d'objets-------*/
		layerObjectPoints = mapLayers.get("layerObjectPoints");
		/*--------------------------------*/


		loadNamCapAnimation();
		loadFantomeAnimation();
		loadPointAnimation();
	}

	private static void loadNamCapAnimation(){
		TextureRegion namCap0 = tiledMap.getTileSets().getTileSet("foot").getTile(641).getTextureRegion();
		// Cette méthode .flip() crée un miroir vertical ou horizontal ou les 2
		// de la TextureRegion
		namCap0.flip(false, true);

		TextureRegion namCap1 = tiledMap.getTileSets().getTileSet("foot").getTile(661).getTextureRegion();
		namCap1.flip(false, true);

		TextureRegion namCap2 = tiledMap.getTileSets().getTileSet("foot").getTile(681).getTextureRegion();
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

	private static void loadFantomeAnimation(){
		TextureRegion fantome0 = tiledMap.getTileSets().getTileSet("foot").getTile(642).getTextureRegion();
		fantome0.flip(false, true);

		TextureRegion fantome1 = tiledMap.getTileSets().getTileSet("foot").getTile(662).getTextureRegion();
		fantome1.flip(false, true);

		TextureRegion fantome2 = tiledMap.getTileSets().getTileSet("foot").getTile(682).getTextureRegion();
		fantome2.flip(false, true);

		/*
		 * On crée un tableau de textureRegion pour générer l'animation
		 */
		TextureRegion[] fantome012 = {fantome0, fantome1, fantome2};

		/*
		 * L'animation est créée avec le tableau de textureRegion.
		 * 0.1 est la durée d'une frame.
		 */
		fantomeAnimation = new Animation(0.1f, fantome012);
		fantomeAnimation.setPlayMode(Animation.LOOP_PINGPONG);
	}
	
	private static void loadPointAnimation(){
//		TextureRegion point0 = tiledMap.getTileSets().getTileSet("foot").getTile(643).getTextureRegion();
//		point0.flip(false, true);
//
//		TextureRegion point1 = tiledMap.getTileSets().getTileSet("foot").getTile(663).getTextureRegion();
//		point1.flip(false, true);
//		
//		/*
//		 * On crée un tableau de textureRegion pour générer l'animation
//		 */
//		TextureRegion[] point01 = {point0, point1};
//
//		/*
//		 * L'animation est créée avec le tableau de textureRegion.
//		 * 0.1 est la durée d'une frame.
//		 */
//		pointAnimation = new Animation(0.2f, point01);
//		pointAnimation.setPlayMode(Animation.LOOP_PINGPONG);
//		/*--------------------------------*/
		
		point = tiledMap.getTileSets().getTileSet("foot").getTile(663).getTextureRegion();
		
	}



	public static void dispose(){
		tiledMap.dispose();
	}
}
