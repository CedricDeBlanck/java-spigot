package me.SnakesDevelopment.SnDrugs.Main;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import me.SnakesDevelopment.SnDrugs.Config.Config;
import me.SnakesDevelopment.SnDrugs.Events.BlackMoney;
import me.SnakesDevelopment.SnDrugs.Events.Coke;
import me.SnakesDevelopment.SnDrugs.Events.Meth;
import me.SnakesDevelopment.SnDrugs.Events.Weed;
import me.SnakesDevelopment.SnDrugs.Mechanics.MenuClickEvent;
import me.SnakesDevelopment.SnDrugs.Commands.Items;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	public static WorldGuardPlugin worldGuardPlugin;
	public static WorldGuardPlugin worldGuardPlugin2;
	static Config config = new Config();
	public static Economy econ;
	public static final Logger log = Logger.getLogger("Minecraft");
	
	public static ArrayList<Player> entered = new ArrayList<>();
	public static ArrayList<Player> left = new ArrayList<>();
	
	public static Random numGen = new Random();

	@Override
	public void onEnable() {
		/*
		 *	Startup message
	    */
		getServer().getConsoleSender().sendMessage("                                       ");
		getServer().getConsoleSender().sendMessage("                 §aEnabled               ");
		getServer().getConsoleSender().sendMessage("              §3SnDrugs §av1.0         ");
		getServer().getConsoleSender().sendMessage("                    By                 ");
		getServer().getConsoleSender().sendMessage("            §3SnakesDevelopment        ");
		getServer().getConsoleSender().sendMessage("                                       ");
		
		/*
		 * Config shizzle 
		*/
		config.start(this);
		config.init(this);
		
		
		worldGuardPlugin = getWorldGuard();

		/*
		 * Commands
		*/
		getCommand("drugsmenu").setExecutor(new Items());

		/*
		 * Events
		 * 
		*/
		getServer().getPluginManager().registerEvents(new MenuClickEvent() ,this);

		
		/*
		 * PLUK TIMERS
		*/
		
		

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					Weed.onRegionEnterWiet(p);
					Coke.onRegionEnterCoke(p);
					Meth.onRegionEnterMeth(p);
				}
			}
		}, 0, config.getGatherTimer() * 20);
	
		
		/*
		 * VERWERK TIMERS 
		*/
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
						Weed.onRegionEnterWietVerwerk(p);
						Coke.onRegionEnterCokeVerwerk(p);
						Meth.onRegionEnterMethVerwerk(p);
						
				}
			}
		}, 0, config.getProcessTimer() * 20);
		
		/*
		 * 
		 * VERKOOP TIMERS
		 * 
		 * */
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					Weed.onRegionEnterWietVerkoop(p);
					Coke.onRegionEnterCokeVerkoop(p);
					Meth.onRegionEnterMethVerkoop(p);
					
				}
			}
		}, 0, config.getSellTimer() * 20);
		
		/*
		 * 
		 * WITWAS TIMER
		 * 
		 * */
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
						BlackMoney.onRegionEnterWitwas(p);
				}
			}
		}, 0, config.getLaundryTimer() * 20);
		
		
		/*
		 * Plugin Checks
		 */
		checkPlugins();
		
		
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("                                       ");
		getServer().getConsoleSender().sendMessage("                §cDisabled             ");
		getServer().getConsoleSender().sendMessage("              §3SnDrugs §av1.0         ");
		getServer().getConsoleSender().sendMessage("                    By                 ");
		getServer().getConsoleSender().sendMessage("            §3SnakesDevelopment        ");
		getServer().getConsoleSender().sendMessage("                                       ");
	}
	
	private void checkPlugins() {
		if (!setupEconomy() ) {
            log.severe(String.format("[SnDrugs] Disabled due to no Vault dependency found!", getDescription().getName()));
            log.severe(String.format("[SnDrugs] You need a Economy plugin like EssentialsX or BOSEconomy ", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		if (!setupWorldguard() ) {
			log.severe(String.format("[SnDrugs] Disabled due to no WorldGuard plugin found!", getDescription().getName()));
            log.severe(String.format("[SnDrugs] You need Worldguard to let this plugin work ", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
		}
	}
	
	 private boolean setupEconomy() {
	        if (getServer().getPluginManager().getPlugin("Vault") == null) {
	            return false;
	        }
	        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
	        if (rsp == null) {
	            return false;
	        }
	        econ = rsp.getProvider();
	        return econ != null;
	 }
	 
	 private boolean setupWorldguard() {
	        if (getServer().getPluginManager().getPlugin("WorldGuard") == null) {
	            return false;
	        } else {
	        	return true;
	        }
	 }
	 
	 public WorldGuardPlugin getWorldGuard() {
			Plugin plugin = this.getServer().getPluginManager().getPlugin("WorldGuard");
			
			if(plugin == null || !(plugin instanceof WorldGuardPlugin)) {
	            return null;
			}
			
			return (WorldGuardPlugin) plugin;
	 }
	 
	public static boolean isInventoryFull(Player p) {
		Player player = (Player) p;
		   return player.getInventory().firstEmpty() == -1;
	}
	
	public static void zwartGeldItem(Player player) {
		ItemStack zwartGeld = new ItemStack(Material.getMaterial(config.getBlackMoneyItemID()),1, Short.valueOf(config.getBlackMoneyItemData()));
		ItemMeta zmeta1 = zwartGeld.getItemMeta();
		zmeta1.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getBlackMoneyItemName()));
		ArrayList<String> zLore = new ArrayList<String>();
		zLore.add(ChatColor.translateAlternateColorCodes('&', config.getBlackMoneyItemLore()));
		zmeta1.setLore(zLore);
		zwartGeld.setItemMeta(zmeta1);
		player.getPlayer().getInventory().addItem(zwartGeld);
	}
}
