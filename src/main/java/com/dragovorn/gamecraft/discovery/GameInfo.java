package com.dragovorn.gamecraft.discovery;

public class GameInfo {

    private String name;
    private String developer;
    private String version;
    private String supportedVersions;

    class Builder {
        private String name;
        private String developer;
        private String version;
        private String supportedVersions;

        public Builder() { }

        public void setName(String name) {
            this.name = name;
        }

        public void setDeveloper(String developer) {
            this.developer = developer;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public void setSupportedVersions(String supportedVersions) {
            this.supportedVersions = supportedVersions;
        }
    }

    private GameInfo(String name, String developer, String version, String supportedVersions) {
        this.name = name;
        this.developer = developer;
        this.version = version;
        this.supportedVersions = supportedVersions;
    }

    public String getName() {
        return this.name;
    }

    public String getDeveloper() {
        return this.developer;
    }

    public String getVersion() {
        return this.version;
    }

    public String getSupportedVersions() {
        return this.supportedVersions;
    }
}