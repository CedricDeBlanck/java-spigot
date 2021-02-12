package me.snakebite.politiebadge;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {

	
	public void onEnable() {
		System.out.println("[PolitieBadge] Enabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.DARK_RED + "Je kan deze commando alleen maar in-game uitvoeren!");
		} else {
			Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("badge")) {
				if(p.hasPermission("politiebadge.use")) {
					if(args.length == 0) {
						p.sendMessage(ChatColor.RED + "[PolitieBadge]" + ChatColor.GRAY + " Gebruik: /Badge <speler>");
					} else if(args.length == 1)  {
						ItemStack i = new ItemStack(Material.NAME_TAG,1);
						ItemMeta im = i.getItemMeta();
						im.setDisplayName("§6Politie Badge");
						ArrayList<String> lore = new ArrayList<String>();
						lore.add(ChatColor.AQUA + args[0]);
						im.setLore(lore);
						im.setUnbreakable(false);
						im.addEnchant(Enchantment.MENDING, -1, true);
						im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						i.setItemMeta(im);
						p.getInventory().addItem(i);
						p.sendMessage("§4Badge aangemaakt!");
					} else {
						p.sendMessage(ChatColor.RED + "[PolitieBadge]" + ChatColor.GRAY + " Gebruik: /badge <speler>");
					}
				} else {
					p.sendMessage(ChatColor.RED + "[PolitieBadge]" + ChatColor.GRAY + " Je hebt geen toegang tot dit commando!");
				}
			}
		}
		
		return true;
	}
}
