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

	
	MainGame game;
	Stage stage;
	private Image gyroscopeButton;
	private Image tactileButton;
	private Image versionproButton;
	private Image aproposButton;
	private Image menuButton;

	
	float largeurscreen = Gdx.graphics.getWidth();
	float hauteurscreen =  Gdx.graphics.getHeight();
	
	public Options(MainGame game) {
		this.game = game;
	}
	
	
	
	
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
		
		Gdx.graphics.getDensity ();
		
		float margebouton = hauteurscreen * 1/45;
		
		gyroscopeButton = new Image(new Texture("data/bouton9.png"));
		gyroscopeButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 6/9.0f + 5* margebouton);
		gyroscopeButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);

		
		tactileButton = new Image(new Texture("data/bouton10.png"));
		tactileButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 5/9.0f + 5* margebouton);
		tactileButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);

		
		versionproButton = new Image(new Texture("data/bouton11.png"));
		versionproButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 4/9.0f + 5* margebouton);
		versionproButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		
		aproposButton = new Image(new Texture("data/bouton12.png"));
		aproposButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 3/9.0f + 5* margebouton);
		aproposButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		aproposButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				game.setScreen((Screen) new APropos(game));
				
				return true;
		}
		});
		
		menuButton = new Image(new Texture("data/bouton7.png"));
		menuButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 0/9.0f);
		menuButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		menuButton.addListener(new InputListener() {
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int image) {
			
			game.setScreen((Screen) new MainMenuScreen(game));
			
			return true;
		}
		
		});
		
		stage.addActor(gyroscopeButton);
		stage.addActor(tactileButton);
		stage.addActor(versionproButton);
		stage.addActor(aproposButton);
		stage.addActor(menuButton);
		
		}
			
	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {

		
	}

}
