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

 public class Scores implements Screen {

	
	MainGame game;
	Stage stage;
	
	private Image retourButton;
//	private Actor plan3Image;
	
	float largeurscreen = Gdx.graphics.getWidth();
	float hauteurscreen =  Gdx.graphics.getHeight();
	
	public Scores(MainGame game) {
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
		
		
		Image plan3Image = new Image(Assets.plan3Texture);
		plan3Image.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		
		stage.addActor(plan3Image);
		
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
 
 
 
 
 
 
 
 
 
// package gr.sullenart.games.fruitcatcher;
//
// import java.util.List;
//
// import gr.sullenart.ads.AdsManager;
// import gr.sullenart.games.fruitcatcher.HighScoreManager.HighScore;
// import android.app.ListActivity;
// import android.content.Intent;
// import android.os.Bundle;
// import android.widget.LinearLayout;
// import android.widget.ListView;
//
// public class ScoresActivity extends ListActivity  {
//
// 	private AdsManager adsManager = new AdsManager();
//
// 	private List<HighScore> scores;
// 	
// 	@Override
//     public void onCreate(Bundle savedInstanceState) {
//     	super.onCreate(savedInstanceState);
//
//     	setContentView(R.layout.scores);
//
//         Intent intent = getIntent();
//         Bundle bundle = intent.getExtras();
//         String scoresStr = bundle.getString("scores");
//         scores = HighScoreManager.deserialize(scoresStr);
//
//         final ListView listView = getListView();
//         listView.setItemsCanFocus(false);
//         listView.setChoiceMode(ListView.CHOICE_MODE_NONE);
//         listView.setDivider(null);
//         listView.setDividerHeight(0);
//         listView.setFocusable(false);
//
//         LinearLayout layout = (LinearLayout)findViewById(R.id.banner_layout_scores);
//         adsManager.addAdsView(this, layout);
//     }
//
// 	   @Override
// 	    protected void onResume() {
// 	        super.onResume();
// 			setListAdapter(new ScoreItemAdapter(this, scores));
// 	    }
// }
//
