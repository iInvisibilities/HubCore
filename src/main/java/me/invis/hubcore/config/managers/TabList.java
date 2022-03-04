package me.invis.hubcore.config.managers;

public class TabList {
    private final boolean enabled;
    private final String header;
    private final String footer;

    public TabList(boolean enabled, String... headerAndFooter) {
        this.enabled = enabled;
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
}
