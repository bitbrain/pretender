package de.myreality.pretender.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.myreality.pretender.PretenderGame;
import de.myreality.pretender.Resources;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 900;
		config.height = 600;
		config.title = Resources.APP_NAME + " v. " + Resources.APP_VERSION;
		
		new LwjglApplication(new PretenderGame(), config);
	}
}
