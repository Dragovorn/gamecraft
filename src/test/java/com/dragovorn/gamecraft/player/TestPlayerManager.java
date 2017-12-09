package com.dragovorn.gamecraft.player;

import com.dragovorn.gamecraft.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest({Main.class, PlayerManager.class, Player.class, PlayerJoinEvent.class, PlayerQuitEvent.class})
@RunWith(PowerMockRunner.class)
public class TestPlayerManager {

    private PlayerManager manager;

    @Before
    public void before() {
        this.manager = new PlayerManager();
    }

    @Test
    public void testRegisterPlayerNoType() {
        assertEquals(GamePlayer.class, this.manager.getPlayer(registerPlayer()).getClass());
    }

    @Test
    public void testRegisterPlayerCustomType() {
        this.manager.setPlayerClass(TestPlayer.class);

        assertEquals(TestPlayer.class, this.manager.getPlayer(registerPlayer()).getClass());
    }

    @Test
    public void testDeregisterPlayerNoType() {
        Player player = registerPlayer();

        PlayerQuitEvent event = mock(PlayerQuitEvent.class);
        when(event.getPlayer()).thenReturn(player);

        this.manager.deregisterPlayer(event);
    }

    @Test
    public void testDeregisterPlayerCustomType() {
        this.manager.setPlayerClass(TestPlayer.class);

        Player player = registerPlayer();

        PlayerQuitEvent event = mock(PlayerQuitEvent.class);
        when(event.getPlayer()).thenReturn(player);

        this.manager.deregisterPlayer(event);
    }

    private Player registerPlayer() {
        Player player = mock(Player.class);
        when(player.getUniqueId()).thenReturn(UUID.randomUUID());

        PlayerJoinEvent event = mock(PlayerJoinEvent.class);
        when(event.getPlayer()).thenReturn(player);

        this.manager.registerPlayer(event);

        return player;
    }

    public static class TestPlayer extends GamePlayer {

        TestPlayer(Player player) {
            super(player);
        }
    }
}