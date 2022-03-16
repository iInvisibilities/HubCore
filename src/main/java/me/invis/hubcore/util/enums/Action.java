package me.invis.hubcore.util.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Action {
    MESSAGE("m"),
    INVENTORY("i"),
    SOUND("s"),
    PARTICLE("e"),
    COMMAND("c");

    private final String CHAR;
    private static final Map<String, Action> ID_MAP = new HashMap<>();

    Action(String CHAR) {
        this.CHAR = CHAR;
    }

    public static String charOf(Action action) {
        return action.CHAR;
    }

    public static Action fromChar(String CHAR) {
        return ID_MAP.get(CHAR);
    }

    static {
        Arrays.stream(values()).forEach(type -> ID_MAP.put(type.CHAR, type));
    }
}
