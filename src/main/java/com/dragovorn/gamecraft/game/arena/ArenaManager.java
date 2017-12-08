package com.dragovorn.gamecraft.game.arena;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cameron
 * @since 12/7/2017
 */
public final class ArenaManager {
    
    private static List<Arena> arenas = new ArrayList<>();
    
    /**
     *
     * Registers an arena to the arena manager.
     *
     * @param arena Arena to register
     */
    public static void register(Arena arena) {
        arenas.add(arena);
    }
    
    /**
     *
     * De-registers an arena from the arena manager.
     *
     * @param arena Arena to de-register.
     */
    public static void deregister(Arena arena) {
        arenas.remove(arena);
    }

    /**
     *
     * Returns the list managing list of arenas.
     *
     * @return List of arenas.
     */
    public static List<Arena> get() {
        return arenas;
    }
}