package com.dragovorn.gamecraft.discovery.info;

import java.io.File;

public class GameInfo {

    private File file;

    private CommandInfo commandInfo;

    private PlayerInfo playerInfo;

    private String name;
    private String author;
    private String version;
    private String main;
    private String supportedVersions;
    private String incompatibleWith;

    public static class Builder {

        private File file;

        private CommandInfo.Builder commandInfo;

        private PlayerInfo.Builder playerInfo;

        private String name;
        private String author;
        private String version;
        private String main;
        private String supportedVersions;
        private String incompatibleWith;

        public Builder(File file) {
            this.file = file;
            this.commandInfo = new CommandInfo.Builder();
            this.main = "";
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public void setSupportedVersions(String supportedVersions) {
            this.supportedVersions = supportedVersions;
        }

        public void setIncompatibleWith(String incompatibleWith) {
            this.incompatibleWith = incompatibleWith;
        }

        public CommandInfo.Builder getCommandInfo() {
            return this.commandInfo;
        }

        public PlayerInfo.Builder getPlayerInfo() {
            return this.playerInfo;
        }

        public boolean hasMain() {
            return !this.main.equals("");
        }

        public GameInfo build() {
            return new GameInfo(this);
        }
    }

    private GameInfo(Builder builder) {
        this.file = builder.file;
        this.name = builder.name;
        this.author = builder.author;
        this.version = builder.version;
        this.main = builder.main;
        this.supportedVersions = builder.supportedVersions;
        this.incompatibleWith = builder.incompatibleWith;
        this.commandInfo = builder.commandInfo.build();
        this.playerInfo = builder.playerInfo.build();
    }

    public File getFile() {
        return this.file;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getVersion() {
        return this.version;
    }

    public String getMain() {
        return this.main;
    }

    public String getSupportedVersions() {
        return this.supportedVersions;
    }

    public String getIncompatibleWith() {
        return this.incompatibleWith;
    }

    public CommandInfo getCommandInfo() {
        return this.commandInfo;
    }

    public PlayerInfo getPlayerInfo() {
        return this.playerInfo;
    }
}