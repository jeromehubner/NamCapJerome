package namcap.main.screens;

import namcap.main.MainGame;
import namcap.main.helpers.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Help implements Screen {

	private Stage stage;

	private Image buttonBack;

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

		Image screenHelp = new Image(AssetLoader.TEXTURE_SCREEN_HELP);
		screenHelp.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		stage.addActor(screenHelp);

		buttonBack = new Image(new Texture("button/back.png"));
		buttonBack.setPosition(largeurscreen * 1 / 20.0f,
				hauteurscreen * 0 / 9.0f);
		buttonBack.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);
	
		stage.addActor(buttonBack);
		
		installListener();
	}

	
	private void installListener(){
		buttonBack.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {

				MainGame.getUniqueMainGame().setScreen((Screen) new MainMenu());

				return true;
			}
		});
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