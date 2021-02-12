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

public class Meth implements Listener {
	
	static Config config = new Config();
	public static ArrayList<Player> entered = new ArrayList<>();
	public static ArrayList<Player> left = new ArrayList<>();
	
	/*
	 * GATHER
	 */
	
	public static void onRegionEnterMeth(Player player) {
		LocalPlayer localPlayer = Main.worldGuardPlugin.wrapPlayer(player);
		Vector playerVector = localPlayer.getPosition();
		RegionManager regionManager = Main.worldGuardPlugin.getRegionManager(player.getWorld());
		ApplicableRegionSet applicableregionset = regionManager.getApplicableRegions(playerVector);
		
		for(ProtectedRegion regions: applicableregionset.getRegions()) {
			if(regions.getId().contains(config.getMethGatherRegion().toLowerCase())) {
					if(!entered.contains(player)) {
							left.remove(player);
							entered.add(player);
							
							ItemStack meth = new ItemStack(Material.getMaterial(config.getMethSeedItemID()), 1, Short.valueOf(config.getMethSeedItemDataID()));
							ItemMeta mmeta = meth.getItemMeta();
							mmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getMethSeedItemName()));
							ArrayList<String> lore = new ArrayList<String>();
							lore.add(ChatColor.translateAlternateColorCodes('&', config.getMethSeedLore()));
							mmeta.setLore(lore);
							meth.setItemMeta(mmeta);
							
							if (player.getInventory().containsAtLeast(meth, 1)) {
								
								ItemStack meth1 = new ItemStack(Material.getMaterial(config.getMethItemID()), 1, Short.valueOf(config.getMethItemData()));
								ItemMeta mmeta1 = meth1.getItemMeta();
								mmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getMethItemName()));
								ArrayList<String> mlore = new ArrayList<String>();
								mlore.add(ChatColor.translateAlternateColorCodes('&', config.getMethItemLore()));
								mmeta1.setLore(mlore);
								meth1.setItemMeta(mmeta1);
								player.getPlayer().getInventory().addItem(meth1);
								player.getPlayer().getInventory().removeItem(meth);
								player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
								ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getGatheringMethMessage()));	
								//onRegionEnterCoke(player);
							} else {
								ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getNoMethSeeds()));	
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
	 * PROCESSING
	 */

	public static void onRegionEnterMethVerwerk(Player player) {
		LocalPlayer localPlayer = Main.worldGuardPlugin.wrapPlayer(player);
		Vector playerVector = localPlayer.getPosition();
		RegionManager regionManager = Main.worldGuardPlugin.getRegionManager(player.getWorld());
		ApplicableRegionSet applicableregionset = regionManager.getApplicableRegions(playerVector);
		
		for(ProtectedRegion regions: applicableregionset.getRegions()) {
			if(regions.getId().contains(config.getMethProcessRegion().toLowerCase())) {
					if(!entered.contains(player)) {
							left.remove(player);
							entered.add(player);
							
							ItemStack meth = new ItemStack(Material.getMaterial(config.getMethItemID()), 1, Short.valueOf(config.getMethItemData()));
							ItemMeta mmeta = meth.getItemMeta();
							mmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getMethItemName()));
							ArrayList<String> mLore = new ArrayList<String>();
							mLore.add(ChatColor.translateAlternateColorCodes('&', config.getMethItemLore()));
							mmeta.setLore(mLore);
							meth.setItemMeta(mmeta);
							
						if(player.getInventory().containsAtLeast(meth, 1)) {
							
							
							player.getPlayer().getInventory().removeItem(meth);
							
							ItemStack meth1 = new ItemStack(Material.getMaterial(config.getMethProcessedItemID()), 1, Short.valueOf(config.getMethProcessedItemData()));
							ItemMeta mmeta1 = meth1.getItemMeta();
							mmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getMethProcessedItem()));
							ArrayList<String> mmLore = new ArrayList<String>();
							mmLore.add(ChatColor.translateAlternateColorCodes('&', config.getMethProcessedLore()));
							mmeta1.setLore(mmLore);
							meth1.setItemMeta(mmeta1);
							player.getPlayer().getInventory().addItem(meth1);
							player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
							ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getProcessingMethMessage()));	
							//onRegionEnterMethVerwerk(player);
							
						} else {
							ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getNoMeth()));	

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
	
	public static void onRegionEnterMethVerkoop(Player player) {
		LocalPlayer localPlayer = Main.worldGuardPlugin.wrapPlayer(player);
		Vector playerVector = localPlayer.getPosition();
		RegionManager regionManager = Main.worldGuardPlugin.getRegionManager(player.getWorld());
		ApplicableRegionSet applicableregionset = regionManager.getApplicableRegions(playerVector);
		
		for(ProtectedRegion regions: applicableregionset.getRegions()) {
			if(regions.getId().contains(config.getMethSellRegion().toLowerCase())) {
					if(!entered.contains(player)) {
							left.remove(player);
							entered.add(player);
							
							ItemStack meth = new ItemStack(Material.getMaterial(config.getMethProcessedItemID()), 1, Short.valueOf(config.getMethProcessedItemData()));
							ItemMeta mmeta = meth.getItemMeta();
							mmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getMethProcessedItem()));
							ArrayList<String> mLore = new ArrayList<String>();
							mLore.add(ChatColor.translateAlternateColorCodes('&', config.getMethProcessedLore()));
							mmeta.setLore(mLore);
							meth.setItemMeta(mmeta);
							
						if(player.getInventory().containsAtLeast(meth, 1)) {
							
							
							player.getPlayer().getInventory().removeItem(meth);
							
							Main.zwartGeldItem(player);
							Main.zwartGeldItem(player);
							Main.zwartGeldItem(player);
							player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
							ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getSellingMethMessage()));	
							//onRegionEnterMethVerkoop(player);
							
						} else {
							ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getNoProcessedMeth()));	
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
}
