package namcap.main.gameObject;

import namcap.main.helpers.AssetLoaderMap;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Classe contenant le NamCap, sa position, ses dimensions, sa vitesse, son accélération, sa rotation.
 * @author jerome
 *
 */
public class NamCap {

	protected Vector2 position;
	protected Vector2 vitesse;
//	private Vector2 acceleration;

	private float rotation;
	private float namCapWidth;
	private float namCapHeight;

	/*
	 * Cette variable correspond au cercle entourant le NamCap.
	 * Elle sera utilsée pour la gestion des collisions.
	 */
	protected Circle boundingCircle;
	

	/*
	 * Cette variable permet de mémoriser le geste du joueur.
	 */
	protected Vector2 vitesseMemorisee;


	/*------- PARAMETRES DU NAMCAP MODIFIABLES -------*/
	/*----------- (dans le menu 'Option') ------------*/
	protected float vitesseDeplacement = 3;
	/*------------------------------------------------*/


	/* ------- Calques utilisés pour la gestion des collisions -------*/
	protected TiledMapTileLayer murs;
	protected TiledMapTileLayer intersections;
	/* ---------------------------------------------------------------*/

	/*------- Dimensions de l'écran -------*/
	private float screenWidth = AssetLoaderMap.tiledMapWidth;
	private float screenHeight = AssetLoaderMap.tiledMapHeight -3;
	
	
	public NamCap(Vector2 position) {
		this.position = position;
		this.namCapWidth = 1;
		this.namCapHeight = 1;

		vitesse = new Vector2(0, 0);
		vitesseMemorisee = new Vector2();

		boundingCircle = new Circle();

		// TODO : Utilisé les calques d'objet pour créer une HashMap d'objet Murs
		murs = AssetLoaderMap.layerMurs;
		intersections = AssetLoaderMap.layerIntersections;
	}




	/**
	 * Mise à jour la position de notre NamCap
	 * en ajoutant à celle-ci le nouveau vecteur vitesse
	 * en prenant en compte Delta qui est le laps de temps passé
	 * depuis le dernier appel de la méthode update().
	 * Cela permet de prendre en compte la vitesse du cpu
	 * du mobile ou de la tablette utilisé.
	 * @param delta
	 */
	public void update(float delta){
		// Met à jour la rotation du NamCap.
		testRotation();


		checkOutOfScreen();


		//-------------------------//
		//------- Move on Y -------//
		//-------------------------//
		position.y += vitesse.y * delta;

		//------- Check collisionY -------//
		if(isGoingUp())
			checkGoingUpCollision();

		else if(isGoingDown())
			checkGoingDownCollision();
		//--------------------------------//


		//-------------------------//
		//------- Move on X -------//
		//-------------------------//
		position.x += vitesse.x *  delta;

		//------- Check collisionX -------//
		if(isGoingLeft())
			checkGoingLeftCollision();
		else if(isGoingRight())
			checkGoingRightCollision();
		//--------------------------------//


		//----------------------------------//
		//------- Check Intersection -------//
		checkIntersection();
		//----------------------------------//

		// On place les nouveaux bords du NamCap après avoir fait toutes les vérifications
		boundingCircle.set(position.x +namCapWidth/2, position.y +namCapHeight/2, namCapWidth/2);
	}


	/**
	 * Méthode qui teste la variable vitesse du NamCap est place ainsi
	 * la rotation de celui-ci.
	 */
	protected void testRotation(){
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
	 * compte de la largeur du NamCap et de la largeur de l'écran.
	 */
	private void checkOutOfScreen(){
		// Vérification en Y
		if(position.y > (screenHeight - namCapHeight))
			position.y = 0;
		if(position.y < 0)
			position.y = (screenHeight - namCapHeight);

		// Vérification en X
		if(position.x > (screenWidth - namCapWidth))
			position.x = 0;
		if(position.x < 0)
			position.x = (screenWidth - namCapWidth);


	}


	/**
	 * Méthode qui vérifie que la cellule qui vient au dessus de celle du NamCap
	 * ne contient pas la propriété "block". Au quel cas, la méthode reactToUpCollision
	 * est appelée.
	 */
	private void checkGoingUpCollision(){
		Cell cell = getCellOnUp(murs);

		if(cell != null)

			if(cell.getTile().getProperties().containsKey("block"))
				reactToUpCollision();
	}

	/**
	 * Méthode qui vérifie que la cellule qui vient au dessous de celle du NamCap
	 * ne contient pas la propriété "block". Au quel cas, la méthode reactToDownCollision
	 * est appelée.
	 */
	private void checkGoingDownCollision(){
		Cell cell = getCellOnDown(murs);

		if(cell != null)

			if(cell.getTile().getProperties().containsKey("block"))
				reactToDownCollision();
	}

	/**
	 * Méthode qui vérifie que la cellule qui vient à gauche de celle du NamCap
	 * ne contient pas la propriété "block". Au quel cas, la méthode reactToLeftCollision
	 * est appelée.
	 */
	private void checkGoingLeftCollision(){
		Cell cell = getCellOnLeft(murs);

		if(cell != null)

			if(cell.getTile().getProperties().containsKey("block"))
				reactToLeftCollision();
	}

	/**
	 * Méthode qui vérifie que la cellule qui vient à droite de celle du NamCap
	 * ne contient pas la propriété "block". Au quel cas, la méthode reactToRightCollision
	 * est appelée.
	 */
	private void checkGoingRightCollision(){
		Cell cell = getCellOnRight(murs);

		if(cell != null)

			if(cell.getTile().getProperties().containsKey("block"))
				reactToRightCollision();
	}


	/**
	 * Méthode qui met à jour le vecteur vitesse du NamCap si celui-ci
	 * est sur une case à la propriété "intersection" dans le calque 
	 * AssetLoader.layerIntersections.
	 */
	private void checkIntersection(){
		Cell cell;

		if(isGoingUp() || isGoingDown()){
			// Si l'utilisateur a choisi d'aller à gauche ou à droite
			if(vitesseMemorisee.x != 0)
				// Si la position du namCap est proche de 8% des bords d'une tile
				if((position.y - (int)position.y) < 0.08){
					// Je récupère la tile dans laquelle je me trouve
					cell = intersections.getCell((int)position.x, (int)position.y);
					// Si je suis sur une tile non nulle du calque Intersection
					if(cell !=null)
						// Si cette tile a la propriété "intersection"
						if(cell.getTile().getProperties().containsKey("intersection"))
						{
							// si je veux aller à droite
							if(vitesseMemorisee.x > 0){
								if(canGoingRight()){
									position.y = (int)position.y;
									vitesse.x = vitesseMemorisee.x;
									vitesse.y = 0;
								}
							}
							else if(vitesseMemorisee.x < 0)
								if(canGoingLeft2()){
									position.y = (int)position.y;
									vitesse.x = vitesseMemorisee.x;
									vitesse.y = 0;
								}
						}
				}
		}

		else if(isGoingLeft() || isGoingRight())
			if(vitesseMemorisee.y != 0)
				// On vérifie que la position du namCap est proche de 8%
				if((position.x - (int)position.x) < 0.08){
					cell = intersections.getCell((int) position.x,
							(int) position.y);
					if(cell !=null)
						if(cell.getTile().getProperties().containsKey("intersection"))
						{
							if(vitesseMemorisee.y > 0){
								if(canGoingUp()){
									position.x = (int)position.x;
									vitesse.x = 0;
									vitesse.y = vitesseMemorisee.y;
								}
							}
							else if(vitesseMemorisee.y < 0)
								if(canGoingDown2()){
									position.x = (int)position.x;
									vitesse.x = 0;
									vitesse.y = vitesseMemorisee.y;
								}
						}
				}
	}



	/**
	 * Méthode qui permet de définir si le NamCap peut aller dans la 
	 * cellule au dessus (cellule dans le calque murs
	 * est nulle, donc ne contient pas la propriété "block").
	 * @return
	 */
	protected boolean canGoingUp(){
		return getCellOnUp(murs) == null;
	}
	protected boolean canGoingDown(){
		return getCellOnDown(murs) == null;
	}
	/**
	 * Méthode dans la méthode checkIntersection().
	 * @return
	 */
	protected boolean canGoingDown2(){
		return getCellOnDown2(murs) == null;
	}
	protected boolean canGoingLeft(){
		return getCellOnLeft(murs) == null;
	}
	/**
	 * Méthode dans la méthode checkIntersection().
	 * @return
	 */
	protected boolean canGoingLeft2(){
		return getCellOnLeft2(murs) == null;
	}
	
	protected boolean canGoingRight(){
		return getCellOnRight(murs) == null;
	}



	/**
	 * Méthode qui retourne la cellule au dessus de celle du NamCap dans le calque passé en paramètre
	 * (avant que celui-ci ne soit dessus).
	 * @param calque
	 * @return
	 */
	protected Cell getCellOnUp(TiledMapTileLayer calque){
		return calque.getCell((int)position.x, (int)position.y +1);
	}

	/**
	 * Méthode qui retourne la cellule au dessous de celle du NamCap dans le calque passé en paramètre.
	 * (avant que celui-ci ne soit dessus).
	 * @param calque
	 * @return
	 */
	protected Cell getCellOnDown(TiledMapTileLayer calque){
		return calque.getCell((int)position.x, (int)position.y);
	}
	
	/**
	 * Méthode utilisée dans la méthode canGoDown2().
	 * Elle est une peu différente car elle teste la tile avant que le NamCap arrive sur celle-ci.
	 * @param calque
	 * @return
	 */
	protected Cell getCellOnDown2(TiledMapTileLayer calque){
		return calque.getCell((int)position.x, (int)position.y -1);
	}

	/**
	 * Méthode qui retourne la cellule à gauche de celle du NamCap dans le calque passé en paramètre.
	 * (avant que celui-ci ne soit dessus).
	 * @param calque
	 * @return
	 */
	protected Cell getCellOnLeft(TiledMapTileLayer calque){
		//TODO : ne fonctionne pas avec la cellule de gauche
		return calque.getCell((int)position.x, (int)position.y);
	}
	
	/**
	 * Méthode utilisée dans la méthode canGoLeft2().
	 * Elle est une peu différente car elle teste la tile avant que le NamCap arrive sur celle-ci.
	 * @param calque
	 * @return
	 */
	protected Cell getCellOnLeft2(TiledMapTileLayer calque){
		//TODO : ne fonctionne pas avec la cellule de gauche
		return calque.getCell((int)position.x -1, (int)position.y);
	}

	/**
	 * Méthode qui retourne la cellule à droite de celle du NamCap dans le calque passé en paramètre.
	 * (avant que celui-ci ne soit dessus).
	 * @param calque
	 * @return
	 */
	protected Cell getCellOnRight(TiledMapTileLayer calque){
		return calque.getCell((int)position.x +1, (int)position.y);
	}


	/**
	 * Liste de méthodes utilisées pour mémoriser la direction du joueur.
	 * Méthodes appelées par la classe inputHandler.
	 * Lorsque l'utilisateur souhaite bouger le NamCap, sa position est vérifiée. 
	 * Si position.X ou position.Y du joueur (selon la direction actuelle du joueur)
	 * est un entier alors le NamCap bouge sinon sa vitesse (qui détermine aussi sa direction)
	 * est enregistrée.
	 */
	public void memorizeGoUp(){
		/*
		 * Vérification que la positionX du NamCap est un entier.
		 * Si oui, le NamCap tourne (sur un mur, il s'arrête)
		 * Si non, la vitesse est mémorisée dans le vector2 vitesseMemorisee (laquelle sera
		 * interprétée lors d'une collision). 
		 */
		vitesseMemorisee.set(0, 0);		// Initialisation de la vitesseMémorisée
		//TODO : tenir compte de position.x = 0
		if((position.x - (int)position.x) == 0)
			goUp();
		else {
			vitesseMemorisee.x = 0;
			vitesseMemorisee.y = vitesseDeplacement;
		}
	}

	public void memorizeGoDown(){
		vitesseMemorisee.set(0, 0);		// Initialisation de la vitesseMémorisée
		//TODO : tenir compte de position.x = 0
		//TODO : déplacer ce test de condition
		if((position.x - (int)position.x) == 0)
			goDown();
		else {
			vitesseMemorisee.x = 0;
			vitesseMemorisee.y = -vitesseDeplacement;
		}
	}

	public void memorizeGoLeft(){
		vitesseMemorisee.set(0, 0);		// Initialisation de la vitesseMémorisée
		if((position.y - (int)position.y) == 0)
			goLeft();
		else {
			vitesseMemorisee.x = -vitesseDeplacement;
			vitesseMemorisee.y = 0;
		}
	}

	public void memorizeGoRight(){
		vitesseMemorisee.set(0, 0);		// Initialisation de la vitesseMémorisée
		if((position.y - (int)position.y) == 0)
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
	 * Liste de méthodes permettant de donner la nouvelle direction 
	 * au NamCap lors d'une collision.
	 */
	public void reactToUpCollision(){
		// On replace la position.y du NamCap sur un nombre entier
		position.y = (int)position.y;

		// On arrête le déplacement du NamCap en Y 
		vitesse.y = 0;

		// On attribut la vitesseMémorisée en X
		vitesse.x = vitesseMemorisee.x;
	}
	public void reactToDownCollision(){
		//		// On replace la position.y du NamCap sur un nombre entier
		position.y = (int)position.y +1;

		// On arrête le déplacement du NamCap en Y
		vitesse.y = 0;

		// On attribut la vitesseMémorisée en X.
		vitesse.x = vitesseMemorisee.x;
	}
	public void reactToLeftCollision(){
		// On replace la position.x du NamCap sur un nombre entier
		position.x = (int)position.x +1;

		// On arrête le déplacement du NamCap en X
		vitesse.x = 0;

		// On attribut la vitesseMémorisée en Y.
		vitesse.y = vitesseMemorisee.y;
	}
	public void reactToRightCollision(){
		// On replace la position.x du NamCap sur un nombre entier
		position.x = (int)position.x;

		// On arrête le déplacement du NamCap en X
		vitesse.x = 0;

		// On attribut la vitesseMémorisée en Y.
		vitesse.y = vitesseMemorisee.y;
	}



	/*--------------------------------*/
	/*------- GETTER && SETTER -------*/
	/*--------------------------------*/
	public float getLargeur() {
		return namCapWidth;
	}
	public float getHauteur() {
		return namCapHeight;
	}


	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	
	public float getRotation() {
		return rotation;
	}
	
	
	public Vector2 getVitesse() {
		return vitesse;
	}
	
	
	public float getVitesseDeplacement() {
		return vitesseDeplacement;
	}
	public void setVitesseDeplacement(float vitesseDeplacement) {
		this.vitesseDeplacement = vitesseDeplacement;
	}
	
	
	public Circle getBoundingCircle() {
		return boundingCircle;
	}
}
