package me.invis.hubcore.config.managers;

import org.bukkit.entity.Player;

public class TabList {
    private final boolean enabled;
    private final String header, footer;
    private final Player target;

    public TabList(Player target, boolean enabled, String... headerAndFooter) {
        this.enabled = enabled;
        this.target = target;
        this.header = headerAndFooter[0];
        this.footer = headerAndFooter[1];
    }

    public boolean enabled() {
        return this.enabled;
    }

    public String header() {
        return this.header;
    }

    public String footer() {
        return this.footer;
    }

    public Player target() {
        return this.target;
    }
}
