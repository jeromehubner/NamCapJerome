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

 public class ChoixJeu implements Screen {

	
	MainGame game;
	Stage stage;
	private Image playButton;
	private Image niveauxButton;
	private Image scoresButton;
	private Image aideButton;
	private Image menuButton;
	
	
	float largeurscreen = Gdx.graphics.getWidth();
	float hauteurscreen =  Gdx.graphics.getHeight();
	
	
	public ChoixJeu(MainGame game) {
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
		
		float margebouton = hauteurscreen * 1/45;
		
//		Table table = new Table(Assets.skin);
		
		playButton = new Image(new Texture("data/bouton4.png"));
		playButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 6/9.0f + 5* margebouton);
		playButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		playButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				game.setScreen((Screen) new GameScreen());
				
				return true;
		}
		});
		
		niveauxButton = new Image(new Texture("data/bouton5.png"));
		niveauxButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 5/9.0f + 5* margebouton);
		niveauxButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		niveauxButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				game.setScreen((Screen) new ChoisirNiveaux(game));
				
				return true;
		}
		});
		
		
		scoresButton = new Image(new Texture("data/bouton6.png"));
		scoresButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 4/9.0f + 5* margebouton);
		scoresButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		scoresButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				game.setScreen((Screen) new Scores(game));
				
				return true;
		}
		});
		
		
		aideButton = new Image(new Texture("data/bouton18.png"));
		aideButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 3/9.0f + 5* margebouton);
		aideButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		aideButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				game.setScreen((Screen) new Aide(game));
				
				return true;
		}
		});
		
				
		menuButton = new Image(new Texture("data/bouton7.png"));
		menuButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 0/9.0f);
		menuButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		menuButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				game.setScreen((Screen) new MainMenuScreen(game));
				
				return true;
		}
		});
		
		
		stage.addActor(playButton);
		stage.addActor(niveauxButton);
		stage.addActor(scoresButton);
		stage.addActor(aideButton);
		stage.addActor(menuButton);
		
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
