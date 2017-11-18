package com.dragovorn.gamecraft.command;

import com.dragovorn.gamecraft.Main;
import com.dragovorn.gamecraft.player.GamePlayer;
import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class GameCommandExecutor  extends Command implements PluginIdentifiableCommand {

    private final GameCommand command;

    GameCommandExecutor(GameCommand command) {
        super(command.getName(), command.getDescription(), command.getUsage(), Arrays.asList(command.getAliases()));

        this.command = command;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        GameCommand command = getExecutable(this.command, args);

        if (command != this.command) {
            args = trimArgs(command, args);
        }

        if (sender instanceof Player) {
            GamePlayer player = Main.getInstance().getPlayerManager().getPlayer((Player) sender);

            if (!command.hasPermission(player)) {
                player.sendMessage("");

                return true;
            }

            if (args.length < command.getMinimumArgs()) {
                player.sendMessage("command.usage");

                return true;
            }

            command.execute(player, label, args);
            return true;
        }

        if (args.length < command.getMinimumArgs()) {
            sender.sendMessage("command.usage");

            return true;
        }

        command.execute(sender, label, args);

        return true;
    }

    @Override
    public Plugin getPlugin() {
        return Main.getInstance();
    }

    private GameCommand getExecutable(GameCommand command, String[] args) {
        for (String arg : args) {
            GameCommand child = getChild(command, arg);

            if (child == null) {
                break;
            }

            command = child;
        }

        return command;
    }

    private GameCommand getChild(GameCommand command, String arg) {
        GameCommand[] children = command.getChildren();

        if (children.length == 0) {
            return null;
        }

        for (GameCommand child : children) {
            if (child.getName().equalsIgnoreCase(arg)) {
                return child;
            }

            for (String alias : child.getAliases()) {
                if (alias.equalsIgnoreCase(arg)) {
                    return child;
                }
            }
        }

        return null;
    }

    private String[] trimArgs(GameCommand child, String[] args) {
        for (int x = 0; x < args.length; x++) {
            if (args[x].equalsIgnoreCase(child.getName())) {
                return ArrayUtils.subarray(args, x + 1, args.length);
            }

            for (String alias : child.getAliases()) {
                if (args[x].equalsIgnoreCase(alias)) {
                    return ArrayUtils.subarray(args, x + 1, args.length);
                }
            }
        }

        return args;
    }
}