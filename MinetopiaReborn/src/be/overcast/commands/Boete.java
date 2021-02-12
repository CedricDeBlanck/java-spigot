package be.overcast.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Boete implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		Player player = (Player)sender;
		
		if(player instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("boetes")) {
				if(player.hasPermission("boete.get")) {
					
				}
			}
		}
		
		return true;
	}

	
	
}
