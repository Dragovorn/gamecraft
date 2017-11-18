package com.dragovorn.gamecraft.discovery;

import java.io.File;

public class GameInfo {

    private File file;

    private String name;
    private String author;
    private String version;
    private String supportedVersions;

    public static class Builder {
        private File file;

        private String name;
        private String author;
        private String version;
        private String supportedVersions;

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

        public void setSupportedVersions(String supportedVersions) {
            this.supportedVersions = supportedVersions;
        }

        GameInfo build() {
            return new GameInfo(this.file, this.name, this.author, this.version, this.supportedVersions);
        }
    }

    private GameInfo(File file, String name, String author, String version, String supportedVersions) {
        this.file = file;
        this.name = name;
        this.author = author;
        this.version = version;
        this.supportedVersions = supportedVersions;
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

    public String getSupportedVersions() {
        return this.supportedVersions;
    }
}