package namcap.main.screens;

import namcap.main.MainGame;
import namcap.main.ThemeEnum;
import namcap.main.helpers.AssetLoaderMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Level implements Screen {

	private static Level UNIQUE_LEVEL_SCREEN = null;
	private Stage stage;

	private ThemeEnum themeSelected;

	private Image buttonLevel1;
	private Image buttonLevel2;
	private Image buttonLevel3;
	private Image buttonLevel4;
	private Image buttonLevel5;
	private Image buttonBack;

	float largeurscreen = Gdx.graphics.getWidth();
	float hauteurscreen = Gdx.graphics.getHeight();
	float margebouton = hauteurscreen * 1 / 30;

	private Level() {
	}

	public static Level getUniqueLevel() {
		if (UNIQUE_LEVEL_SCREEN == null)
			UNIQUE_LEVEL_SCREEN = new Level();
		return UNIQUE_LEVEL_SCREEN;
	}

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

		themeSelected.getImageBackground().setSize(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		buttonLevel1 = new Image(new Texture("button/level_1.png"));
		// choixButton.setPosition(30, 1500);
		buttonLevel1.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 6
				/ 9.0f + 5 * margebouton);
		buttonLevel1.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		buttonLevel2 = new Image(new Texture("button/level_2.png"));
		// classiqueButton.setPosition(30, 1200);
		buttonLevel2.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 5
				/ 9.0f + 4 * margebouton);
		buttonLevel2.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		buttonLevel3 = new Image(new Texture("button/level_3.png"));
		// sportButton.setPosition(30, 900);
		buttonLevel3.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 4
				/ 9.0f + 3 * margebouton);
		buttonLevel3.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		buttonLevel4 = new Image(new Texture("button/level_4.png"));
		// nouritureButton.setPosition(30, 600);
		buttonLevel4.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 3
				/ 9.0f + 2 * margebouton);
		buttonLevel4.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		buttonLevel5 = new Image(new Texture("button/level_5.png"));
		// natureButton.setPosition(30, 300);
		buttonLevel5.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 2
				/ 9.0f + 1 * margebouton);
		buttonLevel5.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		buttonBack = new Image(new Texture("button/back.png"));
		// retourButton.setPosition(30, 0);
		buttonBack.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 0
				/ 9.0f + 0 * margebouton);
		buttonBack.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);
		buttonBack.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {
				MainGame.getUniqueMainGame().setScreen((Screen) new Theme());
				return true;
			}
		});

		installListener();
		
		stage.addActor(themeSelected.getImageBackground());
		stage.addActor(buttonLevel1);
		stage.addActor(buttonLevel2);
		stage.addActor(buttonLevel3);
		stage.addActor(buttonLevel4);
		stage.addActor(buttonLevel5);
		stage.addActor(buttonBack);
	}

	private void installListener() {
		buttonLevel1.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// Chargement des Assetloader correspondant à la map
				// sélectionnée
				AssetLoaderMap.load(themeSelected.getFileTmxPath()[0]
						.toString());
				GameScreen.getUniqueGameScreen().initGameScreen();
				MainGame.getUniqueMainGame().setScreen(
						(Screen) GameScreen.getUniqueGameScreen());
				return true;
			}
		});

		buttonLevel2.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				AssetLoaderMap.load(themeSelected.getFileTmxPath()[1]
						.toString());
				GameScreen.getUniqueGameScreen().initGameScreen();
				MainGame.getUniqueMainGame().setScreen(
						(Screen) GameScreen.getUniqueGameScreen());
				return true;
			}
		});

		buttonLevel3.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				AssetLoaderMap.load(themeSelected.getFileTmxPath()[2]
						.toString());
				GameScreen.getUniqueGameScreen().initGameScreen();
				MainGame.getUniqueMainGame().setScreen(
						(Screen) GameScreen.getUniqueGameScreen());
				return true;
			}
		});

		buttonLevel4.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				AssetLoaderMap.load(themeSelected.getFileTmxPath()[3]
						.toString());
				GameScreen.getUniqueGameScreen().initGameScreen();
				MainGame.getUniqueMainGame().setScreen(
						(Screen) GameScreen.getUniqueGameScreen());
				return true;
			}
		});

		buttonLevel5.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				AssetLoaderMap.load(themeSelected.getFileTmxPath()[4]
						.toString());
				GameScreen.getUniqueGameScreen().initGameScreen();
				MainGame.getUniqueMainGame().setScreen(
						(Screen) GameScreen.getUniqueGameScreen());
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
	}

	public ThemeEnum getThemeSelected() {
		return themeSelected;
	}

	public void setThemeSelected(ThemeEnum themeSelected) {
		this.themeSelected = themeSelected;
	}
}
