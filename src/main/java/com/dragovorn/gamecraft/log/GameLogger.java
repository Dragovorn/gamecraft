package com.dragovorn.gamecraft.log;

import com.dragovorn.gamecraft.Main;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class GameLogger extends Logger {

    public GameLogger(String name) {
        super(name, null);

        setParent(Main.getInstance().getLogger());
        setLevel(Level.ALL);
    }

    public void log(LogRecord logRecord) {
        logRecord.setMessage("[GC] [" + getName() + "] " + logRecord.getMessage());
        super.log(logRecord);
    }
}