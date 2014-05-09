package namcap.main.helpers;

import namcap.main.gameObject.NamCap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

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
	private int touchX, touchY;
	
	// Vitesse utilisée pour la méthode accelerometre2()
	private Vector2 vitesseExponentielle;
	
	
	/*------- PARAMETRES MODIFIABLES -------*/
	/*-------(dans le menu 'Option') -------*/
	
	// Cette variable doit être comprise entre 0 et 10.
	private float sensibiliteAccelerometre = 3;
	/*------------------------------------------------*/

	
	
	public InputHandler(NamCap namCap) {
		/*
		 * Le NamCap est celui de la classe GameWorld.
		 */
		this.namCap = namCap;
		
		
		vitesseExponentielle = new Vector2();
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
		case Input.Keys.LEFT:
			namCap.goLeft();	
			break;

		case Input.Keys.DOWN:
			namCap.goDown();
			break;

		case Input.Keys.RIGHT:
			namCap.goRight();
			break;

		case Input.Keys.UP:
			namCap.goUp();
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
	 * --- Utilisée pour le lanceur Android ---
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		this.touchX = screenX;
		this.touchY = screenY;

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
		int deltaX = screenX - this.touchX;
		int deltaY = screenY - this.touchY;

		if (deltaX > 100) {
			namCap.goRight();
		}
		else if (deltaX < -100) {
			namCap.goLeft();
		}
		if (deltaY > 100) {
			namCap.goDown();
		}
		else if (deltaY < -100) {
			namCap.goUp();
		}

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

		if(Gdx.input.getAccelerometerX()>sensibiliteAccelerometre){		// Inclinaison vers soi
			namCap.goDown();
		}
		else if(Gdx.input.getAccelerometerX()<-sensibiliteAccelerometre){	// Inclinaison au dela de soi
			namCap.goUp();
		}
		else if(Gdx.input.getAccelerometerY()>sensibiliteAccelerometre){	// Inclinaison vers la droite
			namCap.goRight();
		}
		else if (Gdx.input.getAccelerometerY()<-sensibiliteAccelerometre){	// Inclinaison vers la gauche
			namCap.goLeft();
		}
		return true;
	}
	
	/**
	 * Cette méthode permet de déplacer le NamCap à la manière d'une bille
	 * sur une surface plane (permet donc le déplacement en diagonale). 
	 */
	public boolean accelerometre2(){
		vitesseExponentielle.x += Gdx.input.getAccelerometerY() *2;
		vitesseExponentielle.y += Gdx.input.getAccelerometerX() *2;
		namCap.setVitesse(vitesseExponentielle);
		return true;
	}
}
