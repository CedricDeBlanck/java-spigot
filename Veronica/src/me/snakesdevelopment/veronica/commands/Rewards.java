package me.snakesdevelopment.veronica.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.snakesdevelopment.veronica.mysql.mysqlSetterGetter;

public class Rewards implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		Player p = (Player)sender;
		
		if(p instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("rewards")) {
				if(p.hasPermission("Veronica.rewards")) {
					mysqlSetterGetter.getRewards(p, sender);
				}
			}
		}
		
		return true;
	}

}
