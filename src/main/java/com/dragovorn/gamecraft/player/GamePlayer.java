package com.dragovorn.gamecraft.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GamePlayer {

    private Player player;

    GamePlayer(Player player) {
        this.player = player;
    }

    public void save() {

    }

    public void sendMessage(String message) {
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}