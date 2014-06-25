package namcap.main.helpers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Classe qui permet le chargement de fichier .tmx généré à partir du logiciel
 * Tiled. ATTENTION : Cette classe n'utilise que le TileSet[0] pour charger les
 * animations.
 * 
 * @author jerome layerBackground
 */
public class AssetLoaderMap {
	/*------- NOM DES CALQUES dans Tiled -------*/
	/*------- OBJETS -------*/
	private static String nameLayerObjectStartNamCap = "layerObjectStartNamCap";
	private static String nameLayerObjectStartFantome = "layerObjectStartFantome";
	private static String nameLayerObjectStartSurprise = "layerObjectStartSurprise";
	private static String nameLayerObjectPoints = "layerObjectPoints";
	private static String nameLayerObjectSortie = "layerObjectSortie";
	/*------- TILES -------*/
	private static String nameLayerPonts = "layerPonts";
	private static String nameLayerIntersections = "layerIntersections";
	private static String nameLayerMurs = "layerMurs";
	private static String nameLayerBackGround = "layerBackground";
	/*------------------------------------------*/

	/*------- Inutilisé pour le moment mais bon à savoir -------/
	// TiledMapTileSets => tableau de tous les tileSets du fichier .tmx
	public static TiledMapTileSets tiledMapTileSets;
	// TiledMapTileSet => un tileSet
	public static TiledMapTileSet tiledMapTileSet;
	/----------------------------------------------------------*/

	// Permet de récupérer le fichier .tmx
	public static TiledMap tiledMap;

	/*
	 * Variables utilisées pour placer la camera du renderer et pour empêcher la
	 * sortie du NamCap
	 */
	public static float tiledMapWidth;
	public static float tiledMapHeight;

	// MapLayers => tableau de tous les calques du fichier .tmx
	public static MapLayers mapLayers;

	// MapLayer => CALQUE D'OBJETS
	public static MapLayer layerObjectStartNamCap;
	public static MapLayer layerObjectStartFantome;
	public static MapLayer layerObjectStartSurprise;
	public static MapLayer layerObjectPoints;
	public static MapLayer layerObjectSortie;

	// TiledMapTileLayer => CALQUE DE TILES
	public static TiledMapTileLayer layerBackground;
	public static TiledMapTileLayer layerMurs;
	public static TiledMapTileLayer layerIntersections;
	public static TiledMapTileLayer layerPonts;

	// Les TextureRegion permettent de récupérer une image non animée
	public static TextureRegion point;
	public static TextureRegion sortie;

	/*------- ANIMATION -------*/
	/*------- Animation du NamCap -------*/
	public static Animation namCapAnimation;

	/*------- Animation des Fantomes -------*/
	public static Animation fantomeAnimation;

	/*------- Animation des Surprises -------*/
	public static Animation surpriseAnimation;

	/*-------------------------*/

	public static void load(String tmxFilePath) {
		// On récupère le fichier .tmx
		tiledMap = new TmxMapLoader().load(tmxFilePath);

		// On récupère les nombres de tile du tilemap
		tiledMapWidth = ((int) tiledMap.getProperties().get("width"));
		tiledMapHeight = ((int) tiledMap.getProperties().get("height"));

		// On instancie les calques de tiles et d'objets contenus dans le
		// mapLayers
		mapLayers = tiledMap.getLayers();

		/*------- CALQUES D'OBJETS -------*/
		layerObjectStartNamCap = mapLayers.get(nameLayerObjectStartNamCap);
		layerObjectStartFantome = mapLayers.get(nameLayerObjectStartFantome);
		layerObjectStartSurprise = mapLayers.get(nameLayerObjectStartSurprise);
		layerObjectPoints = mapLayers.get(nameLayerObjectPoints);
		layerObjectSortie = mapLayers.get(nameLayerObjectSortie);
		/*--------------------------------*/

		/*------- CALQUES DE TILES -------*/
		layerBackground = (TiledMapTileLayer) mapLayers
				.get(nameLayerBackGround);
		layerMurs = (TiledMapTileLayer) mapLayers.get(nameLayerMurs);
		layerIntersections = (TiledMapTileLayer) mapLayers
				.get(nameLayerIntersections);
		layerPonts = (TiledMapTileLayer) mapLayers.get(nameLayerPonts);

		/*------- TextureRegion -------*/
		// On récupère le tileset[0] car il n'y en a qu'un pour chaque map
		point = tiledMap.getTileSets().getTileSet(0).getTile(643)
				.getTextureRegion();
		sortie = tiledMap.getTileSets().getTileSet(0).getTile(648)
				.getTextureRegion();
		/*-----------------------------*/

		/*------- load Animations -------*/
		loadNamCapAnimation();
		loadFantomeAnimation();
		loadSurpriseAnimation();
		/*-------------------------------*/
	}

	/**
	 * Charge les images de l'animation du NamCap en récupérant les
	 * textureRegion correspondantes du TileSet (numéro des tiles du tileset
	 * correspondant au NamCap : 641, 661, 681).
	 */
	private static void loadNamCapAnimation() {
		TextureRegion namCap0 = tiledMap.getTileSets().getTileSet(0)
				.getTile(641).getTextureRegion();
		// Cette méthode .flip() crée un miroir vertical ou horizontal ou les 2
		// de la TextureRegion
		namCap0.flip(false, true);

		TextureRegion namCap1 = tiledMap.getTileSets().getTileSet(0)
				.getTile(661).getTextureRegion();
		namCap1.flip(false, true);

		TextureRegion namCap2 = tiledMap.getTileSets().getTileSet(0)
				.getTile(681).getTextureRegion();
		namCap2.flip(false, true);

		/*
		 * On crée un tableau de textureRegion pour générer l'animation
		 */
		TextureRegion[] namCap012 = { namCap0, namCap1, namCap2 };

		/*
		 * L'animation est créée avec le tableau de textureRegion. 0.1 est la
		 * durée d'une frame.
		 */
		namCapAnimation = new Animation(0.1f, namCap012);
		namCapAnimation.setPlayMode(Animation.LOOP_PINGPONG);
	}

	private static void loadFantomeAnimation() {
		TextureRegion fantome0 = tiledMap.getTileSets().getTileSet(0)
				.getTile(642).getTextureRegion();
		fantome0.flip(false, true);

		TextureRegion fantome1 = tiledMap.getTileSets().getTileSet(0)
				.getTile(662).getTextureRegion();
		fantome1.flip(false, true);

		TextureRegion fantome2 = tiledMap.getTileSets().getTileSet(0)
				.getTile(682).getTextureRegion();
		fantome2.flip(false, true);

		/*
		 * On crée un tableau de textureRegion pour générer l'animation
		 */
		TextureRegion[] fantome012 = { fantome0, fantome1, fantome2 };

		/*
		 * L'animation est créée avec le tableau de textureRegion. 0.1 est la
		 * durée d'une frame.
		 */
		fantomeAnimation = new Animation(0.1f, fantome012);
		fantomeAnimation.setPlayMode(Animation.LOOP_PINGPONG);
	}

	private static void loadSurpriseAnimation() {
		TextureRegion surprise0 = tiledMap.getTileSets().getTileSet(0)
				.getTile(647).getTextureRegion();
		surprise0.flip(false, true);

		TextureRegion surprise1 = tiledMap.getTileSets().getTileSet(0)
				.getTile(667).getTextureRegion();
		surprise1.flip(false, true);

		TextureRegion surprise2 = tiledMap.getTileSets().getTileSet(0)
				.getTile(687).getTextureRegion();
		surprise2.flip(false, true);

		TextureRegion surprise3 = tiledMap.getTileSets().getTileSet(0)
				.getTile(707).getTextureRegion();
		surprise3.flip(false, true);

		TextureRegion surprise4 = tiledMap.getTileSets().getTileSet(0)
				.getTile(727).getTextureRegion();
		surprise4.flip(false, true);

		TextureRegion surprise5 = tiledMap.getTileSets().getTileSet(0)
				.getTile(747).getTextureRegion();
		surprise5.flip(false, true);

		TextureRegion surprise6 = tiledMap.getTileSets().getTileSet(0)
				.getTile(767).getTextureRegion();
		surprise6.flip(false, true);

		TextureRegion surprise7 = tiledMap.getTileSets().getTileSet(0)
				.getTile(787).getTextureRegion();
		surprise7.flip(false, true);

		TextureRegion surprise8 = tiledMap.getTileSets().getTileSet(0)
				.getTile(807).getTextureRegion();
		surprise8.flip(false, true);

		/*
		 * On crée un tableau de textureRegion pour générer l'animation
		 */
		TextureRegion[] surprise012345678 = { surprise0, surprise1, surprise2,
				surprise3, surprise4, surprise5, surprise6, surprise7,
				surprise8 };

		/*
		 * L'animation est créée avec le tableau de textureRegion. 0.1 est la
		 * durée d'une frame.
		 */
		surpriseAnimation = new Animation(0.1f, surprise012345678);
		surpriseAnimation.setPlayMode(Animation.LOOP_PINGPONG);
	}

	public static void dispose() {
		if (tiledMap != null)
			tiledMap.dispose();
	}
}
