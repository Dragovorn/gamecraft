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

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (obj instanceof GamePlayer) {
            return ((GamePlayer) obj).getUuid().equals(this.uuid);
        }

        return obj instanceof Player && obj.equals(player);
    }
}