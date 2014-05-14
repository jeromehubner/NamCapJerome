package namcap.main.gameObject;

import namcap.main.helpers.AssetLoaderTiled;
import namcap.main.screens.GameScreen;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Classe contenant le NamCap, sa position, ses dimensions, sa vitesse, son accélération, sa rotation.
 * @author jerome
 *
 */
public class NamCap {

	private Vector2 position;
	private Vector2 vitesse;
	private Vector2 acceleration;

	private float rotation;
	private float largeur;
	private float hauteur;

	/*
	 * Cette variable permet de mémoriser le geste du joueur.
	 */
	private Vector2 vitesseMemorisee;


	//------- Variables utilisées pour la détection et la réaction aux collisions -------//
	int oldX, oldY;
	float tileWidth , tileHeight;
	int FacteurOldPosition = 3;


	/*------- PARAMETRES DU NAMCAP MODIFIABLES -------*/
	/*----------- (dans le menu 'Option') ------------*/
	private float vitesseDeplacement = 3*32;	// 32 est la largeur du NamCap
	/*------------------------------------------------*/


	/*
	 * Cette variable correspond au cercle entourant le NamCap.
	 * Elle sera utilsée pour la gestion des collisions.
	 */
	private Circle boundingCircle;



	public NamCap(int x , int y, float largeur, float hauteur) {

		this.position = new Vector2(x, y);
		this.largeur = largeur;
		this.hauteur = hauteur;

		vitesse = new Vector2(0, 0);
		vitesseMemorisee = new Vector2();

		boundingCircle = new Circle();
	}


	public void update(float delta){
		/*
		 * On met à jour la position de notre NamCap
		 * en ajoutant à celle-ci le nouveau vecteur vitesse
		 * en prenant en compte Delta qui est le laps de temps passé
		 * depuis le dernier appel de la méthode update().
		 * Cela permet de prendre en compte la vitesse du cpu
		 * du mobile ou de la tablette utilisé.
		 */

		// Met à jour la rotation du NamCap.
		testRotation();

		// Empêche le NamCap de sortir de l'écran.
		empecheSortie();


		// Save oldPosition
		oldX = (int)position.x;
		oldY = (int)position.y;


		// Move on X:
		position.x += vitesse.x * delta;

		// Move on Y:
		position.y += vitesse.y * delta;


		boundingCircle.set(position.x +largeur/2, position.y +hauteur/2, largeur/2);

	}

	
	/**
	 * Méthode qui teste la variable vitesse du NamCap est place ainsi
	 * la rotation de celui-ci.
	 */
	private void testRotation(){
		if(isGoingUp())
			rotation = 90;
		if(isGoingDown())
			rotation = -90;
		if (isGoingLeft())
			rotation = -180;
		if(isGoingRight())
			rotation = 0;
	}


	
	/**
	 * Méthode qui fait rentrer le NamCap de l'autre côté de l'écran
	 * lorsque celui-ci sort. La sortie et l'entrée dans l'écran tiennent
	 * compte de la largeur du NamCap.
	 */
	private void empecheSortie(){
		// Vérification en X 
		if(position.x > (AssetLoaderTiled.tiledMapWidth - largeur))
			position.x = 4; // 4px correspond au bord du NamCap
		if(position.x < 0)
			position.x = (AssetLoaderTiled.tiledMapWidth - largeur);

		// Vérification en Y
		if(position.y > (AssetLoaderTiled.tiledMapHeight - hauteur))
			position.y = 4; // 4px correspond au bord du NamCap
		if(position.y < 0)
			position.y = (AssetLoaderTiled.tiledMapHeight - hauteur);
	}

	
	
	/**
	 * Liste de méthodes utilisées pour mémoriser la direction du joueur.
	 * Si le joueur est sur 
	 */
	public void wantGoUp(){
		/*
		 * Vérification que la positionX du NamCap est un multiple de 32 (largeur d'un tile)
		 * Cette vérification prend en compte la position initiale du NamCap (4,4) + une tolérance (1)
		 * Si oui, le NamCap tourne (sur un mur, il s'arrête)
		 * Si non, la vitesse est mémorisée dans le vector2 vitesseMemorisee (laquelle sera
		 * interprété lors d'une collision). 
		 */
		vitesseMemorisee.set(0, 0);		// Initialisation de la vitesseMérmorisée
		if((position.x)%32 <= 5)
			goUp();
		else {
			vitesseMemorisee.x = 0;
			vitesseMemorisee.y = vitesseDeplacement;
		}
	}

	public void wantGoDown(){
		vitesseMemorisee.set(0, 0);		// Initialisation de la vitesseMérmorisée
		if((position.x)%32 <= 5)
			goDown();
		else {
			vitesseMemorisee.x = 0;
			vitesseMemorisee.y = -vitesseDeplacement;
		}
	}

	public void wantGoLeft(){
		vitesseMemorisee.set(0, 0);		// Initialisation de la vitesseMérmorisée
		if((position.y)%32 <= 5)
			goLeft();
		else {
			vitesseMemorisee.x = -vitesseDeplacement;
			vitesseMemorisee.y = 0;
		}
	}

	public void wantGoRight(){
		vitesseMemorisee.set(0, 0);		// Initialisation de la vitesseMérmorisée
		if((position.y)%32 <= 5)
			goRight();
		else {
			vitesseMemorisee.x = vitesseDeplacement;
			vitesseMemorisee.y = 0;
		}
	}


	/**
	 * Liste de méthodes qui permet de diriger le NamCap à droite, en bas, à gauche ou en haut.
	 * Elles sont utilisées dans la classe InputHandler.
	 * Ces méthodes agissent pour cela sur le vecteur vitesse du NamCap.
	 */
	private void goUp(){
		vitesse.x = 0;
		vitesse.y = vitesseDeplacement;
	}
	private void goDown(){
		vitesse.x = 0;
		vitesse.y = -vitesseDeplacement;
	}
	private void goLeft(){
		vitesse.x = -vitesseDeplacement;
		vitesse.y = 0;
	}
	private void goRight(){
		vitesse.x = vitesseDeplacement;
		vitesse.y = 0;
	}

	public void stop(){
		vitesse.set(0, 0);
	}


	/**
	 * Liste de méthodes qui détermine si le NamCap descend, monte, va à gauche ou à droite.
	 * C'est la variable vitesse.y qui permet de définir si la NamCap monte ou descend.
	 * C'est la variable vitesse.x qui permet de définir si la NamCap va à gauche ou à droite.
	 * L'axe des ordonnées est inversé. C'est à dire que le NamCap descend si vitesse.y > 0.
	 * 
	 * @return
	 */
	public boolean isGoingUp(){
		return vitesse.y > 0;
	}
	public boolean isGoingDown(){
		return vitesse.y < 0;
	}
	public boolean isGoingLeft(){
		return vitesse.x < 0;
	}
	public boolean isGoingRight(){
		return vitesse.x > 0;
	}


	/**
	 * Liste de méthodes permettant de donner la nouvelle au CamCap
	 * lors d'une collision.
	 */
	public void reactToUpCollision(){
		position.y = oldY -FacteurOldPosition;
		vitesse.y = 0;
		
		// Après chaque collision en Y, on attribut la vitesseMémorisée en X.
		vitesse.x = vitesseMemorisee.x;
	}
	public void reactToDownCollision(){
		position.y = oldY +FacteurOldPosition;
		vitesse.y = 0;
		// Après chaque collision en Y, on attribut la vitesseMémorisée en X.
		vitesse.x = vitesseMemorisee.x;
	}
	public void reactToLeftCollision(){
		position.x = oldX +FacteurOldPosition;
		vitesse.x = 0;
		// Après chaque collision en X, on attribut la vitesseMémorisée en Y.
		vitesse.y = vitesseMemorisee.y;
	}
	public void reactToRightCollision(){
		position.x = oldX -FacteurOldPosition;
		vitesse.x = 0;
		// Après chaque collision en X, on attribut la vitesseMémorisée en Y.
		vitesse.y = vitesseMemorisee.y;
	}



	/*--------------------------------*/
	/*------- GETTER && SETTER -------*/
	/*--------------------------------*/
	public float getLargeur() {
		return largeur;
	}
	public float getHauteur() {
		return hauteur;
	}


	public Vector2 getPosition() {
		return position;
	}
	public float getRotation() {
		return rotation;
	}
	public Vector2 getVitesse() {
		return vitesse;
	}


	public Circle getBoundingCircle() {
		return boundingCircle;
	}
}
