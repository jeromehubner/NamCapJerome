package com.namcap.desktop;

import namcap.main.MainGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class LanceurDesktop {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "NamCap";
		config.useGL20 = true;
		config.height = 729;
		config.width = 438;
		
		// Résolution NEXUS 4 (768x1280)
		// Résolution de l'émulateur Nexus 4 (729x438) => Format Paysage
		new LwjglApplication(MainGame.getUniqueMainGame(), config);
	}
}
