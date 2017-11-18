package com.dragovorn.gamecraft.discovery;

public interface IGame {

    default void onLoad() {

    }

    default void onDisable() {

    }

    void onEnable();
}