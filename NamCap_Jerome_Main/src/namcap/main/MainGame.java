package namcap.main;

import namcap.main.helpers.AssetLoader;
import namcap.main.screens.SplashScreen;

import com.badlogic.gdx.Game;

public class MainGame extends Game {

	private static MainGame uniqueMainGame = null;

	private MainGame() {
	}

	public static MainGame getUniqueMainGame() {
		if (uniqueMainGame == null)
			uniqueMainGame = new MainGame();

		return uniqueMainGame;
	}

	@Override
	public void create() {
		System.out.println("Game Created!");

		/*
		 * Chargement des images avant que le SplashScreen ne soit crée. Toutes
		 * les méthodes et variables de la classe AssetLoader ont été déclarées
		 * 'static' pour permettre leur accès depuis d'autres Classe.
		 */
		AssetLoader.load();

		setScreen(new SplashScreen());
		// Preferences preferences = Gdx.app.getPreferences(name);
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}