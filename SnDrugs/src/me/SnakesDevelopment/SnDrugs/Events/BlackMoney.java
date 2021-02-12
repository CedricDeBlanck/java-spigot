package me.SnakesDevelopment.SnDrugs.Events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
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
import net.milkbowl.vault.economy.EconomyResponse;

public class BlackMoney {
	
	static Config config = new Config();
	public static ArrayList<Player> entered = new ArrayList<>();
	public static ArrayList<Player> left = new ArrayList<>();
	/*
	 * LAUNDERING
	 */
	
	public static void onRegionEnterWitwas(Player player) {
		LocalPlayer localPlayer = Main.worldGuardPlugin.wrapPlayer(player);
		Vector playerVector = localPlayer.getPosition();
		RegionManager regionManager = Main.worldGuardPlugin.getRegionManager(player.getWorld());
		ApplicableRegionSet applicableregionset = regionManager.getApplicableRegions(playerVector);
		
		for(ProtectedRegion regions: applicableregionset.getRegions()) {
			if(regions.getId().contains(config.getMoneyWashLocation().toLowerCase())) {
					if(!entered.contains(player)) {
							left.remove(player);
							entered.add(player);
							
							ItemStack zwartGeld = new ItemStack(Material.getMaterial(config.getBlackMoneyItemID()),1, Short.valueOf(config.getBlackMoneyItemData()));
							ItemMeta zmeta1 = zwartGeld.getItemMeta();
							zmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getBlackMoneyItemName()));
							ArrayList<String> zLore = new ArrayList<String>();
							zLore.add(ChatColor.translateAlternateColorCodes('&', config.getBlackMoneyItemLore()));
							zmeta1.setLore(zLore);
							zwartGeld.setItemMeta(zmeta1);
							
						if(player.getInventory().containsAtLeast(zwartGeld, 1)) {
							
							int rand = Config.getBlackMoneyAmount();
					    	String numberString = Integer.toString(rand);

							String mld = Bukkit.getServer().getPluginManager().getPlugin("SnDrugs").getConfig().getString("Wash_Laundry");
							String washedMoney = mld.replace("%washed_money%", numberString);
							ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', washedMoney));
							
							@SuppressWarnings("deprecation")
							EconomyResponse r = Main.econ.depositPlayer(player.getName(), rand);
							if (r.transactionSuccess()) {
								player.getPlayer().getInventory().removeItem(zwartGeld);
							} else {
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getTransactionFail()));
							}
							//onRegionEnterWitwas(player);
							
						} else {
							ActionBarAPI.sendActionBar(player.getPlayer(), ChatColor.translateAlternateColorCodes('&', config.getNoBlackMoney()));	
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
