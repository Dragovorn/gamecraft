package com.dragovorn.gamecraft.command;

import com.dragovorn.gamecraft.player.GamePlayer;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public final class GameCommand {

    private final CommandExecutor executor;

    private final List<GameCommand> children;

    private final String name;
    private String description;
    private String usage;
    private String[] aliases;

    private int minimumArgs;

    protected GameCommand(String name, CommandExecutor executor) {
        this.name = name;
        this.executor = executor;
        this.description = "";
        this.usage = "/" + name;
        this.minimumArgs = 0;
        this.aliases = new String[0];
        this.children = new ArrayList<>();
    }

    void execute(GamePlayer player, String label, String[] args) {
        executor.execute(player, label, args);
    }

    boolean hasPermission(GamePlayer player) {
        return executor.hasPermission(player);
    }

    void execute(CommandSender sender, String label, String[] args) {
        executor.execute(sender, label, args);
    }

    protected String getName() {
        return this.name;
    }

    protected String getDescription() {
        return this.description;
    }

    protected String getUsage() {
        return this.usage;
    }

    protected String[] getAliases() {
        return this.aliases;
    }

    protected int getMinimumArgs() {
        return this.minimumArgs;
    }

    protected GameCommand[] getChildren() {
        return this.children.toArray(new GameCommand[this.children.size()]);
    }
}