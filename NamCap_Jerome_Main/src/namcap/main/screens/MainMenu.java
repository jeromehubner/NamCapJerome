package namcap.main.screens;

import namcap.main.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MainMenu implements Screen {

	private Stage stage;

	private Image buttonPlay;
	private Image buttonLevel;
	private Image buttonScores;
	private Image buttonHelp;
	private Image buttonMenu;

	private float largeurscreen = Gdx.graphics.getWidth();
	private float hauteurscreen = Gdx.graphics.getHeight();


	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);

		float margebouton = hauteurscreen * 1 / 45;

		buttonPlay = new Image(new Texture("button/start.png"));
		buttonPlay.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 6
				/ 9.0f + 5 * margebouton);
		buttonPlay.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);
		buttonPlay.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {

//				MainGame.getUniqueMainGame().setScreen((Screen) new GameScreen());

				return true;
			}
		});

		buttonLevel = new Image(new Texture("button/level.png"));
		buttonLevel.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 5
				/ 9.0f + 5 * margebouton);
		buttonLevel.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);
		buttonLevel.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {

				MainGame.getUniqueMainGame().setScreen((Screen) new Theme());

				return true;
			}
		});

		buttonScores = new Image(new Texture("button/scores.png"));
		buttonScores.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 4
				/ 9.0f + 5 * margebouton);
		buttonScores.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);
		buttonScores.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {

				MainGame.getUniqueMainGame().setScreen((Screen) new Scores());

				return true;
			}
		});

		buttonHelp = new Image(new Texture("button/help.png"));
		buttonHelp.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 3
				/ 9.0f + 5 * margebouton);
		buttonHelp.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);
		buttonHelp.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {

				MainGame.getUniqueMainGame().setScreen((Screen) new Help());

				return true;
			}
		});

		buttonMenu = new Image(new Texture("button/menu.png"));
		buttonMenu.setPosition(largeurscreen * 1 / 20.0f,
				hauteurscreen * 0 / 9.0f);
		buttonMenu.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);
		buttonMenu.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {

				MainGame.getUniqueMainGame().setScreen((Screen) new Home());

				return true;
			}
		});

		stage.addActor(buttonPlay);
		stage.addActor(buttonLevel);
		stage.addActor(buttonScores);
		stage.addActor(buttonHelp);
		stage.addActor(buttonMenu);
	}

	@Override
	public void resize(int width, int height) {
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
