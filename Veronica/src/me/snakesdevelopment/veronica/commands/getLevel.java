package me.snakesdevelopment.veronica.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.snakesdevelopment.veronica.mysql.mysqlSetterGetter;

public class getLevel implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = Bukkit.getServer().getPlayer(args[0]);
			
			if(player != null) {
				if(cmd.getName().equalsIgnoreCase("level")) {
					mysqlSetterGetter.getLevel(player, sender);
				}
			} else {
				sender.sendMessage("§4Speler niet gevonden!");
			}
			
		}
		
		return true;
	}

}
