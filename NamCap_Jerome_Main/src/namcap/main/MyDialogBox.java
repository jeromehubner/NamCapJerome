package namcap.main;

import namcap.main.gameWorld.GameWorld;
import namcap.main.screens.GameScreen;
import namcap.main.screens.Home;
import namcap.main.screens.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MyDialogBox {
	private Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

	public MyDialogBox() {
		/*------- Empeche l'utilisation des touches BACK et MENU du téléphone -------*/
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);

		skin.getFont("default-font").setScale(0.8f);
	}

	/**
	 * Methode qui retourne une boite de dialogue initialisee. (renseigne les
	 * dimensions et la position de celle-ci).
	 * 
	 * @param title
	 * @return
	 */
	private Dialog initDialog(String title) {
		/*------- LARGEUR et HAUTEUR de la fenetre -------*/
		float nbPixelsWidth = Gdx.graphics.getWidth(), nbPixelsHeight = Gdx.graphics
				.getHeight();

		/*------- Les DIMENSIONS et POSITIONS de la fenetre d'infos Surprise -------*/
		float widthDialogInfo = nbPixelsWidth * 60 / 100, heightDialogInfo = nbPixelsHeight * 30 / 100;
		float xDialogInfo = (nbPixelsWidth - widthDialogInfo) / 2, yDialogInfo = (nbPixelsHeight - heightDialogInfo) / 2;

		Dialog dialog = new Dialog(title, skin);
		dialog.setX(xDialogInfo);
		dialog.setY(yDialogInfo);
		dialog.setWidth(widthDialogInfo);
		dialog.setHeight(heightDialogInfo);
		return dialog;
	}

	/**
	 * Methode qui affiche une boite de dialogue avec l'effet produit de la
	 * surprise selectionne.
	 * 
	 * @param gameWorld
	 * @return
	 */
	public Dialog getInfoSurpriseDialog(final GameWorld gameWorld) {
		TextButton textButtonResume = new TextButton("Resume...", skin);
		textButtonResume.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				gameWorld.setGameState(GameStateEnum.READY);
				gameWorld.setSurprise(null);

				MainGame.getUniqueMainGame().setScreen(
						GameScreen.getUniqueGameScreen());
				return true;
			}
		});

		Dialog dialog = initDialog("Surprises !!!");
		dialog.getContentTable().add(gameWorld.getSurprise().getDescription());
		dialog.getButtonTable().add(textButtonResume).center();
		return dialog;
	}

	/**
	 * Methode qui affiche une boite de dialogue avec l'effet produit de la
	 * surprise selectionne.
	 * 
	 * @param gameWorld
	 * @return
	 */
	public Dialog getPauseDialog(final GameWorld gameWorld) {
		TextButton textButtonResume = new TextButton("Resume...", skin);
		TextButton textButtonRetry = new TextButton("Retry", skin);
		TextButton textButtonHome = new TextButton("Home", skin);
		TextButton textButtonQuit = new TextButton("Quit", skin);

		textButtonResume.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				gameWorld.setGameState(GameStateEnum.READY);
				MainGame.getUniqueMainGame().setScreen(
						GameScreen.getUniqueGameScreen());
				return true;
			}
		});

		textButtonRetry.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameScreen.getUniqueGameScreen().initGameScreen();
				MainGame.getUniqueMainGame().setScreen(
						GameScreen.getUniqueGameScreen());
				return true;
			}
		});

		textButtonHome.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				MainGame.getUniqueMainGame().setScreen(new Home());
				return true;
			}
		});

		textButtonQuit.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.exit(0);
				return true;
			}
		});

		Dialog dialog = initDialog("Pause");
		dialog.getButtonTable().add(textButtonResume).center().row();
		dialog.getButtonTable().add(textButtonRetry).center().row();
		dialog.getButtonTable().add(textButtonHome).center().row();
		dialog.getButtonTable().add(textButtonQuit).center().row();
		return dialog;
	}

	/**
	 * Methode qui informe l'utilisateur qu'il a perdu et le redirige vers une
	 * nouvelle partie.
	 * 
	 * @return
	 */
	public Dialog getYouLoseDialog() {
		String description = "Try again...";
		TextButton textButtonMenu = new TextButton("Menu", skin);
		TextButton textButtonRetry = new TextButton("Retry...", skin);

		textButtonMenu.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameScreen.getUniqueGameScreen().initGameScreen();
				MainGame.getUniqueMainGame().setScreen(Level.getUniqueLevel());
				return true;
			}
		});

		textButtonRetry.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameScreen.getUniqueGameScreen().initGameScreen();
				MainGame.getUniqueMainGame().setScreen(
						GameScreen.getUniqueGameScreen());
				return true;
			}
		});

		Dialog dialog = initDialog("You lose ...");
		dialog.getContentTable().add(description);
		dialog.getButtonTable().add(textButtonMenu).left();
		dialog.getButtonTable().add(textButtonRetry).right();
		return dialog;
	}

	/**
	 * Methode qui affiche une boite de dialogue informant le joueur qu'il a
	 * gagné la partie et le redirige vers l'ecran de selection du level.
	 * 
	 * @return
	 */
	public Dialog getYouWinDialog() {
		String description = "Niveau fini...";
		TextButton textButtonChange = new TextButton("Choix de niveau...", skin);
		textButtonChange.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO : renvoyer à l'écran LEVEL plutot que THEME
				MainGame.getUniqueMainGame().setScreen(Level.getUniqueLevel());
				return true;
			}
		});

		Dialog dialog = initDialog("You win !!!");
		dialog.getContentTable().add(description);
		dialog.getButtonTable().add(textButtonChange).center();
		return dialog;
	}

	/**
	 * Methode qui affiche un boite de confirmation avant de quitter le jeu.
	 * 
	 * @param gameWorld
	 * @return
	 */
	public Dialog ConfirmExitGameDialog(final GameWorld gameWorld) {
		TextButton textButtonYes = new TextButton("YES", skin);
		TextButton textButtonNo = new TextButton("NO", skin);

		// TODO : a completer

		textButtonYes.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO : Finir la méthode pour quitter le jeu
				return true;
			}
		});

		textButtonNo.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				MainGame.getUniqueMainGame().setScreen(
						GameScreen.getUniqueGameScreen());
				return true;
			}
		});

		Dialog dialog = initDialog("Surprises !!!");
		dialog.getContentTable().add(gameWorld.getSurprise().getDescription());
		dialog.getButtonTable().add(textButtonYes).center();
		dialog.getButtonTable().add(textButtonNo).center();
		return dialog;
	}
}