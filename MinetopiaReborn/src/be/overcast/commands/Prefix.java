package be.overcast.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class Prefix implements CommandExecutor {
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(sender.hasPermission("mt.setprefix")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Gebruik: /prefix <speler> <prefix>");
			}else if(args.length == 1) {
				sender.sendMessage(ChatColor.RED + "Gebruik: /prefix <speler> <prefix>");
			}else if(args.length == 2) {
				String prefix = args[1].replaceAll("_", " ");
				String fin = prefix.replaceAll("&", "§");
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " prefix \"" + fin + "\"");
				sender.sendMessage("§8(§b!§8) §bPrefix ingesteld voor §c" + args[0] + " §b!");
			}else if(args.length > 2) {
				sender.sendMessage(ChatColor.RED + "Gebruik: /prefix <speler> <prefix>");
			}
		}
		return true;
	}
}
