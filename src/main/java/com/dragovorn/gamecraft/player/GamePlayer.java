package com.dragovorn.gamecraft.player;

import com.dragovorn.gamecraft.game.arena.Arena;
import com.dragovorn.gamecraft.game.arena.ArenaManager;
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

    public void save() { }

    public UUID getUUID() {
        return this.uuid;
    }

    public void sendMessage(String message) {
        this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public UUID getUuid() {
        return uuid;
    }

    /**
     *
     * Returns the arena that the player is currently in.
     *
     * <p><strong>If the player is not in an arena the method will return null</strong></p>
     *
     * @return Arena player is in.
     */
    public Arena getArena() {

        Arena a = null;

        for (Arena arena : ArenaManager.get()) {
            if (arena.getPlayers().contains(this)) {
                a = arena;
            }
        }

        return a;
    }

    /**
     *
     * Returns a boolean based on if the player is in
     * an arena.
     *
     * @return Arena player is in.
     */
    public boolean isInArena() {
        return this.getArena() == null;
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