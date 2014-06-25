package namcap.main.gameWorld;

import java.util.List;

import namcap.main.MyDialogBox;
import namcap.main.gameObject.Fantome;
import namcap.main.gameObject.NamCap;
import namcap.main.gameObject.Point;
import namcap.main.gameObject.Points;
import namcap.main.gameObject.Sortie;
import namcap.main.gameObject.Surprise;
import namcap.main.helpers.AssetLoaderMap;
import namcap.main.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameRenderer {

	private GameWorld gameWorld;

	private OrthographicCamera orthographicCamera;
	private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

	// ------- GameObject -------//
	private NamCap namCap;
	private List<Fantome> fantomeList;
	private Points pointObjects;
	private Surprise surpriseObject;
	private Sortie sortieObject;
	// --------------------------//

	// ------- GameAssets -------//
	private Animation namCapAnimation;
	private Animation fantomeAnimation;
	private Animation surpriseAnimation;
	private TextureRegion pointTexture, sortieTexture ;
	private TiledMapTileLayer tileBackGround, tileMurs, tilePonts;
	private Skin skin;
	// --------------------------//

	// ------- GameInfos -------//
	private float runTime;
	private Label scoreLabel, lifeLabel, timeLabel;
	private float xScoreLabel, yScoreLabel, xLifeLabel, yLifeLabel, xTimeLabel,
			yTimeLabel;
	private Image imageLife;
	private float xImageLife, yImageLife;
	private Stage stageInfosGame;
	// -------------------------//

	/*------- Dimensions de l'écran en nombre de tiles -------*/
	private float nbTilesWidth = AssetLoaderMap.tiledMapWidth;
	private float nbTilesHeight = AssetLoaderMap.tiledMapHeight;
	/*--------------------------------------------------------*/

	/*------- Le nbre de pixels d'un tile (32x32) -------*/
	public static float unitScale = 1 / 32f;

	/*---------------------------------------------------*/

	public GameRenderer(GameWorld gameWorld) {
		this.gameWorld = gameWorld;
		this.skin = GameScreen.SKIN;

		unitScale = 1 / 32f; // => 0 < position.x < 20 et 0 < position.y < 32

		orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(
				AssetLoaderMap.tiledMap, unitScale);
		orthographicCamera = new OrthographicCamera();

		/*
		 * On place les valeurs de la camera avec les dimensions du tiledMap
		 * pour afficher tout le terrain à l'écran
		 */
		orthographicCamera.setToOrtho(false, nbTilesWidth, nbTilesHeight);

		// On renseigne la camera au orthogonalTiledMapRenderer
		orthogonalTiledMapRenderer.setView(orthographicCamera);

		initAssets();
		initGameObjects();
		initGameInfos();
	}

	/**
	 * Méthode utilisée dans le contructeur de cette classe pour initialiser les
	 * objets du jeu.
	 */
	private void initGameObjects() {
		namCap = gameWorld.getNamCap();
		fantomeList = gameWorld.getFantomeList();
		pointObjects = gameWorld.getPoints();
		surpriseObject = gameWorld.getSurprise();
		sortieObject = gameWorld.getSortie();
	}

	/**
	 * Méthode utilisée dans le contructeur de cette classe pour charger les
	 * assets.
	 */
	private void initAssets() {
		tileBackGround = AssetLoaderMap.layerBackground;
		tileMurs = AssetLoaderMap.layerMurs;
		tilePonts = AssetLoaderMap.layerPonts;

		namCapAnimation = AssetLoaderMap.namCapAnimation;
		fantomeAnimation = AssetLoaderMap.fantomeAnimation;
		surpriseAnimation = AssetLoaderMap.surpriseAnimation;

		pointTexture = AssetLoaderMap.point;
		sortieTexture = AssetLoaderMap.sortie;
	}

	private void initGameInfos() {
		// TODO : à corriger
		// TODO : penser à utiliser la classe pref pour modifier les données du
		// jeu (Sound on/off, highScrore...)

		/*------- LABEL SCORE -------*/
		scoreLabel = new Label("", skin);
		xScoreLabel = 0;
		yScoreLabel = Gdx.graphics.getHeight() * 96/100; // On place le label
															// à 96% de la
															// hauteur de
															// l'écran
		scoreLabel.setPosition(xScoreLabel, yScoreLabel);

		/*------- IMAGE LIFE -------*/
		imageLife = new Image(pointTexture);
		xImageLife = Gdx.graphics.getWidth() * 47/100;
		yImageLife = Gdx.graphics.getHeight() * 94/100;
		imageLife.setPosition(xImageLife, yImageLife);

		/*------- LABEL LIFE -------*/
		lifeLabel = new Label("", skin);
		xLifeLabel = Gdx.graphics.getWidth() * 53/100;
		yLifeLabel = Gdx.graphics.getHeight() * 96/100;
		lifeLabel.setPosition(xLifeLabel, yLifeLabel);

		/*------- LABEL TIME ------- */
		timeLabel = new Label("", skin);
		xTimeLabel = Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() * 35/100);
		yTimeLabel = Gdx.graphics.getHeight() * 96/100;
		timeLabel.setPosition(xTimeLabel, yTimeLabel);

		/*------- STAGE -------*/
		stageInfosGame = new Stage();
		stageInfosGame.addActor(scoreLabel);
		stageInfosGame.addActor(imageLife);
		stageInfosGame.addActor(lifeLabel);
		stageInfosGame.addActor(timeLabel);
	}

	/**
	 * Cette méthode met à jour le rendu du jeu. Elle commence par dessiner un
	 * fond noir (en utilisant le shapeRenderer) pour prévenir du flickering
	 * puis redessine chaque élement du jeu dans l'ordre. Elle peut prendre en
	 * compte le transparance (par exemple pour le NamCap).
	 * 
	 * @param runTime
	 *            Variable nécessaire pour l'animation du NamCap. L'objet
	 *            animation utilisera cette valeur (couplé à la durée d'une
	 *            trame) pour déterminer quelle TextureRegion afficher.
	 * 
	 */
	public void render(float delta) {
		runTime += delta;

		/*
		 * BackGround noir pour prévenir le flickering.
		 */
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// ------- SpriteBatch BEGIN -------//
		orthogonalTiledMapRenderer.getSpriteBatch().begin();

		/*
		 * 1. Dessin du Background Cette méthode pourrait être remplacée par :
		 * orthogonalTiledMapRenderer.render();
		 */
		drawBackGround();

		/*
		 * 2. Dessin des murs
		 */
		drawMurs();

		/*
		 * 3. Dessin des points
		 */
		drawPoints();

		/*
		 * 4. Dessin du NamCap
		 */
		drawNamCap(runTime);

		/*
		 * 6. Dessin des fantômes
		 */
		drawFantomes(runTime);

		/*
		 * 7. Dessin des ponts à la fin pour passer derrière
		 */
		drawponts();

		/*
		 * 8. Dessin des surprises si le score est atteint
		 */
		drawSurprises(runTime);

		/*
		 * 9. Dessin de la porte de sortie quand le score requis est atteint.
		 */
		drawSortie();

		// ------- SpriteBatch END -------//
		orthogonalTiledMapRenderer.getSpriteBatch().end();

		/*
		 * 10. Dessin des informations du jeu (Score, vie, time) n'utilise pas
		 * le SpriteBatch mais un stage, qui est une vue sur l'écran
		 */
		drawGameInfos(runTime);
	}

	/**
	 * Méthode utilisée dans cette classe pour dessiner le background (terrain).
	 */
	private void drawBackGround() {
		orthogonalTiledMapRenderer.renderTileLayer(tileBackGround);
	}

	/**
	 * Méthode utilisée dans cette classe pour dessiner les murs.
	 */
	private void drawMurs() {
		orthogonalTiledMapRenderer.renderTileLayer(tileMurs);
	}

	/**
	 * Méthode utilisée dans cette classe pour dessiner le NamCap. La variable
	 * runtime est utilisée pour retrouver la trame courante. On dessine le
	 * NamCap avec sa rotation. Celle-si s'effectue autour de l'axe définie par
	 * namCap.getLargeur() /2.0f, namCap.getHauteur()/2.0f.
	 */
	private void drawNamCap(float runTime) {

		orthogonalTiledMapRenderer.getSpriteBatch().draw(
				namCapAnimation.getKeyFrame(runTime), namCap.getPosition().x,
				namCap.getPosition().y, namCap.getLargeur() / 2.0f,
				namCap.getHauteur() / 2.0f, namCap.getLargeur(),
				namCap.getHauteur(), 1, 1, namCap.getRotation());
	}

	private void drawFantomes(float runTime) {
		for (Fantome fantome : fantomeList)
			orthogonalTiledMapRenderer.getSpriteBatch().draw(
					fantomeAnimation.getKeyFrame(runTime),
					fantome.getPosition().x, fantome.getPosition().y,
					fantome.getLargeur() / 2.0f, fantome.getHauteur() / 2.0f,
					fantome.getLargeur(), fantome.getHauteur(), 1, 1,
					fantome.getRotation());
	}

	private void drawponts() {
		orthogonalTiledMapRenderer.renderTileLayer(tilePonts);
	}

	private void drawPoints() {
		for (Point p : pointObjects.getHashMapPoints().values())
			orthogonalTiledMapRenderer.getSpriteBatch().draw(pointTexture,
					p.getPosition().x, p.getPosition().y, p.getLargeur(),
					p.getHauteur());
	}

	/**
	 * Méthode qui dessine la surprise à chaque fois que le joueur atteint
	 * l'intervalle renseigné dans le constructeur de la classe GameWorld.
	 * 
	 * @param runTime
	 */
	private void drawSurprises(float runTime) {
		surpriseObject = gameWorld.getSurprise();
		
		if (surpriseObject != null)
			orthogonalTiledMapRenderer.getSpriteBatch().draw(
					surpriseAnimation.getKeyFrame(runTime),
					surpriseObject.getPosition().x,
					surpriseObject.getPosition().y,
					surpriseObject.getLargeur() / 2.0f,
					surpriseObject.getHauteur() / 2.0f,
					surpriseObject.getLargeur(), surpriseObject.getHauteur(),
					1, 1, 180);
	}

	/**
	 * Méthode qui dessinne la porte de sortie du niveau pour terminer celui-ci.
	 */
	private void drawSortie() {
		sortieObject = gameWorld.getSortie();

		if (sortieObject != null)
			orthogonalTiledMapRenderer.getSpriteBatch().draw(sortieTexture,
					sortieObject.getPosition().x, sortieObject.getPosition().y, sortieObject.getLargeur(),
					sortieObject.getHauteur());
	}

	/**
	 * Mise à jour des infos de jeu (score, time, lifes...). On utilise un
	 * nouveau SpriteBatch().
	 * 
	 * @param runTime
	 */
	private void drawGameInfos(float runTime) {
		/*----------------------------------------------------------*/
		/*------- Mise à jour du texte des différents labels -------*/
		scoreLabel.setText("Score:" + String.valueOf(gameWorld.getScore()));
		// TODO : remplacer le nombre de vies par le nombre de vies du joueur
		lifeLabel.setText("3");
		timeLabel.setText("Time:" + String.valueOf((int) (runTime * 10) / 10f));
		/*----------------------------------------------------------*/

		/*-----------------------------------------------------*/
		/*------- Mise à jour de l'affichage des labels -------*/
		stageInfosGame.draw();
		/*-----------------------------------------------------*/
	}

	/**
	 * Permet d'afficher la description de la surprise capturée.
	 * 
	 * @param runtime
	 */
	public void renderSupriseInfos() {
		Stage stage = new Stage();
		stage.addActor(new MyDialogBox().getInfoSurpriseDialog(gameWorld));
		Gdx.input.setInputProcessor(stage);
		stage.draw();
	}
	
	/**
	 * Permet d'afficher la description de la surprise capturée.
	 * 
	 * @param runtime
	 */
	public void renderPause() {
		Stage stage = new Stage();
		stage.addActor(new MyDialogBox().getPauseDialog(gameWorld));
		Gdx.input.setInputProcessor(stage);
		stage.draw();
	}
	
	/**
	 * Permet d'afficher la description de la surprise capturée.
	 * 
	 * @param runtime
	 */
	public void renderGameWin() {
		Stage stage = new Stage();
		stage.addActor(new MyDialogBox().getYouWinDialog());
		Gdx.input.setInputProcessor(stage);
		stage.draw();
	}
	
	
	/**
	 * Permet d'afficher la description de la surprise capturée.
	 * 
	 * @param runtime
	 */
	public void renderGameOver() {
		Stage stage = new Stage();
		stage.addActor(new MyDialogBox().getYouLoseDialog());
		Gdx.input.setInputProcessor(stage);
		stage.draw();
	}
}
