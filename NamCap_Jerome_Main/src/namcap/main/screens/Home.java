package namcap.main.screens;

import namcap.main.MainGame;
import namcap.main.helpers.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * glClearColor indique la couleur du fond s'il n'y a aucune image de fond
 * (background)
 */
public class Home implements Screen {

	private Stage stage;

	private Image buttonStart;
	private Image buttonOptions;
	private Image buttonQuit;

	private float largeurscreen = Gdx.graphics.getWidth();
	private float hauteurscreen = Gdx.graphics.getHeight();

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		/*
		 * Permet de quitter le jeu si apppui sur back quand on se trouve sur le
		 * MenuScreen
		 */
		Gdx.input.setCatchBackKey(false);
		Gdx.input.setCatchMenuKey(true);
		/**
		 * Re-taille automatiquement les images par rapport au t�l�phone ou
		 * tablette
		 */

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Image screenHome = new Image(AssetLoader.TEXTURE_SCREEN_HOME);
		screenHome.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		/**
		 * Cette m�thode permet d'afficher les boutons sur le menu : 5 boutons
		 * en tout new Image permet d'afficher l'image correspondant au bouton
		 * setPosition permet de g�rer la position sur l'�cran addListener
		 * permet de g�rer l'action sur le bouton
		 */

		float margebouton = hauteurscreen * 1 / 45;

		/*------- BUTTON START -------*/
		buttonStart = new Image(new Texture("button/start.png"));
		buttonStart.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 5
				/ 9.0f + 5 * margebouton);
		buttonStart.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		/*------- BUTTON OPTIONS -------*/
		buttonOptions = new Image(new Texture("button/options.png"));
		buttonOptions.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 4
				/ 9.0f + 4 * margebouton);
		buttonOptions.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		/*------- BUTTON QUIT -------*/
		buttonQuit = new Image(new Texture("button/quit.png"));
		buttonQuit.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 3
				/ 9.0f + 3 * margebouton);
		buttonQuit.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		stage.addActor(screenHome);
		stage.addActor(buttonStart);
		stage.addActor(buttonOptions);
		stage.addActor(buttonQuit);

		installListener();
	}

	private void installListener() {

		buttonStart.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				MainGame.getUniqueMainGame().setScreen((Screen) new MainMenu());
				return true;
			}
		});

		buttonOptions.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				MainGame.getUniqueMainGame().setScreen((Screen) new Options());
				return true;
			}
		});

		buttonQuit.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				Gdx.app.exit();
				return false;
			}
		});
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
