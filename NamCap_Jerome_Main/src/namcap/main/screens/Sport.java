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

 public class Sport implements Screen {
	 
	MainGame game;
	Stage stage;
	private Image niveau1Button;
	private Image niveau2Button;
	private Image niveau3Button;
	private Image niveau4Button;
	private Image niveau5Button;
	private Image retourButton;
	
	
	float largeurscreen = Gdx.graphics.getWidth();
	float hauteurscreen =  Gdx.graphics.getHeight();
	float margebouton = hauteurscreen * 1/30;
	
	
	public Sport(MainGame game) {
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
		
	 
	    Image backImage = new Image(AssetLoader.fond2Texture);
		backImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		niveau1Button = new Image(new Texture("data/niveau1.png"));
//		choixButton.setPosition(30, 1500);
		niveau1Button.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 6/9.0f + 5* margebouton);
		niveau1Button.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		niveau1Button.addListener(new InputListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				game.setScreen((Screen) new GameScreen());
				
				return true;
			}
		});
		
		niveau2Button = new Image(new Texture("data/niveau2.png"));
//		classiqueButton.setPosition(30, 1200);
		niveau2Button.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 5/9.0f + 4* margebouton);
		niveau2Button.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		
		
		niveau3Button = new Image(new Texture("data/niveau3.png"));
//		sportButton.setPosition(30, 900);
		niveau3Button.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 4/9.0f + 3* margebouton);
		niveau3Button.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		
		
		niveau4Button = new Image(new Texture("data/niveau4.png"));
//		nouritureButton.setPosition(30, 600);
		niveau4Button.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 3/9.0f + 2* margebouton);
		niveau4Button.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		
		
		niveau5Button = new Image(new Texture("data/niveau5.png"));
//		natureButton.setPosition(30, 300);
		niveau5Button.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 2/9.0f + 1* margebouton);
		niveau5Button.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		
		
		retourButton = new Image(new Texture("data/bouton8.png"));
//		retourButton.setPosition(30, 0);
		retourButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 0/9.0f + 0* margebouton);
		retourButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		retourButton.addListener(new InputListener() {
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int image) {
			
			game.setScreen((Screen) new ChoisirNiveaux(game));
			
			return true;
		}
		});
		
		

		
		stage.addActor(backImage);
		stage.addActor(niveau1Button);
		stage.addActor(niveau2Button);
		stage.addActor(niveau3Button);
		stage.addActor(niveau4Button);
		stage.addActor(niveau5Button);
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
