package namcap.main.screens;

import namcap.main.MainGame;
import namcap.main.helpers.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

 public class APropos implements Screen {

	
	MainGame game;
	Stage stage;
	
	private Image retourButton;
//	private Actor plan2Image;
	
	float largeurscreen = Gdx.graphics.getWidth();
	float hauteurscreen =  Gdx.graphics.getHeight();
	
	public APropos(MainGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
//		Table.drawDebug(stage);
	

	}

		
		@Override
	public void show() {
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setCatchMenuKey(true);
		
		
		Image plan2Image = new Image(Assets.plan2Texture);
		plan2Image.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		
		stage.addActor(plan2Image);
		
		retourButton = new Image(new Texture("data/bouton8.png"));
		retourButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 0/9.0f);
		retourButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		retourButton.addListener(new InputListener() {
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int image) {
			
			game.setScreen((Screen) new Options(game));
			
			return true;
		}
		
		});
		
		stage.addActor(retourButton);
		
		
		}
			
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

}
