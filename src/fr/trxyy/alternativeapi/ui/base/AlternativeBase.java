package fr.trxyy.alternativeapi.ui.base;

import fr.trxyy.alternativeapi.utils.ResourceLocation;
import javafx.application.Application;
import javafx.stage.Stage;

public abstract class AlternativeBase extends Application {
	private static ResourceLocation RESOURCE_LOCATION = new ResourceLocation();

	public abstract void start(Stage primaryStage) throws Exception;

	public ResourceLocation getResourceLocation() {
		return this.RESOURCE_LOCATION;
	}
}
