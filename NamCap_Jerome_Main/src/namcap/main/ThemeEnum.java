package namcap.main;

import namcap.main.helpers.AssetLoader;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Cette enum contient le nom des themes, leur image de backGround et les chemins
 * des fichiers Tmx correspondant au map. 
 * @author jerome
 *
 */
public enum ThemeEnum {
	NORMAL(0, "normal", new Image(AssetLoader.TEXTURE_BACKGROUND_NORMAL),
			"tmxFiles/normalTheme/level_1.tmx",
			"tmxFiles/normalTheme/level_2.tmx",
			"tmxFiles/normalTheme/level_3.tmx",
			"tmxFiles/normalTheme/level_4.tmx",
			"tmxFiles/normalTheme/level_5.tmx"),
										
	SPORT(1, "sport", new Image(AssetLoader.TEXTURE_BACKGROUND_SPORT),
			"tmxFiles/sportTheme/level_1.tmx",
			"tmxFiles/sportTheme/level_2.tmx",
			"tmxFiles/sportTheme/level_3.tmx",
			"tmxFiles/sportTheme/level_4.tmx",
			"tmxFiles/sportTheme/level_5.tmx"),
			
	FOOD(2, "food", new Image(AssetLoader.TEXTURE_BACKGROUND_FOOD),
			"tmxFiles/foodTheme/level_1.tmx",
			"tmxFiles/foodTheme/level_2.tmx",
			"tmxFiles/foodTheme/level_3.tmx",
			"tmxFiles/foodTheme/level_4.tmx",
			"tmxFiles/foodTheme/level_5.tmx"),
	
	NATURE(3, "nature", new Image(AssetLoader.TEXTURE_BACKGROUND_NATURE),
			"tmxFiles/natureTheme/level_1.tmx",
			"tmxFiles/natureTheme/level_2.tmx",
			"tmxFiles/natureTheme/level_3.tmx",
			"tmxFiles/natureTheme/level_4.tmx",
			"tmxFiles/natureTheme/level_5.tmx"),
	
	VEHICLE(4, "vehicle", new Image(AssetLoader.TEXTURE_BACKGROUND_VEHICLE),
			"tmxFiles/vehicleTheme/level_1.tmx",
			"tmxFiles/vehicleTheme/level_2.tmx",
			"tmxFiles/vehicleTheme/level_3.tmx",
			"tmxFiles/vehicleTheme/level_4.tmx",
			"tmxFiles/vehicleTheme/level_5.tmx");
	
	private int index;
	private String libelle;
	private Image imageBackground;
	private String[] fileTmxPath;

	
	// Création d'une ellipse
	private ThemeEnum(int index, String libelle, Image imageBackground, String...fileTmxPath) {
		this.index = index;
		this.libelle = libelle;
		this.imageBackground = imageBackground;
		this.fileTmxPath = fileTmxPath;
	}

	public String getLibelle() {
		return libelle;
	}

	public Image getImageBackground() {
		return imageBackground;
	}

	public String[] getFileTmxPath() {
		return fileTmxPath;
	}
}
