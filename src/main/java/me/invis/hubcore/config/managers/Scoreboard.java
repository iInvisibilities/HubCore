package me.invis.hubcore.config.managers;

import org.bukkit.entity.Player;

import java.util.List;

public class Scoreboard {
    private static boolean enabled;
    private String title;
    private List<String> content;
    private Player target;

    public Scoreboard(boolean enabled, Player target, String title, List<String> content) {
        Scoreboard.enabled = enabled;
        this.target = target;
        this.title = title;
        this.content = content;
    }

    public static boolean enabled() {
        return enabled;
    }

    public String title() {
        return this.title;
    }

    public List<String> content() {
        return this.content;
    }

    public Player target() {
        return this.target;
    }
}
