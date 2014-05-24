package namcap.main.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class AssetLoader {
	public static TextureRegion platform;
	public static TextureRegion fallingManSplash;
	public static Animation fallingManAnim;
	public static Texture chargementTexture;
	public static Texture backgroundTexture;
	public static Texture fond1Texture;
	public static Texture fond2Texture;
	public static Texture fond3Texture;
	public static Texture fond4Texture;
	public static Texture fond5Texture;
	public static Texture planTexture;
	public static Texture plan2Texture;
	public static Texture plan3Texture;
	public static Texture plan4Texture;


	public static void load () {

		TextureAtlas textureAtlas = new TextureAtlas("data/PigTest.pack");
		fallingManAnim = new Animation(0.2f, textureAtlas.findRegion("falling1"), textureAtlas.findRegion("falling2"));
		platform = textureAtlas.findRegion("platform");
		fallingManSplash = textureAtlas.findRegion("rip");
		chargementTexture = new Texture(Gdx.files.internal("data/chargement.png"));
		backgroundTexture = new Texture(Gdx.files.internal("data/back.png"));
		fond1Texture = new Texture(Gdx.files.internal("data/fond1.png"));
		fond2Texture = new Texture(Gdx.files.internal("data/fond2.png"));
		fond3Texture = new Texture(Gdx.files.internal("data/fond3.png"));
		fond4Texture = new Texture(Gdx.files.internal("data/fond4.png"));
		fond5Texture = new Texture(Gdx.files.internal("data/fond5.png"));
		planTexture = new Texture(Gdx.files.internal("data/plan.png"));
		plan2Texture = new Texture(Gdx.files.internal("data/plan2.png"));
		plan3Texture = new Texture(Gdx.files.internal("data/plan3.png"));
		plan4Texture = new Texture(Gdx.files.internal("data/plan4.png"));

	}

	public static void dispose(){
		chargementTexture.dispose();
		backgroundTexture.dispose();
		fond1Texture.dispose();
		fond2Texture.dispose();
		fond3Texture.dispose();
		fond4Texture.dispose();
		fond5Texture.dispose();
		planTexture.dispose();
		plan2Texture.dispose();
		plan3Texture.dispose();
		plan4Texture.dispose();
	}
}
