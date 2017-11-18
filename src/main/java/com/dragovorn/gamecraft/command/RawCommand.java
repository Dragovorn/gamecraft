package com.dragovorn.gamecraft.command;

import com.dragovorn.gamecraft.discovery.info.CommandInfo;

import java.util.ArrayList;
import java.util.List;

public class RawCommand {

    private CommandInfo.Builder builder;

    private List<String> aliases;
    private List<String> children;

    private String path;
    private String name;

    private boolean child;

    public RawCommand(String path, CommandInfo.Builder builder) {
        this.path = path;
        this.builder = builder;
        this.child = false;
        this.aliases = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChild(boolean child) {
        this.child = child;
    }

    public void addAlias(String alias) {
        if (this.aliases.contains(alias)) {
            return;
        }

        this.aliases.add(alias);
    }

    public void addChild(String child) {
        if (this.children.contains(child)) {
            return;
        }

        this.children.add(child);
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public String[] getAliases() {
        return this.aliases.toArray(new String[this.aliases.size()]);
    }

    public GameCommand[] getChildren(ClassLoader loader) {
        List<GameCommand> commands = new ArrayList<>();

        for (String path : this.children) {
            RawCommand command = this.builder.findCommand(path);

            if (command != null) {
                try {
                    commands.add(new GameCommand(command.getName(), (CommandExecutor) loader.loadClass(command.getPath()).newInstance(), command.getChildren(loader), command.getAliases()));
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return commands.toArray(new GameCommand[commands.size()]);
    }

    public boolean isChild() {
        return this.child;
    }
}