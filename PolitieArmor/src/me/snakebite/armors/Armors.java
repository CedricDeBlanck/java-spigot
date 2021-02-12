package me.snakebite.armors;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.snakebite.files.config;
import net.md_5.bungee.api.ChatColor;

public class Armors implements CommandExecutor {
	
	@SuppressWarnings("unused")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		String Aspirant = "Aspirant";
		String Wijkagent = "Wijkagent";
		String Agent = "Agent";
		String HoofdAgent = "HoofdAgent";
		String Inspecteur = "Inspecteur";
		String HoofdInspecteur = "HoofdInspecteur";
		String Commisaris = "Commisaris";
		String LoreAspirant = config.get().getString("LoreAspirant");
		String LoreWijkagent = config.get().getString("LoreWijkagent");
		String LoreAgent = config.get().getString("LoreAgent");
		String LoreHoofdAgent = config.get().getString("LoreHoofdAgent");
		String LoreInspecteur = config.get().getString("LoreInspecteur");
		String LoreHoofdInspecteur = config.get().getString("LoreHoofdInspecteur");
		String LoreCommisaris = config.get().getString("LoreCommisaris");

		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.DARK_RED + "Je kan deze commando alleen maar in-game uitvoeren!");
		} else {
			Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("politiearmor")) {
				if(!p.hasPermission("politiearmor.use")) {
					p.sendMessage(ChatColor.RED + "[PolitieArmor]" + ChatColor.GRAY + " Je hebt geen toegang tot dit commando!");
				}
				if(p.hasPermission("politiearmor.use")) {
					if(args.length == 0) {
						p.sendMessage(ChatColor.RED + "[PolitieArmor]" + ChatColor.GRAY + " Type \"/politiearmor help\" voor de commando's");
					} else if(args.length == 1)  {
						if(args[0].equalsIgnoreCase("help")) {
							p.sendMessage(ChatColor.RED + "[PolitieArmor]" + ChatColor.GRAY + " PolitieArmor commando's");
							p.sendMessage(ChatColor.RED + "- " + ChatColor.GRAY + "/politiearmor <politiegroep> - armor van bepaalde groep");
							p.sendMessage(ChatColor.RED + "- " + ChatColor.GRAY + "/politiearmor lijst - lijst van groepen");
						} else if(args[0].equalsIgnoreCase("reload")) {							
								p.sendMessage(ChatColor.RED + "[PolitieArmor]" + ChatColor.GRAY + " Config succesvol gereload!");
								System.out.println(ChatColor.GRAY + "[" + ChatColor.RED + "PolitieArmor" + ChatColor.GRAY + "]" + ChatColor.GREEN + "Config succesvol gereload!");
								config.reload();
						} else if(args[0].equalsIgnoreCase("lijst")) {						
							p.sendMessage(ChatColor.RED + "[PolitieArmor]" + ChatColor.GRAY + " Lijst van groepen");
							p.sendMessage(ChatColor.RED + "- " + ChatColor.GRAY + Aspirant);
							p.sendMessage(ChatColor.RED + "- " + ChatColor.GRAY + Wijkagent);
							p.sendMessage(ChatColor.RED + "- " + ChatColor.GRAY + Agent);
							p.sendMessage(ChatColor.RED + "- " + ChatColor.GRAY + HoofdAgent);
							p.sendMessage(ChatColor.RED + "- " + ChatColor.GRAY + Inspecteur);
							p.sendMessage(ChatColor.RED + "- " + ChatColor.GRAY + HoofdInspecteur);
							p.sendMessage(ChatColor.RED + "- " + ChatColor.GRAY + Commisaris);
						} else if(args[0].equalsIgnoreCase("aspirant")) {
						
						// ITEMS
						
						ItemStack h = new ItemStack(Material.IRON_HELMET,1);
						ItemStack c = new ItemStack(Material.IRON_CHESTPLATE,1);
						ItemStack l = new ItemStack(Material.IRON_LEGGINGS,1);
						ItemStack b = new ItemStack(Material.LEATHER_BOOTS,1);
						
						// ITEMMETA
						ItemMeta hm = h.getItemMeta();
						ItemMeta cm = c.getItemMeta();
						ItemMeta lm = l.getItemMeta();
						ItemMeta bm = b.getItemMeta();
						
						// DISPLAYNAMES
						hm.setDisplayName("§bPolitie Armor");
						cm.setDisplayName("§bPolitie Armor");
						lm.setDisplayName("§bPolitie Armor");
						bm.setDisplayName("§bPolitie Armor");						
						
						// ARRAYLIST LORES
						ArrayList<String> lore = new ArrayList<String>();
						// LORE
						lore.add(ChatColor.translateAlternateColorCodes('&', LoreAspirant));
						
						// LORES TO ITEM
						hm.setLore(lore);
						cm.setLore(lore);
						lm.setLore(lore);
						bm.setLore(lore);
						
						// UNBREAKABLE
						hm.setUnbreakable(true);
						cm.setUnbreakable(true);
						lm.setUnbreakable(true);
						bm.setUnbreakable(true);

						// ADD ENCHANTMENTS
						hm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						cm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						lm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);

						// SET ITEM META
						h.setItemMeta(hm);
						c.setItemMeta(cm);
						l.setItemMeta(lm);
						b.setItemMeta(bm);
						
						// ITEM IN INVENTORY
						p.getInventory().addItem(h);
						p.getInventory().addItem(c);
						p.getInventory().addItem(l);
						p.getInventory().addItem(b);
						
						// RECEIVE MESSAGE
						p.sendMessage("§4Armor aangemaakt!");
						
					} else if(args[0].equalsIgnoreCase("wijkagent"))  {
						
						// ITEMS
						
						ItemStack h = new ItemStack(Material.IRON_HELMET,1);
						ItemStack c = new ItemStack(Material.IRON_CHESTPLATE,1);
						ItemStack l = new ItemStack(Material.IRON_LEGGINGS,1);
						ItemStack b = new ItemStack(Material.IRON_BOOTS,1);
						
						// ITEMMETA
						ItemMeta hm = h.getItemMeta();
						ItemMeta cm = c.getItemMeta();
						ItemMeta lm = l.getItemMeta();
						ItemMeta bm = b.getItemMeta();
						
						// DISPLAYNAMES
						hm.setDisplayName("§bPolitie Armor");
						cm.setDisplayName("§bPolitie Armor");
						lm.setDisplayName("§bPolitie Armor");
						bm.setDisplayName("§bPolitie Armor");						
						
						// ARRAYLIST LORES
						ArrayList<String> lore = new ArrayList<String>();
						
						// LORE
						lore.add(ChatColor.translateAlternateColorCodes('&', LoreWijkagent));
						
						// LORES TO ITEM
						hm.setLore(lore);
						cm.setLore(lore);
						lm.setLore(lore);
						bm.setLore(lore);
						
						// UNBREAKABLE
						hm.setUnbreakable(true);
						cm.setUnbreakable(true);
						lm.setUnbreakable(true);
						bm.setUnbreakable(true);

						// ADD ENCHANTMENTS
						hm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						cm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						lm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);

						// SET ITEM META
						h.setItemMeta(hm);
						c.setItemMeta(cm);
						l.setItemMeta(lm);
						b.setItemMeta(bm);
						
						// ITEM IN INVENTORY
						p.getInventory().addItem(h);
						p.getInventory().addItem(c);
						p.getInventory().addItem(l);
						p.getInventory().addItem(b);
						
						// RECEIVE MESSAGE
						p.sendMessage("§4Armor aangemaakt!");
						
					} else if(args[0].equalsIgnoreCase("agent"))  {
						
						// ITEMS
						
						ItemStack h = new ItemStack(Material.IRON_HELMET,1);
						ItemStack c = new ItemStack(Material.IRON_CHESTPLATE,1);
						ItemStack l = new ItemStack(Material.IRON_LEGGINGS,1);
						ItemStack b = new ItemStack(Material.IRON_BOOTS,1);
						
						// ITEMMETA
						ItemMeta hm = h.getItemMeta();
						ItemMeta cm = c.getItemMeta();
						ItemMeta lm = l.getItemMeta();
						ItemMeta bm = b.getItemMeta();
						
						// DISPLAYNAMES
						hm.setDisplayName("§bPolitie Armor");
						cm.setDisplayName("§bPolitie Armor");
						lm.setDisplayName("§bPolitie Armor");
						bm.setDisplayName("§bPolitie Armor");						
						
						// ARRAYLIST LORES
						ArrayList<String> lore = new ArrayList<String>();
						
						// LORE
						lore.add(ChatColor.translateAlternateColorCodes('&', LoreAgent));
						
						// LORES TO ITEM
						hm.setLore(lore);
						cm.setLore(lore);
						lm.setLore(lore);
						bm.setLore(lore);
						
						// UNBREAKABLE
						hm.setUnbreakable(true);
						cm.setUnbreakable(true);
						lm.setUnbreakable(true);
						bm.setUnbreakable(true);

						// ADD ENCHANTMENTS
						hm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						cm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						lm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						bm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);

						// SET ITEM META
						h.setItemMeta(hm);
						c.setItemMeta(cm);
						l.setItemMeta(lm);
						b.setItemMeta(bm);
						
						// ITEM IN INVENTORY
						p.getInventory().addItem(h);
						p.getInventory().addItem(c);
						p.getInventory().addItem(l);
						p.getInventory().addItem(b);
						
						// RECEIVE MESSAGE
						p.sendMessage("§4Armor aangemaakt!");
						
					} else if(args[0].equalsIgnoreCase("hoofdagent"))  {
						
						// ITEMS
						
						ItemStack h = new ItemStack(Material.IRON_HELMET,1);
						ItemStack c = new ItemStack(Material.IRON_CHESTPLATE,1);
						ItemStack l = new ItemStack(Material.IRON_LEGGINGS,1);
						ItemStack b = new ItemStack(Material.GOLD_BOOTS,1);
						
						// ITEMMETA
						ItemMeta hm = h.getItemMeta();
						ItemMeta cm = c.getItemMeta();
						ItemMeta lm = l.getItemMeta();
						ItemMeta bm = b.getItemMeta();
						
						// DISPLAYNAMES
						hm.setDisplayName("§bPolitie Armor");
						cm.setDisplayName("§bPolitie Armor");
						lm.setDisplayName("§bPolitie Armor");
						bm.setDisplayName("§bPolitie Armor");						
						
						// ARRAYLIST LORES
						ArrayList<String> lore = new ArrayList<String>();
						
						// LORE
						lore.add(ChatColor.translateAlternateColorCodes('&', LoreHoofdAgent));
						
						// LORES TO ITEM
						hm.setLore(lore);
						cm.setLore(lore);
						lm.setLore(lore);
						bm.setLore(lore);
						
						// UNBREAKABLE
						hm.setUnbreakable(true);
						cm.setUnbreakable(true);
						lm.setUnbreakable(true);
						bm.setUnbreakable(true);

						// ADD ENCHANTMENTS
						hm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						cm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						lm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);

						// SET ITEM META
						h.setItemMeta(hm);
						c.setItemMeta(cm);
						l.setItemMeta(lm);
						b.setItemMeta(bm);
						
						// ITEM IN INVENTORY
						p.getInventory().addItem(h);
						p.getInventory().addItem(c);
						p.getInventory().addItem(l);
						p.getInventory().addItem(b);
						
						// RECEIVE MESSAGE
						p.sendMessage("§4Armor aangemaakt!");
						
					} else if(args[0].equalsIgnoreCase("inspecteur"))  {
						
						// ITEMS
						
						ItemStack h = new ItemStack(Material.IRON_HELMET,1);
						ItemStack c = new ItemStack(Material.IRON_CHESTPLATE,1);
						ItemStack l = new ItemStack(Material.IRON_LEGGINGS,1);
						ItemStack b = new ItemStack(Material.GOLD_BOOTS,1);
						
						// ITEMMETA
						ItemMeta hm = h.getItemMeta();
						ItemMeta cm = c.getItemMeta();
						ItemMeta lm = l.getItemMeta();
						ItemMeta bm = b.getItemMeta();
						
						// DISPLAYNAMES
						hm.setDisplayName("§bPolitie Armor");
						cm.setDisplayName("§bPolitie Armor");
						lm.setDisplayName("§bPolitie Armor");
						bm.setDisplayName("§bPolitie Armor");						
						
						// ARRAYLIST LORES
						ArrayList<String> lore = new ArrayList<String>();
						
						// LORE
						lore.add(ChatColor.translateAlternateColorCodes('&', LoreInspecteur));
						
						// LORES TO ITEM
						hm.setLore(lore);
						cm.setLore(lore);
						lm.setLore(lore);
						bm.setLore(lore);
						
						// UNBREAKABLE
						hm.setUnbreakable(true);
						cm.setUnbreakable(true);
						lm.setUnbreakable(true);
						bm.setUnbreakable(true);

						// ADD ENCHANTMENTS
						hm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						cm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						lm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						bm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);

						// SET ITEM META
						h.setItemMeta(hm);
						c.setItemMeta(cm);
						l.setItemMeta(lm);
						b.setItemMeta(bm);
						
						// ITEM IN INVENTORY
						p.getInventory().addItem(h);
						p.getInventory().addItem(c);
						p.getInventory().addItem(l);
						p.getInventory().addItem(b);
						
						// RECEIVE MESSAGE
						p.sendMessage("§4Armor aangemaakt!");
						
					} else if(args[0].equalsIgnoreCase("HoofdInspecteur"))  {
						
						// ITEMS
						
						ItemStack h = new ItemStack(Material.IRON_HELMET,1);
						ItemStack c = new ItemStack(Material.IRON_CHESTPLATE,1);
						ItemStack l = new ItemStack(Material.IRON_LEGGINGS,1);
						ItemStack b = new ItemStack(Material.DIAMOND_BOOTS,1);
						
						// ITEMMETA
						ItemMeta hm = h.getItemMeta();
						ItemMeta cm = c.getItemMeta();
						ItemMeta lm = l.getItemMeta();
						ItemMeta bm = b.getItemMeta();
						
						// DISPLAYNAMES
						hm.setDisplayName("§bPolitie Armor");
						cm.setDisplayName("§bPolitie Armor");
						lm.setDisplayName("§bPolitie Armor");
						bm.setDisplayName("§bPolitie Armor");						
						
						// ARRAYLIST LORES
						ArrayList<String> lore = new ArrayList<String>();
						
						// LORE
						lore.add(ChatColor.translateAlternateColorCodes('&', LoreHoofdInspecteur));
						
						// LORES TO ITEM
						hm.setLore(lore);
						cm.setLore(lore);
						lm.setLore(lore);
						bm.setLore(lore);
						
						// UNBREAKABLE
						hm.setUnbreakable(true);
						cm.setUnbreakable(true);
						lm.setUnbreakable(true);
						bm.setUnbreakable(true);

						// ADD ENCHANTMENTS
						hm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						cm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						lm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);

						// SET ITEM META
						h.setItemMeta(hm);
						c.setItemMeta(cm);
						l.setItemMeta(lm);
						b.setItemMeta(bm);
						
						// ITEM IN INVENTORY
						p.getInventory().addItem(h);
						p.getInventory().addItem(c);
						p.getInventory().addItem(l);
						p.getInventory().addItem(b);
						
						// RECEIVE MESSAGE
						p.sendMessage("§4Armor aangemaakt!");
						
					} else if(args[0].equalsIgnoreCase("Commisaris"))  {
						
						// ITEMS
						
						ItemStack h = new ItemStack(Material.IRON_HELMET,1);
						ItemStack c = new ItemStack(Material.IRON_CHESTPLATE,1);
						ItemStack l = new ItemStack(Material.IRON_LEGGINGS,1);
						ItemStack b = new ItemStack(Material.DIAMOND_BOOTS,1);
						
						// ITEMMETA
						ItemMeta hm = h.getItemMeta();
						ItemMeta cm = c.getItemMeta();
						ItemMeta lm = l.getItemMeta();
						ItemMeta bm = b.getItemMeta();
						
						// DISPLAYNAMES
						hm.setDisplayName("§bPolitie Armor");
						cm.setDisplayName("§bPolitie Armor");
						lm.setDisplayName("§bPolitie Armor");
						bm.setDisplayName("§bPolitie Armor");						
						
						// ARRAYLIST LORES
						ArrayList<String> lore = new ArrayList<String>();
						
						// LORE
						lore.add(ChatColor.translateAlternateColorCodes('&', LoreCommisaris));
						
						// LORES TO ITEM
						hm.setLore(lore);
						cm.setLore(lore);
						lm.setLore(lore);
						bm.setLore(lore);
						
						// UNBREAKABLE
						hm.setUnbreakable(true);
						cm.setUnbreakable(true);
						lm.setUnbreakable(true);
						bm.setUnbreakable(true);

						// ADD ENCHANTMENTS
						hm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						cm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						lm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);
						bm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 7, true);

						// SET ITEM META
						h.setItemMeta(hm);
						c.setItemMeta(cm);
						l.setItemMeta(lm);
						b.setItemMeta(bm);
						
						// ITEM IN INVENTORY
						p.getInventory().addItem(h);
						p.getInventory().addItem(c);
						p.getInventory().addItem(l);
						p.getInventory().addItem(b);
						
						// RECEIVE MESSAGE
						p.sendMessage("§4Armor aangemaakt!");
						
					} else {
						p.sendMessage(ChatColor.RED + "[PolitieArmor]" + ChatColor.GRAY + " Type \"/politiearmor help\" voor de commando's");				
					}
				} else {
					p.sendMessage(ChatColor.RED + "[PolitieArmor]" + ChatColor.GRAY + " Je hebt geen toegang tot dit commando!");
				}
			}
		} else {
			p.sendMessage(ChatColor.RED + "[PolitieArmor]" + ChatColor.GRAY + " Je hebt geen toegang tot dit commando!");
		}	
	  }	
		return true;
	}
}
