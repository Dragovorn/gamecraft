package com.dragovorn.gamecraft.command;

import java.util.ArrayList;
import java.util.List;

public class RawCommand {

    private List<String> aliases;

    private String path;
    private String name;

    private boolean child;

    public RawCommand(String path) {
        this.path = path;
        this.child = false;
        this.aliases = new ArrayList<>();
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

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public String[] getAliases() {
        return this.aliases.toArray(new String[this.aliases.size()]);
    }

    public boolean isChild() {
        return this.child;
    }
}