package be.overcast.minetopia;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import be.overcast.commands.Addpermission;
import be.overcast.commands.Commands;
import be.overcast.commands.Discord;
import be.overcast.commands.Facturen;
import be.overcast.commands.GetItem;
import be.overcast.commands.Guns;
import be.overcast.commands.Prefix;
import be.overcast.commands.Shop;
import be.overcast.commands.Vote;
import be.overcast.events.Chat;
import be.overcast.events.Events;
import files.Config;
import files.Data;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.TextChannel;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin implements Listener, CommandExecutor {

	// DISCORD BOT
	public final String TOKEN = "Nzc2ODMwNTA5MzY3MDMzODY2.X66lyg.IYv8drEfH8NA1fYOrGQfIQ5SGVo";
	public static JDA jda;
	public static String prefix = "!";

	// MINECRAFT PLUGIN
	public WorldGuardPlugin worldGuardPlugin;
	private static final Logger log = Logger.getLogger("Minecraft");
	public static Economy econ = null;
	private static Permission perms = null;
	public static Player player;
	Plugin p;
    static Main instance;
	static Config config = new Config();


	// ARRAYS
	public ArrayList<Player> frozen = new ArrayList<Player>();
	public ArrayList<Player> messages = new ArrayList<Player>();

	ArrayList<Player> frozen1 = new ArrayList<Player>();
    public static String prefix1 = ChatColor.GRAY + "[" + ChatColor.BLUE + "OCFreezer" + ChatColor.GRAY + "]";
	
    
	@Override
	public void onEnable() {
		
		System.out.println("===============================================================");
		System.out.println("====================== MINETOPIA REBORN =======================");
		System.out.println("===============================================================");
		System.out.println("============================== BY =============================");
		System.out.println("===============================================================");
		System.out.println("=================== Dempsey_ & Snakebite2205 ==================");
		System.out.println("===============================================================");
		System.out.println("====================== Plugin Version: 1.0 ====================");
		System.out.println("===============================================================");
		
		//Config
		config.start(this);
		config.init(this);
		
		// Events
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new Events(), this);
		getServer().getPluginManager().registerEvents(new Chat(), this);

		// Commands
		getCommand("boete").setExecutor(new Commands());	
		getCommand("pr").setExecutor(new Commands());
		getCommand("ar").setExecutor(new Commands());
		getCommand("br").setExecutor(new Commands());
		getCommand("items").setExecutor(new Commands());
		getCommand("setlevel").setExecutor(new Commands());
		getCommand("noodmelding").setExecutor(new Commands());
		getCommand("stuurbericht").setExecutor(new Commands());
		getCommand("badge").setExecutor(new Commands());
		getCommand("rijbewijs").setExecutor(new Commands());
		getCommand("anwbr").setExecutor(new Commands());
		getCommand("mijnrole").setExecutor(new Commands());
		getCommand("prefix").setExecutor(new Prefix());
		getCommand("uuid").setExecutor(new Commands());
		getCommand("getitem").setExecutor(new GetItem());
		getCommand("factuur").setExecutor(new Facturen());
		getCommand("facturen").setExecutor(new Facturen());
		getCommand("geefwapen").setExecutor(new Guns());
		getCommand("permissionsadd").setExecutor(new Addpermission());
		getCommand("discord").setExecutor(new Discord());
		getCommand("vote").setExecutor(new Vote());
		getCommand("permissionsremove").setExecutor(new Addpermission());
		getCommand("winkel").setExecutor(new Shop());
		getCommand("shop").setExecutor(new Shop());

		start();
		
		worldGuardPlugin = getWorldGuard();
		
		if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		
        setupPermissions();
				
		Main.instance = this;
		
	
				

				
	}
	
	// END OF ONENABLE
	@Override
	public void onDisable() {
		this.saveConfig();
		
	}
	
    public static Main getInstance() {
        return instance;
    }
	
	private void start() {
		try {
			jda = new JDABuilder(AccountType.BOT).setToken(TOKEN).build(); 
			jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
			jda.getPresence().setGame(Game.playing("play.minetopia.be"));
			jda.addEventListener(new Chat());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		TextChannel tc = jda.getTextChannelById(config.getTextChannel());
		tc.sendMessage("> **" + player.getName() + "** heeft de server betreden! :green_circle:").queue();
		
		
		Data.setup(this, event.getPlayer());
		
		
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		TextChannel tc = jda.getTextChannelById(config.getTextChannel());
		tc.sendMessage("> **" + player.getName() + "** heeft de server verlaten! :red_circle:").queue();

	}
	
 	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();
		
		double x = Math.round(player.getLocation().getX());
		double y = Math.round(player.getLocation().getY());
		double z = Math.round(player.getLocation().getZ());

		String xString = String.valueOf(x);
		String yString = String.valueOf(y);
		String zString = String.valueOf(z);
	
		
		if(Commands.politieRadio.contains(event.getPlayer())) {
			TextChannel tc = jda.getTextChannelById(config.getPolitieRadioChannel());
			tc.sendMessage("[Politie Radio] **" + player.getName() + "** >> " + event.getMessage()).queue();
		} else if(Commands.ambulanceRadio.contains(event.getPlayer())) {
			TextChannel tc = jda.getTextChannelById(config.getAmbulanceRadioChannel());
			tc.sendMessage("[Ambulance Radio] **" + player.getName() + "** >> " + event.getMessage()).queue();
		} else if(Commands.brandweerRadio.contains(event.getPlayer())) {
			TextChannel tc = jda.getTextChannelById(config.getBrandweerRadioChannel());
			tc.sendMessage("[Brandweer Radio] **" + player.getName() + "** >> " + event.getMessage()).queue();
		} else if(Commands.anwbRadio.contains(event.getPlayer())) {
			TextChannel tc = jda.getTextChannelById(config.getAnwbRadioChannel());
			tc.sendMessage("[ANWB Radio] **" + player.getName() + "** >> " + event.getMessage()).queue();
		} else if(Events.politieMelding.contains(player)) {
			event.setCancelled(true);
			
			for(Player p : Commands.politieRadio) {
				p.sendMessage("§7[§9Melding§7]§c " + event.getPlayer().getName() + "(" + " x:" + xString + " y:" + yString + " z:"  + zString + " ) §7Oproep: §c" + event.getMessage());
			}
			
			TextChannel tc = jda.getTextChannelById(config.getPolitieChannel());
			tc.sendMessage("[**MELDING**] **" + player.getName() + "**" +  "(" + " x:" + xString + " y:" + yString + " z:"  + zString + " ) >> " + event.getMessage()).queue();
			
			player.sendMessage("§aDe §1politie §ais op de hoogte!");
			
			Events.politieMelding.remove(player);
		} else if(Events.ambulanceMelding.contains(player)) {
			event.setCancelled(true);
			
			for(Player p : Commands.ambulanceRadio) {
				p.sendMessage("§7[§9Melding§7]§c " + event.getPlayer().getName() + "(" + " x:" + xString + " y:" + yString + " z:"  + zString + " ) §7Oproep: §c" + event.getMessage());
			}
			
			TextChannel tc = jda.getTextChannelById(config.getAmbulanceChannel());
			tc.sendMessage("[**MELDING**] **" + player.getName() + "**" +  "(" + " x:" + xString + " y:" + yString + " z:"  + zString + " ) >> " + event.getMessage()).queue();
			
			player.sendMessage("§aDe §eambulance §ais op de hoogte!");

			Events.ambulanceMelding.remove(player);
		} else if(Events.brandweerMelding.contains(player)) {
			event.setCancelled(true);
			
			for(Player p : Commands.brandweerRadio) {
				p.sendMessage("§7[§9Melding§7]§c " + event.getPlayer().getName() + "(" + " x:" + xString + " y:" + yString + " z:"  + zString + " ) §7Oproep: §c" + event.getMessage());
			}

			TextChannel tc = jda.getTextChannelById(config.getBrandweerChannel());
			tc.sendMessage("[**MELDING**] **" + player.getName() + "**" +  "(" + " x:" + xString + " y:" + yString + " z:"  + zString + " ) >> " + event.getMessage()).queue();
			
			player.sendMessage("§aDe §cbrandweer §ais op de hoogte!");
			
			Events.brandweerMelding.remove(player);
		} else if(Events.anwbMelding.contains(player)) {
			event.setCancelled(true);
			
			for(Player p : Commands.anwbRadio) {
				p.sendMessage("§7[§9Melding§7]§c " + event.getPlayer().getName() + "(" + " x:" + xString + " y:" + yString + " z:"  + zString + " ) §7Oproep: §c" + event.getMessage());
			}

			TextChannel tc = jda.getTextChannelById(config.getAnwbChannel());
			tc.sendMessage("[**MELDING**] **" + player.getName() + "**" +  "(" + " x:" + xString + " y:" + yString + " z:"  + zString + " ) >> " + event.getMessage()).queue();
			
			player.sendMessage("§aDe §6ANWB §ais op de hoogte!");
			
			Events.anwbMelding.remove(player);
		} else {
		
		
			TextChannel tc = jda.getTextChannelById(config.getTextChannel());
			tc.sendMessage("**" + player.getName() + "** >> " + event.getMessage()).queue();
		}
 	}
	
    public boolean setupEconomy() {
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
    
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	
	public WorldGuardPlugin getWorldGuard() {
		Plugin plugin = this.getServer().getPluginManager().getPlugin("WorldGuard");
		
		if(plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}
		
		return (WorldGuardPlugin) plugin;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(frozen.contains(p)) {
			e.setTo(e.getFrom());
			p.sendMessage(prefix + " " + ChatColor.RED + "Je bent bevroren!");
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§4Je kan deze alleen in-game gebruiken!");
		} else {
			Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("aanhoudingsbevel")) {
				if(p.hasPermission("politie.bevel")) {
					if(args.length == 0) {
						p.sendMessage(ChatColor.DARK_AQUA + "[Politie]" + ChatColor.GRAY + " Gebruik: /aanhoudingsbevel <speler> <reden>");
					} else if (args.length > 1) {
						
						TextChannel tc = jda.getTextChannelById(config.getPolitieAanhoudingsBevelen());
						tc.sendMessage("Tags: <@&786323131534278658>").queue();;
						
						  
						        //retrieve the first argument as a player
						        Player target = Bukkit.getServer().getPlayer(args[0]);
						        String sm = "";

						        //combine the arguments the player typed
						        for (int i = 1; i < args.length; i++){
						            String arg = (args[i] + " ");
						            sm = (sm + arg);
						        }
						        
						        EmbedBuilder eb = new EmbedBuilder();
								eb.setTitle("Aanhoudingsbevel");
								eb.addField("**Naam**: ", target.getName(),false);
								eb.addField("**Uitzender**:",p.getName(), false);
								eb.addField("**Reden**:", sm, false);
								//eb.addField("**STATUS:**", "Betaald", false);
								eb.setColor(Color.CYAN);
								//tc.sendMessage("@everyone\n**MELDING**:\n**Naam**:"+s.getName()+"\n**X**:"+ s.getLocation().getX() + "\n**Y**:" + s.getLocation().getY() + "\n**Z**:" + s.getLocation().getZ() + "\n"+ message).complete();
								tc.sendMessage(eb.build()).complete();
								//sender.sendMessage("§8(§b!§8) §4112 §b>> §6Hulpdiensten zijn op de hoogte!");
								eb.clear();
						    
						
								p.sendMessage(ChatColor.DARK_AQUA + "[Politie]" + ChatColor.GRAY + " Aanhoudingsbevel voor " + ChatColor.DARK_AQUA + args[0] + ChatColor.GRAY + " aangemaakt! Reden: " + ChatColor.DARK_AQUA + sm);

						 
					} else {
						p.sendMessage(ChatColor.DARK_AQUA + "[Politie]" + ChatColor.GRAY + " Gebruik: /aanhoudingsbevel <speler> <reden>");
					}
				} else {
					p.sendMessage("§4Je hebt geen toegang tot die commando!");
				}
			} 
		}
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("§4Je kan deze alleen in-game gebruiken!");
		} else {
			Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("notitie")) {
				if(p.hasPermission("politie.notitie")) {
					if(args.length == 0) {
						p.sendMessage(ChatColor.DARK_AQUA + "[Politie]" + ChatColor.GRAY + " Gebruik: /notitie <speler> <bedrag> <reden>");
					} else if(args.length == 1) {
						p.sendMessage(ChatColor.DARK_AQUA + "[Politie]" + ChatColor.GRAY + " Gebruik: /notitie <speler> <bedrag> <reden>");
					} else if(args.length == 2) {
						p.sendMessage(ChatColor.DARK_AQUA + "[Politie]" + ChatColor.GRAY + " Gebruik: /notitie <speler> <bedrag> <reden>");
					} else if(args.length > 2) {
						TextChannel tc = jda.getTextChannelById(config.getPolitieNotitieChannel());
						
						 Player target = Bukkit.getServer().getPlayer(args[0]);
					        String sm = "";

					        //combine the arguments the player typed
					        for (int i = 2; i < args.length; i++){
					            String arg = (args[i] + " ");
					            sm = (sm + arg);
					        }
						
						EmbedBuilder eb = new EmbedBuilder();
							eb.setTitle("Notitie");
							eb.addField("**Naam:**", target.getName(),false);
							eb.addField("**Agent:**", p.getName(), false);
							eb.addField("**Reden**", sm, false);
							eb.addField("**Bedrag:**", args[1], false);
							eb.setColor(Color.GREEN);
							tc.sendMessage(eb.build()).complete();
							eb.clear();
							
						p.sendMessage(ChatColor.DARK_AQUA + "[Politie]" + ChatColor.GRAY + " Notitie van " + ChatColor.DARK_AQUA + args[0] + ChatColor.GRAY + " aangemaakt! Reden: " + ChatColor.DARK_AQUA + sm + ChatColor.GRAY + " Bedrag: " + ChatColor.DARK_AQUA + args[1] );

					} else {
						p.sendMessage(ChatColor.DARK_AQUA + "[Politie]" + ChatColor.GRAY + " Gebruik: /notitie <speler> <bedrag> <reden>");
					}
				} else {
					p.sendMessage("§4Je hebt geen toegang tot die commando!");
				}
			}
		}
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("§4Je kan deze alleen in-game gebruiken!");
		} else {
			Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("politie")) {
				if(p.hasPermission("politie.use")) {
					if(args.length == 0) {
						p.sendMessage("§7Politie help commando's:");
						p.sendMessage("§9------------------------------------");
						p.sendMessage("§9- §7/politie §9- §7politie.use");
						p.sendMessage("§9- §7/boete lijst §9- §7politie.boetelijst");
						p.sendMessage("§9- §7/boete <speler> <boetenaam> §9- §7politie.boete");
						p.sendMessage("§9- §7/aanhoudingsbevel <speler> <reden> §9- §7politie.bevel");
						p.sendMessage("§9- §7/notitie <speler> <reden> <bedrag> §9- §7politie.notitie");
						p.sendMessage("§9------------------------------------");
					} else if(args.length == 1 && args[0].equalsIgnoreCase("help")) {
						p.sendMessage("§7Politie help commando's:");
						p.sendMessage("§9------------------------------------");
						p.sendMessage("§9- §7/politie §9- §7politie.use");
						p.sendMessage("§9- §7/boete lijst §9- §7politie.boetelijst");
						p.sendMessage("§9- §7/boete <speler> <boetenaam> §9- §7politie.boete");
						p.sendMessage("§9- §7/aanhoudingsbevel <speler> <reden> §9- §7politie.bevel");
						p.sendMessage("§9- §7/notitie <speler> <reden> <bedrag> §9- §7politie.notitie");
						p.sendMessage("§9------------------------------------");
					}
				} else {
					p.sendMessage("§4Je hebt geen toegang tot die commando!");
				}
			} else if(cmd.getName().equalsIgnoreCase("report")) {
				if(args.length == 0) {
					p.sendMessage("§cGebruik: /report <bericht>");
				} else if(args.length > 0) {
					String sm = "";

			        //combine the arguments the player typed
			        for (int i = 1; i < args.length; i++){
			            String arg = (args[i] + " ");
			            sm = (sm + arg);
			        }
			        			        
			        TextChannel tc = jda.getTextChannelById(config.getReportChannel());					

			        EmbedBuilder eb = new EmbedBuilder();
						eb.setTitle("Report");
						eb.addField("**Naam:**", p.getName(), false);
						eb.addField("**Bericht**",args[0] + " " + sm, false);
						eb.setColor(Color.RED);
						tc.sendMessage(eb.build()).complete();
						eb.clear();
					
				    p.sendMessage("§7[§3Overcast§7] §3Jou verzonden report: §c" + args[0] +  " " + sm);
				    
				    for(Player staff : Bukkit.getOnlinePlayers()) {
				    	if(staff.hasPermission("report.receive")) {
				    		staff.sendMessage("§7[§3Overcast§7] §2" + p.getName() +  ": §c" + args[0] + " " + sm);
				    	}
				    }

				}
			}
		}
					
			Player p = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("freeze")) {
				if(sender instanceof Player) {
					
				
				if(p.hasPermission("playerfreeze.freeze")) {
					if(args.length == 0) {
						p.sendMessage(prefix1 + " " + ChatColor.GOLD + "Je hebt geen speler genoemd!");
						return true;
					}
					
					
					if(Bukkit.getServer().getPlayer(args[0]) == null) {
						p.sendMessage(prefix1 + " " + ChatColor.RED + "Speler" + " " + ChatColor.GRAY + args[0] + " " + ChatColor.RED + "niet gevonden!");
						return true;
					}
					
					Player t = Bukkit.getServer().getPlayer(args[0]);

					
					if(frozen1.contains(t)) {
						frozen1.remove(t);
						p.sendMessage(prefix1 + " " + ChatColor.BLUE + args[0] + " " + "is niet meer bevroren!");
						t.sendMessage(prefix1 + " " + ChatColor.BLUE + "Je bent niet meer bevroren door" + " " + p.getName() + "!");
						return true;
					} else {
						frozen1.add(t);
						p.sendMessage(prefix1 + " " + ChatColor.BLUE + args[0] + " " + "is nu bevroren!");
						t.sendMessage(prefix1 + " " + ChatColor.BLUE + "Je bent bevroren door" + " " + p.getName() + "!");
					}
				} else {
					p.sendMessage(" " + ChatColor.RED + "Je hebt geen toegang tot dit commando!");
					return true;
				}
			}
				
			}
			
		return true;
	}

	
		
}
	
