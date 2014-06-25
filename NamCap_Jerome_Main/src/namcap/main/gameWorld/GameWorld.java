package namcap.main.gameWorld;

import java.util.ArrayList;
import java.util.List;

import namcap.main.GameStateEnum;
import namcap.main.gameObject.Fantome;
import namcap.main.gameObject.NamCap;
import namcap.main.gameObject.Points;
import namcap.main.gameObject.Sortie;
import namcap.main.gameObject.StartObjectPositions;
import namcap.main.gameObject.Surprise;
import namcap.main.screens.GameScreen;

import com.badlogic.gdx.Gdx;

/**
 * Classe permettant la 'Updating task'. Cette classe a besoin de savoir quand
 * une collision a lieu pour réagir en conséquence (récupérer le score, stopper
 * le NamCap, Jouer un son...)
 * 
 * @author jerome
 * 
 */
public class GameWorld {

	private NamCap namCap;

	private List<Fantome> fantomeList;
	private int nbFantomes;

	private Points points;
	private Surprise surprise;
	private Sortie sortie;

	private StartObjectPositions startObjectPositions;

	private int score, pointsACapturerForSurprise, nbPointsCapture;
	private GameStateEnum gameState;

	/**
	 * Ce constructeur permet de créer un {@link GameWorld} avec un nombre
	 * initial de fantômes à afficher, l'intervalle nécessaire pour faire
	 * apparaître une surprise et le nombre d'effets surprises disponibles.
	 * 
	 * @param nbFantomes
	 * @param nbPointForSurprise
	 */
	public GameWorld() {
		this.nbFantomes = GameScreen.getUniqueGameScreen()
				.getNbFantomesDepart();
		this.pointsACapturerForSurprise = GameScreen.getUniqueGameScreen()
				.getPointsACapturerForSurprise();
		initGame();
	}

	/**
	 * Méthode qui réinitialise les données du jeu
	 */
	public void initGame() {
		nbPointsCapture = 0;
		startObjectPositions = new StartObjectPositions();

		namCap = new NamCap(startObjectPositions.getNamCapStartPosition());

		fantomeList = new ArrayList<Fantome>(nbFantomes);
		// Initialisation de la liste de fantômes
		for (int i = 0; i < nbFantomes; i++) {
			// On récupère une des positions de départ pour un fantôme
			Fantome fantome = new Fantome(
					startObjectPositions.getFantomeStartPosition());

			// on ajoute le fantôme à la liste de fantôme
			fantomeList.add(fantome);

			// On supprime la position de départ utilisée pour éviter qu'un
			// autre fantôme démarre au même endroit.
			startObjectPositions.removeFantomeStartPosition(fantome);

			gameState = GameStateEnum.READY;
		}

		/*
		 * On envoie le layerObjectPoints à la classe Points qui initialisera
		 * une hashMap contenant les points et leurs positions.
		 */
		points = new Points();
	}

	/**
	 * Cette méthode met à jour les objets du jeu en utilisant la méthode
	 * update() de chacun d'eux (s'ils sont animés...). Les murs n'ont pas
	 * besoin d'être mis à jour car ils ne bougent pas sur le terrain.
	 * 
	 * @param delta
	 */
	public void update(float delta) {

		/*
		 * La méthode update() est définie dans la classe NamCap Elle contient
		 * les changements à apporter au namCap.
		 */
		namCap.update(delta);

		// Pour chaque fantome, on met à jour sa position,
		// et on check s'il y a collision
		for (Fantome fantome : fantomeList) {
			fantome.update(delta);

			if (fantome.collision(namCap)) {
				Gdx.input.vibrate(200);
				gameState = GameStateEnum.GAME_OVER;
			}

			if (surprise != null)
				if (fantome.collision(surprise)) {
					Gdx.input.vibrate(100);
					surprise = null;
				}
		}

		if (points.collision(namCap)) {
			score += points.getValeur();

			if (surprise == null)
				nbPointsCapture += 1;
		}

		if (surprise != null)
			if (surprise.collision(this)) {
				score += surprise.getValeur();
				gameState = GameStateEnum.SURPRISE_CATCHED;
				nbPointsCapture = 0;
			}

		checkScore();

		checkGameEnd();
	}

	/**
	 * Méthode qui vérifie le score et ajoute une surprise tous les
	 * 'nbPointsForSurprise' points s'il n'y en pas déjà une.
	 */
	private void checkScore() {
		// S'il n'y a pas encore de surprise, si le score > 0 et si le
		// nombre de points pour une surprise est atteint
		if (surprise == null)
			if (score > 0
					&& (nbPointsCapture >= pointsACapturerForSurprise))
				surprise = new Surprise(
						startObjectPositions.getSurprisePosition());
	}

	/**
	 * Méthode qui affiche une sortie quand il n'y a plus de points à attraper.
	 * 
	 * @return
	 */
	private void checkGameEnd() {
		if (sortie == null) {
			if (getPoints().getCollectionPoints().isEmpty())
				sortie = new Sortie(startObjectPositions.getSortiePosition());
		} else if (sortie.collision(namCap))
			gameState = GameStateEnum.WIN;
	}

	public NamCap getNamCap() {
		return namCap;
	}

	public List<Fantome> getFantomeList() {
		return fantomeList;
	}

	public Points getPoints() {
		return points;
	}

	public Surprise getSurprise() {
		return surprise;
	}

	public void setSurprise(Surprise surprise) {
		this.surprise = surprise;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public StartObjectPositions getObjectPositions() {
		return startObjectPositions;
	}

	public GameStateEnum getGameState() {
		return gameState;
	}

	public void setGameState(GameStateEnum gameStateEnum) {
		this.gameState = gameStateEnum;
	}

	public Sortie getSortie() {
		return sortie;
	}
}