package com.dragovorn.gamecraft.discovery;

import com.dragovorn.gamecraft.Main;
import com.dragovorn.gamecraft.asm.GameClassVisitor;
import org.objectweb.asm.ClassReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
                this.games.add(game);
            } catch (MalformedURLException | InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        for (Game game : this.games) {
            game.onLoad();
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