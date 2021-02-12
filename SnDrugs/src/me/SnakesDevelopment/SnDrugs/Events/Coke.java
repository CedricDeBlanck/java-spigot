package me.SnakesDevelopment.SnDrugs.Events;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import me.SnakesDevelopment.SnDrugs.Config.Config;
import me.SnakesDevelopment.SnDrugs.Main.Main;
import net.md_5.bungee.api.ChatColor;

public class Coke implements Listener {
	
	static Config config = new Config();
	public static ArrayList<Player> entered = new ArrayList<>();
	public static ArrayList<Player> left = new ArrayList<>();
	public Plugin plugin;
	
	/*
	 * GATHER
	 */

	public static void onRegionEnterCoke(Player player) {
		LocalPlayer localPlayer = Main.worldGuardPlugin.wrapPlayer(player);
		Vector playerVector = localPlayer.getPosition();
		RegionManager regionManager = Main.worldGuardPlugin.getRegionManager(player.getWorld());
		ApplicableRegionSet applicableregionset = regionManager.getApplicableRegions(playerVector);
		
		for(ProtectedRegion regions: applicableregionset.getRegions()) {
			if(regions.getId().contains(config.getCokeGatherRegion().toLowerCase())) {
					if(!entered.contains(player)) {
							left.remove(player);
							entered.add(player);
							
							ItemStack coke = new ItemStack(Material.getMaterial(config.getCokeSeedItem()), 1, Short.valueOf(config.getCokeSeedItemData()));
							ItemMeta cmeta = coke.getItemMeta();
							cmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getCokeSeedItemName()));
							ArrayList<String> lore = new ArrayList<String>();
							lore.add(ChatColor.translateAlternateColorCodes('&', config.getCokeSeedLore()));
							cmeta.setLore(lore);
							coke.setItemMeta(cmeta);
							
							if(player.getInventory().containsAtLeast(coke, 1)) {
								
								ItemStack coke1 = new ItemStack(Material.getMaterial(config.getCokeItem()), 1, Short.valueOf(config.getCokeItemData()));
								ItemMeta cmeta1 = coke1.getItemMeta();
								cmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getCokeItemName()));
								ArrayList<String> clore = new ArrayList<String>();
								clore.add(ChatColor.translateAlternateColorCodes('&', config.getCokeItemLore()));
								cmeta1.setLore(clore);
								coke1.setItemMeta(cmeta1);
								player.getPlayer().getInventory().addItem(coke1);
								player.getPlayer().getInventory().removeItem(coke);
								player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
								ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getGatheringCokeMessage()));	
								//onRegionEnterCoke(player);
							} else {
								ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getNoCokeSeeds()));	
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
	
	public static void onRegionEnterCokeVerwerk(Player player) {
		LocalPlayer localPlayer = Main.worldGuardPlugin.wrapPlayer(player);
		Vector playerVector = localPlayer.getPosition();
		RegionManager regionManager = Main.worldGuardPlugin.getRegionManager(player.getWorld());
		ApplicableRegionSet applicableregionset = regionManager.getApplicableRegions(playerVector);
		
		for(ProtectedRegion regions: applicableregionset.getRegions()) {
			if(regions.getId().contains(config.getCokeProcessRegion().toLowerCase())) {
					if(!entered.contains(player)) {
							left.remove(player);
							entered.add(player);
							
							ItemStack coke = new ItemStack(Material.getMaterial(config.getCokeItem()), 1, Short.valueOf(config.getCokeItemData()));
							ItemMeta cmeta = coke.getItemMeta();
							cmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getCokeItemName()));
							ArrayList<String> ccLore = new ArrayList<String>();
							ccLore.add(ChatColor.translateAlternateColorCodes('&', config.getCokeItemLore()));
							cmeta.setLore(ccLore);
							coke.setItemMeta(cmeta);
							
						if(player.getInventory().containsAtLeast(coke, 1)) {
							
							
							player.getPlayer().getInventory().removeItem(coke);
							
							ItemStack coke1 = new ItemStack(Material.getMaterial(config.getCokeProcessedItem1()), 1, Short.valueOf(config.getCokeProcessedItemData()));
							ItemMeta cmeta1 = coke1.getItemMeta();
							cmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getCokeProcessedItem()));
							ArrayList<String> cokeLore = new ArrayList<String>();
							cokeLore.add(ChatColor.translateAlternateColorCodes('&', config.getCokeProcessedLore()));
							cmeta1.setLore(cokeLore);
							coke1.setItemMeta(cmeta1);
							player.getPlayer().getInventory().addItem(coke1);
							player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
							ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getProcessingCokeMessage()));	
							//onRegionEnterCokeVerwerk(player);
						} else {
							ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getNoCoke()));	
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
	
	public static void onRegionEnterCokeVerkoop(Player player) {
		LocalPlayer localPlayer = Main.worldGuardPlugin.wrapPlayer(player);
		Vector playerVector = localPlayer.getPosition();
		RegionManager regionManager = Main.worldGuardPlugin.getRegionManager(player.getWorld());
		ApplicableRegionSet applicableregionset = regionManager.getApplicableRegions(playerVector);
		
		for(ProtectedRegion regions: applicableregionset.getRegions()) {
			if(regions.getId().contains(config.getCokeSellRegion().toLowerCase())) {
					if(!entered.contains(player)) {
							left.remove(player);
							entered.add(player);
							
							ItemStack coke = new ItemStack(Material.getMaterial(config.getCokeProcessedItem1()), 1, Short.valueOf(config.getCokeProcessedItemData()));
							ItemMeta cmeta = coke.getItemMeta();
							cmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getCokeProcessedItem()));
							ArrayList<String> cLore = new ArrayList<String>();
							cLore.add(ChatColor.translateAlternateColorCodes('&', config.getCokeProcessedLore()));
							cmeta.setLore(cLore);
							coke.setItemMeta(cmeta);
							
						if(player.getInventory().containsAtLeast(coke, 1)) {
							
							
							player.getPlayer().getInventory().removeItem(coke);
							
							Main.zwartGeldItem(player);
							Main.zwartGeldItem(player);
							player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
							ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getSellingCokeMessage()));	
							//onRegionEnterCokeVerkoop(player);
							
						} else {
							ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getNoProcessedCoke()));	
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
