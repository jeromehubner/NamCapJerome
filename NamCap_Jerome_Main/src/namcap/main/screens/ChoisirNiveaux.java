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

 public class ChoisirNiveaux implements Screen {
	 
	MainGame game;
	Stage stage;
	private Image normalButton;
	private Image sportButton;
	private Image foodButton;
	private Image natureButton;
	private Image vehiculeButton;
	private Image retourButton;
	
	
	float largeurscreen = Gdx.graphics.getWidth();
	float hauteurscreen =  Gdx.graphics.getHeight();
	float margebouton = hauteurscreen * 1/45;
	
	
	public ChoisirNiveaux(MainGame game) {
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
		
		
		normalButton = new Image(new Texture("data/bouton13.png"));
		normalButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 6/9.0f + 5* margebouton);
		normalButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		normalButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				game.setScreen((Screen) new Normal(game));
				
				return true;
		}
		});
		
		
		sportButton = new Image(new Texture("data/bouton14.png"));
		sportButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 5/9.0f + 5* margebouton);
		sportButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		sportButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				game.setScreen((Screen) new Sport(game));
				
				return true;
		}
		});
		
		
		foodButton = new Image(new Texture("data/bouton15.png"));
		foodButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 4/9.0f + 5* margebouton);
		foodButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		foodButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				game.setScreen((Screen) new Food(game));
				
				return true;
		}
		});
		
		
		natureButton = new Image(new Texture("data/bouton16.png"));
		natureButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 3/9.0f + 5* margebouton);
		natureButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		natureButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				game.setScreen((Screen) new Nature(game));
				
				return true;
		}
		});
		
		
		vehiculeButton = new Image(new Texture("data/bouton17.png"));
		vehiculeButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 2/9.0f + 5* margebouton);
		vehiculeButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		vehiculeButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				
				game.setScreen((Screen) new Vehicule(game));
				
				return true;
		}
		});
		
		
		retourButton = new Image(new Texture("data/bouton8.png"));
		retourButton.setPosition ( largeurscreen * 1/20.0f , hauteurscreen * 0/9.0f);
		retourButton.setSize(largeurscreen * 18/20 , hauteurscreen * 1/6);
		retourButton.addListener(new InputListener() {
		public boolean touchDown(InputEvent event, float x, float y,
				int pointer, int image) {
			
			game.setScreen((Screen) new ChoixJeu(game));
			
			return true;
		}
		});
		
		

		
		stage.addActor(normalButton);
		stage.addActor(sportButton);
		stage.addActor(foodButton);
		stage.addActor(natureButton);
		stage.addActor(vehiculeButton);
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

//public boolean keyDown(int keycode) {
//	if(keycode == Keys.BACK){
//		game.gotoMenuScreen();
//    }
//	else if (keycode == Keys.UP && startLine > 0) {
//		startLine--;
//	}
//	else if (keycode == Keys.DOWN && startLine < lastLineIndex) {
//		startLine++;
//	}		
//	return true;
//}
//
//@Override
//public boolean keyUp(int keycode) {
//	return false;
//}
//
//@Override
//public boolean keyTyped(char character) {
//	return false;
//}
//
//@Override
//public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//	touchStartY = screenY;
//	return false;
//}
//
//@Override
//public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//    Vector3 touchPos = new Vector3();
//    touchPos.set(screenX, screenY, 0);
//    camera.unproject(touchPos);
//    
//    for(int i=0;i<buttons.length;i++) {          	
//    	if (buttons[i].isPressed(touchPos)) {
//    		if (i == 0) {
//    			game.gotoMenuScreen();
//    		}
//    		break;
//    	}
//    }
//	return true;
//}
//
//@Override
//public boolean touchDragged(int screenX, int screenY, int pointer) {
//	int diffY = touchStartY - screenY;
//	int linesDiff = diffY / lineHeight;
//	if (linesDiff != 0) {
//		touchStartY = screenY;
//		scrolled(linesDiff);
//	}
//	return true;
//}
//
//@Override
//public boolean mouseMoved(int screenX, int screenY) {
//	return false;
//}
//
//@Override
//public boolean scrolled(int amount) {
//	startLine += amount;
//	if (startLine < 0) {
//		startLine = 0;
//	}
//	if (startLine > lastLineIndex) {
//		startLine = lastLineIndex;
//	}
//	return true;
//}	

}
