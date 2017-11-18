package com.dragovorn.gamecraft.command;

import com.dragovorn.gamecraft.util.Reflection;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

public class GameCommandRegistry {

    private CommandMap commandMap;

    public GameCommandRegistry() {
        this.commandMap = Reflection.getValue(Bukkit.getServer().getClass(), Bukkit.getServer(), "commandMap");
    }

    public void register(GameCommand command) {
        this.commandMap.register(command.getName(), new GameCommandExecutor(command));
    }
}