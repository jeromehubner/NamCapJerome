package namcap.main;


import namcap.main.helpers.AssetLoaderTiled;
import namcap.main.helpers.Assets;
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
		Assets.load();
		AssetLoaderTiled.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
//		AssetLoader.dispose();
		Assets.dispose();
		AssetLoaderTiled.dispose();
	}
	
	
}