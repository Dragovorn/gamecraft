package com.dragovorn.gamecraft.discovery;

import com.dragovorn.gamecraft.discovery.info.GameInfo;
import com.dragovorn.gamecraft.log.GameLogger;

import java.util.logging.Logger;

public abstract class Game {

    private GameInfo info;

    private Logger logger;

    void setInfo(GameInfo info) {
        this.info = info;
        this.logger = new GameLogger(info.getName());
    }

    public void onLoad() {

    }

    public void onDisable() {

    }

    public abstract void onEnable();

    public final Logger getLogger() {
        return this.logger;
    }
}