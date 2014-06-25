package namcap.main.screens;

import namcap.main.MainGame;
import namcap.main.ThemeEnum;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Theme implements Screen {

	private Stage stage;

	private Image buttonNormal;
	private Image buttonSport;
	private Image buttonFood;
	private Image buttonNature;
	private Image buttonVehicle;
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

		createButton();
		configureButtonOnScreen();
		installListener();

		stage.addActor(buttonNormal);
		stage.addActor(buttonSport);
		stage.addActor(buttonFood);
		stage.addActor(buttonNature);
		stage.addActor(buttonVehicle);
		stage.addActor(buttonBack);
	}

	/**
	 * Méthode qui initilise les boutons présents sur la page.
	 */
	private void createButton() {
		buttonNormal = new Image(new Texture("button/normal.png"));
		buttonSport = new Image(new Texture("button/sport.png"));
		buttonFood = new Image(new Texture("button/food.png"));
		buttonNature = new Image(new Texture("button/nature.png"));
		buttonVehicle = new Image(new Texture("button/vehicle.png"));
		buttonBack = new Image(new Texture("button/back.png"));
	}

	/**
	 * Méthode qui renseigne les tailles et coordonnées des boutons avec une
	 * marge.
	 */
	private void configureButtonOnScreen() {

		float buttonMarge = hauteurscreen * 1 / 45;

		/*------- BOUTON NORMAL -------*/
		buttonNormal.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 6
				/ 9.0f + 5 * buttonMarge);
		buttonNormal.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		/*------- BOUTON SPORT -------*/
		buttonSport.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 5
				/ 9.0f + 5 * buttonMarge);
		buttonSport.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		/*------- BOUTON FOOD -------*/
		buttonFood.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 4
				/ 9.0f + 5 * buttonMarge);
		buttonFood.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		/*------- BOUTON NATURE -------*/
		buttonNature.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 3
				/ 9.0f + 5 * buttonMarge);
		buttonNature.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		/*------- BOUTON VEHICLE -------*/
		buttonVehicle.setPosition(largeurscreen * 1 / 20.0f, hauteurscreen * 2
				/ 9.0f + 5 * buttonMarge);
		buttonVehicle.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);

		/*------- BOUTON BACK -------*/
		buttonBack.setPosition(largeurscreen * 1 / 20.0f,
				hauteurscreen * 0 / 9.0f);
		buttonBack.setSize(largeurscreen * 18 / 20, hauteurscreen * 1 / 6);
	}

	/**
	 * Méthode qui permet de placer les listener sur les boutons.
	 */
	private void installListener() {

		/*------- BOUTON NORMAL -------*/
		buttonNormal.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {

				Level.getUniqueLevel().setThemeSelected(ThemeEnum.NORMAL);
				MainGame.getUniqueMainGame().setScreen(
						(Screen) Level.getUniqueLevel());
				return true;
			}
		});

		/*------- BOUTON SPORT -------*/
		buttonSport.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {

				Level.getUniqueLevel().setThemeSelected(ThemeEnum.SPORT);
				MainGame.getUniqueMainGame().setScreen(
						(Screen) Level.getUniqueLevel());
				return true;
			}
		});

		/*------- BOUTON FOOD -------*/
		buttonFood.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {

				Level.getUniqueLevel().setThemeSelected(ThemeEnum.FOOD);
				MainGame.getUniqueMainGame().setScreen(
						(Screen) Level.getUniqueLevel());
				return true;
			}
		});

		/*------- BOUTON NATURE -------*/
		buttonNature.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {

				Level.getUniqueLevel().setThemeSelected(ThemeEnum.NATURE);
				MainGame.getUniqueMainGame().setScreen(
						(Screen) Level.getUniqueLevel());
				return true;
			}
		});

		/*------- BOUTON VEHICLE -------*/
		buttonVehicle.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int image) {

				Level.getUniqueLevel().setThemeSelected(ThemeEnum.VEHICLE);
				MainGame.getUniqueMainGame().setScreen(
						(Screen) Level.getUniqueLevel());
				return true;
			}
		});

		/*------- BOUTON BACK -------*/
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
