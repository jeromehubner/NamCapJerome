//package namcap.main.gameWorld;
//
//import namcap.main.gameObject.Mur;
//import namcap.main.gameObject.NamCap;
//import namcap.main.helpers.AssetLoader;
//import namcap.main.screens.GameScreen;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL10;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
//import com.badlogic.gdx.utils.Array;
//
//
///**
// * Classe permettant la 'Rendering task'.
// * Cette classe a besoin de savoir quand le NamCap est mort
// * pour réagir en conséquence pour afficher un screenScore...
// * 
// */
//public class GameRenderer {
//
//	private GameWorld gameWorld;
//	private OrthographicCamera orthographicCamera;
//	private ShapeRenderer shapeRenderer;
//	
//	
//	//------- GameObject -------//
//	/*
//	 *  On crée un NamCap qui sera instancié une seule fois
//	 *  dans le constructeur de cette classe.
//	 */
//	private NamCap namCap;
//	private Array<Mur> tableauDeMurs;
//	//--------------------------//
//	
//	
//	//------- GameAssets -------//
//	private TextureRegion backGround, murRegion;
//	private Animation namCapAnimation;
//	//--------------------------//
//	
//	
//	/*
//	 * Le SpriteBatch permet de dessiner une TextureRegion
//	 */
//	private SpriteBatch spriteBatch;
//
//	/*
//	 * On récupère les dimensions de l'écran déclarées dans la Classe GameScreen.
//	 */
//	private float largeur_camera = GameScreen.largeurScreen;
//	private float hauteur_camera = GameScreen.hauteurScreen;
//
//	
//	
//	
//	public GameRenderer(GameWorld gameWorld) {
//		this.gameWorld = gameWorld;
//
//		orthographicCamera = new OrthographicCamera();
//		orthographicCamera.setToOrtho(true, largeur_camera, hauteur_camera);		
//
//		spriteBatch = new SpriteBatch();
//		spriteBatch.setProjectionMatrix(orthographicCamera.combined);
//		
//		shapeRenderer = new ShapeRenderer();
//		shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
//		
//		// Initialisation des variables
//		initGameObjects();
//		initAssets();		
//	}
//	
//	
//	/**
//	 * Méthode utilisée dans le contructeur de cette classe pour 
//	 * initialiser les objets du jeu.
//	 */
//	private void initGameObjects(){
//		namCap = gameWorld.getNamCap();
//	}
//	
//
//	/**
//	 * Méthode utilisée dans le contructeur de cette classe pour charger les assets.
//	 */
//	private void initAssets(){
//		backGround = AssetLoader.backGround;
//		
//		namCapAnimation = AssetLoader.namCapAnimation;
//		murRegion = AssetLoader.murRegion;
//	}
//	
//	
//	/**
//	 * Méthode utilisée dans cette classe pour dessiner le background (terrain).
//	 */
//	private void drawBackGround(){
//		/*
//		 * Les coordonnées coïncident avec la largeur et la hauteur de la
//		 * caméraOrthographic utilisées dans la méthode camera.setToOrtho()
//		 */
//		spriteBatch.draw(backGround, 0, 0, largeur_camera, hauteur_camera);
//	}
//	
//	/**
//	 * Méthode utilisée dans cette classe pour dessiner les murs.
//	 */
//	private void drawMurs(Array<Mur> murs){
//
////		for(Mur m : murs)
////			spriteBatch.draw(murRegion, m.getPosition().x, m.getPosition().y,
////				m.getLargeur(), m.getHauteur());
////		
////		shapeRenderer.begin(ShapeType.Filled);
////		shapeRenderer.setColor(Color.BLUE);
////		shapeRenderer.rect(mur.getPosition().x, mur.getPosition().y, mur.getLargeur(), mur.getHauteur());
////		shapeRenderer.end();
//	}
//	
//	
//	/**
//	 * Cette méthode met à jour le rendu du jeu.
//	 * Elle commence par dessiner un fond noir (en utilisant le shapeRenderer)
//	 * pour prévenir du flickering puis redessine chaque élement du jeu dans l'ordre.
//	 * Elle peut prendre en compte le transparance (par exemple pour le NamCap).
//	 * 
//	 * @param runTime Variable nécessaire pour l'animation du NamCap.
//	 * L'objet animation utilisera cette valeur (couplé à la durée d'une trame)
//	 * pour déterminer quelle TextureRegion afficher.
//	 * 
//	 */
//	public void render(float runTime) {
//		
//		/*
//		 * BackGround noir pour prévenir le flickering.
//		 */
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		
//		
//		//-------------------------------------------------------
//		shapeRenderer.begin(ShapeType.Filled);
//		
//		/*
//		 * Dessin du background couleur noir
//		 */
//		shapeRenderer.setColor(Color.BLACK);
//		shapeRenderer.rect(0, 0, largeur_camera, hauteur_camera);		
//		
//		shapeRenderer.end();
//		//-------------------------------------------------------
//		
//		
//		
//		
//		//-------------------------------------------------------
//		spriteBatch.begin();
//		
////		/*
////		 * La suppression de la transparence améliore les performances.
////		 * Il est préferable d'utiliser des images n'utilisant pas la transprence.
////		 */
////		spriteBatch.disableBlending();
//		
//		/*
//		 * Le namCap utilise la transparence, donc on réactive celle-ci.
//		 */
////		spriteBatch.enableBlending();
//		
//		
//		/*
//		 * 1. Dessin de l'image du backGround (le terrain).
//		 */
//		drawBackGround();
//		
//		
//		/*
//		 * 2. Dessin des murs
//		 */
//		drawMurs(tableauDeMurs);		
//		
//		
//		/*
//		 * 3. Dessin du NamCap
//		 */
//		/*
//		 * On utilise la variable runtime pour retrouver la trame courante.
//		 * On dessine le NamCap avec sa rotation. Celle-si s'effectue autour de l'axe
//		 * définie par namCap.getLargeur() /2.0f, namCap.getHauteur()/2.0f.
//		 */
//		spriteBatch.draw(namCapAnimation.getKeyFrame(runTime),
//				namCap.getPosition().x, namCap.getPosition().y,
//				namCap.getLargeur() /2.0f, namCap.getHauteur()/2.0f,
//				namCap.getLargeur(), namCap.getHauteur(), 
//				1,1,
//				namCap.getRotation());
//		//--------------------
//		
//		
//		spriteBatch.end(); 
//		//-------------------------------------------------------
//		
//		
//		
//		//------- Pour visualiser la gestion des collisions -------
//		/*
//		 * Dessine un cercle rouge correspondant au NamCap.
//		 * Ce cercle reprend les coordonnées et dimensions du NamCap.
//		 */
////		shapeRenderer.begin(ShapeType.Filled);
////		shapeRenderer.setColor(Color.RED);
////		shapeRenderer.circle(namCap.getBoundingCircle().x, namCap.getBoundingCircle().y, namCap.getBoundingCircle().radius);
////		shapeRenderer.end();
//		//---------------------------------------------------------
//	}
//}
