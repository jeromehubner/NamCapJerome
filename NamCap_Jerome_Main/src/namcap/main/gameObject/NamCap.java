package namcap.main.gameObject;

import namcap.main.screens.GameScreen;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

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
	
	
	/*------- PARAMETRES DU NAMCAP MODIFIABLES -------*/
	/*----------- (dans le menu 'Option') ------------*/
	private float vitesseDeplacement = 3*25;	// 25 est la largeur du NamCap
	/*------------------------------------------------*/

	
	/*
	 * Cette variable correspond au cercle entourant le NamCap.
	 * Elle sera utilsée pour la gestion des collisions.
	 */
	private Circle boundingCircle;


	public NamCap(float x , float y, float largeur, float hauteur) {

		this.position = new Vector2(x, y);
		this.largeur = largeur;
		this.hauteur = hauteur;

		vitesse = new Vector2(0, 0);
//		acceleration = new Vector2(0, 140);

		boundingCircle = new Circle();
	}



	public void update(float delta){
		
		// Met à jour la rotation du NamCap.
		testRotation();
		
		
		// Empêche le NamCap de sortir de l'écran.
		empecheSortie();


		// On place les coordonnées des bords du NamCap.
		boundingCircle.set(position.x + largeur/2, position.y + hauteur/2, largeur/2);


		/*
		 * On met à jour la position de notre NamCap
		 * en ajoutant à celle-ci le nouveau vecteur vitesse
		 * en prenant en compte Delta qui est le laps de temps passé
		 * depuis le dernier appel de la méthode update().
		 * Cela permet de prendre en compte la vitesse du cpu
		 * du mobile ou de la tablette utilisé.
		 */
		position.add(vitesse.cpy().scl(delta));
	}
	
	

	/**
	 * Méthode qui teste la variable vitesse du NamCap est place ainsi
	 * la rotation de celui-ci.
	 */
	private void testRotation(){
		if(isRight())
			rotation = 0;
		if(isDown())
			rotation = 90;
		if (isLeft())
			rotation = -180;
		if(isUp())
			rotation = -90;
	}
	
	
	/**
	 * Méthode qui fait rentrer le NamCap de l'autre côté de l'écran
	 * lorsque celui-ci sort. La sortie et l'entrée dans l'écran tiennent
	 * compte de la largeur du NamCap.
	 */
	private void empecheSortie(){
		if(position.x > GameScreen.largeurScreen)
			position.x = 0-largeur;
		if(position.x < 0-largeur)
			position.x = GameScreen.largeurScreen;
		if(position.y > GameScreen.hauteurScreen)
			position.y = 0-hauteur;
		if(position.y < 0-hauteur)
			position.y = GameScreen.hauteurScreen;
	}
	
	
//	/**
//	 * Méthode appelée par la Classe InputHandler dans la méthode onTouch().
//	 */
//	public void onClick(Vector2 vitesseModifiee){
//		vitesse = vitesseModifiee;
//	}


	/**
	 * Liste de méthodes qui détermine si le NamCap descend, monte, va à gauche ou à droite.
	 * C'est la variable vitesse.y qui permet de définir si la NamCap monte ou descend.
	 * C'est la variable vitesse.x qui permet de définir si la NamCap va à gauche ou à droite.
	 * L'axe des ordonnées est inversé. C'est à dire que le NamCap descend si vitesse.y > 0.
	 * Ces méthodes sont utilisées dans la méthode 'update()' de cette Classe.
	 * 
	 * @return
	 */
	private boolean isDown(){
		return vitesse.y > 0;
	}
	private boolean isLeft(){
		return vitesse.x < 0;
	}
	private boolean isUp(){
		return vitesse.y < 0;
	}
	private boolean isRight(){
		return vitesse.x > 0;
	}
	
	
	/**
	 * Liste de méthodes qui permet de diriger le NamCap à droite, en bas, à gauche ou en haut.
	 * Ces méthodes agissent pour cela sur le vecteur vitesse du NamCap.
	 */
	public void goRight(){
		vitesse.x = vitesseDeplacement;
		vitesse.y = 0;
	}
	public void goDown(){
		vitesse.x = 0;
		vitesse.y = -vitesseDeplacement;
	}
	public void goLeft(){
		vitesse.x = -vitesseDeplacement;
		vitesse.y = 0;
	}
	public void goUp(){
		vitesse.x = 0;
		vitesse.y = vitesseDeplacement;
	}
	
	
	/**
	 * Liste de méthodes vérifiant si le NamCap peut tourner.
	 * Ces méthodes sont utilisées dans cette classe.
	 */
	private boolean canGoUp(){
		return true;
	}
	private boolean canGoDown(){
		return true;		
	}
	private boolean canGoLeft(){
		return true;
	}
	private boolean canGoRight(){
		return true;
	}
	
	
	public void stopNamCap(){
		vitesse.set(0, 0);
	}
	
	
	/*--------------------------------*/
	/*------- GETTER && SETTER -------*/
	/*--------------------------------*/
	public Vector2 getPosition() {
		return position;
	}


	public float getRotation() {
		return rotation;
	}


	public float getLargeur() {
		return largeur;
	}


	public float getHauteur() {
		return hauteur;
	}


	public Circle getBoundingCircle() {
		return boundingCircle;
	}


	public Vector2 getVitesse() {
		return vitesse;
	}
	public void setVitesse(Vector2 vitesse) {
		this.vitesse= vitesse ;
	}
}
