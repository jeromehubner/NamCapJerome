package namcap.main.screens;

import namcap.main.gameWorld.GameRenderer;
import namcap.main.gameWorld.GameWorld;
import namcap.main.helpers.InputHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * 
 * @author jerome
 * 
 */
public class GameScreen implements Screen {
	private GameWorld gameWorld;
	private GameRenderer gameRenderer;
	
	/*
	 * Cette valeur sera utilsée dans la méthode render(float runTime)
	 * de la classe GameRenderer.
	 */
	public static float runTime;
	
	/*------------------------------------*/
	/*------- PARAMETRE MODIFIABLE -------*/
	/*
	 * Cette variable est un paramètre modifiable dans le menu du jeu.
	 */
	private static boolean useAccelerometre = false;
	/*------------------------------------*/

	
	/*
	 * Cette instance permet d'utiliser la méthode inputHandler.accelerometre()
	 * Si l'utilisateur a choisi d'utiliser celui-ci.
	 */	
	private InputHandler inputHandler;
	
	
	public GameScreen() {
		
		gameWorld = new GameWorld();
		gameRenderer = new GameRenderer(gameWorld);
		
		inputHandler = new InputHandler(gameWorld.getNamCap());
		
		/*
		 * Si l'utilisateur a choisi l'accéléromètre,
		 * on ne renseigne pas le InputProcessor à la crétaion du GameScreen.
		 */
		if(!useAccelerometre)
			Gdx.input.setInputProcessor(inputHandler);
		
		
		System.out.println("GameScreen Attached");
	}

	
	
	// delta correspond au FPS, 1/delta correspond au nbre de x que
	// la méthode render est appelé
	// Cette méthode est traitée comme une boucle de jeu (gameLoop) pendant 
	// laquelle il faut update/render les objets du jeu.
	@Override
	public void render(float delta) {
		runTime += delta;
		
		/*-----------------------------------------------*/
		/*-----------------------------------------------*/
		/*
		 * Cette méthode n'est pas utilisée par défaut.
		 * On lui trouvera peut être une utilité plus tard.
		 */
//		inputHandler.accelerometre2();
		/*-----------------------------------------------*/
		/*-----------------------------------------------*/
		
		
		// Test de l'accéléromètre
		if(useAccelerometre)
			inputHandler.accelerometre();
		
		/*----------------------------------------------*/
		/*------- Mise à jour des objets du jeux -------*/
		gameWorld.update(delta); 
		/*----------------------------------------------*/
		
		/*-------------------------------------------*/
		/*------- Mise à jour du rendu du jeu -------*/
		gameRenderer.render(runTime);
		/*-------------------------------------------*/
		
	}

	// Appelé 2x de suite après la méthode show()
	// Appelé après avoir mis le jeu en pause
	@Override
	public void resize(int width, int height) {
		System.out.println("GameScreen - resizing");		
	}
	
	@Override
	public void show() {
		System.out.println("GameScreen - show called");
	}
	
	//Appelé après la méthode pause() lors d'un click sur la touche 'Back'
	@Override
	public void hide() {
		System.out.println("GameScreen - hide called");     
	}

	// Appelée par un appui sur la touche 'Accueil' et 'Back'
	@Override
	public void pause() {
		System.out.println("GameScreen - pause called");        
	}

	//Appelé après la méthode resizing() si la méthode pause() a été appelé
	@Override
	public void resume() {
		System.out.println("GameScreen - resume called");       
	}

	@Override
	public void dispose() {
	}
}
