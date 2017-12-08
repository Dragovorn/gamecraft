package com.dragovorn.gamecraft.game.arena;

import com.dragovorn.gamecraft.game.Team;

import javax.xml.stream.Location;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Cam
 * @since 2017-12-07
 */
public class Arena {
    
    private String id;

    private Set<Team> teams = new HashSet<>();
    private Set<ArenaFlag> flags = new HashSet<>();
    
    private Location spectator;
    
    /**
     *
     * Creates a new arena object with the passed in id.
     *
     * <p><strong>ID must be unique</strong></p>
     *
     * @param id Id associated with the constructed arena.
     */
    public Arena(String id) {
        this.id = id;
    }
    
    /**
     *
     * Returns the unique id of the arena.
     *
     * @return Id of arena.
     */
    public String getId() {
        return id;
    }
    
    /**
     *
     * Sets the spectator location of the invoked-upon
     * arena to the passed in location.
     *
     * @param location Location to set spectator to.
     */
    public void setSpectator(Location location) {
        this.spectator = location;
    }
    
    /**
     *
     * Adds a flag to the list of flags in
     * the arena.
     *
     * @param flag Flag to add.
     */
    public void addFlag(ArenaFlag flag) {
        this.flags.add(flag);
    }
    
    /**
     *
     * Returns a boolean based on if the arena
     * has the passed in flag.
     *
     * @param flag Flag to check for.
     * @return Boolean is has flag or not.
     */
    public boolean hasFlag(ArenaFlag flag) {
        return this.flags.contains(flag);
    }
    
    /**
     *
     * Returns the list of teams in the arena.
     *
     * @return Teams.
     */
    public Set<Team> getTeams() {
        return teams;
    }
    
    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;
        }
        
        if (obj instanceof Arena) {
            return ((Arena) obj).getId().equalsIgnoreCase(this.getId());
        }
        
        return false;
    }
}