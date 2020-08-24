package fr.trxyy.alternativeapi.ui;

import fr.trxyy.alternativeapi.GameEngine;
import javafx.scene.layout.Pane;

public class LauncherPane extends Pane {

	public LauncherPane(GameEngine engine) {
		this.setPrefSize(engine.getLauncherPreferences().getWidth(), engine.getLauncherPreferences().getHeight());
	}

}
