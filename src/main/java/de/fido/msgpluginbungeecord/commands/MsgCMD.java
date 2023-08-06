package de.fido.msgpluginbungeecord.commands;

import de.fido.msgpluginbungeecord.data.UserConnector;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Arrays;

public class MsgCMD extends Command {
    private final ProxyServer proxyServer = ProxyServer.getInstance();
    private final UserConnector userConnector = UserConnector.getInstance();

    public MsgCMD() {
        super("msg", null, "");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You must be a player to use this command!"));
            return;
        }

        if (args.length <= 1) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Usage: /msg <player> <message>"));
            return;
        }

        ProxiedPlayer target = this.proxyServer.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Player not found!"));
            return;
        } else if (!target.isConnected()) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Player is not connected!"));
            return;
        } else if (target == sender) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You can't message yourself!"));
            return;
        }

        String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        ResponseCMD.sendMessages(sender, target, message);

        this.userConnector.setLastUser((ProxiedPlayer) sender, target);
        this.userConnector.setLastUser(target, (ProxiedPlayer) sender);
        // safe both players takes 2x RAM but helps to reduce CPU Usage since O(1) is way better then
        // iterating for each user O(n)
    }
}
