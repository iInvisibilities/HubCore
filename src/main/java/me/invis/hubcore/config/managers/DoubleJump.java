package me.invis.hubcore.config.managers;

import org.bukkit.Effect;
import org.bukkit.Sound;

public class DoubleJump {
    private final boolean enabled;
    private final Sound sound;
    private final Effect effect;
    private final double power;
    private final double Y_axis;

    public DoubleJump(boolean enabled, Sound sound, Effect effect, double power, double Y_axis) {
        this.enabled = enabled;
        this.sound = sound;
        this.effect = effect;
        this.power = power;
        this.Y_axis = Y_axis;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public Sound sound() {
        return this.sound;
    }

    public Effect effect() {
        return this.effect;
    }

    public double power() {
        return this.power;
    }

    public double Y_axis() {
        return this.Y_axis;
    }
}
