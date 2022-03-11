package me.invis.hubcore.scheduler;

import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import me.invis.hubcore.HubCore;
import org.bukkit.scheduler.BukkitRunnable;

public class UpdateScoreboard extends BukkitRunnable {
    private final JPerPlayerMethodBasedScoreboard scoreboard;

    public UpdateScoreboard() {
        this.scoreboard = HubCore.SCOREBOARD;
    }

    @Override
    public void run() {
        scoreboard.updateScoreboard();
    }
}
