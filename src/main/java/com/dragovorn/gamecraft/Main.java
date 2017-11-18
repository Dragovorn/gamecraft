package com.dragovorn.gamecraft;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    // INJECT DISCOVERER -> DISCOVER GAMES -> DISABLE INCOMPATIBLE GAMES -> CONFIGURE GAMES -> LAUNCH GAMES
    // DISABLE INCOMPATIBLE GAMES: CHECK THEIR VERSION -> CHECK PROVIDED INCOMPATIBILITIES

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }
}