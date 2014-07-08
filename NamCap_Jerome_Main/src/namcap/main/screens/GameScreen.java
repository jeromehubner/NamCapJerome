package namcap.main.screens;

import namcap.main.gameWorld.GameRenderer;
import namcap.main.gameWorld.GameWorld;
import namcap.main.helpers.InputHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * 
 * @author jerome
 * 
 */
public class GameScreen implements Screen {
	private static GameScreen UNIQUE_GAME_SCREEN = null;
	private GameWorld gameWorld;
	private GameRenderer gameRenderer;

	private GameScreen() {
	}

	public static GameScreen getUniqueGameScreen() {
		if (UNIQUE_GAME_SCREEN == null)
			UNIQUE_GAME_SCREEN = new GameScreen();
		return UNIQUE_GAME_SCREEN;
	}

	/**
	 * Initialise les gameWorld et GameRenderer du GameScreen.
	 */
	public void initGameScreen() {
		gameWorld = new GameWorld();
		gameRenderer = new GameRenderer(gameWorld);
		inputHandler = new InputHandler(gameWorld);
	}

	private final static TextureAtlas TEXTURE_ATLAS = new TextureAtlas(
			Gdx.files.internal("ui/uiskin.atlas"));
	public final static Skin SKIN = new Skin(
			Gdx.files.internal("ui/uiskin.json"), TEXTURE_ATLAS);

	/*--------------------------------------*/
	/*------- PARAMETRES MODIFIABLES -------*/
	/*
	 * Cette variable est un paramètre modifiable dans le menu du jeu.
	 */
	private final static boolean USE_ACCELEROMETER = false;

	private final int nbFantomesDepart = 5;
	private final int pointsACapturerForSurprise = 15;
	private final int nbSurpriseEffects = 15;
	/*--------------------------------------*/

	/*
	 * Cette instance permet d'utiliser la méthode inputHandler.accelerometre()
	 * Si l'utilisateur a choisi d'utiliser celui-ci.
	 */
	private InputHandler inputHandler;

	// delta correspond au FPS, 1/delta correspond au nbre de x que
	// la méthode render est appelé
	// Cette méthode est traitée comme une boucle de jeu (gameLoop) pendant
	// laquelle il faut update/render les objets du jeu.
	@Override
	public void render(float delta) {
		/*-----------------------------------------------*/
		/*-----------------------------------------------*/
		/*
		 * Cette méthode n'est pas utilisée par défaut. On lui trouvera peut
		 * être une utilité plus tard.
		 */
		// inputHandler.accelerometre2();
		/*-----------------------------------------------*/
		/*-----------------------------------------------*/

		switch (gameWorld.getGameState()) {
		case READY:
			System.out.println("Game - READY");
			/*----------------------------------------------*/
			/*------- Mise à jour des objets du jeux -------*/
			gameWorld.update(delta);
			/*----------------------------------------------*/

			/*-------------------------------------------*/
			/*------- Mise à jour du rendu du jeu -------*/
			gameRenderer.render(delta);
			/*-------------------------------------------*/
			break;

		case SURPRISE_CATCHED:
			System.out.println("Game - SURPRISE_CATCHING");
			gameRenderer.renderSupriseInfos();
			break;
			
		case PAUSE:
			System.out.println("Game - PAUSE");
			gameRenderer.renderPause();
			break;

		case WIN:
			System.out.println("Game - WIN");
			gameRenderer.renderGameWin();
			break;

		case GAME_OVER:
			System.out.println("Game - GAME_OVER");
			gameRenderer.renderGameOver();
			// show();
			break;

		default:
			break;
		}
	}

	// Appelé 2x de suite après la méthode show()
	// Appelé après avoir mis le jeu en pause
	@Override
	public void resize(int width, int height) {
		System.out.println("GameScreen - resizing");
	}

	@Override
	public void show() {
		if (gameWorld == null || gameRenderer == null)
			initGameScreen();
		
		/*
		 * Permet de capter l'appui sur la touche Back du téléphone et d'y
		 * réagir dans la classe InputHandler
		 */
		Gdx.input.setCatchBackKey(true);

		if (USE_ACCELEROMETER)
			inputHandler.accelerometre();
		else
			Gdx.input.setInputProcessor(inputHandler);

		System.out.println("GameScreen - show called");
	}

	// Appelé après la méthode pause() lors d'un click sur la touche 'Back'
	@Override
	public void hide() {
		System.out.println("GameScreen - hide called");
	}

	// Appelée par un appui sur la touche 'Accueil' et 'Back'
	@Override
	public void pause() {
		System.out.println("GameScreen - pause called");
	}

	// Appelé après la méthode resizing() si la méthode pause() a été appelé
	@Override
	public void resume() {
		System.out.println("GameScreen - resume called");
	}

	@Override
	public void dispose() {
	}

	public int getNbFantomesDepart() {
		return nbFantomesDepart;
	}

	public int getPointsACapturerForSurprise() {
		return pointsACapturerForSurprise;
	}

	public int getNbSurpriseEffects() {
		return nbSurpriseEffects;
	}
}
