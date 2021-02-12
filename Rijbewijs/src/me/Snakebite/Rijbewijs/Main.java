package me.Snakebite.Rijbewijs;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {

	
	public void onEnable() {
		System.out.println("[Rijbewijs Snakebite] Enabled!");
	}
	
	@SuppressWarnings("static-access")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.DARK_RED + "Je kan deze commando alleen maar in-game uitvoeren!");
		} else {
			Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("rijbewijs")) {
				if(p.hasPermission("rijbewijs.use")) {
					if(args.length == 0) {
						p.sendMessage(ChatColor.RED + "[Rijbewijs]" + ChatColor.GRAY + "Gebruik: /rijbewijs <speler> <motor:auto> <nummerplaat>");
					} else if(args.length == 1) {
						p.sendMessage(ChatColor.RED + "[Rijbewijs]" + ChatColor.GRAY + "Gebruik: /rijbewijs <speler> <motor:auto> <nummerplaat>");
					} else if(args.length == 2) {
						p.sendMessage(ChatColor.RED + "[Rijbewijs]" + ChatColor.GRAY + "Gebruik: /rijbewijs <speler> <motor:auto> <nummerplaat>");
					} else if(args.length == 3) {
						ItemStack i = new ItemStack(Material.DIAMOND_HOE,1, (short)90);
						ItemMeta im = i.getItemMeta();
						im.setDisplayName("§3§lRijbewijs");
						ArrayList<String> lore = new ArrayList<String>();
						lore.add(ChatColor.DARK_AQUA + "Naam: " + ChatColor.GREEN + args[0]);
						lore.add(ChatColor.DARK_AQUA + "Type: " + ChatColor.GREEN  + args[1]);
						lore.add(ChatColor.DARK_AQUA + "Nummerplaat: " + ChatColor.GREEN + args[2]);
						im.setLore(lore);
						im.setUnbreakable(true);
						i.setItemMeta(im);
						p.getInventory().addItem(i);
						p.sendMessage("Rijbewijs aangemaakt!");
					} else {
						p.sendMessage(ChatColor.RED + "[Rijbewijs]" + ChatColor.GRAY + "Gebruik: /rijbewijs <speler> <motor:auto> <nummerplaat>");
					}
				} else {
					p.sendMessage(ChatColor.RED + "[Rijbewijs]" + ChatColor.GRAY + "Je hebt geen toegang tot dit commando!");
				}
			}
		}
		
		return true;
	}
}
