package be.overcast.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Shop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		Player t = Bukkit.getPlayer(args[0]);
		
		String store = args[1];
		
		if(!(sender instanceof Player)) {
			if(cmd.getName().equalsIgnoreCase("winkel")) {
				if(args.length > 3) {
					sender.sendMessage("Wrong arguments!");
				} else {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "shop " + t.getName() + " " + store);
				}
			} else if(cmd.getName().equalsIgnoreCase("shop")) {
				if(args[0].equalsIgnoreCase("slager") ||
						args[0].equalsIgnoreCase("smid") ||
						args[0].equalsIgnoreCase("houthakker") ||
						args[0].equalsIgnoreCase("mijner") ||
						args[0].equalsIgnoreCase("slager") ||
						args[0].equalsIgnoreCase("imker") ||
						args[0].equalsIgnoreCase("bakker") ||
						args[0].equalsIgnoreCase("visser")) {
					return true;
				}
				return true;

			}
		} else {
			sender.sendMessage("§cDeze commando kan je niet in-game gebruiken!");
		}
		
		return true;
	}

}
