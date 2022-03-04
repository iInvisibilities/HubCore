package me.invis.hubcore.listener;

import me.invis.hubcore.HubCore;
import me.invis.hubcore.config.ConfigManager;
import me.invis.hubcore.config.managers.DoubleJump;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class FlyToggleEvent implements Listener {

    @EventHandler
    private void onFly(PlayerToggleFlightEvent event) {
        ConfigManager configManager = HubCore.CONFIG_MANAGER;
        DoubleJump doubleJump = configManager.doubleJump();
        if(!doubleJump.enabled()) return;
        Player target = event.getPlayer();
        if(target.getGameMode() == GameMode.CREATIVE) return;
        event.setCancelled(true);
        target.setVelocity(target.getLocation().getDirection().setY(doubleJump.Y_axis()).multiply(doubleJump.power()));
        target.playEffect(target.getLocation(), doubleJump.effect(), doubleJump.effect().getData());
        target.playSound(target.getLocation(), doubleJump.sound(), 1, 1);
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        if(HubCore.CONFIG_MANAGER.doubleJump().enabled()) event.getPlayer().setAllowFlight(true);
    }

    @EventHandler
    private void onGMChange(PlayerGameModeChangeEvent event) {
        if(HubCore.CONFIG_MANAGER.doubleJump().enabled()) event.getPlayer().setAllowFlight(true);
    }
}
