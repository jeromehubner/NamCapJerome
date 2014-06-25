package namcap.main.screens;

import namcap.main.MainGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Options implements Screen {

	private Stage stage;
	
	private Image buttonGyroscope;
	private Image buttonTactile;
	private Image buttonBuyPro;
	private Image buttonAbout;
	private Image buttonMenu;

	private float largeurscreen = Gdx.graphics.getWidth();
	private float hauteurscreen = Gdx.graphics.getHeight();


	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void show() {

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);

		Gdx.graphics.getDensity();

		float margebouton = hauteurscreen * 1 / 45;

		buttonGyroscope = new Image(new Texture("button/gyro.png"));
		buttonGyroscope.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen
				* 6 / 9.0f + 5 * margebouton);
		buttonGyroscope.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		buttonTactile = new Image(new Texture("button/tactile.png"));
		buttonTactile.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 5
				/ 9.0f + 5 * margebouton);
		buttonTactile.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		buttonBuyPro = new Image(new Texture("button/buy_pro.png"));
		buttonBuyPro.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 4
				/ 9.0f + 5 * margebouton);
		buttonBuyPro.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		buttonAbout = new Image(new Texture("button/about.png"));
		buttonAbout.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 3
				/ 9.0f + 5 * margebouton);
		buttonAbout.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);
		buttonAbout.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {

				MainGame.getUniqueMainGame().setScreen((Screen) new APropos());

				return true;
			}
		});

		buttonMenu = new Image(new Texture("button/menu.png"));
		buttonMenu.setPosition(largeurscreen * 1 / 20.0f,
				hauteurscreen * 0 / 9.0f);
		buttonMenu.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);
		buttonMenu.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {

				MainGame.getUniqueMainGame().setScreen((Screen) new Home());

				return true;
			}

		});

		stage.addActor(buttonGyroscope);
		stage.addActor(buttonTactile);
		stage.addActor(buttonBuyPro);
		stage.addActor(buttonAbout);
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
