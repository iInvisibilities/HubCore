package me.invis.hubcore.config.managers;

public class Title {

    private final boolean enabled;
    private final String header;
    private final String footer;

    public Title(boolean enabled, String... footerAndHeader) {
        this.enabled = enabled;
        this.header = footerAndHeader[0];
        this.footer = footerAndHeader[1];
    }

    public String header() {
        return this.header;
    }

    public String footer() {
        return this.footer;
    }

    public boolean enabled() {
        return this.enabled;
    }
}
