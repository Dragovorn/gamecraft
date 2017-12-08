package com.dragovorn.gamecraft.game.arena;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Cameron
 * @since 12/7/2017
 */
public final class ArenaManager {
    
    private static Set<Arena> arenas = new HashSet<>();
    
    /**
     *
     * Registers an arena to the arena manager.
     *
     * @param arena Arena to register
     */
    public void register(Arena arena) {
        arenas.add(arena);
    }
    
    /**
     *
     * De-registers an arena from the arena manager.
     *
     * @param arena Arena to de-register.
     */
    public void deregister(Arena arena) {
        arenas.remove(arena);
    }
}