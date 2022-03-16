package me.invis.hubcore.listener;

import me.invis.hubcore.HubCore;
import me.invis.hubcore.config.managers.Scoreboard;
import me.invis.hubcore.scheduler.UpdateScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ScoreboardApplier implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Player target = event.getPlayer();
        Scoreboard scoreboardSettings = HubCore.CONFIG_MANAGER.scoreboard(target);

        if(!Scoreboard.enabled()) {
            target.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            return;
        }

        HubCore.SCOREBOARD.setTitle(target, scoreboardSettings.title());
        HubCore.SCOREBOARD.setLines(target, scoreboardSettings.content());
        HubCore.SCOREBOARD.addPlayer(target);

        new UpdateScoreboard(target).runTaskTimer(HubCore.PLUGIN, 20, 20);
    }

}
