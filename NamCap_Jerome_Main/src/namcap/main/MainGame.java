package namcap.main;


import namcap.main.helpers.AssetLoader;
import namcap.main.helpers.AssetLoaderMap;
import namcap.main.screens.SplashScreen;

import com.badlogic.gdx.Game;

public class MainGame extends Game {

	@Override
	public void create() {
		System.out.println("Game Created!");
		
		/*
		 * Chargement des images avant que le GamScreen ne soit crée.
		 * Toutes les méthodes et variables de la classe AssetLoader ont été
		 * déclarées 'static' pour permettre leur accès depuis d'autres Classe.
		 */
//		AssetLoader.load();
		AssetLoader.load();
		AssetLoaderMap.load();
		setScreen(new SplashScreen(this));
//		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
		AssetLoaderMap.dispose();
	}
	
	
}