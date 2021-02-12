package me.SnakesDevelopment.SnDrugs.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.SnakesDevelopment.SnDrugs.Config.Config;

public class Items implements CommandExecutor {
	
	Config config = new Config();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		Player player = (Player) sender;
		
		if(player instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("Drugsmenu")) {
				if(player.hasPermission("SnDrugs.menu")) {
					
				
				
				ItemStack weed = new ItemStack(Material.getMaterial(config.getWeedSeedItem()) , 1, Short.valueOf(config.getWeedSeedDataItem()));
				ItemMeta weedMeta = weed.getItemMeta();
				weedMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getWeedSeedItemName()));
				ArrayList<String> Lore = new ArrayList<String>();
				Lore.add(ChatColor.translateAlternateColorCodes('&', config.getWeedSeedLore()));
				weedMeta.setLore(Lore);
				weed.setItemMeta(weedMeta);
				
				ItemStack wiet1 = new ItemStack(Material.getMaterial(config.getWeedItem()) , 1, Short.valueOf(config.getWeedItemData()));
				ItemMeta wmeta1 = wiet1.getItemMeta();
				wmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getWeedItemName()));
				ArrayList<String> lore1 = new ArrayList<String>();
				lore1.add(ChatColor.translateAlternateColorCodes('&', config.getWeedItemLore()));
				wmeta1.setLore(lore1);
				wiet1.setItemMeta(wmeta1);
				
				ItemStack wiet2 = new ItemStack(Material.getMaterial(config.getWeedProcessedItem()), 1, Short.valueOf(config.getWeedProcessedItemData()));
				ItemMeta wmeta2 = wiet2.getItemMeta();
				wmeta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getWeedProcessedItemName()));
				ArrayList<String> Lore2 = new ArrayList<String>();
				Lore2.add(ChatColor.translateAlternateColorCodes('&', config.getWeedProcessedLore()));
				wmeta2.setLore(Lore2);
				wiet2.setItemMeta(wmeta2);
				
				ItemStack coke = new ItemStack(Material.getMaterial(config.getCokeSeedItem()), 1, Short.valueOf(config.getCokeSeedItemData()));
				ItemMeta cmeta = coke.getItemMeta();
				cmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getCokeSeedItemName()));
				ArrayList<String> cccclore = new ArrayList<String>();
				cccclore.add(ChatColor.translateAlternateColorCodes('&', config.getCokeSeedLore()));
				cmeta.setLore(cccclore);
				coke.setItemMeta(cmeta);
				
				ItemStack coke1 = new ItemStack(Material.getMaterial(config.getCokeItem()), 1, Short.valueOf(config.getCokeItemData()));
				ItemMeta cmeta1 = coke1.getItemMeta();
				cmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getCokeItemName()));
				ArrayList<String> clore = new ArrayList<String>();
				clore.add(ChatColor.translateAlternateColorCodes('&', config.getCokeItemLore()));
				cmeta1.setLore(clore);
				coke1.setItemMeta(cmeta1);
				
				ItemStack coke2 = new ItemStack(Material.getMaterial(config.getCokeProcessedItem1()), 1, Short.valueOf(config.getCokeProcessedItemData()));
				ItemMeta cmeta2 = coke2.getItemMeta();
				cmeta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getCokeProcessedItem()));
				ArrayList<String> cccLore = new ArrayList<String>();
				cccLore.add(ChatColor.translateAlternateColorCodes('&', config.getCokeProcessedLore()));
				cmeta2.setLore(cccLore);
				coke2.setItemMeta(cmeta2);
				
				ItemStack meth = new ItemStack(Material.getMaterial(config.getMethSeedItemID()), 1, Short.valueOf(config.getMethSeedItemDataID()));
				ItemMeta mmeta = meth.getItemMeta();
				mmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getMethSeedItemName()));
				ArrayList<String> mmlore = new ArrayList<String>();
				mmlore.add(ChatColor.translateAlternateColorCodes('&', config.getMethSeedLore()));
				mmeta.setLore(mmlore);
				meth.setItemMeta(mmeta);
				
				ItemStack meth1 = new ItemStack(Material.getMaterial(config.getMethItemID()), 1, Short.valueOf(config.getMethItemData()));
				ItemMeta mmeta1 = meth1.getItemMeta();
				mmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getMethItemName()));
				ArrayList<String> mlore = new ArrayList<String>();
				mlore.add(ChatColor.translateAlternateColorCodes('&', config.getMethItemLore()));
				mmeta1.setLore(mlore);
				meth1.setItemMeta(mmeta1);
				
				ItemStack meth2 = new ItemStack(Material.getMaterial(config.getMethProcessedItemID()), 1, Short.valueOf(config.getMethProcessedItemData()));
				ItemMeta mmeta2 = meth2.getItemMeta();
				mmeta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getMethProcessedItem()));
				ArrayList<String> mmLore = new ArrayList<String>();
				mmLore.add(ChatColor.translateAlternateColorCodes('&', config.getMethProcessedLore()));
				mmeta2.setLore(mmLore);
				meth2.setItemMeta(mmeta2);
				
				ItemStack zwartGeld = new ItemStack(Material.getMaterial(config.getBlackMoneyItemID()),1, Short.valueOf(config.getBlackMoneyItemData()));
				ItemMeta zmeta1 = zwartGeld.getItemMeta();
				zmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getBlackMoneyItemName()));
				ArrayList<String> zLore = new ArrayList<String>();
				zLore.add(ChatColor.translateAlternateColorCodes('&', config.getBlackMoneyItemLore()));
				zmeta1.setLore(zLore);
				zwartGeld.setItemMeta(zmeta1);
				
				Inventory inv = Bukkit.createInventory(player, 27, "§2Drugsmenu");
				
				inv.setItem(0, weed);
				inv.setItem(1, wiet1);
				inv.setItem(2, wiet2);
				
				inv.setItem(6, coke);
				inv.setItem(7, coke1);
				inv.setItem(8, coke2);
				
				inv.setItem(18, meth);
				inv.setItem(19, meth1);
				inv.setItem(20, meth2);
				
				inv.setItem(26, zwartGeld);
				
				
				player.openInventory(inv);
				return true;
				} else {
					player.sendMessage("§cYou don't have permission to use this command!");
					return true;
				}
			}
		}
		
		
		return false;
	}

}
