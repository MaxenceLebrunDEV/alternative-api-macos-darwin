package fr.trxyy.alternativeapi.updater;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import fr.trxyy.alternativeapi.GameEngine;
import fr.trxyy.alternativeapi.GameVerifier;
import fr.trxyy.alternativeapi.utils.Logger;
import fr.trxyy.alternativeapi.utils.file.FileUtil;

public class Downloader extends Thread {
	private final String url;
	private final String sha1;
	private final File file;
	private final GameEngine engine;

	public void run() {
		try {
			download();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Downloader(File file, String url, String sha1, GameEngine engine_) {
		this.file = file;
		this.url = url;
		this.sha1 = sha1;
		this.engine = engine_;
		GameVerifier.addToFileList(file.getAbsolutePath().replace(engine.getGameFolder().getGameDir().getAbsolutePath(), "").replace("\\", "/"));
		file.getParentFile().mkdirs();
	}

	public void download() throws IOException {
		Logger.log("Acquiring file '" + this.file.getName() + "'");
		engine.getGameUpdater().setCurrentFille(this.file.getName());
		if (this.file.getAbsolutePath().contains("assets")) {
			engine.getGameUpdater().setCurrentInfoText("Telechargement d'une ressource.");
		}
		else {
			engine.getGameUpdater().setCurrentInfoText("Telechargement d'une librairie.");
		}
		BufferedInputStream bufferedInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			URL downloadUrl = new URL(this.url.replace(" ", "%20"));
			URLConnection urlConnection = downloadUrl.openConnection();
			urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36");
			bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
			fileOutputStream = new FileOutputStream(this.file);
			

			byte[] data = new byte[1024];
			int read;

			while ((read = bufferedInputStream.read(data, 0, 1024)) != -1) {
				fileOutputStream.write(data, 0, read);
			}
			engine.getGameUpdater().downloadedFiles++;
		} finally {
			if (bufferedInputStream != null) {
				bufferedInputStream.close();
			}
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}

	public boolean requireUpdate() {
		if ((this.file.exists()) && (FileUtil.matchSHA1(this.file, this.sha1))) {
			return false;
		}
		return true;
	}
}