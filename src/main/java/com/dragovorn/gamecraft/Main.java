package com.dragovorn.gamecraft;

import com.dragovorn.gamecraft.command.GameCommandRegistry;
import com.dragovorn.gamecraft.discovery.GameManager;
import com.dragovorn.gamecraft.player.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private static Main instance;

    private GameCommandRegistry commandRegistry;

    private GameManager gameManager;

    private File gameDir;

    // INJECT DISCOVERER -> DISCOVER GAMES -> DISABLE INCOMPATIBLE GAMES -> CONFIGURE GAMES -> LAUNCH GAMES
    // DISABLE INCOMPATIBLE GAMES: CHECK THEIR VERSION -> CHECK PROVIDED INCOMPATIBILITIES

    @Override
    public void onLoad() {
        instance = this;
        this.gameDir = new File(Bukkit.getWorldContainer(), "games");
        this.gameManager = new GameManager();
        this.commandRegistry = new GameCommandRegistry();

        if (this.gameDir.mkdirs()) {
            getLogger().warning("No games directory found, Making one!");
        }

        if (this.gameDir.listFiles().length == 0) {
            getLogger().warning("No functionality will be provided by this plugin as there are no games!");
        }

        this.gameManager.loadGames();
    }

    @Override
    public void onEnable() {
        this.gameManager.enableGames();
    }

    @Override
    public void onDisable() {
        this.gameManager.disableGames();
    }

    public GameCommandRegistry getCommandRegistry() {
        return this.commandRegistry;
    }

    public File getGameDir() {
        return this.gameDir;
    }

    public static GamePlayer getPlayer(Player player) {
        return null; // Stubbed out until i do player shit
    }

    public static Main getInstance() {
        return instance;
    }
}