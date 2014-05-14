package namcap.main.gameWorld;

import namcap.main.gameObject.NamCap;
import namcap.main.helpers.AssetLoaderTiled;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameRendererTiled {	
	
	private GameWorld gameWorld;
	
	private OrthographicCamera orthographicCamera;
//	private SpriteBatch spriteBatch;
	private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
//	private ShapeRenderer shapeRenderer;
	
	
	//------- GameObject -------//
	/*
	 *  On crée un NamCap qui sera instancié une seule fois
	 *  dans le contructeur de cette classe.
	 */
	private NamCap namCap;
	//--------------------------//
	
	
	
	//------- GameAssets -------//
	private Animation namCapAnimation;
	private TiledMapTileLayer tileBackGround, tileMurs;
	//--------------------------//
	
	/*
	 * Le SpriteBatch utilisée sera celui de la orthogonalTiledMapRenderer.
	 */	
	
	
	public GameRendererTiled(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		
		
		// unitScale => le nbre de pixels d'un tile (32x32)
		float unitScale = 1f;
		
		orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(AssetLoaderTiled.tiledMap, unitScale);
		orthographicCamera = new OrthographicCamera();
		
		
		/*
		 * On place les valeurs de la camera avec les dimensions du tiledMap pour
		 * afficher tout le terrain à l'écran
		 */		
		orthographicCamera.setToOrtho(false, AssetLoaderTiled.tiledMapWidth, AssetLoaderTiled.tiledMapHeight);
		
		
		// On renseigne la camera au orthogonalTiledMapRenderer 
		orthogonalTiledMapRenderer.setView(orthographicCamera);
		
		
//		// On utilise le SpriteBatch du orthogonalTiledMapRenderer.
//		spriteBatch = orthogonalTiledMapRenderer.getSpriteBatch();
//		spriteBatch.setProjectionMatrix(orthographicCamera.combined);
				
		// Initialisation des variables
		initGameObjects();
		initAssets();
	}
	
	
	/**
	 * Méthode utilisée dans le contructeur de cette classe pour 
	 * initialiser les objets du jeu.
	 */
	private void initGameObjects(){
		namCap = gameWorld.getNamCap();
	}
	

	/**
	 * Méthode utilisée dans le contructeur de cette classe pour charger les assets.
	 */
	private void initAssets(){
		tileBackGround = AssetLoaderTiled.layerTileTerrain;
		tileMurs = AssetLoaderTiled.layerTileMurs;
		namCapAnimation = AssetLoaderTiled.namCapAnimation;
		
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
		
		
		/*
		 * 3. Dessin du NamCap
		 */
		drawNamCap(runTime);
		
		
		//------- SpriteBatch END -------//
		orthogonalTiledMapRenderer.getSpriteBatch().end();

		/*
		 * Les 2 méthodes drawBackGround() et drawMurs() pourrait être remplacées par :
		 * 
		 * orthogonalTiledMapRenderer.render();
		 */
	}
}
