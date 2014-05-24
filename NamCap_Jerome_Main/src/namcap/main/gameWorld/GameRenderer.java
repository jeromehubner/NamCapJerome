package namcap.main.gameWorld;

import namcap.main.gameObject.Fantome;
import namcap.main.gameObject.NamCap;
import namcap.main.gameObject.Point;
import namcap.main.gameObject.Points;
import namcap.main.helpers.AssetLoaderMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameRenderer {	
	
	private GameWorld gameWorld;
	
	private OrthographicCamera orthographicCamera;
	private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
	
	
	//------- GameObject -------//
	/*
	 *  On crée un NamCap qui sera instancié une seule fois
	 *  dans le contructeur de cette classe.
	 */
	private NamCap namCap;
	
	private Fantome fantome1;
	private Fantome fantome2;
	private Fantome fantome3;
	
	private Points pointObjects;
	//--------------------------//
	
	//------- GameAssets -------//
	private Animation namCapAnimation;
	private Animation fantomeAnimation;
	private TextureRegion point;
//	private Animation pointAnimation;
	
	private TiledMapTileLayer tileBackGround, tileMurs, tilePonts;
//	tilePoints;
	//--------------------------//
	
	
	/*------- Dimensions de l'écran -------*/
	private float screenWidth = AssetLoaderMap.tiledMapWidth;
	private float screenHeight = AssetLoaderMap.tiledMapHeight;
	/*-------------------------------------*/
	
	
	/*------- Le nbre de pixels d'un tile (32x32) -------*/
	public static float unitScale = 1/32f;
	/*---------------------------------------------------*/
	
	
	public GameRenderer(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		

		unitScale = 1/32f;	// => 0 < position.x < 20 et 0 < position.y < 32
		
		orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(AssetLoaderMap.tiledMap, unitScale);
		orthographicCamera = new OrthographicCamera();
		
		
		/*
		 * On place les valeurs de la camera avec les dimensions du tiledMap pour
		 * afficher tout le terrain à l'écran
		 */	
		orthographicCamera.setToOrtho(false, screenWidth, screenHeight);
		
		
		// On renseigne la camera au orthogonalTiledMapRenderer 
		orthogonalTiledMapRenderer.setView(orthographicCamera);
		
				
		// Initialisation des variables
		initAssets();
		initGameObjects();
	}
	
	
	/**
	 * Méthode utilisée dans le contructeur de cette classe pour 
	 * initialiser les objets du jeu.
	 */
	private void initGameObjects(){
		namCap = gameWorld.getNamCap();
		fantome1 = gameWorld.getFantome1();
		fantome2 = gameWorld.getFantome2();
		fantome3 = gameWorld.getFantome3();
		pointObjects = gameWorld.getPoints();
	}
	

	/**
	 * Méthode utilisée dans le contructeur de cette classe pour charger les assets.
	 */
	private void initAssets(){
		tileBackGround = AssetLoaderMap.layerBackground;
		tileMurs = AssetLoaderMap.layerMurs;
		tilePonts = AssetLoaderMap.layerPonts;
//		tilePoints = AssetLoaderMap.layerPoints;
		
		
		namCapAnimation = AssetLoaderMap.namCapAnimation;
		fantomeAnimation = AssetLoaderMap.fantomeAnimation;
//		pointAnimation = AssetLoaderMap.pointAnimation;
		point = AssetLoaderMap.point;
	}
	
	
	/**
	 * Méthode utilisée dans cette classe pour dessiner le background (terrain).
	 */
	private void drawBackGround(){
		orthogonalTiledMapRenderer.renderTileLayer(tileBackGround);
	}
	
	
	/**
	 * Méthode utilisée dans cette classe pour dessiner les murs.
	 */
	private void drawMurs(){
		orthogonalTiledMapRenderer.renderTileLayer(tileMurs);
	}
	
	
	/**
	 * Méthode utilisée dans cette classe pour dessiner le NamCap.
	 * La variable runtime est utilisée pour retrouver la trame courante.
	 * On dessine le NamCap avec sa rotation. Celle-si s'effectue autour de l'axe
	 * définie par namCap.getLargeur() /2.0f, namCap.getHauteur()/2.0f.
	 */
	private void drawNamCap(float runTime){
		 
		orthogonalTiledMapRenderer.getSpriteBatch().draw(
				namCapAnimation.getKeyFrame(runTime),
				namCap.getPosition().x, namCap.getPosition().y,
				namCap.getLargeur() /2.0f, namCap.getHauteur()/2.0f,
				namCap.getLargeur(), namCap.getHauteur(), 
				1,1,
				namCap.getRotation());
	}
	
	private void drawFantomes(float runTime){
		orthogonalTiledMapRenderer.getSpriteBatch().draw(
				fantomeAnimation.getKeyFrame(runTime),
				fantome1.getPosition().x, fantome1.getPosition().y,
				fantome1.getLargeur() /2.0f, fantome1.getHauteur()/2.0f,
				fantome1.getLargeur(), fantome1.getHauteur(), 
				1,1,
				fantome1.getRotation());
		
		orthogonalTiledMapRenderer.getSpriteBatch().draw(
				fantomeAnimation.getKeyFrame(runTime),
				fantome2.getPosition().x, fantome2.getPosition().y,
				fantome2.getLargeur() /2.0f, fantome2.getHauteur()/2.0f,
				fantome2.getLargeur(), fantome2.getHauteur(), 
				1,1,
				fantome2.getRotation());
		
		orthogonalTiledMapRenderer.getSpriteBatch().draw(
				fantomeAnimation.getKeyFrame(runTime),
				fantome3.getPosition().x, fantome3.getPosition().y,
				fantome3.getLargeur() /2.0f, fantome3.getHauteur()/2.0f,
				fantome3.getLargeur(), fantome3.getHauteur(), 
				1,1,
				fantome3.getRotation());
	}
	
	
	private void drawponts(){
		orthogonalTiledMapRenderer.renderTileLayer(tilePonts);
	}
	
	private void drawPoints(){
//		orthogonalTiledMapRenderer.renderTileLayer(tilePoints);
//		
//		for(Point p : pointObjects.getHashMapPoints().values()){
//			orthogonalTiledMapRenderer.getSpriteBatch().draw(
//					pointAnimation.getKeyFrame(runTime),
//					p.getPosition().x, p.getPosition().y,
//					p.getLargeur() /2.0f, p.getHauteur()/2.0f,
//					p.getLargeur(), p.getHauteur(), 
//					1,1,
//					0);
//		}	
		
		for(Point p : pointObjects.getHashMapPoints().values())
			orthogonalTiledMapRenderer.getSpriteBatch().draw(
					point, p.getPosition().x, p.getPosition().y, p.getLargeur(), p.getHauteur());
		
	}
	
	
	/**
	 * Cette méthode met à jour le rendu du jeu.
	 * Elle commence par dessiner un fond noir (en utilisant le shapeRenderer)
	 * pour prévenir du flickering puis redessine chaque élement du jeu dans l'ordre.
	 * Elle peut prendre en compte le transparance (par exemple pour le NamCap).
	 * 
	 * @param runTime Variable nécessaire pour l'animation du NamCap.
	 * L'objet animation utilisera cette valeur (couplé à la durée d'une trame)
	 * pour déterminer quelle TextureRegion afficher.
	 * 
	 */
	public void render(float runTime) {
		
		/*
		 * BackGround noir pour prévenir le flickering.
		 */
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		
		
		
		//-------------------------------------------------------
//		/*
//		 * Dessin du background couleur noir
//		 * 
//		 */
//		shapeRenderer.begin(ShapeType.Filled);
//		shapeRenderer.setColor(Color.BLACK);
//		shapeRenderer.rect(0, 0, largeurScreen, hauteurScreen);		
//		
//		shapeRenderer.end();
		//-------------------------------------------------------
		
		
		//------- SpriteBatch BEGIN -------//
		orthogonalTiledMapRenderer.getSpriteBatch().begin();
		
		
		
		/*
		 * 1. Dessin du Background
		 */
		drawBackGround();
		
		
		/*
		 * 2. Dessin des murs
		 */
		drawMurs();
		
		
		
		drawPoints();
		
		
		/*
		 * 3. Dessin du NamCap
		 */
		drawNamCap(runTime);
		
		
		drawFantomes(runTime);
		
		
		drawponts();
		
		
		//------- SpriteBatch END -------//
		orthogonalTiledMapRenderer.getSpriteBatch().end();

		/*
		 * La méthode drawBackGround() pourrait être remplacée par :
		 * 
		 * orthogonalTiledMapRenderer.render();
		 */
	}
}
