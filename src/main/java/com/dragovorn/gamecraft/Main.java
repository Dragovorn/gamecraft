package com.dragovorn.gamecraft;

import com.dragovorn.gamecraft.command.GameCommand;
import com.dragovorn.gamecraft.command.GameCommandRegistry;
import com.dragovorn.gamecraft.command.executor.GamesCommandExecutor;
import com.dragovorn.gamecraft.discovery.GameManager;
import com.dragovorn.gamecraft.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private static Main instance;

    private GameCommandRegistry commandRegistry;

    private PlayerManager playerManager;

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
        this.playerManager = new PlayerManager();
        Bukkit.getPluginManager().registerEvents(this.playerManager, this);

        this.commandRegistry.register(new GameCommand("games", new GamesCommandExecutor(), new GameCommand[0], new String[0]));

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

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public GameManager getGameManager() {
        return this.gameManager;
    }

    public static Main getInstance() {
        return instance;
    }
}