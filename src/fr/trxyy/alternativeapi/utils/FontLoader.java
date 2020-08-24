package fr.trxyy.alternativeapi.utils;

import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class FontLoader {

	public void loadFont(String s) {
		Font.loadFont(this.getClass().getResourceAsStream(String.valueOf("/resources/") + s), 14.0);
	}

	public void setFont(String fontName, float size) {
		Font.font(fontName, (double) size);
	}

	public static Font loadFont(String fullFont, String fontName, float size) {
		Font.loadFont(FontLoader.class.getResourceAsStream(String.valueOf("/resources/") + fullFont), 14.0);
		final Font font = Font.font(fontName, (double) size);
		return font;
	}
	
	public static Font loadFontItalic(String fullFont, String fontName, float size) {
		Font.loadFont(FontLoader.class.getResourceAsStream(String.valueOf("/resources/") + fullFont), 14.0);
		final Font font = Font.font(fontName, FontPosture.ITALIC, (double) size);
		return font;
	}
	
	public static java.awt.Font loadFontAWT(String fullFont, String fontName, float size) {
		java.awt.Font font = null;
		InputStream is = FontLoader.class.getResourceAsStream(String.valueOf("/resources/" + fullFont));
			try {
				font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, is).deriveFont(java.awt.Font.PLAIN, 15f);
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		font.deriveFont(size);
		return font;
	}
}