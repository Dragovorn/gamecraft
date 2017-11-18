package com.dragovorn.gamecraft.command;

public class RawCommand {

    private String path;
    private String name;

    public RawCommand(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }
}