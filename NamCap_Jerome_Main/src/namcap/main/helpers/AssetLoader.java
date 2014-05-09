package namcap.main.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	/*
	 * Sans l'utilisation de Tiled, on utilise une texture.
	 * Cette texture est un set de plusieurs images regroupant différentes
	 * plus petites images (TextureRegion).
	 */
	public static Texture texture;
	
	
	/*
	 * Ces TextureRegion peuvent par exemple être assimilées à la série
	 * d'images composant une Animation.
	 */
	public static TextureRegion namCapRight0, namCapRight1, namCapRight2, backGround, murRegion;
	
	
	/*
	 * Une Animation est créée par un tableau de TextureRegion.
	 * Il faut lui définir un PlayMode (ex : animation.setPlayMode(Animation.LOOP_PINGPONG).
	 */
	public static Animation namCapAnimation;
	
	
	public static void load() {

		texture = new Texture(Gdx.files.internal("images/texture.png"));
		/*
		 * Les coordonnées des TextureRegion sont celles contenues dans 
		 * le fichier texture ("texture.png").
		 * On a ensuite les hauteur et largeur de la TextureRegion.
		 */
		backGround = new TextureRegion(texture, 0,0, 1023, 640);
		
		
		namCapRight0 = new TextureRegion(texture, 0, 641, 128, 128);
		// Cette méthode .flip() crée un miroir vertical ou horizontal ou les 2
		// de la TextureRegion
		namCapRight0.flip(false, true);
		namCapRight1 = new TextureRegion(texture, 128, 641, 128, 128);
		namCapRight1.flip(false, true);
		namCapRight2 = new TextureRegion(texture, 256, 641, 128, 128);
		namCapRight2.flip(false, true);
		
		/*
		 * On crée un tableau de textureRegion pour générer l'animation
		 */
		TextureRegion[] namCapRights = {namCapRight0, namCapRight1, namCapRight2};
		
		/*
		 * L'animation est créée avec le tableau de textureRegion.
		 * 0.1 est la durée d'une frame.
		 */
		namCapAnimation = new Animation(0.1f, namCapRights);
		namCapAnimation.setPlayMode(Animation.LOOP_PINGPONG);
		
		
		murRegion = new TextureRegion(texture, 384, 641, 128, 128);
		
	}

	public static void dispose(){
		texture.dispose();
	}
}
