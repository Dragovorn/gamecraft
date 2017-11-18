package com.dragovorn.gamecraft.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager implements Listener {

    private Map<UUID, GamePlayer> players;

    public PlayerManager() {
        this.players = new HashMap<>();
    }

    public GamePlayer getPlayer(Player player) {
        return this.players.get(player.getUniqueId());
    }

    @EventHandler
    public void registerPlayer(PlayerJoinEvent event) {
        this.players.put(event.getPlayer().getUniqueId(), new GamePlayer(event.getPlayer()));
    }

    @EventHandler
    public void deregisterPlayer(PlayerQuitEvent event) {
        this.players.remove(event.getPlayer().getUniqueId()).save();
    }
}