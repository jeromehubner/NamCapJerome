package namcap.main.helpers;

import namcap.main.gameObject.NamCap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Classe implémentant des méthodes appelées quand l'appli reçoit
 * une entrée utilisateur (ex : touch...)
 * Certaines de ces méthodes sont utilisées que dans 
 * la partie Desktop et d'autres dans la partie Android.
 * @author jerome
 *
 */
public class InputHandler implements InputProcessor {
	
	private NamCap namCap;
	private int touchDownX, touchDownY;
	
	
//	// Vitesse utilisée pour la méthode accelerometre2()
//	private Vector2 vitesseExponentielle;
	
	
	/*------- PARAMETRES MODIFIABLES -------*/
	/*-------(dans le menu 'Option') -------*/
	
	// Cette variable doit être comprise entre 0 et 10.
	private float sensibiliteAccelerometre = 3;
	private float sensibiliteTactile = 60;
	/*------------------------------------------------*/


	
	public InputHandler(NamCap namCap) {
		/*
		 * Le NamCap est celui de la classe GameWorld.
		 */
		this.namCap = namCap;
		
//		vitesseExponentielle = new Vector2();
	}


	/*---------------------------------------------------------*/
	/*------- Méthodes utilisées par le lanceur Desktop -------*/
	/*---------------------------------------------------------*/
	/**
	 * Cette méthode modifie directement le vecteur vitesse du NamCap.
	 */
	@Override
	public boolean keyDown(int keycode) {
		switch(keycode)
		{
		case Input.Keys.UP:
			namCap.memorizeGoUp();
			break;
			
		case Input.Keys.DOWN:
			namCap.memorizeGoDown();
			break;
			
		case Input.Keys.LEFT:
			namCap.memorizeGoLeft();
			break;

		case Input.Keys.RIGHT:
			namCap.memorizeGoRight();
			break;
			
		case Input.Keys.SPACE:
			namCap.stop();
			break;
		}
		return true;
	}

	
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	/*---------------------------------------------------------*/



	/**
	 * Cette méthode est utilisée pour récupérer les coordonnées lorsque
	 * doigt touche l'écran. Ces coordonnées sont renseignées dans
	 * les 2 variables d'instance correspondantes.
	 */
	/*---------------------------------------------------------*/
	/*------- Méthodes utilisées par le lanceur Android -------*/
	/*---------------------------------------------------------*/
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		this.touchDownX = screenX;
		this.touchDownY = screenY;

		return true;
	}


	/**
	 * Cette méthode calcul le deltaX et le deltaY lorsque le doigt se lève de l'écran.
	 * Cette méthode modifie directement le vecteur vitesse du NamCap avec
	 * la méthode du NamCap corespondante.
	 * --- Utilisée pour le lanceur Android ---
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		int deltaX = screenX - this.touchDownX;
		int deltaY = screenY - this.touchDownY;

		// Mouvement en X
		if (deltaY < -sensibiliteTactile) {
			namCap.memorizeGoUp();
		}
		else if (deltaY > sensibiliteTactile) {
			namCap.memorizeGoDown();
		}
		
		// Mouvement en Y
		if (deltaX < -sensibiliteTactile) {
			namCap.memorizeGoLeft();
		}
		else if (deltaX > sensibiliteTactile) {
			namCap.memorizeGoRight();
		}
		
		// Pause (le toucher doit être < 10% du déplacement tactile)
//		if (deltaX < (sensibiliteTactile/10) && deltaY < (sensibiliteTactile/10))
//			namCap.stop();
		
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	/**
	 * Cette méthode qui teste les valeurs de l'accéléromètre et modifie le vesteur vitesse en condition.
	 * ATTENTION : Les coordonnées de mesure de l'accéléromètre ne tiennent pas compte de format paysage.
	 * (voir la représentation ci dessous)
	 *      _______
	 *     |	   |
	 *     |	   |
	 *     |       |
	 *     |       |
	 *   x |_______|
	 *     |___o___|
	 *     y
	 */
	public boolean accelerometre(){
		// Les inclinaisons CI-DESSOUS tiennent compte du mode paysage du jeu.

		if(Gdx.input.getAccelerometerX()<-sensibiliteAccelerometre)	// Inclinaison au dela de soi
			namCap.memorizeGoUp();
		
		else if(Gdx.input.getAccelerometerX()>sensibiliteAccelerometre)		// Inclinaison vers soi
			namCap.memorizeGoDown();
		
		else if (Gdx.input.getAccelerometerY()<-sensibiliteAccelerometre)	// Inclinaison vers la gauche
			namCap.memorizeGoLeft();
		
		else if(Gdx.input.getAccelerometerY()>sensibiliteAccelerometre)	// Inclinaison vers la droite
			namCap.memorizeGoRight();
		
		return true;
	}
	
//	/**
//	 * Cette méthode permet de déplacer le NamCap à la manière d'une bille
//	 * sur une surface plane (permet donc le déplacement en diagonale). 
//	 */
//	public boolean accelerometre2(){
//		vitesseExponentielle.x += Gdx.input.getAccelerometerY() *2;
//		vitesseExponentielle.y += Gdx.input.getAccelerometerX() *2;
//		namCap.setVitesse(vitesseExponentielle);
//		return true;
//	}
}
