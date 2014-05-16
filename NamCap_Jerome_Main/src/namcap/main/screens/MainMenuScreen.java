package namcap.main.screens;


import namcap.main.MainGame;
import namcap.main.helpers.Assets;

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
 * glClearColor indique la couleur du fond s'il n'y a aucune image de fond (background)		
 */
public class MainMenuScreen implements Screen {
	
	MainGame game;
	Stage stage;

//	private Actor backImage;
//	private Actor planImage;
	private Image startGameButton;
	private Image optionsButton;
	private Image quitButton;
	

	float largeurscreen = Gdx.graphics.getWidth();
	float hauteurscreen =  Gdx.graphics.getHeight();
	
	
	public MainMenuScreen(final MainGame game) {
		this.game = game;
	}

		
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
/**
 * Empeche le retour arri�re sur la page de menu et donc de quitter l'application
 */
		Gdx.input.setCatchBackKey(true);
	    Gdx.input.setCatchMenuKey(true);
/**
 * Re-taille automatiquement les images par rapport au t�l�phone ou tablette
 */
	  
	    stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
//		Table table = new Table(Assets.skin);
		
		Image backImage = new Image(Assets.backgroundTexture);
		backImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	
		
//		table.setFillParent(true);
		
		
/**
 * Cette m�thode permet d'afficher les boutons sur le menu : 5 boutons en tout
 * new Image permet d'afficher l'image correspondant au bouton
 * setPosition permet de g�rer la position sur l'�cran
 * addListener permet de g�rer l'action sur le bouton
 */
		
		float margebouton = hauteurscreen * 1/45;

		
		startGameButton = new Image(new Texture("data/bouton1.png"));
		startGameButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 5/9.0f + 5* margebouton);
		startGameButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		startGameButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				game.setScreen((Screen) new ChoixJeu(game));
				
				return true;
		}
		});
		
						
		optionsButton = new Image(new Texture("data/bouton2.png"));
		optionsButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 4/9.0f + 4* margebouton);
		optionsButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		optionsButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				game.setScreen((Screen) new Options(game));
				
				return true;
			}
		});
						
				
		quitButton = new Image(new Texture("data/bouton3.png"));
		quitButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 3/9.0f + 3* margebouton);
		quitButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		quitButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				Gdx.app.exit();
				
				return false;
			}
		});
		
				
		stage.addActor(backImage);
		stage.addActor(startGameButton);
		stage.addActor(optionsButton);
		stage.addActor(quitButton);
		
				
	}

//	private int yUnite(int i) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//
//	private int xUnite(int i) {
//		// TODO Auto-generated method stub
//		return 0;
//	}


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
//		 batch.dispose();
		
	}

}
