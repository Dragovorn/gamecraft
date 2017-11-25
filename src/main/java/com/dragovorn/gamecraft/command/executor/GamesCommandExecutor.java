package com.dragovorn.gamecraft.command.executor;

import com.dragovorn.gamecraft.Main;
import com.dragovorn.gamecraft.command.CommandExecutor;
import com.dragovorn.gamecraft.discovery.Game;
import com.dragovorn.gamecraft.player.GamePlayer;

public class GamesCommandExecutor implements CommandExecutor {

    @Override
    public void execute(GamePlayer player, String label, String[] args) {
        StringBuilder builder = new StringBuilder();

        builder.append("Loaded ").append(Main.getInstance().getGameManager().getGames().size()).append(" Games: ");

        for (Game game : Main.getInstance().getGameManager().getGames()) {
            builder.append(game.getInfo().getName()).append(", ");
        }

        player.sendMessage("&a" + builder.toString().trim());
    }

    @Override
    public boolean hasPermission(GamePlayer player) {
        return true;
    }
}
