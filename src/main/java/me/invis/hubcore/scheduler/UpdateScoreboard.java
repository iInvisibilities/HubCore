package me.invis.hubcore.scheduler;

import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import me.invis.hubcore.HubCore;
import me.invis.hubcore.config.managers.Scoreboard;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class UpdateScoreboard extends BukkitRunnable {
    private final JPerPlayerMethodBasedScoreboard scoreboard;

    private final Player target;
    private Scoreboard scoreboardSettings;

    public UpdateScoreboard(Player target) {
        this.scoreboard = HubCore.SCOREBOARD;
        this.scoreboardSettings = HubCore.CONFIG_MANAGER.scoreboard(target);
        this.target = target;
    }

    @Override
    public void run() {
        this.scoreboardSettings = HubCore.CONFIG_MANAGER.scoreboard(target);

        scoreboard.setTitle(target, scoreboardSettings.title());
        scoreboard.setLines(target, scoreboardSettings.content());

        scoreboard.updateScoreboard();
    }
}
