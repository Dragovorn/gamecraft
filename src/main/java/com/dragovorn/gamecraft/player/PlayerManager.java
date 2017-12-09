package com.dragovorn.gamecraft.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager implements Listener {

    private Class<? extends GamePlayer> playerClass = GamePlayer.class;

    private Map<UUID, GamePlayer> players;

    public PlayerManager() {
        this.players = new HashMap<>();
    }

    public GamePlayer getPlayer(Player player) {
        return this.players.get(player.getUniqueId());
    }

    public void setPlayerClass(Class<? extends GamePlayer> clazz) {
        this.playerClass = clazz;
    }

    @EventHandler
    public void registerPlayer(PlayerJoinEvent event) {
        try {
            this.players.put(event.getPlayer().getUniqueId(), this.playerClass.getDeclaredConstructor(Player.class).newInstance(event.getPlayer()));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void deregisterPlayer(PlayerQuitEvent event) {
        this.players.remove(event.getPlayer().getUniqueId()).save();
    }
}