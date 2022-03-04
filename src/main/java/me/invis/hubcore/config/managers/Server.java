package me.invis.hubcore.config.managers;

import java.util.Map;

public class Server {
    private final String name;
    private final Map<String, Object> sites;

    public Server(String name, Map<String, Object> sites) {
        this.name = name;
        this.sites = sites;
    }

    public String name() {
        return this.name;
    }

    public Map<String, Object> sites() {
        return this.sites;
    }
}
