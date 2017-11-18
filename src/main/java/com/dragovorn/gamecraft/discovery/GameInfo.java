package com.dragovorn.gamecraft.discovery;

import java.io.File;

public class GameInfo {

    private File file;

    private String name;
    private String author;
    private String version;
    private String main;
    private String supportedVersions;
    private String incompatibleWith;

    public static class Builder {
        private File file;

        private String name;
        private String author;
        private String version;
        private String main;
        private String supportedVersions;
        private String incompatibleWith;

        Builder(File file) {
            this.file = file;
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

        GameInfo build() {
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
}