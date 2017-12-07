package com.dragovorn.gamecraft.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GamePlayer {

    private Player player;
    private UUID uuid;

    GamePlayer(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
    }

    public void save() {

    }

    public void sendMessage(String message) {
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}