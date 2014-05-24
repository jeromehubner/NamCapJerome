package namcap.main.gameWorld;

import namcap.main.gameObject.Fantome;
import namcap.main.gameObject.NamCap;
import namcap.main.gameObject.Points;
import namcap.main.helpers.AssetLoaderMap;

import com.badlogic.gdx.math.Intersector;


/**
 * Classe permettant la 'Updating task'.
 * Cette classe a besoin de savoir quand une collision a lieu pour
 * réagir en conséquence (récupérer le score, stopper le NamCap, Jouer un son...) 
 * 
 * @author jerome
 *
 */
public class GameWorld {

	private NamCap namCap;

	private Fantome fantome1;
	private Fantome fantome2;
	private Fantome fantome3;
	
	private Points points;


	public GameWorld() {

		namCap = new NamCap(2, 1, 1, 1);

		fantome1 = new Fantome(7, 2, 1, 1);
		fantome2 = new Fantome(1, 27, 1, 1);
		fantome3 = new Fantome(10, 12, 1, 1);
		
		/*
		 * On envoie le layerObjectPoints à la classe Points qui initialisera 
		 * une hashMap contenant les points et leurs positions.
		 */ 
		points = new Points(AssetLoaderMap.layerObjectPoints.getObjects());
		
	}



	/**
	 * Cette méthode met à jour les objets du jeu en utilisant 
	 * la méthode update() de chacun d'eux (s'ils sont animés...).
	 * Les murs n'ont pas besoin d'être mis à jour car ils ne bougent pas
	 * sur le terrain.
	 * @param delta
	 */
	public void update(float delta) {
		// System.out.println("GameWorld - update");

		/*
		 * La méthode update() est définie dans la classe NamCap
		 * Elle contient les changements à apporter au namCap.
		 */
		namCap.update(delta);

		
		fantome1.update(delta);
		fantome2.update(delta);
		fantome3.update(delta);


		if(Intersector.overlaps(namCap.getBoundingCircle(), fantome1.getBoundingCircle())
				|| Intersector.overlaps(namCap.getBoundingCircle(), fantome2.getBoundingCircle())
				|| Intersector.overlaps(namCap.getBoundingCircle(), fantome3.getBoundingCircle()))
		{
			namCap.stop();
			fantome1.stop();
			fantome2.stop();
			fantome3.stop();
		}
		
		points.collision(namCap);
		
//		points.collision(fantome2);
	}


	public NamCap getNamCap() {
		return namCap;
	}

	public Fantome getFantome1() {
		return fantome1;
	}

	public Fantome getFantome2() {
		return fantome2;
	}

	public Fantome getFantome3() {
		return fantome3;
	}

	public Points getPoints() {
		return points;
	}
}