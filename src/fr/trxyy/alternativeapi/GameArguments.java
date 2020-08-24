package fr.trxyy.alternativeapi;

import java.util.ArrayList;
import java.util.List;

public class GameArguments {

	private List<String> arguments;

	public GameArguments(String[] argments) {
		this.arguments = new ArrayList<String>();
		for (int i = 0; i < argments.length; i++) {
			this.arguments.add(argments[i]);
		}
	}

	public List<String> getArguments() {
		return this.arguments;
	}

}
