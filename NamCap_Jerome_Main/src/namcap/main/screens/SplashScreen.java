package namcap.main.screens;

import namcap.main.MainGame;
import namcap.main.helpers.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SplashScreen implements Screen {

	private Stage stage;
	private float dureeSplashScreen = 0;


	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		Image screenSplash = new Image(AssetLoader.TEXTURE_SCREEN_LOADING);
		screenSplash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// splashImage.addAction(Actions.fadeIn( 2f ));
		screenSplash.addAction(Actions.sequence(Actions.fadeOut(0.001f),
				Actions.fadeIn(dureeSplashScreen),
				Actions.run(onSplashFinishedRunnable)));

		stage.addActor(screenSplash);
	}

	Runnable onSplashFinishedRunnable = new Runnable() {

		@Override
		public void run() {
			MainGame.getUniqueMainGame().setScreen(new Home());
		}
	};

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
