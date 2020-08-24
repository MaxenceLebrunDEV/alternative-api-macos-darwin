package fr.trxyy.alternativeapi.maintenance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import fr.trxyy.alternativeapi.GameEngine;

public class GameMaintenance {

    public Maintenance maintenance;
    public GameEngine engine;

    public GameMaintenance(Maintenance enumMaintenance, GameEngine eng) {
        this.maintenance = enumMaintenance;
        this.engine = eng;
    }

    public String readMaintenance() throws IOException {
        URL oracle = new URL(this.engine.getGameLinks().getMaintenanceUrl());
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        String s = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            s = inputLine;
        }
        in.close();
        return s;
    }

    public Maintenance getMaintenance() {
        return this.maintenance;
    }

}
