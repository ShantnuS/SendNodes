package com.sendnodes.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sendnodes.Core;
import com.sendnodes.Properties;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Properties.SCREEN_WIDTH;
		config.height = Properties.SCREEN_HEIGHT;
		new LwjglApplication(new Core(), config);
	}
}
