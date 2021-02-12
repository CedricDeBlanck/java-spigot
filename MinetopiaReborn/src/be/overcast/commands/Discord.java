package be.overcast.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;



public class Discord implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("discord")) {
			if(args[0].equalsIgnoreCase("link")) {
				Bukkit.dispatchCommand(sender, "discordsrv link");
			}
		}
		
		return true;
	}
	
	


}
