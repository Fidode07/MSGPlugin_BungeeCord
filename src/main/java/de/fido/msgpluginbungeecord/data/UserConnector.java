package de.fido.msgpluginbungeecord.data;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;

public class UserConnector {
    private static final UserConnector instance = new UserConnector();
    public static final HashMap<ProxiedPlayer, ProxiedPlayer> lastWritten = new HashMap<>();

    private UserConnector() {
    }

    public static UserConnector getInstance() {
        return instance;
    }

    public ProxiedPlayer getLastUser(ProxiedPlayer p) {
        return lastWritten.getOrDefault(p, null);
    }

    public void setLastUser(ProxiedPlayer p, ProxiedPlayer last) {
        lastWritten.put(p, last);
    }
}
