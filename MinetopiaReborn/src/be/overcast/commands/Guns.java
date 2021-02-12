package be.overcast.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Guns implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {


		Player p = (Player)sender;
		
		if(p instanceof Player) {
			if(p.hasPermission("minetopiareborn.guns") && p.isOp()) {
				if(cmd.getName().equalsIgnoreCase("geefwapen")) {
					Bukkit.dispatchCommand(p, "qa give glock " + p.getName() + " 1");
					Bukkit.dispatchCommand(p, "qa give 9mm " + p.getName() + " 90");
				}
			} else {
				p.sendMessage("§cJe hebt geen toegang tot deze commando!");
			}
		}
		
		
		return true;
	}

}
