package com.dragovorn.gamecraft.discovery;

import com.dragovorn.gamecraft.Main;
import com.dragovorn.gamecraft.asm.GameClassVisitor;
import com.dragovorn.gamecraft.command.CommandExecutor;
import com.dragovorn.gamecraft.command.GameCommand;
import com.dragovorn.gamecraft.discovery.info.GameInfo;
import org.objectweb.asm.ClassReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class GameManager {

    private List<Game> games;

    public GameManager() {
        this.games = new ArrayList<>();
    }

    public void loadGames() {
        Main.getInstance().getLogger().info("Beginning game discovery process...");

        for (File file : Main.getInstance().getGameDir().listFiles()) {
            if (!file.getName().matches("(.+).(jar)")) {
                continue;
            }

            GameInfo info = loadGameInfo(file);

            Main.getInstance().getLogger().info("    Discovered: " + info.getName() + " v" + info.getVersion() + ", By: " + info.getAuthor());

            try {
                GameLoader loader = new GameLoader(new URL[] {info.getFile().toURI().toURL()});

                Class<?> main = loader.loadClass(info.getMain());
                Game game = (Game) main.getDeclaredConstructor().newInstance();
                game.setInfo(info);

                info.getCommandInfo().getCommands().forEach(rawCommand -> {
                    try {
                        Main.getInstance().getCommandRegistry().register(new GameCommand(rawCommand.getName(), (CommandExecutor) loader.loadClass(rawCommand.getPath()).newInstance()));
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                this.games.add(game);
            } catch (MalformedURLException | InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        try {
            executeMethodOnAllGames(Game.class.getMethod("onLoad"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void executeMethodOnAllGames(Method method) {
        for (Game game : this.games) {
            try {
                method.invoke(game);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public void enableGames() {
        try {
            executeMethodOnAllGames(Game.class.getMethod("onEnable"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void disableGames() {
        try {
            executeMethodOnAllGames(Game.class.getMethod("onDisable"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private GameInfo loadGameInfo(File file) {
        GameInfo.Builder builder = new GameInfo.Builder(file);

        try {
            JarFile jar = new JarFile(file);

            for (ZipEntry entry : Collections.list(jar.entries())) {
                if (!entry.getName().matches("(.+).(class)")) {
                    continue;
                }

                ClassReader reader = new ClassReader(jar.getInputStream(entry));
                reader.accept(new GameClassVisitor(builder), 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.build();
    }
}