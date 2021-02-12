package be.overcast.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Facturen implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		
		Player player = (Player)sender;
		
		if(player instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("facturen")) {
				player.sendMessage("§aFacturen geopend!");
				
				Inventory inventory = Bukkit.createInventory(player, 27, "§3Facturen");
				
				// API DEMPSEY
				
				player.openInventory(inventory);
			} else if(cmd.getName().equalsIgnoreCase("factuur")) {
				if(player.hasPermission("minetopiareborn.factuur")) {
					if(args.length < 3) {
						player.sendMessage("§cGebruik: /factuur <speler> <bedrag> <reden>");
					} else {
						// API DEMPSEY
					}
				} else {
					player.sendMessage("§cJe hebt geen toegang tot deze commando!");
				}
			}
		}
		
		
		
		
		
		return true;
	}

}
