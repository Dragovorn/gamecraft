package com.dragovorn.gamecraft.discovery;

import com.dragovorn.gamecraft.log.GameLogger;

import java.util.logging.Logger;

public abstract class Game {

    private Logger logger;

    private Game(GameInfo info) {
        this.logger = new GameLogger(info.getName());
    }

    public void onLoad() {

    }

    public void onDisable() {

    }

    public abstract void onEnable();
}