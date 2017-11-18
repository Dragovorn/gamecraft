package com.dragovorn.gamecraft.command;

import com.dragovorn.gamecraft.player.GamePlayer;
import org.bukkit.command.CommandSender;

public interface CommandExecutor {

    /*
     * For player sender
     */
    void execute(GamePlayer player, String label, String[] args);

    /*
     * For console sender
     */
    default void execute(CommandSender sender, String label, String[] args) {
        sender.sendMessage("Sorry, this command is player only.");
    }

    boolean hasPermission(GamePlayer player);
}