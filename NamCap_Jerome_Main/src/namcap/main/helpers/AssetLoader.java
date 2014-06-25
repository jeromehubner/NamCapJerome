package namcap.main.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class AssetLoader {
	public static Texture TEXTURE_SCREEN_LOADING;
	public static Texture TEXTURE_SCREEN_HOME;
	public static Texture TEXTURE_SCREEN_ABOUT;
	public static Texture TEXTURE_SCREEN_SCORES;
	public static Texture TEXTURE_SCREEN_HELP;
	
	public static Texture TEXTURE_BACKGROUND_NORMAL;
	public static Texture TEXTURE_BACKGROUND_SPORT;
	public static Texture TEXTURE_BACKGROUND_FOOD;
	public static Texture TEXTURE_BACKGROUND_NATURE;
	public static Texture TEXTURE_BACKGROUND_VEHICLE;

	public static void load () {
		
		/*------- Chargement des SCREENS -------*/
		TEXTURE_SCREEN_LOADING = new Texture(Gdx.files.internal("screen/loading.png"));
		TEXTURE_SCREEN_HOME = new Texture(Gdx.files.internal("screen/home.png"));
		TEXTURE_SCREEN_ABOUT = new Texture(Gdx.files.internal("screen/about.png"));
		TEXTURE_SCREEN_SCORES = new Texture(Gdx.files.internal("screen/scores.png"));
		TEXTURE_SCREEN_HELP = new Texture(Gdx.files.internal("screen/help.png"));
		/*--------------------------------------*/
		
		/*------- Chargement des BACKGROUNDS -------*/
		TEXTURE_BACKGROUND_NORMAL = new Texture(Gdx.files.internal("background/normal.png"));
		TEXTURE_BACKGROUND_SPORT = new Texture(Gdx.files.internal("background/sport.png"));
		TEXTURE_BACKGROUND_FOOD = new Texture(Gdx.files.internal("background/food.png"));
		TEXTURE_BACKGROUND_NATURE = new Texture(Gdx.files.internal("background/nature.png"));
		TEXTURE_BACKGROUND_VEHICLE = new Texture(Gdx.files.internal("background/vehicle.png"));
		/*------------------------------------------*/
	}

	public static void dispose(){
		TEXTURE_SCREEN_LOADING.dispose();
		TEXTURE_SCREEN_HOME.dispose();
		TEXTURE_SCREEN_ABOUT.dispose();
		TEXTURE_SCREEN_SCORES.dispose();
		TEXTURE_SCREEN_HELP.dispose();
		
		TEXTURE_BACKGROUND_NORMAL.dispose();
		TEXTURE_BACKGROUND_SPORT.dispose();
		TEXTURE_BACKGROUND_FOOD.dispose();
		TEXTURE_BACKGROUND_NATURE.dispose();
		TEXTURE_BACKGROUND_VEHICLE.dispose();
	}
}