package be.overcast.events;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import be.overcast.minetopia.Main;
import ch.dempsey.mt.api.MTPlayer;
import net.dv8tion.jda.core.entities.TextChannel;
import net.milkbowl.vault.economy.EconomyResponse;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;

public class Events implements Listener {
	
	Plugin plugin;
	
	public static ArrayList<Player> politieMelding = new ArrayList<Player>();
	public static ArrayList<Player> ambulanceMelding = new ArrayList<Player>();
	public static ArrayList<Player> brandweerMelding = new ArrayList<Player>();
	public static ArrayList<Player> anwbMelding = new ArrayList<Player>();
	public static ArrayList<Player> cooldown = new ArrayList<Player>();
	public static ArrayList<Player> betalenAanSpeler = new ArrayList<Player>();
	public static ArrayList<Player> betalendeSpeler = new ArrayList<Player>();

	 HashMap<String, Long> cooldowns = new HashMap<String, Long>(); //Player name, Time in milliseconds when the player ran cooldown starts

	static ItemStack[] content;
	
	@EventHandler
	public void antiDropItems(PlayerDropItemEvent e) {
		if(e.getItemDrop().getItemStack().getType() == Material.DIAMOND_AXE) {
			e.setCancelled(true);
		}
		
		if(e.getItemDrop().getItemStack().getType() == Material.NETHER_BRICK_ITEM) {
				e.setCancelled(true);
		}
			
	}
	
	@EventHandler
	public void onCocoaBean(BlockPlaceEvent e) {
		if(e.getBlock().getType().equals(Material.COCOA)) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cDit moet je opeten en niet plaatsen!");
		}

	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEntityEvent e) {
		if(e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NAME_TAG)) {
			if(e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("§6Politie Badge")) {
				if(e.getPlayer().hasPermission("politie.badge")) {
						if(e.getRightClicked() instanceof Player) {
							Player p = (Player)e.getRightClicked();
							if(p != null) {
								if(!p.hasPermission("politie.bypass")) {
									e.getPlayer().openInventory(p.getInventory());
								}else {
									e.getPlayer().sendMessage(ChatColor.RED + "Je kan deze persoon niet fouilleren ga maar verder met je dienst!");
								}
							}
						}else {
							e.getPlayer().sendMessage(ChatColor.RED + "Dit is geen speler!");
						}
					} else {
					e.getPlayer().sendMessage(ChatColor.RED + "Je hebt geen toestemming om deze badge te gebruiken!");
					e.getPlayer().sendMessage(ChatColor.RED + "112 is op de hoogte gebracht hiervan!");
					
					TextChannel tc = Main.jda.getTextChannelById("787007681692303400");
					tc.sendMessage("> :no_entry: **" + e.getPlayer().getName() + "** is in het bezit van een **Politie badge** zonder toestemming! :no_entry:").queue();
					
					for(Player pl : Bukkit.getOnlinePlayers()) {
						if(pl.hasPermission("politie.agent")) {		
							pl.sendMessage("§8(§b!§8) §4[NOODMELDING] §4112 §b>> §a§l"+ e.getPlayer().getName() + " §4is in het bezit van een badge zonder toestemming!");
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onFireworkDamage(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Firework) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event) {
	    Player player = event.getPlayer();
	    Action action = event.getAction();

	     if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
	         if(event.getItem() != null) {
	        	 if (event.getItem().getType() == Material.GOLD_HOE) {
	        		 if(event.getItem().getItemMeta().getDisplayName() != null) {
	        			 
	        		 
			        	 if(event.getItem().getItemMeta().getDisplayName().contains("§3Portofoon")) {
			        		 Player p = (Player) player;
			        		 
			        		 portofoonMenu(p);
			        		 
							 event.setCancelled(true);
			        	 } else if (event.getItem().getItemMeta().getDisplayName().contains("§aGroene telefoon")) {      		 
			        		 player.sendMessage("§aTelefoon ontgrendeld!");
			        		 event.setCancelled(true);
			        		 groenMenu(player);
			        		 playSound(player);
			        	 } else if (event.getItem().getItemMeta().getDisplayName().contains("§cRode telefoon")) {
			        		 player.sendMessage("§cTelefoon ontgrendeld!");
			        		 event.setCancelled(true);
			        		 roodMenu(player);
			        		 playSound(player);
			        	 } else if (event.getItem().getItemMeta().getDisplayName().contains("§dRoze telefoon")) {
			        		 player.sendMessage("§dTelefoon ontgrendeld!");
			        		 event.setCancelled(true);
			        		 rozeMenu(player);
			        		 playSound(player);
			        	 } else if (event.getItem().getItemMeta().getDisplayName().contains("§bBlauwe telefoon")) {
			        		 player.sendMessage("§bTelefoon ontgrendeld!");
			        		 event.setCancelled(true);
			        		 blauwMenu(player);
			        		 playSound(player);
			        	 } else if (event.getItem().getItemMeta().getDisplayName().contains("§eGele telefoon")) {
			        		 player.sendMessage("§eTelefoon ontgrendeld!");
			        		 event.setCancelled(true);
			        		 geelMenu(player);
			        		 playSound(player);
			        	 }
	        		 } else {
	        			 return;
	        		 }
		         } else if(event.getItem().getType() == Material.CARROT_STICK) {
		        	 if(event.getItem().getItemMeta().getDisplayName() != null) {
		        		 if(event.getItem().getItemMeta().getDisplayName().equals("§aGroene rugzak")) {
			    			 Bukkit.dispatchCommand(player, "customec open");
			    		 } else if(event.getItem().getItemMeta().getDisplayName().equals("§9Blauwe rugzak")) {
			    			 Bukkit.dispatchCommand(player, "customec open");
			    		 } else if(event.getItem().getItemMeta().getDisplayName().equals("§cRode rugzak")) {
			    			 Bukkit.dispatchCommand(player, "customec open");
			    		 } else if(event.getItem().getItemMeta().getDisplayName().equals("§dRoze rugzak")) {
			    			 Bukkit.dispatchCommand(player, "customec open");
			    		 } else if(event.getItem().getItemMeta().getDisplayName().equals("§eGele rugzak")) {
			    			 Bukkit.dispatchCommand(player, "customec open");
			    		 }
		        	 }
		         }
	         } 
	     } else if(action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
	    	 if(event.getItem() != null) {
	    		 int cooldownTime = 2;
	    		 if(event.getItem().getType() == Material.CARROT_STICK) {
	    			 if(event.getItem().getItemMeta().getDisplayName() != null) {
	    				 
			    		 if(event.getItem().getItemMeta().getDisplayName().equals("§aGroene rugzak")) {
			    			 if(cooldowns.containsKey(player.getName())) {
			    				 long secondsLeft = ((cooldowns.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
			    				 if(secondsLeft > 0) {
					    			 player.sendMessage("§cWacht " + secondsLeft + " seconde(n) vooraleer je je rugzak kan aandoen!");

			    				 } else {
			    					 cooldowns.put(player.getName(), System.currentTimeMillis());
				    				 Bukkit.dispatchCommand(player, "hat");
			    				 }
				    			 
			    			 } else {
			    				 cooldowns.put(player.getName(), System.currentTimeMillis());
			    				 Bukkit.dispatchCommand(player, "hat");
			    			 }
			    				 

			    		 } else if(event.getItem().getItemMeta().getDisplayName().equals("§9Blauwe rugzak")) {
			    			 if(cooldowns.containsKey(player.getName())) {
			    				 long secondsLeft = ((cooldowns.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
			    				 if(secondsLeft > 0) {
					    			 player.sendMessage("§cWacht " + secondsLeft + " seconde(n) vooraleer je je rugzak kan aandoen!");

			    				 } else {
			    					 cooldowns.put(player.getName(), System.currentTimeMillis());
				    				 Bukkit.dispatchCommand(player, "hat");
			    				 }
				    			 
			    			 } else {
			    				 cooldowns.put(player.getName(), System.currentTimeMillis());
			    				 Bukkit.dispatchCommand(player, "hat");
			    			 }
			    		 } else if(event.getItem().getItemMeta().getDisplayName().equals("§cRode rugzak")) {
			    			 if(cooldowns.containsKey(player.getName())) {
			    				 long secondsLeft = ((cooldowns.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
			    				 if(secondsLeft > 0) {
					    			 player.sendMessage("§cWacht " + secondsLeft + " seconde(n) vooraleer je je rugzak kan aandoen!");

			    				 } else {
			    					 cooldowns.put(player.getName(), System.currentTimeMillis());
				    				 Bukkit.dispatchCommand(player, "hat");
			    				 }
				    			 
			    			 } else {
			    				 cooldowns.put(player.getName(), System.currentTimeMillis());
			    				 Bukkit.dispatchCommand(player, "hat");
			    			 }
			    		 } else if(event.getItem().getItemMeta().getDisplayName().equals("§dRoze rugzak")) {
			    			 if(cooldowns.containsKey(player.getName())) {
			    				 long secondsLeft = ((cooldowns.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
			    				 if(secondsLeft > 0) {
					    			 player.sendMessage("§cWacht " + secondsLeft + " seconde(n) vooraleer je je rugzak kan aandoen!");

			    				 } else {
			    					 cooldowns.put(player.getName(), System.currentTimeMillis());
				    				 Bukkit.dispatchCommand(player, "hat");
				    				 
			    				 };
				    			 
			    			 } else {
			    				 cooldowns.put(player.getName(), System.currentTimeMillis());
			    				 Bukkit.dispatchCommand(player, "hat");
			    			 }
			    		 } else if(event.getItem().getItemMeta().getDisplayName().equals("§eGele rugzak")) {
			    			 if(cooldowns.containsKey(player.getName())) {
			    				 long secondsLeft = ((cooldowns.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
			    				 if(secondsLeft > 0) {
					    			 player.sendMessage("§cWacht " + secondsLeft + " seconde(n) vooraleer je je rugzak kan aandoen!");

			    				 } else {
			    					 cooldowns.put(player.getName(), System.currentTimeMillis());
				    				 Bukkit.dispatchCommand(player, "hat");
			    				 }
				    			 
			    			 } else {
			    				 cooldowns.put(player.getName(), System.currentTimeMillis());
			    				 Bukkit.dispatchCommand(player, "hat");
			    			 }
			    		 }
	    			 } else {
	    				 return;
	    			 }
	    		 }
	    	 }
	     }
	}


	
	/*
	 * 
	 * PORTOFOON
	 * 
	 */
	
	public static void portofoonMenu(Player p) {
		 Inventory pmenu = Bukkit.createInventory(p, 27, "§3Portofoon");

		 ItemStack noodknop = new ItemStack(Material.WOOL, 1, (short)14);
		 ItemMeta noodknopMeta = noodknop.getItemMeta();
		 noodknopMeta.setDisplayName("§cNoodknop");
		 ArrayList<String> noodknopLore = new ArrayList<String>();
		 noodknopLore.add("§7Gebruik deze voor als je in nood bent!");
		 noodknopLore.add("§cAlleen beschikbaar voor politie!");
		 noodknopMeta.setLore(noodknopLore);
		 noodknop.setItemMeta(noodknopMeta);
		 
		 ItemStack radio = new ItemStack(Material.NAME_TAG, 1);
		 ItemMeta radioMeta = radio.getItemMeta();
		 radioMeta.setDisplayName("§aRadio");
		 ArrayList<String> radioLore = new ArrayList<String>();
		 radioLore.add("§3Selecteer de radio frequentie die je wilt joinen!");
		 noodknopMeta.setLore(radioLore);
		 radio.setItemMeta(radioMeta);
		 
		 pmenu.setItem(13, radio);
		 pmenu.setItem(16, noodknop);
		 
		 
		 
		 p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HARP, 1, 1);
		 p.openInventory(pmenu);

	}
	
	
	
    /*
     * 
     * 
     * TELEFOON
     * 
     */
	

	/*
	 * 
	 * SOUND
	 * 
	 */
	
	public void playSound(Player player) {
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
	}
    
	@SuppressWarnings("deprecation")
	public static void groenMenu(Player p) {		
		Inventory Telefoon = Bukkit.createInventory(p, 54, "§aGroene telefoon");
		
		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.setDisplayName("§0");
		empty.setItemMeta(emptyMeta);
		
		ItemStack rand = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
		ItemMeta randMeta = rand.getItemMeta();
		randMeta.setDisplayName("§0");
		rand.setItemMeta(randMeta);
		
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(p.getName());
		meta.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName());
        ArrayList<String> skullLore = new ArrayList<String>();
        skullLore.add(" ");
        skullLore.add("§bLevel: §7" + String.valueOf(new MTPlayer(p).getLevel()));
        skullLore.add("§bExp: §7" + String.valueOf(new MTPlayer(p).getExp()));
        
		meta.setLore(skullLore);
        skull.setItemMeta(meta);
        
        ItemStack boete = new ItemStack(Material.PAPER, 1);
        ItemMeta boeteMeta = boete.getItemMeta();
        boeteMeta.setDisplayName("§cBoetes");
        ArrayList<String> boeteLore = new ArrayList<String>();
        boeteLore.add("§7Klik hier om je boetes te bekijken");
        boeteMeta.setLore(boeteLore);
        boete.setItemMeta(boeteMeta);
        
    	double bal = Main.econ.getBalance(p.getName());
        
        ItemStack bankKaart = new ItemStack(Material.INK_SACK, 1, (short)10);
        ItemMeta bankkaartMeta = bankKaart.getItemMeta();
        bankkaartMeta.setDisplayName("§fBank applicatie");
        bankkaartMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        bankkaartMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> bankkaartLore = new ArrayList<String>();
        bankkaartLore.add("§aKlik hier om je bank app te openen!");
        bankkaartLore.add("§7Als je geen telefoon op zak hebt werkt dit niet!");
        bankkaartLore.add("§6Balans: §f" + bal + " §");
        bankkaartMeta.setLore(bankkaartLore);
        bankKaart.setItemMeta(bankkaartMeta);
        
        ItemStack hulpdiensten = new ItemStack(Material.DIAMOND_HOE, 1, (short)67);
        ItemMeta hulpdienstenMeta = hulpdiensten.getItemMeta();
        hulpdienstenMeta.setDisplayName("§9Hulpdiensten 112");
        ArrayList<String> hulpLore = new ArrayList<String>();
        hulpLore.add("§fKlik hier om de hulpdiensten te verwittigen!");
        hulpLore.add("§cContacteer de politie/ambulance of brandweer!");
        hulpLore.add("§4Alleen voor dringende zaken!");
        hulpdienstenMeta.setLore(hulpLore);
        hulpdienstenMeta.setUnbreakable(true);
        hulpdiensten.setItemMeta(hulpdienstenMeta);
        
        ItemStack strafblad = new ItemStack(Material.BOOK, 1);
        ItemMeta strafbladMeta = strafblad.getItemMeta();
        strafbladMeta.setDisplayName("§bStrafblad");
        ArrayList<String> strafbladLore = new ArrayList<String>();
        strafbladLore.add("§fBinnenkort...!");
        strafbladMeta.setLore(strafbladLore);
        strafbladMeta.setUnbreakable(true);
        strafblad.setItemMeta(strafbladMeta);
        
        ItemStack discord = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short)96);
        ItemMeta dMeta = discord.getItemMeta();
        dMeta.setDisplayName("§9Discord app");
        ArrayList<String> dLore = new ArrayList<String>();
        dLore.add("§fKlik hier voor de discord link!");
        dMeta.setUnbreakable(true);
        dMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        discord.setItemMeta(dMeta);
        
        ItemStack vrienden = new ItemStack(Material.TOTEM, 1);
        ItemMeta vMeta = vrienden.getItemMeta();
        vMeta.setDisplayName("§aVrienden");
        ArrayList<String> vLore = new ArrayList<String>();
        vLore.add("§7Klik hier om je vrienden te beheren!");
        vMeta.setLore(vLore);
        vrienden.setItemMeta(vMeta);
        
        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§cSluit je telefoon");
        close.setItemMeta(closeMeta);
        
        ItemStack politie = new ItemStack(Material.WOOL, 1, (short)11);
        ItemMeta politieMeta = politie.getItemMeta();
        politieMeta.setDisplayName("§1Politie Menu");
        ArrayList<String> politieLore = new ArrayList<String>();
        politieLore.add("§7Commands voor politie");
        politieMeta.setLore(politieLore);
        politie.setItemMeta(politieMeta);
        
        ItemStack ambulance = new ItemStack(Material.WOOL, 1, (short)4);
        ItemMeta ambulanceMeta = ambulance.getItemMeta();
        ambulanceMeta.setDisplayName("§eAmbulance Menu");
        ArrayList<String> ambulanceLore = new ArrayList<String>();
        ambulanceLore.add("§7Commands voor Ambulance");
        ambulanceMeta.setLore(ambulanceLore);
        ambulance.setItemMeta(ambulanceMeta);
        
        ItemStack brandweer = new ItemStack(Material.WOOL, 1, (short)14);
        ItemMeta brandweerMeta = brandweer.getItemMeta();
        brandweerMeta.setDisplayName("§cBrandweer Menu");
        ArrayList<String> brandweerLore = new ArrayList<String>();
        brandweerLore.add("§7Commands voor Brandweer");
        brandweerMeta.setLore(brandweerLore);
        brandweer.setItemMeta(brandweerMeta);
        
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName("§cGPS");
        compass.setItemMeta(compassMeta);
        
        Telefoon.setItem(0, empty);
        Telefoon.setItem(1, empty);
        Telefoon.setItem(2, rand);
        Telefoon.setItem(3, rand);
        Telefoon.setItem(4, rand);
        Telefoon.setItem(5, rand);
        Telefoon.setItem(6, rand);
        Telefoon.setItem(7, empty);
        Telefoon.setItem(8, empty);
        Telefoon.setItem(9, empty);
        Telefoon.setItem(10, empty);
        Telefoon.setItem(11, rand);
        Telefoon.setItem(12, skull); // ITEM
        Telefoon.setItem(13, vrienden); // ITEM
        Telefoon.setItem(14, bankKaart); // ITEM
        Telefoon.setItem(15, rand);
        Telefoon.setItem(16, empty);
        Telefoon.setItem(17, empty);
        Telefoon.setItem(18, empty);
        Telefoon.setItem(19, empty);
        Telefoon.setItem(20, rand);
        Telefoon.setItem(21, strafblad); //ITEM
        Telefoon.setItem(22, hulpdiensten); //ITEM
        Telefoon.setItem(23, discord); //ITEM      
        Telefoon.setItem(24, rand);
        Telefoon.setItem(25, empty);
        Telefoon.setItem(26, empty);
        Telefoon.setItem(27, empty);
        Telefoon.setItem(28, empty);
        Telefoon.setItem(29, rand);
        Telefoon.setItem(30, boete); //ITEM
        Telefoon.setItem(31, compass); //ITEM
        Telefoon.setItem(32, empty); //ITEM
        Telefoon.setItem(33, rand);
        Telefoon.setItem(34, empty);
        Telefoon.setItem(35, empty);
        Telefoon.setItem(36, empty);
        Telefoon.setItem(37, empty);
        Telefoon.setItem(38, rand);
        Telefoon.setItem(39, empty); //ITEM
        Telefoon.setItem(40, empty); //ITEM
        Telefoon.setItem(41, empty); //ITEM
        Telefoon.setItem(42, rand);
        Telefoon.setItem(43, empty);
        Telefoon.setItem(44, empty);
        Telefoon.setItem(45, empty);
        Telefoon.setItem(46, empty);
        Telefoon.setItem(47, rand);
        Telefoon.setItem(48, rand);
        Telefoon.setItem(49, close); //ITEM
        Telefoon.setItem(50, rand);
        Telefoon.setItem(51, rand);
        Telefoon.setItem(52, empty);
        
        Telefoon.setItem(53, empty);
        

		p.openInventory(Telefoon);
	}
	
	@SuppressWarnings("deprecation")
	public void roodMenu(Player p) {
		
		Inventory Telefoon = Bukkit.createInventory(p, 54, "§cRode telefoon");

		
		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.setDisplayName("§0");
		empty.setItemMeta(emptyMeta);
		
		ItemStack rand = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
		ItemMeta randMeta = rand.getItemMeta();
		randMeta.setDisplayName("§0");
		rand.setItemMeta(randMeta);
		
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(p.getName());
		meta.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName());
        ArrayList<String> skullLore = new ArrayList<String>();
        skullLore.add(" ");
        skullLore.add("§bLevel: §7" + String.valueOf(new MTPlayer(p).getLevel()));
        skullLore.add("§bExp: §7" + String.valueOf(new MTPlayer(p).getExp()));
        
		meta.setLore(skullLore);
        skull.setItemMeta(meta);
        
        ItemStack boete = new ItemStack(Material.PAPER, 1);
        ItemMeta boeteMeta = boete.getItemMeta();
        boeteMeta.setDisplayName("§cBoetes");
        ArrayList<String> boeteLore = new ArrayList<String>();
        boeteLore.add("§7Klik hier om je boetes te bekijken");
        boeteMeta.setLore(boeteLore);
        boete.setItemMeta(boeteMeta);
        
    	double bal = Main.econ.getBalance(p.getName());
        
        ItemStack bankKaart = new ItemStack(Material.INK_SACK, 1, (short)10);
        ItemMeta bankkaartMeta = bankKaart.getItemMeta();
        bankkaartMeta.setDisplayName("§fBank applicatie");
        bankkaartMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        bankkaartMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> bankkaartLore = new ArrayList<String>();
        bankkaartLore.add("§aKlik hier om je bank app te openen!");
        bankkaartLore.add("§7Als je geen telefoon op zak hebt werkt dit niet!");
        bankkaartLore.add("§6Balans: §f" + bal + " §");
        bankkaartMeta.setLore(bankkaartLore);
        bankKaart.setItemMeta(bankkaartMeta);
        
        ItemStack hulpdiensten = new ItemStack(Material.DIAMOND_HOE, 1, (short)67);
        ItemMeta hulpdienstenMeta = hulpdiensten.getItemMeta();
        hulpdienstenMeta.setDisplayName("§9Hulpdiensten 112");
        ArrayList<String> hulpLore = new ArrayList<String>();
        hulpLore.add("§fKlik hier om de hulpdiensten te verwittigen!");
        hulpLore.add("§cContacteer de politie/ambulance of brandweer!");
        hulpLore.add("§4Alleen voor dringende zaken!");
        hulpdienstenMeta.setLore(hulpLore);
        hulpdienstenMeta.setUnbreakable(true);
        hulpdiensten.setItemMeta(hulpdienstenMeta);
        
        ItemStack strafblad = new ItemStack(Material.BOOK, 1);
        ItemMeta strafbladMeta = strafblad.getItemMeta();
        strafbladMeta.setDisplayName("§bStrafblad");
        ArrayList<String> strafbladLore = new ArrayList<String>();
        strafbladLore.add("§fBinnenkort...!");
        strafbladMeta.setLore(strafbladLore);
        strafbladMeta.setUnbreakable(true);
        strafblad.setItemMeta(strafbladMeta);
        
        ItemStack discord = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short)96);
        ItemMeta dMeta = discord.getItemMeta();
        dMeta.setDisplayName("§9Discord app");
        ArrayList<String> dLore = new ArrayList<String>();
        dLore.add("§fKlik hier voor de discord link!");
        dMeta.setUnbreakable(true);
        dMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        discord.setItemMeta(dMeta);
        
        ItemStack vrienden = new ItemStack(Material.TOTEM, 1);
        ItemMeta vMeta = vrienden.getItemMeta();
        vMeta.setDisplayName("§aVrienden");
        ArrayList<String> vLore = new ArrayList<String>();
        vLore.add("§7Klik hier om je vrienden te beheren!");
        vMeta.setLore(vLore);
        vrienden.setItemMeta(vMeta);
        
        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§cSluit je telefoon");
        close.setItemMeta(closeMeta);
        
        ItemStack politie = new ItemStack(Material.WOOL, 1, (short)11);
        ItemMeta politieMeta = politie.getItemMeta();
        politieMeta.setDisplayName("§1Politie Menu");
        ArrayList<String> politieLore = new ArrayList<String>();
        politieLore.add("§7Commands voor politie");
        politieMeta.setLore(politieLore);
        politie.setItemMeta(politieMeta);
        
        ItemStack ambulance = new ItemStack(Material.WOOL, 1, (short)4);
        ItemMeta ambulanceMeta = ambulance.getItemMeta();
        ambulanceMeta.setDisplayName("§eAmbulance Menu");
        ArrayList<String> ambulanceLore = new ArrayList<String>();
        ambulanceLore.add("§7Commands voor Ambulance");
        ambulanceMeta.setLore(ambulanceLore);
        ambulance.setItemMeta(ambulanceMeta);
        
        ItemStack brandweer = new ItemStack(Material.WOOL, 1, (short)14);
        ItemMeta brandweerMeta = brandweer.getItemMeta();
        brandweerMeta.setDisplayName("§cBrandweer Menu");
        ArrayList<String> brandweerLore = new ArrayList<String>();
        brandweerLore.add("§7Commands voor Brandweer");
        brandweerMeta.setLore(brandweerLore);
        brandweer.setItemMeta(brandweerMeta);
        
        
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName("§cGPS");
        compass.setItemMeta(compassMeta);
        
        Telefoon.setItem(0, empty);
        Telefoon.setItem(1, empty);
        Telefoon.setItem(2, rand);
        Telefoon.setItem(3, rand);
        Telefoon.setItem(4, rand);
        Telefoon.setItem(5, rand);
        Telefoon.setItem(6, rand);
        Telefoon.setItem(7, empty);
        Telefoon.setItem(8, empty);
        Telefoon.setItem(9, empty);
        Telefoon.setItem(10, empty);
        Telefoon.setItem(11, rand);
        Telefoon.setItem(12, skull); // ITEM
        Telefoon.setItem(13, vrienden); // ITEM
        Telefoon.setItem(14, bankKaart); // ITEM
        Telefoon.setItem(15, rand);
        Telefoon.setItem(16, empty);
        Telefoon.setItem(17, empty);
        Telefoon.setItem(18, empty);
        Telefoon.setItem(19, empty);
        Telefoon.setItem(20, rand);
        Telefoon.setItem(21, strafblad); //ITEM
        Telefoon.setItem(22, hulpdiensten); //ITEM
        Telefoon.setItem(23, discord); //ITEM      
        Telefoon.setItem(24, rand);
        Telefoon.setItem(25, empty);
        Telefoon.setItem(26, empty);
        Telefoon.setItem(27, empty);
        Telefoon.setItem(28, empty);
        Telefoon.setItem(29, rand);
        Telefoon.setItem(30, boete); //ITEM
        Telefoon.setItem(31, compass); //ITEM
        Telefoon.setItem(32, empty); //ITEM
        Telefoon.setItem(33, rand);
        Telefoon.setItem(34, empty);
        Telefoon.setItem(35, empty);
        Telefoon.setItem(36, empty);
        Telefoon.setItem(37, empty);
        Telefoon.setItem(38, rand);
        Telefoon.setItem(39, empty); //ITEM
        Telefoon.setItem(40, empty); //ITEM
        Telefoon.setItem(41, empty); //ITEM
        Telefoon.setItem(42, rand);
        Telefoon.setItem(43, empty);
        Telefoon.setItem(44, empty);
        Telefoon.setItem(45, empty);
        Telefoon.setItem(46, empty);
        Telefoon.setItem(47, rand);
        Telefoon.setItem(48, rand);
        Telefoon.setItem(49, close); //ITEM
        Telefoon.setItem(50, rand);
        Telefoon.setItem(51, rand);
        Telefoon.setItem(52, empty);
        
  
        Telefoon.setItem(53, empty);
       

		p.openInventory(Telefoon);

	}
	
	@SuppressWarnings("deprecation")
	public void rozeMenu(Player p) {
		Inventory Telefoon = Bukkit.createInventory(p, 54, "§dRoze telefoon");

		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)6);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.setDisplayName("§0");
		empty.setItemMeta(emptyMeta);
		
		ItemStack rand = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
		ItemMeta randMeta = rand.getItemMeta();
		randMeta.setDisplayName("§0");
		rand.setItemMeta(randMeta);
		
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(p.getName());
		meta.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName());
        ArrayList<String> skullLore = new ArrayList<String>();
        skullLore.add(" ");
        skullLore.add("§bLevel: §7" + String.valueOf(new MTPlayer(p).getLevel()));
        skullLore.add("§bExp: §7" + String.valueOf(new MTPlayer(p).getExp()));
        
		meta.setLore(skullLore);
        skull.setItemMeta(meta);
        
        ItemStack boete = new ItemStack(Material.PAPER, 1);
        ItemMeta boeteMeta = boete.getItemMeta();
        boeteMeta.setDisplayName("§cBoetes");
        ArrayList<String> boeteLore = new ArrayList<String>();
        boeteLore.add("§7Klik hier om je boetes te bekijken");
        boeteMeta.setLore(boeteLore);
        boete.setItemMeta(boeteMeta);
        
    	double bal = Math.round(Main.econ.getBalance(p.getName()));
    	
    	
        
        ItemStack bankKaart = new ItemStack(Material.INK_SACK, 1, (short)10);
        ItemMeta bankkaartMeta = bankKaart.getItemMeta();
        bankkaartMeta.setDisplayName("§fBank applicatie");
        bankkaartMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        bankkaartMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> bankkaartLore = new ArrayList<String>();
        bankkaartLore.add("§aKlik hier om je bank app te openen!");
        bankkaartLore.add("§7Als je geen telefoon op zak hebt werkt dit niet!");
        bankkaartLore.add("§6Balans: §f" + bal + " §");
        bankkaartMeta.setLore(bankkaartLore);
        bankKaart.setItemMeta(bankkaartMeta);
        
        ItemStack hulpdiensten = new ItemStack(Material.DIAMOND_HOE, 1, (short)67);
        ItemMeta hulpdienstenMeta = hulpdiensten.getItemMeta();
        hulpdienstenMeta.setDisplayName("§9Hulpdiensten 112");
        ArrayList<String> hulpLore = new ArrayList<String>();
        hulpLore.add("§fKlik hier om de hulpdiensten te verwittigen!");
        hulpLore.add("§cContacteer de politie/ambulance of brandweer!");
        hulpLore.add("§4Alleen voor dringende zaken!");
        hulpdienstenMeta.setLore(hulpLore);
        hulpdienstenMeta.setUnbreakable(true);
        hulpdiensten.setItemMeta(hulpdienstenMeta);
        
        ItemStack strafblad = new ItemStack(Material.BOOK, 1);
        ItemMeta strafbladMeta = strafblad.getItemMeta();
        strafbladMeta.setDisplayName("§bStrafblad");
        ArrayList<String> strafbladLore = new ArrayList<String>();
        strafbladLore.add("§fBinnenkort...!");
        strafbladMeta.setLore(strafbladLore);
        strafbladMeta.setUnbreakable(true);
        strafblad.setItemMeta(strafbladMeta);
        
        ItemStack discord = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short)96);
        ItemMeta dMeta = discord.getItemMeta();
        dMeta.setDisplayName("§9Discord app");
        ArrayList<String> dLore = new ArrayList<String>();
        dLore.add("§fKlik hier voor de discord link!");
        dMeta.setUnbreakable(true);
        dMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        discord.setItemMeta(dMeta);
        
        ItemStack vrienden = new ItemStack(Material.TOTEM, 1);
        ItemMeta vMeta = vrienden.getItemMeta();
        vMeta.setDisplayName("§aVrienden");
        ArrayList<String> vLore = new ArrayList<String>();
        vLore.add("§7Klik hier om je vrienden te beheren!");
        vMeta.setLore(vLore);
        vrienden.setItemMeta(vMeta);
        
        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§cSluit je telefoon");
        close.setItemMeta(closeMeta);
        
        ItemStack politie = new ItemStack(Material.WOOL, 1, (short)11);
        ItemMeta politieMeta = politie.getItemMeta();
        politieMeta.setDisplayName("§1Politie Menu");
        ArrayList<String> politieLore = new ArrayList<String>();
        politieLore.add("§7Commands voor politie");
        politieMeta.setLore(politieLore);
        politie.setItemMeta(politieMeta);
        
        ItemStack ambulance = new ItemStack(Material.WOOL, 1, (short)4);
        ItemMeta ambulanceMeta = ambulance.getItemMeta();
        ambulanceMeta.setDisplayName("§eAmbulance Menu");
        ArrayList<String> ambulanceLore = new ArrayList<String>();
        ambulanceLore.add("§7Commands voor Ambulance");
        ambulanceMeta.setLore(ambulanceLore);
        ambulance.setItemMeta(ambulanceMeta);
        
        ItemStack brandweer = new ItemStack(Material.WOOL, 1, (short)14);
        ItemMeta brandweerMeta = brandweer.getItemMeta();
        brandweerMeta.setDisplayName("§cBrandweer Menu");
        ArrayList<String> brandweerLore = new ArrayList<String>();
        brandweerLore.add("§7Commands voor Brandweer");
        brandweerMeta.setLore(brandweerLore);
        brandweer.setItemMeta(brandweerMeta);
        
        
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName("§cGPS");
        compass.setItemMeta(compassMeta);
        
        Telefoon.setItem(0, empty);
        Telefoon.setItem(1, empty);
        Telefoon.setItem(2, rand);
        Telefoon.setItem(3, rand);
        Telefoon.setItem(4, rand);
        Telefoon.setItem(5, rand);
        Telefoon.setItem(6, rand);
        Telefoon.setItem(7, empty);
        Telefoon.setItem(8, empty);
        Telefoon.setItem(9, empty);
        Telefoon.setItem(10, empty);
        Telefoon.setItem(11, rand);
        Telefoon.setItem(12, skull); // ITEM
        Telefoon.setItem(13, vrienden); // ITEM
        Telefoon.setItem(14, bankKaart); // ITEM
        Telefoon.setItem(15, rand);
        Telefoon.setItem(16, empty);
        Telefoon.setItem(17, empty);
        Telefoon.setItem(18, empty);
        Telefoon.setItem(19, empty);
        Telefoon.setItem(20, rand);
        Telefoon.setItem(21, strafblad); //ITEM
        Telefoon.setItem(22, hulpdiensten); //ITEM
        Telefoon.setItem(23, discord); //ITEM      
        Telefoon.setItem(24, rand);
        Telefoon.setItem(25, empty);
        Telefoon.setItem(26, empty);
        Telefoon.setItem(27, empty);
        Telefoon.setItem(28, empty);
        Telefoon.setItem(29, rand);
        Telefoon.setItem(30, boete); //ITEM
        Telefoon.setItem(31, compass); //ITEM
        Telefoon.setItem(32, empty); //ITEM
        Telefoon.setItem(33, rand);
        Telefoon.setItem(34, empty);
        Telefoon.setItem(35, empty);
        Telefoon.setItem(36, empty);
        Telefoon.setItem(37, empty);
        Telefoon.setItem(38, rand);
        Telefoon.setItem(39, empty); //ITEM
        Telefoon.setItem(40, empty); //ITEM
        Telefoon.setItem(41, empty); //ITEM
        Telefoon.setItem(42, rand);
        Telefoon.setItem(43, empty);
        Telefoon.setItem(44, empty);
        Telefoon.setItem(45, empty);
        Telefoon.setItem(46, empty);
        Telefoon.setItem(47, rand);
        Telefoon.setItem(48, rand);
        Telefoon.setItem(49, close); //ITEM
        Telefoon.setItem(50, rand);
        Telefoon.setItem(51, rand);
        Telefoon.setItem(52, empty);
        Telefoon.setItem(53, empty);
        

        
		p.openInventory(Telefoon);

	}
	
	@SuppressWarnings("deprecation")
	public void blauwMenu(Player p) {
		Inventory Telefoon = Bukkit.createInventory(p, 54, "§bBlauwe telefoon");

		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)3);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.setDisplayName("§0");
		empty.setItemMeta(emptyMeta);
		
		ItemStack rand = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
		ItemMeta randMeta = rand.getItemMeta();
		randMeta.setDisplayName("§0");
		rand.setItemMeta(randMeta);
		
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(p.getName());
		meta.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName());
        ArrayList<String> skullLore = new ArrayList<String>();
        skullLore.add(" ");
        skullLore.add("§bLevel: §7" + String.valueOf(new MTPlayer(p).getLevel()));
        skullLore.add("§bExp: §7" + String.valueOf(new MTPlayer(p).getExp()));
        
		meta.setLore(skullLore);
        skull.setItemMeta(meta);
        
        ItemStack boete = new ItemStack(Material.PAPER, 1);
        ItemMeta boeteMeta = boete.getItemMeta();
        boeteMeta.setDisplayName("§cBoetes");
        ArrayList<String> boeteLore = new ArrayList<String>();
        boeteLore.add("§7Klik hier om je boetes te bekijken");
        boeteMeta.setLore(boeteLore);
        boete.setItemMeta(boeteMeta);
        
    	double bal = Main.econ.getBalance(p.getName());
        
        ItemStack bankKaart = new ItemStack(Material.INK_SACK, 1, (short)10);
        ItemMeta bankkaartMeta = bankKaart.getItemMeta();
        bankkaartMeta.setDisplayName("§fBank applicatie");
        bankkaartMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        bankkaartMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> bankkaartLore = new ArrayList<String>();
        bankkaartLore.add("§aKlik hier om je bank app te openen!");
        bankkaartLore.add("§7Als je geen telefoon op zak hebt werkt dit niet!");
        bankkaartLore.add("§6Balans: §f" + Math.round(bal) + " §");
        bankkaartMeta.setLore(bankkaartLore);
        bankKaart.setItemMeta(bankkaartMeta);
        
        ItemStack hulpdiensten = new ItemStack(Material.DIAMOND_HOE, 1, (short)67);
        ItemMeta hulpdienstenMeta = hulpdiensten.getItemMeta();
        hulpdienstenMeta.setDisplayName("§9Hulpdiensten 112");
        ArrayList<String> hulpLore = new ArrayList<String>();
        hulpLore.add("§fKlik hier om de hulpdiensten te verwittigen!");
        hulpLore.add("§cContacteer de politie/ambulance of brandweer!");
        hulpLore.add("§4Alleen voor dringende zaken!");
        hulpdienstenMeta.setLore(hulpLore);
        hulpdienstenMeta.setUnbreakable(true);
        hulpdiensten.setItemMeta(hulpdienstenMeta);
        
        ItemStack strafblad = new ItemStack(Material.BOOK, 1);
        ItemMeta strafbladMeta = strafblad.getItemMeta();
        strafbladMeta.setDisplayName("§bStrafblad");
        ArrayList<String> strafbladLore = new ArrayList<String>();
        strafbladLore.add("§fBinnenkort...!");
        strafbladMeta.setLore(strafbladLore);
        strafbladMeta.setUnbreakable(true);
        strafblad.setItemMeta(strafbladMeta);
        
        ItemStack discord = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short)96);
        ItemMeta dMeta = discord.getItemMeta();
        dMeta.setDisplayName("§9Discord app");
        ArrayList<String> dLore = new ArrayList<String>();
        dLore.add("§fKlik hier voor de discord link!");
        dMeta.setUnbreakable(true);
        dMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        discord.setItemMeta(dMeta);
        
        ItemStack vrienden = new ItemStack(Material.TOTEM, 1);
        ItemMeta vMeta = vrienden.getItemMeta();
        vMeta.setDisplayName("§aVrienden");
        ArrayList<String> vLore = new ArrayList<String>();
        vLore.add("§7Klik hier om je vrienden te beheren!");
        vMeta.setLore(vLore);
        vrienden.setItemMeta(vMeta);
        
        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§cSluit je telefoon");
        close.setItemMeta(closeMeta);
        
        ItemStack politie = new ItemStack(Material.WOOL, 1, (short)11);
        ItemMeta politieMeta = politie.getItemMeta();
        politieMeta.setDisplayName("§1Politie Menu");
        ArrayList<String> politieLore = new ArrayList<String>();
        politieLore.add("§7Commands voor politie");
        politieMeta.setLore(politieLore);
        politie.setItemMeta(politieMeta);
        
        ItemStack ambulance = new ItemStack(Material.WOOL, 1, (short)4);
        ItemMeta ambulanceMeta = ambulance.getItemMeta();
        ambulanceMeta.setDisplayName("§eAmbulance Menu");
        ArrayList<String> ambulanceLore = new ArrayList<String>();
        ambulanceLore.add("§7Commands voor Ambulance");
        ambulanceMeta.setLore(ambulanceLore);
        ambulance.setItemMeta(ambulanceMeta);
        
        ItemStack brandweer = new ItemStack(Material.WOOL, 1, (short)14);
        ItemMeta brandweerMeta = brandweer.getItemMeta();
        brandweerMeta.setDisplayName("§cBrandweer Menu");
        ArrayList<String> brandweerLore = new ArrayList<String>();
        brandweerLore.add("§7Commands voor Brandweer");
        brandweerMeta.setLore(brandweerLore);
        brandweer.setItemMeta(brandweerMeta);
        
        
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName("§cGPS");
        compass.setItemMeta(compassMeta);
        
        Telefoon.setItem(0, empty);
        Telefoon.setItem(1, empty);
        Telefoon.setItem(2, rand);
        Telefoon.setItem(3, rand);
        Telefoon.setItem(4, rand);
        Telefoon.setItem(5, rand);
        Telefoon.setItem(6, rand);
        Telefoon.setItem(7, empty);
        Telefoon.setItem(8, empty);
        Telefoon.setItem(9, empty);
        Telefoon.setItem(10, empty);
        Telefoon.setItem(11, rand);
        Telefoon.setItem(12, skull); // ITEM
        Telefoon.setItem(13, vrienden); // ITEM
        Telefoon.setItem(14, bankKaart); // ITEM
        Telefoon.setItem(15, rand);
        Telefoon.setItem(16, empty);
        Telefoon.setItem(17, empty);
        Telefoon.setItem(18, empty);
        Telefoon.setItem(19, empty);
        Telefoon.setItem(20, rand);
        Telefoon.setItem(21, strafblad); //ITEM
        Telefoon.setItem(22, hulpdiensten); //ITEM
        Telefoon.setItem(23, discord); //ITEM      
        Telefoon.setItem(24, rand);
        Telefoon.setItem(25, empty);
        Telefoon.setItem(26, empty);
        Telefoon.setItem(27, empty);
        Telefoon.setItem(28, empty);
        Telefoon.setItem(29, rand);
        Telefoon.setItem(30, boete); //ITEM
        Telefoon.setItem(31, compass); //ITEM
        Telefoon.setItem(32, empty); //ITEM
        Telefoon.setItem(33, rand);
        Telefoon.setItem(34, empty);
        Telefoon.setItem(35, empty);
        Telefoon.setItem(36, empty);
        Telefoon.setItem(37, empty);
        Telefoon.setItem(38, rand);
        Telefoon.setItem(39, empty); //ITEM
        Telefoon.setItem(40, empty); //ITEM
        Telefoon.setItem(41, empty); //ITEM
        Telefoon.setItem(42, rand);
        Telefoon.setItem(43, empty);
        Telefoon.setItem(44, empty);
        Telefoon.setItem(45, empty);
        Telefoon.setItem(46, empty);
        Telefoon.setItem(47, rand);
        Telefoon.setItem(48, rand);
        Telefoon.setItem(49, close); //ITEM
        Telefoon.setItem(50, rand);
        Telefoon.setItem(51, rand);
        Telefoon.setItem(52, empty);
        
        Telefoon.setItem(53, empty);
        

        
		p.openInventory(Telefoon);

	}
	
	@SuppressWarnings("deprecation")
	public void geelMenu(Player p) {
		Inventory Telefoon = Bukkit.createInventory(p, 54, "§eGele telefoon");

		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.setDisplayName("§0");
		empty.setItemMeta(emptyMeta);
		
		ItemStack rand = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
		ItemMeta randMeta = rand.getItemMeta();
		randMeta.setDisplayName("§0");
		rand.setItemMeta(randMeta);
		
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(p.getName());
		meta.setDisplayName(ChatColor.LIGHT_PURPLE + p.getName());
        ArrayList<String> skullLore = new ArrayList<String>();
        skullLore.add(" ");
        skullLore.add("§bLevel: §7" + String.valueOf(new MTPlayer(p).getLevel()));
        skullLore.add("§bExp: §7" + String.valueOf(new MTPlayer(p).getExp()));
        
		meta.setLore(skullLore);
        skull.setItemMeta(meta);
        
        ItemStack boete = new ItemStack(Material.PAPER, 1);
        ItemMeta boeteMeta = boete.getItemMeta();
        boeteMeta.setDisplayName("§cBoetes");
        ArrayList<String> boeteLore = new ArrayList<String>();
        boeteLore.add("§7Klik hier om je boetes te bekijken");
        boeteMeta.setLore(boeteLore);
        boete.setItemMeta(boeteMeta);
        
    	double bal = Main.econ.getBalance(p.getName());
    	
    	
        
        ItemStack bankKaart = new ItemStack(Material.INK_SACK, 1, (short)10);
        ItemMeta bankkaartMeta = bankKaart.getItemMeta();
        bankkaartMeta.setDisplayName("§fBank applicatie");
        bankkaartMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        bankkaartMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ArrayList<String> bankkaartLore = new ArrayList<String>();
        bankkaartLore.add("§aKlik hier om je bank app te openen!");
        bankkaartLore.add("§7Als je geen telefoon op zak hebt werkt dit niet!");
        bankkaartLore.add("§6Balans: §f" + bal + " §");
        bankkaartMeta.setLore(bankkaartLore);
        bankKaart.setItemMeta(bankkaartMeta);
        
        ItemStack hulpdiensten = new ItemStack(Material.DIAMOND_HOE, 1, (short)67);
        ItemMeta hulpdienstenMeta = hulpdiensten.getItemMeta();
        hulpdienstenMeta.setDisplayName("§9Hulpdiensten 112");
        ArrayList<String> hulpLore = new ArrayList<String>();
        hulpLore.add("§fKlik hier om de hulpdiensten te verwittigen!");
        hulpLore.add("§cContacteer de politie/ambulance of brandweer!");
        hulpLore.add("§4Alleen voor dringende zaken!");
        hulpdienstenMeta.setLore(hulpLore);
        hulpdienstenMeta.setUnbreakable(true);
        hulpdiensten.setItemMeta(hulpdienstenMeta);
        
        ItemStack strafblad = new ItemStack(Material.BOOK, 1);
        ItemMeta strafbladMeta = strafblad.getItemMeta();
        strafbladMeta.setDisplayName("§bStrafblad");
        ArrayList<String> strafbladLore = new ArrayList<String>();
        strafbladLore.add("§fBinnenkort...!");
        strafbladMeta.setLore(strafbladLore);
        strafbladMeta.setUnbreakable(true);
        strafblad.setItemMeta(strafbladMeta);
       
        ItemStack discord = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short)96);
        ItemMeta dMeta = discord.getItemMeta();
        dMeta.setDisplayName("§9Discord app");
        ArrayList<String> dLore = new ArrayList<String>();
        dLore.add("§fKlik hier voor de discord link!");
        dMeta.setUnbreakable(true);
        dMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        discord.setItemMeta(dMeta);
        
        ItemStack vrienden = new ItemStack(Material.TOTEM, 1);
        ItemMeta vMeta = vrienden.getItemMeta();
        vMeta.setDisplayName("§aVrienden");
        ArrayList<String> vLore = new ArrayList<String>();
        vLore.add("§7Klik hier om je vrienden te beheren!");
        vMeta.setLore(vLore);
        vrienden.setItemMeta(vMeta);
        
        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("§cSluit je telefoon");
        close.setItemMeta(closeMeta);
        
        ItemStack politie = new ItemStack(Material.WOOL, 1, (short)11);
        ItemMeta politieMeta = politie.getItemMeta();
        politieMeta.setDisplayName("§1Politie Menu");
        ArrayList<String> politieLore = new ArrayList<String>();
        politieLore.add("§7Commands voor politie");
        politieMeta.setLore(politieLore);
        politie.setItemMeta(politieMeta);
        
        ItemStack ambulance = new ItemStack(Material.WOOL, 1, (short)4);
        ItemMeta ambulanceMeta = ambulance.getItemMeta();
        ambulanceMeta.setDisplayName("§eAmbulance Menu");
        ArrayList<String> ambulanceLore = new ArrayList<String>();
        ambulanceLore.add("§7Commands voor Ambulance");
        ambulanceMeta.setLore(ambulanceLore);
        ambulance.setItemMeta(ambulanceMeta);
        
        ItemStack brandweer = new ItemStack(Material.WOOL, 1, (short)14);
        ItemMeta brandweerMeta = brandweer.getItemMeta();
        brandweerMeta.setDisplayName("§cBrandweer Menu");
        ArrayList<String> brandweerLore = new ArrayList<String>();
        brandweerLore.add("§7Commands voor Brandweer");
        brandweerMeta.setLore(brandweerLore);
        brandweer.setItemMeta(brandweerMeta);
             
        ItemStack compass = new ItemStack(Material.COMPASS, 1);
        ItemMeta compassMeta = compass.getItemMeta();
        compassMeta.setDisplayName("§cGPS");
        compass.setItemMeta(compassMeta);
        
        Telefoon.setItem(0, empty);
        Telefoon.setItem(1, empty);
        Telefoon.setItem(2, rand);
        Telefoon.setItem(3, rand);
        Telefoon.setItem(4, rand);
        Telefoon.setItem(5, rand);
        Telefoon.setItem(6, rand);
        Telefoon.setItem(7, empty);
        Telefoon.setItem(8, empty);
        Telefoon.setItem(9, empty);
        Telefoon.setItem(10, empty);
        Telefoon.setItem(11, rand);
        Telefoon.setItem(12, skull); // ITEM
        Telefoon.setItem(13, vrienden); // ITEM
        Telefoon.setItem(14, bankKaart); // ITEM
        Telefoon.setItem(15, rand);
        Telefoon.setItem(16, empty);
        Telefoon.setItem(17, empty);
        Telefoon.setItem(18, empty);
        Telefoon.setItem(19, empty);
        Telefoon.setItem(20, rand);
        Telefoon.setItem(21, strafblad); //ITEM
        Telefoon.setItem(22, hulpdiensten); //ITEM
        Telefoon.setItem(23, discord); //ITEM      
        Telefoon.setItem(24, rand);
        Telefoon.setItem(25, empty);
        Telefoon.setItem(26, empty);
        Telefoon.setItem(27, empty);
        Telefoon.setItem(28, empty);
        Telefoon.setItem(29, rand);
        Telefoon.setItem(30, boete); //ITEM
        Telefoon.setItem(31, compass); //ITEM
        Telefoon.setItem(32, empty); //ITEM
        Telefoon.setItem(33, rand);
        Telefoon.setItem(34, empty);
        Telefoon.setItem(35, empty);
        Telefoon.setItem(36, empty);
        Telefoon.setItem(37, empty);
        Telefoon.setItem(38, rand);
        Telefoon.setItem(39, empty); //ITEM
        Telefoon.setItem(40, empty); //ITEM
        Telefoon.setItem(41, empty); //ITEM
        Telefoon.setItem(42, rand);
        Telefoon.setItem(43, empty);
        Telefoon.setItem(44, empty);
        Telefoon.setItem(45, empty);
        Telefoon.setItem(46, empty);
        Telefoon.setItem(47, rand);
        Telefoon.setItem(48, rand);
        Telefoon.setItem(49, close); //ITEM
        Telefoon.setItem(50, rand);
        Telefoon.setItem(51, rand);
        Telefoon.setItem(52, empty);
        Telefoon.setItem(53, empty);
        
		p.openInventory(Telefoon);

	}
	
	@SuppressWarnings("unused")
	public void boeteMenu(Player player) {		
		
	}
	
	
	public void politieMenu(Player p) {
		Inventory politieMenu = Bukkit.createInventory(p, 45, "§1Politie Menu");
		
		ItemStack blue = new ItemStack(Material.WOOL, 1, (short)4);
		ItemMeta blueMeta = blue.getItemMeta();
		blueMeta.setDisplayName("§cBoete uitschrijven");
		blue.setItemMeta(blueMeta);
		
		politieMenu.setItem(1, blue);
		
		p.openInventory(politieMenu);

	}
	
	public void bankMenu(Player p) {
		Inventory bankMenu = Bukkit.createInventory(p, 9, "§2Bank Applicatie");
		
		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
		ItemMeta emptyMeta = empty.getItemMeta();
		emptyMeta.setDisplayName("§0");
		empty.setItemMeta(emptyMeta);		

		
		ItemStack yellow = new ItemStack(Material.WOOL, 1, (short)4);
		ItemMeta yellowMeta = yellow.getItemMeta();
		yellowMeta.setDisplayName("§eOverschrijvingen");
		yellow.setItemMeta(yellowMeta);	

		bankMenu.setItem(0, empty);
		bankMenu.setItem(1, empty);
		bankMenu.setItem(2, empty);
		bankMenu.setItem(3, empty);

		bankMenu.setItem(4, yellow);
		bankMenu.setItem(5, empty);
		bankMenu.setItem(6, empty);

		bankMenu.setItem(7, empty);
		bankMenu.setItem(8, empty);


		
		
		
		p.openInventory(bankMenu);
		
	}
	
	@SuppressWarnings("deprecation")
	public void overschrijvingMenu(Player p) {	
		ArrayList<Player> player_list = new ArrayList<>(p.getServer().getOnlinePlayers());
		
		Inventory overSchrijvingsMenu = Bukkit.createInventory(p, 45, "§eOverschrijving naar speler");
		
		
		for (int i = 0; i < player_list.size(); i++) {			
			
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
			SkullMeta meta = (SkullMeta) skull.getItemMeta();
			meta.setOwner(player_list.get(i).getName());
			meta.setDisplayName(player_list.get(i).getName());
			ArrayList<String> lore = new ArrayList<>();
			lore.add(" ");
			meta.setLore(lore);
			skull.setItemMeta(meta);
			
			overSchrijvingsMenu.addItem(skull);
		}
		
		p.openInventory(overSchrijvingsMenu);
		
	}
	
	public void stortMenu(Player p) {
		Inventory stortMenu = Bukkit.createInventory(p, 45, "§2Storten");

		p.openInventory(stortMenu);
	}
	
	public void opneemMenu(Player p) {
		Inventory opneemMenu = Bukkit.createInventory(p, 45, "§1Opnemen");

		p.openInventory(opneemMenu);
	}
	
	public void eeneentwee(Player p) {
		Inventory eeneentwee = Bukkit.createInventory(p, 9, "§9Hulpdiensten");
		
        ItemStack politie = new ItemStack(Material.LEATHER_HELMET, 1);
        LeatherArmorMeta politieMeta = (LeatherArmorMeta) politie.getItemMeta();
        politieMeta.setColor(Color.BLUE);
        politieMeta.setDisplayName("§9Politie");
        politie.setItemMeta(politieMeta);
        
        ItemStack ambu = new ItemStack(Material.LEATHER_HELMET, 1);
        LeatherArmorMeta ambuMeta = (LeatherArmorMeta) ambu.getItemMeta();
        ambuMeta.setColor(Color.YELLOW);
        ambuMeta.setDisplayName("§eAmbulance");
        ambu.setItemMeta(ambuMeta);
        
        ItemStack brandweer = new ItemStack(Material.LEATHER_HELMET, 1);
        LeatherArmorMeta bbmeta4 = (LeatherArmorMeta) brandweer.getItemMeta();
        bbmeta4.setColor(Color.RED);
        bbmeta4.setDisplayName("§cBrandweer");
        brandweer.setItemMeta(bbmeta4);
        
        ItemStack anwb = new ItemStack(Material.LEATHER_HELMET, 1);
        LeatherArmorMeta anwbmeta = (LeatherArmorMeta) anwb.getItemMeta();
        anwbmeta.setColor(Color.ORANGE);
        anwbmeta.setDisplayName("§6ANWB");
        anwb.setItemMeta(anwbmeta);
		
		eeneentwee.setItem(1, politie);
		eeneentwee.setItem(3, ambu);
		eeneentwee.setItem(5, brandweer);
		eeneentwee.setItem(7, anwb);

		
		p.openInventory(eeneentwee);
	}
	
	public void radioMenu(Player p) {
		
        ItemStack is = new ItemStack(Material.NAME_TAG);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName("§6Welke frequentie wil je joinen!");
        ArrayList<String> il = new ArrayList<String>();
        il.add("§fType \"Politie\" voor de §9politie §fradio!");
        il.add("§fType \"Ambulance\" voor de §eambulance §fradio!");
        il.add("§fType \"Brandweer\" voor de §cbrandweer §fradio!");
        is.setItemMeta(im);
		
	}
	
	public void gpsMenu(Player p) {
		
		ItemStack politieItemStack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta politieMeta = (SkullMeta) politieItemStack.getItemMeta();
		politieMeta.setOwner("police");
		politieMeta.setDisplayName("§9Politie");
        politieItemStack.setItemMeta(politieMeta);
        
		ItemStack brandweerItemStack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta brandweerMeta = (SkullMeta) brandweerItemStack.getItemMeta();
		brandweerMeta.setOwner("firefighter");
		brandweerMeta.setDisplayName("§cBrandweer");
		brandweerItemStack.setItemMeta(brandweerMeta);
		
		ItemStack anwbItemStack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta anwbMeta = (SkullMeta) anwbItemStack.getItemMeta();
		anwbMeta.setOwner("Anwb");
		anwbMeta.setDisplayName("§6Anwb");
		anwbItemStack.setItemMeta(anwbMeta);
		
		ItemStack ambuItemStack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta ambuMeta = (SkullMeta) ambuItemStack.getItemMeta();
		ambuMeta.setOwner("nurse");
		ambuMeta.setDisplayName("§eZiekenhuis");
		ambuItemStack.setItemMeta(ambuMeta);
		
		ItemStack stop = new ItemStack(Material.BARRIER, 1);
		ItemMeta stopMeta = stop.getItemMeta();
		stopMeta.setDisplayName("§4Stop GPS");
		stop.setItemMeta(stopMeta);
        
        Inventory gpsInventory = Bukkit.createInventory(p, 45, "§cGPS");
        
        gpsInventory.setItem(0, politieItemStack);
        gpsInventory.setItem(1, brandweerItemStack);
        gpsInventory.setItem(2, anwbItemStack);
        gpsInventory.setItem(3, ambuItemStack);
        gpsInventory.setItem(44, stop);

        p.openInventory(gpsInventory);
        
		
	}
	
	@EventHandler
	public void profielClickEvent(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
        
		if(e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) {
			return;
		} else {
			if(e.getClickedInventory().getTitle().equalsIgnoreCase("§1Politie Menu")) {
				
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cBoete uitschrijven")) {
						p.sendMessage("§cBinnenkort...");
					}
					e.setCancelled(true);
				}
				
				e.setCancelled(true);

			} else if(e.getClickedInventory().getTitle().equalsIgnoreCase("§2Bank Applicatie")) {
						
						if(e.getCurrentItem() != null) {
								if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eOverschrijvingen")) {
									
									p.closeInventory();
									overschrijvingMenu(p);
								}
									
							e.setCancelled(true);
						}
						
				e.setCancelled(true);
				
			} else if(e.getClickedInventory().getTitle().equalsIgnoreCase("§eOverschrijving naar speler")) {
				
				Player player = (Player) e.getWhoClicked();
				Player whoToDonatePlayer = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());
				
				if(e.getCurrentItem() != null) {
									
					if(e.getCurrentItem().getType() == Material.SKULL_ITEM) {
						if(whoToDonatePlayer == player) {
							player.sendMessage("§4Je kan geen geld naar jezelf overschrijven!");
						} else {
							player.sendMessage("§aType hieronder hoeveel je wilt overschrijven!");
							player.sendMessage("§fVoorbeeld: 500 / 5000 / 19522");
							player.sendMessage("§cGebruik geen . , of € tekens!");
							betalenAanSpeler.add(whoToDonatePlayer);
							betalendeSpeler.add(player);
							player.closeInventory();
						}
					}
					e.setCancelled(true);
				}
				e.setCancelled(true);
			} else if(e.getClickedInventory().getTitle().equalsIgnoreCase("§aGroene telefoon") || e.getClickedInventory().getTitle().equalsIgnoreCase("§cRode telefoon") || e.getClickedInventory().getTitle().equalsIgnoreCase("§dRoze telefoon") || e.getClickedInventory().getTitle().equalsIgnoreCase("§bBlauwe telefoon") || e.getClickedInventory().getTitle().equalsIgnoreCase("§eGele telefoon")) {

				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSluit je telefoon")) {
						p.closeInventory();
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§fBank applicatie")) {
						p.closeInventory();
						bankMenu(p);
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§9Hulpdiensten 112")) {
						p.closeInventory();
						eeneentwee(p);
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aVrienden")) {
						p.closeInventory();
						Bukkit.dispatchCommand(p, "vrienden");
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§9Discord app")) {
						p.closeInventory();
						
						String json = "{\"text\":\"[Klik hier om onze discord te betreden!]\",\"color\":\"green\",\"bold\":\"true\",\"underlined\":\"false\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://discord.gg/SnstaTE\"}}]";
						PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(json));
						((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cGPS")) {
						p.closeInventory();
						gpsMenu(p);
					}
					e.setCancelled(true);
 				}

			} else if (e.getClickedInventory().getTitle().equalsIgnoreCase("§3Portofoon")) {
				Player player = (Player) e.getWhoClicked();
				
				if(e.getCurrentItem() != null ) {
					
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cNoodknop")) {
						if(new MTPlayer(p).isPolitie() && p.isOnline()) {
							
							double x = Math.round(player.getLocation().getX());
							double y = Math.round(player.getLocation().getY());
							double z = Math.round(player.getLocation().getZ());

							String xString = String.valueOf(x);
							String yString = String.valueOf(y);
							String zString = String.valueOf(z);
							
							Bukkit.broadcast("§4[§cNOODKNOP§4] §c" + player.getName() + " §4(§cx:§7 " + xString  + "§c y:§7 " + yString + "§c z:§7 " + zString + "§4) §7heeft op de noodknop gedrukt!", "politie.use");
							
							p.closeInventory();
						}
						e.setCancelled(true);
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§aRadio")) {
						if(p.hasPermission("politie.radio") || p.hasPermission("ambulance.radio") || p.hasPermission("brandweer.radio")) {
							p.closeInventory();
						}
					}
					e.setCancelled(true);
				}
				e.setCancelled(true);

			} else if(e.getClickedInventory().getTitle().equalsIgnoreCase("§9Hulpdiensten")) {
				if(e.getCurrentItem() != null) {
					
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§9Politie")) {
						p.closeInventory();
						p.sendMessage("§aType in de chat je melding naar de §9politie§a!");
						politieMelding.add(p);
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eAmbulance")) {
						p.closeInventory();
						p.sendMessage("§aType in de chat je melding naar de §eambulance§a!");
						ambulanceMelding.add(p);
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cBrandweer")) {
						p.closeInventory();
						p.sendMessage("§aType in de chat je melding naar de §cbrandweer§a!");
						brandweerMelding.add(p);
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6ANWB")) {
						p.closeInventory();
						p.sendMessage("§aType in de chat je melding naar de §6ANWB§a!");
						anwbMelding.add(p);
					}
					
					e.setCancelled(true);
				}
				e.setCancelled(true);
			} else if(e.getClickedInventory().getTitle().equalsIgnoreCase("§cGPS")) {
				if(e.getCurrentItem() != null) {
					
					if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§9Politie")) {
						p.closeInventory();
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gps start Politie " + e.getWhoClicked().getName());
						e.setCancelled(true);
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cBrandweer")) {
						p.closeInventory();
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gps start brandweer " + e.getWhoClicked().getName());
						e.setCancelled(true);
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Anwb")) {
						p.closeInventory();
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gps start anwb " + e.getWhoClicked().getName());
						e.setCancelled(true);
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eZiekenhuis")) {
						p.closeInventory();
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gps start ziekenhuis " + e.getWhoClicked().getName());
						e.setCancelled(true);
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4Stop GPS")) {
						p.closeInventory();
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gps stop " + e.getWhoClicked().getName());
						e.setCancelled(true);
					}
				}
			}
		} 
	}
	
	@SuppressWarnings("deprecation")
	public static void overschrijving(Player player, Player target, Integer Amount) {
		Integer amount = Amount;
		
		if(amount != (int)amount) {
			player.sendMessage("§cHet opgegeven bedrag is geen nummer!");
		} else {
			EconomyResponse r = Main.econ.depositPlayer(target.getName(), amount);
			if (r.transactionSuccess()) {
				Main.econ.withdrawPlayer(player, amount);
				player.sendMessage("§aSuccesvol §f€" + amount + " §aovergeschreven naar " + target.getName() + "!");
				target.sendMessage("§aJe hebt §f€" + amount + " §aontvangen van " + player.getName() + "!");
			} else {
				player.sendMessage("§cEr is iets fout gelopen! Probeer het over een paar seconden opnieuw!");
			}
		}
	}
	
	@EventHandler
	public static void onPlayerChatEvent(AsyncPlayerChatEvent e) {
		if(betalendeSpeler.contains(e.getPlayer())) {
			e.setCancelled(true);
			if(Integer.valueOf(e.getMessage()) instanceof Integer) {
				overschrijving(e.getPlayer(), betalenAanSpeler.get(0), Integer.parseInt(e.getMessage()));
				betalenAanSpeler.clear();
				betalendeSpeler.clear();
			} else {
				e.getPlayer().sendMessage("§cHet opgegeven bedrag is geen cijfer!");
			}
		}
	}
}
