package de.fido.msgpluginbungeecord.commands;

import de.fido.msgpluginbungeecord.data.UserConnector;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ResponseCMD extends Command {
    private final UserConnector userConnector = UserConnector.getInstance();

    public ResponseCMD() {
        super("r", null, "");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You must be a player to use this command!"));
            return;
        }
        if (args.length < 1) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Usage: /r <message>"));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;
        ProxiedPlayer lastTarget = userConnector.getLastUser(p);
        if (lastTarget == null) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "No recent player!"));
            return;
        }

        if (!lastTarget.isConnected()) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Player is not connected!"));
            return;
        }

        String message = String.join(" ", args);
        sendMessages(sender, lastTarget, message);
    }

    static void sendMessages(CommandSender sender, ProxiedPlayer target, String message) {
        sender.sendMessage(new TextComponent(ChatColor.GOLD + "You " + ChatColor.WHITE + "->" + ChatColor.AQUA + " [" + ChatColor.GOLD + target.getName() + ChatColor.AQUA + "]: " + ChatColor.WHITE + message));
        target.sendMessage(new TextComponent(ChatColor.AQUA + "[" + ChatColor.GOLD + sender.getName() + ChatColor.AQUA + "] " + ChatColor.WHITE + " -> " + ChatColor.GOLD + " You: " + ChatColor.WHITE + message));
    }
}
