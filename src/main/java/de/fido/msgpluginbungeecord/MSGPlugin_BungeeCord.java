package de.fido.msgpluginbungeecord;

import de.fido.msgpluginbungeecord.commands.MsgCMD;
import de.fido.msgpluginbungeecord.commands.ResponseCMD;
import net.md_5.bungee.api.plugin.Plugin;

public final class MSGPlugin_BungeeCord extends Plugin {

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerCommand(this, new MsgCMD());
        getProxy().getPluginManager().registerCommand(this, new ResponseCMD());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
