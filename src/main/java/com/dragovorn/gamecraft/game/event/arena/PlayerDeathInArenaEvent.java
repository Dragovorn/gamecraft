package com.dragovorn.gamecraft.game.event.arena;

import com.dragovorn.gamecraft.Main;
import com.dragovorn.gamecraft.game.arena.Arena;
import com.dragovorn.gamecraft.player.GamePlayer;
import com.dragovorn.gamecraft.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @author Cam
 * @since 2017-12-08
 */
public class PlayerDeathInArenaEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private GamePlayer gamePlayer;
    private Arena arena;

    /**
     *
     * A custom event created to call when a {@link GamePlayer}
     * dies in an {@link Arena}.
     *
     * @param gamePlayer Player who dies.
     * @param arena Arena in which they died.
     */
    public PlayerDeathInArenaEvent(GamePlayer gamePlayer, Arena arena) {

        this.gamePlayer = gamePlayer;
        this.arena = arena;
    }

    /**
     *
     * Returns the player who dies in the arena.
     *
     * @return Player in arena.
     */
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    /**
     *
     * Returns the arena that the player died in.
     *
     * @return Arena player died in.
     */
    public Arena getArena() {
        return arena;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    
    public static class PlayerDeathInArenaCall implements Listener {
        
        @EventHandler
        public void onDeath(PlayerDeathEvent event) {
            
            GamePlayer gamePlayer = Main.getInstance().getPlayerManager().getPlayer(event.getEntity());
            
            if (!gamePlayer.isInArena()) {
                return;
            }
            
            PlayerDeathInArenaEvent playerDeathInArenaEvent = new PlayerDeathInArenaEvent(gamePlayer, gamePlayer.getArena());
            Bukkit.getServer().getPluginManager().callEvent(playerDeathInArenaEvent);
        }
    }
}