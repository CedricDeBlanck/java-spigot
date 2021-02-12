package me.snakebite.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu {
	
	public static void jobSelection(Player player) {
		
		/*
		 * 
		 * SLAGER
		 * BAKKER
		 * MIJNER
		 * HOUTHAKKER
		 * SMID
		 * BOER
		 * WETENSCHAPPER
		 * 
		 * */
		
		Player p = (Player)player;
		
		Inventory jobSelector = Bukkit.createInventory(player, 45, "§0Selecteer je job");
		
		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.setDisplayName("§0");
		empty.setItemMeta(emptyMeta);
		
		ItemStack slager = new ItemStack(Material.RAW_CHICKEN, 1);
		ItemMeta slagerMeta = slager.getItemMeta();
		slagerMeta.setDisplayName("§6Slager");
		slager.setItemMeta(slagerMeta);
		
		ItemStack bakker = new ItemStack(Material.CAKE, 1);
		ItemMeta bakkerMeta = bakker.getItemMeta();
		bakkerMeta.setDisplayName("§aBakker");
		bakker.setItemMeta(bakkerMeta);
		
		ItemStack mijner = new ItemStack(Material.GOLD_PICKAXE, 1);
		ItemMeta mijnerMeta = mijner.getItemMeta();
		mijnerMeta.setDisplayName("§3Mijner");
		mijner.setItemMeta(mijnerMeta);
		
		ItemStack houthakker = new ItemStack(Material.WOOD_AXE, 1);
		ItemMeta houthakkerMeta = houthakker.getItemMeta();
		houthakkerMeta.setDisplayName("§bHouthakker");
		houthakker.setItemMeta(houthakkerMeta);
		
		ItemStack smid = new ItemStack(Material.ANVIL, 1);
		ItemMeta smidMeta = smid.getItemMeta();
		smidMeta.setDisplayName("§7Smid");
		smid.setItemMeta(smidMeta);
		
		ItemStack boer = new ItemStack(Material.STONE_HOE, 1);
		ItemMeta boerMeta = boer.getItemMeta();
		boerMeta.setDisplayName("§2Boer");
		boer.setItemMeta(boerMeta);
		
//		ItemStack wetenschapper = new ItemStack(Material.ENDER_PEARL, 1);
//		ItemMeta wetenschapperMeta = wetenschapper.getItemMeta();
//		wetenschapperMeta.setDisplayName("§dWetenschapper");
//		wetenschapper.setItemMeta(wetenschapperMeta);
		
		ItemStack solliciteren = new ItemStack(Material.BOOK, 1);
		ItemMeta solliciterenMeta = solliciteren.getItemMeta();
		solliciterenMeta.setDisplayName("§9Solliciteren");
		solliciteren.setItemMeta(solliciterenMeta);
		
		ItemStack imker = new ItemStack(Material.MAGMA, 1);
		ItemMeta imkerMeta = imker.getItemMeta();
		imkerMeta.setDisplayName("§eImker");
		imker.setItemMeta(imkerMeta);
		
		jobSelector.setItem(0, empty);
		jobSelector.setItem(1, empty);
		jobSelector.setItem(2, empty);
		jobSelector.setItem(3, empty);
		jobSelector.setItem(4, empty);
		jobSelector.setItem(5, empty);
		jobSelector.setItem(6, empty);
		jobSelector.setItem(7, empty);
		jobSelector.setItem(8, empty);

		jobSelector.setItem(9, empty);
		jobSelector.setItem(10, slager);
		jobSelector.setItem(11, bakker);
		jobSelector.setItem(12, mijner);
		jobSelector.setItem(13, houthakker);
		jobSelector.setItem(14, smid);
		jobSelector.setItem(15, boer);
		jobSelector.setItem(16, imker);
		jobSelector.setItem(17, empty);
		
		jobSelector.setItem(18, empty);
		jobSelector.setItem(19, empty);
		jobSelector.setItem(20, empty);
		jobSelector.setItem(21, empty);
		jobSelector.setItem(22, solliciteren);
		jobSelector.setItem(23, empty);
		jobSelector.setItem(24, empty);
		jobSelector.setItem(25, empty);
		jobSelector.setItem(26, empty);
		jobSelector.setItem(27, empty);
		jobSelector.setItem(28, empty);
		jobSelector.setItem(29, empty);
		jobSelector.setItem(30, empty);
		jobSelector.setItem(31, empty);
		jobSelector.setItem(32, empty);
		jobSelector.setItem(33, empty);
		jobSelector.setItem(34, empty);
		jobSelector.setItem(35, empty);
		jobSelector.setItem(36, empty);
		jobSelector.setItem(37, empty);
		jobSelector.setItem(38, empty);
		jobSelector.setItem(39, empty);
		jobSelector.setItem(40, empty);
		jobSelector.setItem(41, empty);
		jobSelector.setItem(42, empty);
		jobSelector.setItem(43, empty);
		jobSelector.setItem(44, empty);

		p.openInventory(jobSelector);
		
	}

}
