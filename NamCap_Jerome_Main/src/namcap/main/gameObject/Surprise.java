package namcap.main.gameObject;

import java.util.List;
import java.util.Random;

import namcap.main.gameWorld.GameWorld;
import namcap.main.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

/**
 * Classe permettant de créer un objet Surprise si le {@link GameWorld$}
 * 
 * @author jerome
 * 
 */
public class Surprise {

	private Vector2 position;
	private float largeur, hauteur;
	private int valeur;
	private Circle boundingCircle;
	private String description;
	private String descriptionAucunFantomes = "Cette surprise n'a \n"
			+ "plus effet.        \n" + "Il n'y a plus de   \n" + "fantomes...";

	// ce chiffre permet d'autoriser certaine surprise en fonction du niveau de
	// difficulté atteint, peut⁻être modifié...
	private int nbSurpriseEffect;

	public Surprise(Vector2 position) {
		this.position = position;
		this.nbSurpriseEffect = GameScreen.getUniqueGameScreen()
				.getNbSurpriseEffects();
		largeur = 1;
		hauteur = 1;
		valeur = 50; // La valeur élevée incite le joueur a vouloir la récupérer
		boundingCircle = new Circle(position.x + largeur / 2, position.y
				+ hauteur / 2, largeur / 4);
	}

	public boolean collision(GameWorld gameWorld) {
		if (Intersector.overlaps(gameWorld.getNamCap().getBoundingCircle(),
				getBoundingCircle())) {
			Gdx.input.vibrate(200);

			randomSurpriseEffect(gameWorld);
			return true;
		}

		// Si un des fantomes prend la surprise elle disparait sans effet
		for (Fantome fantome : gameWorld.getFantomeList())
			if (Intersector.overlaps(fantome.getBoundingCircle(),
					getBoundingCircle()))
				return true;
		return false;
	}

	/**
	 * Méthode permettant de choisir un effect qui modifiera les éléments du
	 * jeu. Les éléments sont classés par effet du plus au moins intéressant
	 * pour le joueur. Cela permet en modifiant la variable nbSurpriseEffect
	 * d'ajouter des surprises MALUS en fonction de la difficulté.
	 * 
	 * @param gameWorld
	 */
	private void randomSurpriseEffect(GameWorld gameWorld) {
		// il faut ajouter autant de case que le nbEffetSurprise -1 si on veux
		// avoir accès à toutes les surprises
		switch (new Random().nextInt(nbSurpriseEffect)) {
//		 switch (15) {
		case 0:
			stopAllFantomes(gameWorld);
			break;

		case 1:
			stopOneFantome(gameWorld);
			break;

		case 2:
			increaseVitesseNamCap(gameWorld);
			break;

		case 3:
			moveNamCapOnMap(gameWorld);
			break;

		case 4:
			deleteOneFantome(gameWorld);
			break;

		case 5:
			increasePointsValue(gameWorld);
			break;

		case 6:
			decreaseVitesseAllFantomes(gameWorld);
			break;

		case 7:
			decreaseVitesseOneFantome(gameWorld);
			break;

		case 8:
			stopNamCap(gameWorld);
			break;

		case 9:
			decreasePointsValue(gameWorld);
			break;

		case 10:
			increaseVitesseOneFantome(gameWorld);
			break;

		case 11:
			decreaseVitesseNamCap(gameWorld);
			break;

		case 12:
			increaseVitesseAllFantomes(gameWorld);
			break;

		case 13:
			addOneFantome(gameWorld);
			break;

		case 14:
			inversePosition(gameWorld);
			break;

		case 15:
			inverseMovement(gameWorld);
			break;

		default:
			break;
		}
	}

	/*--------------------------------------------------------------------*/
	/*------- LISTE DE METHODES CONTENANT LES EFFETS DES SURPRISES -------*/
	/*--------------------------------------------------------------------*/
	private void increaseVitesseNamCap(GameWorld gameWorld) {
		NamCap namCap = gameWorld.getNamCap();
		float vitesse = namCap.getVitesseDeplacement();
		namCap.setVitesseDeplacement(++vitesse);

		// On renseigne la description qui sera affichée à l'écran pour informer
		// le joueur des effets de la surprise
		description = "Votre NamCap a pris \n" + "de la vitesse.     \n"
				+ "Attention au radar !";
	}

	private void decreaseVitesseNamCap(GameWorld gameWorld) {
		NamCap namCap = gameWorld.getNamCap();
		float vitesse = namCap.getVitesseDeplacement();
		namCap.setVitesseDeplacement(--vitesse);

		// On renseigne la description qui sera affichée à l'écran pour informer
		// le joueur des effets de la surprise
		description = "Votre NamCap a      \n" + "perdu en vitesse    \n"
				+ "Ne tardez pas en    \n" + "ligne droite.";

	}

	private void increaseVitesseOneFantome(GameWorld gameWorld) {
		// On récupère la liste fantomes du gameWorld
		List<Fantome> fantomeList = gameWorld.getFantomeList();

		if (fantomeList != null) {
			// On prend un nombre aléatoire contenu entre 0 et la taille de la
			// liste
			// de fantomes
			int nbAlea = new Random().nextInt(fantomeList.size());

			// On prend le fantome aléatoirement parmi la liste de fantomes du
			// gameworld
			Fantome fantome = fantomeList.get(nbAlea);

			// On modifie sa vitesse
			float vitesse = fantome.getVitesseDeplacement();
			fantome.setVitesseDeplacement(++vitesse);

			// On renseigne la description qui sera affichée à l'écran pour
			// informer
			// le joueur des effets de la surprise
			// Le retour à la ligne est géré par \n dans la boite de dialogue
			description = "Prenez garde, un    \n" + "fantome a pris de   \n"
					+ "la vitesse !!!";
		} else
			description = descriptionAucunFantomes;
	}

	private void decreaseVitesseOneFantome(GameWorld gameWorld) {
		// On récupère la liste fantomes du gameWorld
		List<Fantome> fantomeList = gameWorld.getFantomeList();

		if (fantomeList != null) {
			// On prend un nombre aléatoire contenu entre 0 et la taille de la
			// liste
			// de fantomes
			int nbAlea = new Random().nextInt(fantomeList.size());

			// On prend le fantome aléatoirement parmi la liste de fantomes du
			// gameworld
			Fantome fantome = fantomeList.get(nbAlea);

			// On modifie sa vitesse
			float vitesse = fantome.getVitesseDeplacement();
			fantome.setVitesseDeplacement(--vitesse);

			// On renseigne la description qui sera affichée à l'écran pour
			// informer
			// le joueur des effets de la surprise
			description = "Profitez-en, un     \n" + "fantome a perdu     \n"
					+ "de la vitesse.";
		} else
			description = descriptionAucunFantomes;
	}

	private void increaseVitesseAllFantomes(GameWorld gameWorld) {
		// On récupère la liste fantomes du gameWorld
		List<Fantome> fantomeList = gameWorld.getFantomeList();

		if (fantomeList != null) {
			// On modifie la vitesse de tous les fantômes
			for (Fantome fantome : fantomeList) {
				// On modifie sa vitesse
				float vitesse = fantome.getVitesseDeplacement();
				fantome.setVitesseDeplacement(++vitesse);
			}

			// On renseigne la description qui sera affichée à l'écran pour
			// informer
			// le joueur des effets de la surprise
			description = "ATTENTION !!!       \n" + "TOUS les fantomes   \n"
					+ "sont plus rapides !";
		} else
			description = descriptionAucunFantomes;
	}

	private void decreaseVitesseAllFantomes(GameWorld gameWorld) {
		// On récupère la liste fantomes du gameWorld
		List<Fantome> fantomeList = gameWorld.getFantomeList();

		// On modifie la vitesse de tous les fantômes
		if (fantomeList != null) {
			for (Fantome fantome : fantomeList) {
				// On modifie sa vitesse
				float vitesse = fantome.getVitesseDeplacement();
				fantome.setVitesseDeplacement(--vitesse);
			}

			// On renseigne la description qui sera affichée à l'écran pour
			// informer
			// le joueur des effets de la surprise
			description = "Tranquille...       \n" + "TOUS les fantomes   \n"
					+ "ont perdu en vitesse.";
		} else
			description = descriptionAucunFantomes;
	}

	/**
	 * La valeur des points est mutipliée par 2.
	 * 
	 * @param gameWorld
	 */
	private void increasePointsValue(GameWorld gameWorld) {
		int valeur = gameWorld.getPoints().getValeur();
		gameWorld.getPoints().setValeur(valeur * 2);

		// On renseigne la description qui sera affichée à l'écran pour informer
		// le joueur des effets de la surprise
		description = "La valeur des points\n" + "est multipliee par 2.";
	}

	/**
	 * La valeurs des points est divisée par 2.
	 * 
	 * @param gameWorld
	 */
	private void decreasePointsValue(GameWorld gameWorld) {
		int valeur = gameWorld.getPoints().getValeur();
		gameWorld.getPoints().setValeur(valeur / 2);

		// On renseigne la description qui sera affichée à l'écran pour informer
		// le joueur des effets de la surprise
		description = "La valeur des points\n" + "est divisee par 2.";
	}

	private void stopNamCap(GameWorld gameWorld) {

		gameWorld.getNamCap().stop();

		// On renseigne la description qui sera affichée à l'écran pour informer
		// le joueur des effets de la surprise
		description = "Depechez-vous       \n" + "de repartir !!!\n";
	}

	private void stopOneFantome(GameWorld gameWorld) {
		// TODO : Methode ne fonctionne pas
		// On récupère la liste fantomes du gameWorld
		List<Fantome> fantomeList = gameWorld.getFantomeList();

		if (fantomeList != null) {
			// On prend un nombre aléatoire contenu entre 0 et la taille de la
			// liste
			// de fantomes
			int nbAlea = new Random().nextInt(fantomeList.size());

			// On prend le fantome aléatoirement parmi la liste de fantomes du
			// gameworld
			Fantome fantome = fantomeList.get(nbAlea);

			fantome.stop();

			// On renseigne la description qui sera affichée à l'écran pour
			// informer
			// le joueur des effets de la surprise
			description = "Surprise nulle...   \n" + "Aucun effet...\n";
		} else
			description = descriptionAucunFantomes;
	}

	private void stopAllFantomes(GameWorld gameWorld) {
		// TODO : Methode ne fonctionne pas
		// On récupère la liste fantomes du gameWorld

		List<Fantome> fantomeList = gameWorld.getFantomeList();

		if (fantomeList != null) {
			// On modifie la vitesse de tous les fantômes
			for (Fantome fantome : fantomeList) {
				fantome.stop();
			}

			// On renseigne la description qui sera affichée à l'écran pour
			// informer
			// le joueur des effets de la surprise
			description = "Surprise nulle...   \n" + "Aucun effet...\n";
		} else
			description = descriptionAucunFantomes;
	}

	/**
	 * Téléporte le NamCap à une autre position.
	 * 
	 * @param gameWorld
	 */
	private void moveNamCapOnMap(GameWorld gameWorld) {
		gameWorld.getNamCap().setPosition(
				gameWorld.getObjectPositions().getNamCapStartPosition());

		// On renseigne la description qui sera affichée à l'écran pour informer
		// le joueur des effets de la surprise
		description = "Teleportation...    \n" + "Attention a         \n"
				+ "l'atterrissage !";
	}

	private void addOneFantome(GameWorld gameWorld) {
		gameWorld.getFantomeList().add(
				new Fantome(gameWorld.getObjectPositions()
						.getFantomeStartPosition()));

		// On renseigne la description qui sera affichée à l'écran pour informer
		// le joueur des effets de la surprise
		description = "Un nouveau fantome  \n" + "est arrive parmi    \n"
				+ "nous...";
	}

	/**
	 * Supprime le premier fantome de la liste de fantome du gameWorld
	 * 
	 * @param gameWorld
	 */
	private void deleteOneFantome(GameWorld gameWorld) {
		if (gameWorld.getFantomeList().remove(0) != null)

			// On renseigne la description qui sera affichée à l'écran pour
			// informer
			// le joueur des effets de la surprise
			description = "Et 1 fantome de     \n" + "moins, 1 !!!";
		else
			description = descriptionAucunFantomes;
	}

	/**
	 * Inverse la position du NamCap avec la position d'un des fantomes.
	 * 
	 * @param gameWorld
	 */
	private void inversePosition(GameWorld gameWorld) {
		Vector2 positionTempFantome = gameWorld.getFantomeList().get(0)
				.getPosition();
		Vector2 positionTempNamCap = gameWorld.getNamCap().getPosition();
		gameWorld.getFantomeList().remove(0);
		gameWorld.getNamCap().setPosition(positionTempFantome);
		gameWorld.getFantomeList().add(new Fantome(positionTempNamCap));

		description = "Position inversee...\n" + "Cherchez votre      \n"
				+ "nouvelle position.  \n";
	}

	/**
	 * Inverse les mouvements du joueurs.
	 */
	private void inverseMovement(GameWorld gameWorld) {
		float vitesseTemp = gameWorld.getNamCap().getVitesseDeplacement();
		gameWorld.getNamCap().setVitesseDeplacement(-vitesseTemp);

		description = "Mouvement inverse ! \n" + "Vos deplacements    \n"
				+ "sont inverses.      \n";
	}

	/*--------------------------------------------------------------------*/
	/*--------------------------------------------------------------------*/

	public float getLargeur() {
		return largeur;
	}

	public float getHauteur() {
		return hauteur;
	}

	public int getValeur() {
		return valeur;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	public String getDescription() {
		return description;
	}
}