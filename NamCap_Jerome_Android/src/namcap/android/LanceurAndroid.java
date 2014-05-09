package namcap.android;

import namcap.main.MainGame;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;


public class LanceurAndroid extends AndroidApplication{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		initialize(new MainGame(), false);
	}
}
