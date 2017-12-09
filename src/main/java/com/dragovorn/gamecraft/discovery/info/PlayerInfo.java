package com.dragovorn.gamecraft.discovery.info;

public class PlayerInfo {

    private String playerTypePath;

    public static class Builder {

        private String playerTypePath;

        public Builder() {
            this.playerTypePath = "";
        }

        public void setPlayerTypePath(String path) {
            this.playerTypePath = path;
        }

        public PlayerInfo build() {
            return new PlayerInfo(this);
        }
    }

    private PlayerInfo(Builder builder) {
        this.playerTypePath = builder.playerTypePath;
    }

    public String getPlayerTypePath() {
        return this.playerTypePath;
    }
}