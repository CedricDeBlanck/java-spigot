package me.snakebite.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.snakebite.menu.Menu;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

		Player p = (Player) sender;
		
		if(p instanceof Player) {
			if(p.hasPermission("mtselector.test")) {
				if(cmd.getName().equalsIgnoreCase("jobs")) {
					Menu.jobSelection(p);
				}
			}
		}
		
		
		
		return true;
	}

}
