package com.dragovorn.gamecraft.game;

import com.dragovorn.gamecraft.player.GamePlayer;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Cam
 * @since 2017-12-07
 */
public class Team {

    private Set<GamePlayer> players = new HashSet<>(); // Set because cannot have duplicates anyway and want the function fo a collection.

    private ChatColor color;
    private String name;
    
    private Location spawn;

    /**
     *
     * Creates a new team with the passed in color
     * and name.
     *
     * @param color Color of team.
      * @param name Team name.
     */
    public Team(ChatColor color, String name) {

        this.color = color;
        this.name = name;
    }

    /**
     *
     * Returns a list of players currently in the team.
     *
     * @return List of players in team.
     */
    public Set<GamePlayer> getPlayers() {
        return players;
    }
    
    /**
     *
     * Sets the passed in location as the spawn point
     * for the invoked-upon instance of team.
     *
     * @param location Location to set spawn to.
     */
    public void setSpawn(Location location) {
        this.spawn = location;
    }

    /**
     *
     * Returns the color assigned to the team.
     *
     * @return Team color.
     */
    public ChatColor getColor() {
        return color;
    }

    /**
     *
     * Returns the name of the team.
     *
     * @return Team name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * Adds a player to the list of players
     * contained in the list.
     *
     * @param gamePlayer Player to add to team.
     */
    public void add(GamePlayer gamePlayer) {
        this.players.add(gamePlayer);
    }

    /**
     *
     * Removes the passed in player from the team.
     *
     * @param gamePlayer Player to remove.
     */
    public void remove(GamePlayer gamePlayer) {
        this.players.remove(gamePlayer);
    }

    /**
     *
     * Returns a boolean based on if the passed in
     * player is in the team.
     *
     * @param gamePlayer Player to check for.
     * @return If player is in team.
     */
    public boolean contains(GamePlayer gamePlayer) {
        return this.players.contains(gamePlayer);
    }
}