package be.overcast.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import ch.dempsey.mt.api.MTPlayer;
import net.dv8tion.jda.core.JDA;
import net.md_5.bungee.api.ChatColor;

public class Commands implements Listener, CommandExecutor {
	
	WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
	public WorldGuardPlugin worldGuardPlugin;
	
	public static JDA jda;
	

	public static ArrayList<Player> politieRadio = new ArrayList<Player>();
	public static ArrayList<Player> ambulanceRadio = new ArrayList<Player>();
	public static ArrayList<Player> brandweerRadio = new ArrayList<Player>();
	public static ArrayList<Player> anwbRadio = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			
		if(!(sender instanceof Player)) {
			if(cmd.getName().equalsIgnoreCase("noodmelding")) {
				
					for(Player p : Commands.politieRadio) {
						String message = String.join(" ", args);
						p.sendMessage("§7[§4NOODMELDING§7] §4" + message);
					}
				
			} else if (cmd.getName().equalsIgnoreCase("stuurbericht")) {
			    if (args.length > 1) {
			        //retrieve the first argument as a player
			        Player target = Bukkit.getServer().getPlayer(args[0]);
			        String sm = "";

			        //combine the arguments the player typed
			        for (int i = 1; i < args.length; i++){
			            String arg = (args[i] + " ");
			            sm = (sm + arg);
			        }

			        //send the actual message
			        target.sendMessage(ChatColor.translateAlternateColorCodes('§', sm));
			    }
			}
		}
		
		if(sender instanceof Player) {
			 if(cmd.getName().equalsIgnoreCase("pr")) {
				Player p = (Player) sender;
				if(p.hasPermission("politie.radio")) {
					if(!politieRadio.contains(p)) {
						politieRadio.add(p);
						p.sendMessage("§8(§b!§8) §9Politie §bradio chat ingeschakelt!");
					}else {
						politieRadio.remove(p);
						p.sendMessage("§8(§b!§8) §9Politie §bradio chat uitgeschakelt!");
					}
				} else {
					p.sendMessage("§8(§b!§8) §cJe hebt geen toegang tot dit commando!");
				}
			}  else if(cmd.getName().equalsIgnoreCase("ar")) {
				Player p = (Player) sender;
				if(p.hasPermission("ambulance.radio")) {
					if(!ambulanceRadio.contains(p)) {
						ambulanceRadio.add(p);
						p.sendMessage("§8(§b!§8) §eAmbulance §bradio chat ingeschakelt!");
					}else {
						ambulanceRadio.remove(p);
						p.sendMessage("§8(§b!§8) §eAmbulance §bradio chat uitgeschakelt!");
					}
				} else {
					p.sendMessage("§8(§b!§8) §cJe hebt geen toegang tot dit commando!");
				}
			} else if(cmd.getName().equalsIgnoreCase("br")) {
				Player p = (Player) sender;
				if(p.hasPermission("brandweer.radio")) {
					if(!brandweerRadio.contains(p)) {
						brandweerRadio.add(p);
						p.sendMessage("§8(§b!§8) §cBrandweer §bradio chat ingeschakelt!");
					}else {
						brandweerRadio.remove(p);
						p.sendMessage("§8(§b!§8) §cBrandweer §bradio chat uitgeschakelt!");
					}
				} else {
					p.sendMessage("§8(§b!§8) §cJe hebt geen toegang tot dit commando!");
				}
			} else if(cmd.getName().equalsIgnoreCase("anwbr")) {
				Player p = (Player) sender;
				if(p.hasPermission("anwb.radio")) {
					if(!anwbRadio.contains(p)) {
						anwbRadio.add(p);
						p.sendMessage("§8(§b!§8) §6ANWB §bradio chat ingeschakelt!");
					}else {
						anwbRadio.remove(p);
						p.sendMessage("§8(§b!§8) §6ANWB §bradio chat uitgeschakelt!");
					}
				} else {
					p.sendMessage("§8(§b!§8) §cJe hebt geen toegang tot dit commando!");
				}
			} else if(sender.hasPermission("boete.use")) {
				if(cmd.getName().equalsIgnoreCase("boete")) {
					if(args.length == 0) {
							sender.sendMessage("§7Boete help:");
							sender.sendMessage("§9------------------------------------");
							sender.sendMessage("§9- §7/boete <naam> <boetenaam>");
							sender.sendMessage("§9- §7/boete lijst");
							sender.sendMessage("§9------------------------------------");
							return true;
					} else if(args[0].equalsIgnoreCase("lijst")) {
							sender.sendMessage("§7Boete lijst:");
							sender.sendMessage("§9------------------------------------");
							sender.sendMessage("§9- §7FoutParkeren - §c€25.000");
							sender.sendMessage("§9- §7ValseMeldingen - §c€30.000");
							sender.sendMessage("§9- §7Beledigen §9/ §7SeksueleOpmerkingen - §c€35.000");
							sender.sendMessage("§9- §7GeenMedewerking §9/§7 Stalken - §c€35.000");
							sender.sendMessage("§9- §7Bedreigen - §c€40.000");
							sender.sendMessage("§9- §7Drugsbezit - §c€45.000");
							sender.sendMessage("§9- §7VehicleGlitchen §9/ §7PrivacySchending - §c€50.000");
							sender.sendMessage("§9- §7Uitbraak - §c€55.000");
							sender.sendMessage("§9- §7Inbraak §9/ §7Scammen §9/ §7Diefstal - §c€70.000");
							sender.sendMessage("§9- §7IllegaleGoederen §9/ §7Wapenbezit - §c€70.000");
							sender.sendMessage("§9- §7PogingDoodslag - §c€95.000");
							sender.sendMessage("§9- §7Doodslag - §c€100.000");
							sender.sendMessage("§9- §7PogingDoodlagAmbtenaar - §c€120.000");
							sender.sendMessage("§9- §7DoodslagAmbtenaar - §c€170.000");
							sender.sendMessage("§9------------------------------------");
							return true;
					} else if(args.length == 2) {
						Bukkit.dispatchCommand(sender, "voucher give " + args[0] + " " + args[1] + " " + 1);
						return true;

					} else {
						sender.sendMessage("§cGebruik: /boete <naam> <boetenaam>");
						return true;
					}
				} 
			} else {
				sender.sendMessage("§4je hebt geen toegang tot die commando");
				return true;

			}
			
			
		}
		
		if(cmd.getName().equalsIgnoreCase("setlevel")) {
			if(sender.hasPermission("level.set")) {
				
				if(args.length == 0) {
					sender.sendMessage("§aGebruik /setlevel <speler> <level>");
				} else if(args.length == 1) {
					sender.sendMessage("§aGebruik /setlevel <speler> <level>");
				} else if(args.length == 2) {
					Player target = Bukkit.getPlayer(args[0]);

					int level = Integer.parseInt(args[1]);
					sender.sendMessage("§1Succesvol §7" + target.getName() + " §1naar level " + level + " gemaakt!");
					new MTPlayer(target).setLevel(level);
					
					MTPlayer pls = new MTPlayer(target);
					pls.setLevel(level);
				}
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("mijnrole")) {
			Player p = (Player) sender;
			MTPlayer pls = new MTPlayer(p);
			if(args.length == 0 ) {
				sender.sendMessage("§7Jou rol: §3" + pls.getRole().name());
			}
		}
		
		if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("items")) {
				if(sender.hasPermission("MinetopiaReborn.items")) {
					Player player = (Player) sender;
					Inventory items = Bukkit.createInventory(player, 54);
					
					ItemStack een = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short)250);
				    ItemMeta eenMeta = een.getItemMeta();
				    eenMeta.setUnbreakable(true);
				    een.setItemMeta(eenMeta);
				    
				    ItemStack twee = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short)251);
				    ItemMeta tweeMeta = twee.getItemMeta();
				    tweeMeta.setUnbreakable(true);
				    twee.setItemMeta(tweeMeta);
				    
				    ItemStack drie = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short)252);
				    ItemMeta drieMeta = drie.getItemMeta();
				    drieMeta.setUnbreakable(true);
				    drie.setItemMeta(drieMeta);
				    
				    ItemStack vier = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short)253);
				    ItemMeta vierMeta = vier.getItemMeta();
				    vierMeta.setUnbreakable(true);
				    vier.setItemMeta(vierMeta);
				    
				    ItemStack vijf = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short)254);
				    ItemMeta vijfMeta = vijf.getItemMeta();
				    vijfMeta.setUnbreakable(true);
				    vijf.setItemMeta(vijfMeta);
				    
				    ItemStack zes = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short)255);
				    ItemMeta zesMeta = zes.getItemMeta();
				    zesMeta.setUnbreakable(true);
				    zes.setItemMeta(zesMeta);
				    
				    ItemStack zeven = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short)256);
				    ItemMeta zevenMeta = zeven.getItemMeta();
				    zevenMeta.setUnbreakable(true);
				    zeven.setItemMeta(zevenMeta);
				    
				    ItemStack acht = new ItemStack(Material.DIAMOND_PICKAXE, 1, (short)257);
				    ItemMeta achtMeta = acht.getItemMeta();
				    achtMeta.setUnbreakable(true);
				    acht.setItemMeta(achtMeta);
				    
					ItemStack telefoon = new ItemStack(Material.GOLD_HOE, 1, (short)1);
			        ItemMeta telMeta = telefoon.getItemMeta();
			        telMeta.setDisplayName("§aGroene telefoon");
			        telMeta.setUnbreakable(true);
			        telefoon.setItemMeta(telMeta);
			        
			        ItemStack telefoon1 = new ItemStack(Material.GOLD_HOE, 1, (short)14);
			        ItemMeta telMeta1 = telefoon.getItemMeta();
			        telMeta1.setDisplayName("§cRode telefoon");
			        telMeta1.setUnbreakable(true);
			        telefoon1.setItemMeta(telMeta1);
			        
			        ItemStack telefoon2 = new ItemStack(Material.GOLD_HOE, 1, (short)2);
			        ItemMeta telMeta2 = telefoon.getItemMeta();
			        telMeta2.setDisplayName("§dRoze telefoon");
			        telMeta2.setUnbreakable(true);
			        telefoon2.setItemMeta(telMeta2);
			        
			        ItemStack telefoon3 = new ItemStack(Material.GOLD_HOE, 1, (short)4);
			        ItemMeta telMeta3 = telefoon.getItemMeta();
			        telMeta3.setDisplayName("§bBlauwe telefoon");
			        telMeta3.setUnbreakable(true);
			        telefoon3.setItemMeta(telMeta3);
			        
			        ItemStack telefoon4 = new ItemStack(Material.GOLD_HOE, 1, (short)7);
			        ItemMeta telMeta4 = telefoon.getItemMeta();
			        telMeta4.setDisplayName("§eGele telefoon");
			        telMeta4.setUnbreakable(true);
			        telefoon4.setItemMeta(telMeta4);
			        
			        ArrayList<String> backpackLore = new ArrayList<String>();
			        backpackLore.add("§8[§eL§8] §7Linker-klik om aan te doen");
			        backpackLore.add("§8[§bR§8] §7Rechter-klik om te openen");
			        
			        ItemStack backpack1 = new ItemStack(Material.CARROT_STICK, 1, (short)20);
					ItemMeta backpack1Meta = backpack1.getItemMeta();
					backpack1Meta.setUnbreakable(true);
					backpack1Meta.setDisplayName("§eGele rugzak");
					backpack1Meta.setLore(backpackLore);
					backpack1.setItemMeta(backpack1Meta);
					
					ItemStack backpack2 = new ItemStack(Material.CARROT_STICK, 1, (short)21);
					ItemMeta backpack1Meta2 = backpack2.getItemMeta();
					backpack1Meta2.setUnbreakable(true);
					backpack1Meta2.setDisplayName("§cRode rugzak");
					backpack1Meta2.setLore(backpackLore);
					backpack2.setItemMeta(backpack1Meta2);
					
					ItemStack backpack3 = new ItemStack(Material.CARROT_STICK, 1, (short)22);
					ItemMeta backpack1Meta3 = backpack3.getItemMeta();
					backpack1Meta3.setUnbreakable(true);
					backpack1Meta3.setDisplayName("§dRoze rugzak");
					backpack1Meta3.setLore(backpackLore);
					backpack3.setItemMeta(backpack1Meta3);
					
					ItemStack backpack4 = new ItemStack(Material.CARROT_STICK, 1, (short)23);
					ItemMeta backpack1Meta4 = backpack4.getItemMeta();
					backpack1Meta4.setUnbreakable(true);
					backpack1Meta4.setDisplayName("§9Blauwe rugzak");
					backpack1Meta4.setLore(backpackLore);
					backpack4.setItemMeta(backpack1Meta4);
					
					ItemStack backpack5 = new ItemStack(Material.CARROT_STICK, 1, (short)24);
					ItemMeta backpack1Meta5 = backpack5.getItemMeta();
					backpack1Meta5.setUnbreakable(true);
					backpack1Meta5.setDisplayName("§aGroene rugzak");
					backpack1Meta5.setLore(backpackLore);
					backpack5.setItemMeta(backpack1Meta5);
				    
					items.setItem(0, een);
					items.setItem(1, twee);
					items.setItem(2, drie);
					items.setItem(3, vier);
					items.setItem(4, vijf);
					items.setItem(5, zes);
					items.setItem(6, zeven);
					items.setItem(7, acht);
					
					items.setItem(8, telefoon);
					items.setItem(9, telefoon1);
					items.setItem(10, telefoon2);
					items.setItem(11, telefoon3);
					items.setItem(12, telefoon4);
					items.setItem(13, backpack1);
					items.setItem(14, backpack2);
					items.setItem(15, backpack3);
					items.setItem(16, backpack4);
					items.setItem(17, backpack5);
					
					player.openInventory(items);
				}
			}
		}
		
		
			if(cmd.getName().equalsIgnoreCase("badge")) {
				Player p = (Player) sender;
				if(p.hasPermission("politiebadge.use")) {
					if(args.length == 0) {
						p.sendMessage(ChatColor.RED + "[PolitieBadge]" + ChatColor.GRAY + " Gebruik: /Badge <speler>");
					} else if(args.length == 1)  {
						ItemStack i = new ItemStack(Material.NAME_TAG,1);
						ItemMeta im = i.getItemMeta();
						im.setDisplayName("§6Politie Badge");
						ArrayList<String> lore = new ArrayList<String>();
						lore.add(ChatColor.AQUA + args[0]);
						im.setLore(lore);
						im.setUnbreakable(false);
						im.addEnchant(Enchantment.MENDING, -1, true);
						im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						i.setItemMeta(im);
						p.getInventory().addItem(i);
						p.sendMessage("§4Badge aangemaakt!");
					} else {
						p.sendMessage(ChatColor.RED + "[PolitieBadge]" + ChatColor.GRAY + " Gebruik: /badge <speler>");
					}
				} else {
					p.sendMessage(ChatColor.RED + "[PolitieBadge]" + ChatColor.GRAY + " Je hebt geen toegang tot dit commando!");
				}
			}
		
	
			if(cmd.getName().equalsIgnoreCase("rijbewijs")) {
				Player p = (Player) sender;
				if(p.hasPermission("rijbewijs.use")) {
					if(args.length == 0) {
						p.sendMessage(ChatColor.RED + "[Rijbewijs]" + ChatColor.GRAY + "Gebruik: /rijbewijs <speler> <motor:auto> <nummerplaat>");
					} else if(args.length == 1) {
						p.sendMessage(ChatColor.RED + "[Rijbewijs]" + ChatColor.GRAY + "Gebruik: /rijbewijs <speler> <motor:auto> <nummerplaat>");
					} else if(args.length == 2) {
						p.sendMessage(ChatColor.RED + "[Rijbewijs]" + ChatColor.GRAY + "Gebruik: /rijbewijs <speler> <motor:auto> <nummerplaat>");
					} else if(args.length == 3) {
						ItemStack i = new ItemStack(Material.DIAMOND_HOE,1, (short)90);
						ItemMeta im = i.getItemMeta();
						im.setDisplayName("§3§lRijbewijs");
						ArrayList<String> lore = new ArrayList<String>();
						lore.add(ChatColor.DARK_AQUA + "Naam: " + ChatColor.GREEN + args[0]);
						lore.add(ChatColor.DARK_AQUA + "Type: " + ChatColor.GREEN  + args[1]);
						lore.add(ChatColor.DARK_AQUA + "Nummerplaat: " + ChatColor.GREEN + args[2]);
						im.setLore(lore);
						im.setUnbreakable(true);
						i.setItemMeta(im);
						p.getInventory().addItem(i);
						p.sendMessage("Rijbewijs aangemaakt!");
					} else {
						p.sendMessage(ChatColor.RED + "[Rijbewijs]" + ChatColor.GRAY + "Gebruik: /rijbewijs <speler> <motor:auto> <nummerplaat>");
					}
				} else {
					p.sendMessage(ChatColor.RED + "[Rijbewijs]" + ChatColor.GRAY + "Je hebt geen toegang tot dit commando!");
				}
			}
			
			if(cmd.getName().equalsIgnoreCase("uuid")) {
				Player player = (Player) sender;
				if(player.hasPermission("uuid.use")) {
					player.sendMessage("§7Je uuid: §3" + player.getUniqueId().toString());
				}
			}
		
	return true;
}
	
}
