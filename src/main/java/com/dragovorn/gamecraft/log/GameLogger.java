package com.dragovorn.gamecraft.log;

import com.dragovorn.gamecraft.Main;

import java.util.logging.Logger;

public class GameLogger extends Logger {

    public GameLogger(String name) {
        super(name, null);

        setParent(Main.getInstance().getLogger());
    }
}