package com.dragovorn.gamecraft.command;

import com.dragovorn.gamecraft.player.GamePlayer;
import org.bukkit.command.CommandSender;

public final class GameCommand {

    private final CommandExecutor executor;

    private final GameCommand[] children;

    private final String name;
    private String description;
    private String usage;
    private String[] aliases;

    private int minimumArgs;

    public GameCommand(String name, CommandExecutor executor, GameCommand[] children, String[] aliases) {
        this.name = name;
        this.executor = executor;
        this.description = "";
        this.usage = "/" + name;
        this.minimumArgs = 0;
        this.aliases = aliases;
        this.children = children;
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
        return this.children;
    }
}