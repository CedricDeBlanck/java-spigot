package me.SnakesDevelopment.SnDrugs.Mechanics;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.SnakesDevelopment.SnDrugs.Config.Config;


public class MenuClickEvent implements Listener {
	
		static Config config = new Config();
		
		public void weed1(Player p) {
			ItemStack weed = new ItemStack(Material.getMaterial(config.getWeedSeedItem()) , 1, Short.valueOf(config.getWeedSeedDataItem()));
			ItemMeta weedMeta = weed.getItemMeta();
			weedMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getWeedSeedItemName()));
			ArrayList<String> Lore = new ArrayList<String>();
			Lore.add(ChatColor.translateAlternateColorCodes('&', config.getWeedSeedLore()));
			weedMeta.setLore(Lore);
			weed.setItemMeta(weedMeta);
			p.getInventory().addItem(weed);
		}
		
		
		public void weed2(Player p) {
			ItemStack wiet1 = new ItemStack(Material.getMaterial(config.getWeedItem()) , 1, Short.valueOf(config.getWeedItemData()));
			ItemMeta wmeta1 = wiet1.getItemMeta();
			wmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getWeedItemName()));
			ArrayList<String> lore1 = new ArrayList<String>();
			lore1.add(ChatColor.translateAlternateColorCodes('&', config.getWeedItemLore()));
			wmeta1.setLore(lore1);
			wiet1.setItemMeta(wmeta1);
			p.getInventory().addItem(wiet1);
		}
		
		
		public void weed3(Player p) {
			ItemStack wiet2 = new ItemStack(Material.getMaterial(config.getWeedProcessedItem()), 1, Short.valueOf(config.getWeedProcessedItemData()));
			ItemMeta wmeta2 = wiet2.getItemMeta();
			wmeta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getWeedProcessedItemName()));
			ArrayList<String> Lore2 = new ArrayList<String>();
			Lore2.add(ChatColor.translateAlternateColorCodes('&', config.getWeedProcessedLore()));
			wmeta2.setLore(Lore2);
			wiet2.setItemMeta(wmeta2);
			p.getInventory().addItem(wiet2);
		}
		
		
		public void coke1(Player p) {
			ItemStack coke = new ItemStack(Material.getMaterial(config.getCokeSeedItem()), 1, Short.valueOf(config.getCokeSeedItemData()));
			ItemMeta cmeta = coke.getItemMeta();
			cmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getCokeSeedItemName()));
			ArrayList<String> cccclore = new ArrayList<String>();
			cccclore.add(ChatColor.translateAlternateColorCodes('&', config.getCokeSeedLore()));
			cmeta.setLore(cccclore);
			coke.setItemMeta(cmeta);
			p.getInventory().addItem(coke);
		}
		
		
		public void coke2(Player p) {
			ItemStack coke1 = new ItemStack(Material.getMaterial(config.getCokeItem()), 1, Short.valueOf(config.getCokeItemData()));
			ItemMeta cmeta1 = coke1.getItemMeta();
			cmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getCokeItemName()));
			ArrayList<String> clore = new ArrayList<String>();
			clore.add(ChatColor.translateAlternateColorCodes('&', config.getCokeItemLore()));
			cmeta1.setLore(clore);
			coke1.setItemMeta(cmeta1);
			p.getInventory().addItem(coke1);
		}
		
		
		public void coke3(Player p) {
			ItemStack coke2 = new ItemStack(Material.getMaterial(config.getCokeProcessedItem1()), 1, Short.valueOf(config.getCokeProcessedItemData()));
			ItemMeta cmeta2 = coke2.getItemMeta();
			cmeta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getCokeProcessedItem()));
			ArrayList<String> cccLore = new ArrayList<String>();
			cccLore.add(ChatColor.translateAlternateColorCodes('&', config.getCokeProcessedLore()));
			cmeta2.setLore(cccLore);
			coke2.setItemMeta(cmeta2);
			p.getInventory().addItem(coke2);
		}
		
		
		public void meth1(Player p) {
			ItemStack meth = new ItemStack(Material.getMaterial(config.getMethSeedItemID()), 1, Short.valueOf(config.getMethSeedItemDataID()));
			ItemMeta mmeta = meth.getItemMeta();
			mmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getMethSeedItemName()));
			ArrayList<String> mmlore = new ArrayList<String>();
			mmlore.add(ChatColor.translateAlternateColorCodes('&', config.getMethSeedLore()));
			mmeta.setLore(mmlore);
			meth.setItemMeta(mmeta);
			p.getInventory().addItem(meth);
		}
		
		
		public void meth2(Player p) {
			ItemStack meth1 = new ItemStack(Material.getMaterial(config.getMethItemID()), 1, Short.valueOf(config.getMethItemData()));
			ItemMeta mmeta1 = meth1.getItemMeta();
			mmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getMethItemName()));
			ArrayList<String> mlore = new ArrayList<String>();
			mlore.add(ChatColor.translateAlternateColorCodes('&', config.getMethItemLore()));
			mmeta1.setLore(mlore);
			meth1.setItemMeta(mmeta1);
			p.getInventory().addItem(meth1);
		}
		
		
		public void meth3(Player p) {
			ItemStack meth2 = new ItemStack(Material.getMaterial(config.getMethProcessedItemID()), 1, Short.valueOf(config.getMethProcessedItemData()));
			ItemMeta mmeta2 = meth2.getItemMeta();
			mmeta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getMethProcessedItem()));
			ArrayList<String> mmLore = new ArrayList<String>();
			mmLore.add(ChatColor.translateAlternateColorCodes('&', config.getMethProcessedLore()));
			mmeta2.setLore(mmLore);
			meth2.setItemMeta(mmeta2);
			p.getInventory().addItem(meth2);
		}
		
		
		public void blackmoney(Player p) {
			ItemStack zwartGeld = new ItemStack(Material.getMaterial(config.getBlackMoneyItemID()),1, Short.valueOf(config.getBlackMoneyItemData()));
			ItemMeta zmeta1 = zwartGeld.getItemMeta();
			zmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getBlackMoneyItemName()));
			ArrayList<String> zLore = new ArrayList<String>();
			zLore.add(ChatColor.translateAlternateColorCodes('&', config.getBlackMoneyItemLore()));
			zmeta1.setLore(zLore);
			zwartGeld.setItemMeta(zmeta1);
			p.getInventory().addItem(zwartGeld);
		}
		

		@EventHandler
		public void onMenuClickEvent(InventoryClickEvent e) {
			
				if(e.getCurrentItem() == null || e.getCurrentItem() == null) {			
				} else {	
					if(e.getClickedInventory().getTitle().equalsIgnoreCase("§2Drugsmenu")) {
						
						ItemStack current = e.getCurrentItem();
						if(current == null) return;
						
						if(e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) {				
							if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getWeedSeedItemName()))) {
								weed1((Player) e.getWhoClicked());
								e.setCancelled(true);
							} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getWeedItemName()))) {
								weed2((Player) e.getWhoClicked());
								e.setCancelled(true);
							} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getWeedProcessedItemName()))) {
								weed3((Player) e.getWhoClicked());
								e.setCancelled(true);
							} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getCokeSeedItemName()))) {
								coke1((Player) e.getWhoClicked());
								e.setCancelled(true);
							} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getCokeItemName()))) {
								coke2((Player) e.getWhoClicked());
								e.setCancelled(true);
							} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getCokeProcessedItem()))) {
								coke3((Player) e.getWhoClicked());
								e.setCancelled(true);
							} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getMethSeedItemName()))) {
								meth1((Player) e.getWhoClicked());
								e.setCancelled(true);
							} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getMethItemName()))) {
								meth2((Player) e.getWhoClicked());
								e.setCancelled(true);
							} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getMethProcessedItem()))) {
								meth3((Player) e.getWhoClicked());
								e.setCancelled(true);
							} else if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', config.getBlackMoneyItemName()))) {
								blackmoney((Player) e.getWhoClicked());
								e.setCancelled(true);
							} else {
								return;
							}
							e.setCancelled(true);
						} else {
							e.setCancelled(true);
							return;
						}
						e.setCancelled(true);
					}
				}
		}
}
