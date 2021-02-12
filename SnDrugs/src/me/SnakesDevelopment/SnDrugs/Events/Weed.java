package me.SnakesDevelopment.SnDrugs.Events;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import me.SnakesDevelopment.SnDrugs.Config.Config;
import me.SnakesDevelopment.SnDrugs.Main.Main;
import net.md_5.bungee.api.ChatColor;

public class Weed implements Listener {
	
	static Config config = new Config();
	public static ArrayList<Player> entered = new ArrayList<>();
	public static ArrayList<Player> left = new ArrayList<>();
	
	/*
	 * GATHER
	 */
	
	public static void onRegionEnterWiet(Player player) {
		
		LocalPlayer localPlayer = Main.worldGuardPlugin.wrapPlayer(player);
		Vector playerVector = localPlayer.getPosition();
		RegionManager regionManager = Main.worldGuardPlugin.getRegionManager(player.getWorld());
		ApplicableRegionSet applicableregionset = regionManager.getApplicableRegions(playerVector);
		for(ProtectedRegion regions: applicableregionset.getRegions()) {
			if(regions.getId().contains(config.getWeedGatherRegion().toLowerCase())) {
					if(!entered.contains(player)) {
							left.remove(player);
							entered.add(player);
							
							ItemStack wiet = new ItemStack(Material.getMaterial(config.getWeedSeedItem()) , 1, Short.valueOf(config.getWeedSeedDataItem()));
							ItemMeta wmeta = wiet.getItemMeta();
							wmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getWeedSeedItemName()));
							ArrayList<String> lore = new ArrayList<String>();
							lore.add(ChatColor.translateAlternateColorCodes('&', config.getWeedSeedLore()));
							wmeta.setLore(lore);
							wiet.setItemMeta(wmeta);
							
							
							if(player.getInventory().containsAtLeast(wiet, 1)) {
									ItemStack wiet1 = new ItemStack(Material.getMaterial(config.getWeedItem()) , 1, Short.valueOf(config.getWeedItemData()));
									ItemMeta wmeta1 = wiet1.getItemMeta();
									wmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getWeedItemName()));
									ArrayList<String> lore1 = new ArrayList<String>();
									lore1.add(ChatColor.translateAlternateColorCodes('&', config.getWeedItemLore()));
									wmeta1.setLore(lore1);
									wiet1.setItemMeta(wmeta1);
								if(Main.isInventoryFull(player)) {
									
									ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getFullInventory()));
									
								} else {
									player.getPlayer().getInventory().addItem(wiet1);
									player.getPlayer().getInventory().removeItem(wiet);
									player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
									//onRegionEnterWietVerwerk(player);
									ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getGatheringWeedMessage()));
								}
					
							} else {
								ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getNoWeedSeeds()));	
							}
						entered.remove(player);
					}
				} else {
					if(entered.contains(player)) {
						entered.remove(player);
						
					}
				}
				if (!left.contains(player)) {
						if(applicableregionset.size() == 0) {
							entered.add(player);
							left.remove(player);
							player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
						}
					}
				}
		}
	
	/*
	 * PROCESS
	 */
	
	public static void onRegionEnterWietVerwerk(Player player) {
		LocalPlayer localPlayer = Main.worldGuardPlugin.wrapPlayer(player);
		Vector playerVector = localPlayer.getPosition();
		RegionManager regionManager =  Main.worldGuardPlugin.getRegionManager(player.getWorld());
		ApplicableRegionSet applicableregionset = regionManager.getApplicableRegions(playerVector);
		
		for(ProtectedRegion regions: applicableregionset.getRegions()) {
			if(regions.getId().contains(config.getWeedProcessRegion().toLowerCase())) {
					if(!entered.contains(player)) {
							left.remove(player);
							entered.add(player);
							
							ItemStack wiet = new ItemStack(Material.getMaterial(config.getWeedItem()), 1, Short.valueOf(config.getWeedItemData()));
							ItemMeta wmeta = wiet.getItemMeta();
							wmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getWeedItemName()));
							ArrayList<String> wLore = new ArrayList<String>();
							wLore.add(ChatColor.translateAlternateColorCodes('&', config.getWeedItemLore()));
							wmeta.setLore(wLore);
							wiet.setItemMeta(wmeta);
							
						if(player.getInventory().containsAtLeast(wiet, 1)) {
							
							
							player.getPlayer().getInventory().removeItem(wiet);
							
							ItemStack wiet1 = new ItemStack(Material.getMaterial(config.getWeedProcessedItem()), 1, Short.valueOf(config.getWeedProcessedItemData()));
							ItemMeta wmeta1 = wiet1.getItemMeta();
							wmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getWeedProcessedItemName()));
							ArrayList<String> wwLore = new ArrayList<String>();
							wwLore.add(ChatColor.translateAlternateColorCodes('&', config.getWeedProcessedLore()));
							wmeta1.setLore(wwLore);
							wiet1.setItemMeta(wmeta1);
							player.getPlayer().getInventory().addItem(wiet1);
							player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
							ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getProcessingWeedMessage()));	
							//onRegionEnterWietVerwerk(player);
							
						} else {
							ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getNoWeed()));	
						}
						entered.remove(player);
					}
				} else {
					if(entered.contains(player)) {
						entered.remove(player);
						
					}
				}
			
			if (!left.contains(player)) {
					if(applicableregionset.size() == 0) {
						entered.add(player);
						left.remove(player);
						player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
					}
				}
			}
		
	}
	
	/*
	 * SELLING
	 */
	
	public static void onRegionEnterWietVerkoop(Player player) {
		LocalPlayer localPlayer = Main.worldGuardPlugin.wrapPlayer(player);
		Vector playerVector = localPlayer.getPosition();
		RegionManager regionManager = Main.worldGuardPlugin.getRegionManager(player.getWorld());
		ApplicableRegionSet applicableregionset = regionManager.getApplicableRegions(playerVector);
		
		for(ProtectedRegion regions: applicableregionset.getRegions()) {
			if(regions.getId().contains(config.getWeedSellRegion().toLowerCase())) {
				if(!entered.contains(player)) {
					left.remove(player);
					entered.add(player);
					
					ItemStack wiet = new ItemStack(Material.getMaterial(config.getWeedProcessedItem()), 1, Short.valueOf(config.getWeedProcessedItemData()));
					ItemMeta wmeta = wiet.getItemMeta();
					wmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getWeedProcessedItemName()));
					ArrayList<String> wLore = new ArrayList<String>();
					wLore.add(ChatColor.translateAlternateColorCodes('&', config.getWeedProcessedLore()));
					wmeta.setLore(wLore);
					wiet.setItemMeta(wmeta);
					
					if(player.getInventory().containsAtLeast(wiet, 1)) {
						
						player.getPlayer().getInventory().removeItem(wiet);
						player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
						Main.zwartGeldItem(player);
						ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getSellingWeedMessage()));	
						//onRegionEnterWietVerkoop(player);
					} else {
						ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getNoProcessedWeed()));	
	
					}
					entered.remove(player);	
				}
			} else {
				if(entered.contains(player)) {
					if(applicableregionset.size() == 0) {
						entered.add(player);
						left.remove(player);
					}
				}
			}
		}
	}

}
