package namcap.android;

import namcap.main.MainGame;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


public class LanceurAndroid extends AndroidApplication{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = true;
		
		//Permet d'eviter que l'ecran se mette en veille lors du jeu
		cfg.useWakelock = false;
		
		initialize(MainGame.getUniqueMainGame(), cfg);
	}
}
