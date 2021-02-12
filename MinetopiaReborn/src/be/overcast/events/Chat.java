package be.overcast.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import be.overcast.commands.Commands;
import be.overcast.minetopia.Main;
import files.Config;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Chat extends ListenerAdapter implements Listener{
	
		static Config config = new Config();

	
		@EventHandler(priority=EventPriority.HIGHEST)
		public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
					
			if(e.getPlayer().hasPermission("politie.radio")) {
				if(Commands.politieRadio.contains(e.getPlayer())) {
					for(Player p : Commands.politieRadio) {
						p.sendMessage("§8(§9Politie Radio§8) §b§l" + e.getPlayer().getName() + " §8: §c§l" + e.getMessage());
						e.setCancelled(true);
					}
				}else {
					return;
				}
			}	
		}
		
		@EventHandler(priority = EventPriority.HIGHEST)
		public void ambuRadio(AsyncPlayerChatEvent e) {
			if(e.getPlayer().hasPermission("ambulance.radio")) {
				if(Commands.ambulanceRadio.contains(e.getPlayer())) {
					for(Player p : Commands.ambulanceRadio) {
						p.sendMessage("§8(§eAmbulance Radio§8) §b§l" + e.getPlayer().getName() + " §8: §c§l" + e.getMessage());
						e.setCancelled(true);
					}
				}else {
					return;
				}
			} 
		}
		
		@EventHandler(priority = EventPriority.HIGHEST)
		public void brandweerRadio(AsyncPlayerChatEvent e) {
			if(e.getPlayer().hasPermission("brandweer.radio")) {
				if(Commands.brandweerRadio.contains(e.getPlayer())) {
					for(Player p : Commands.brandweerRadio) {
						p.sendMessage("§8(§cBrandweer Radio§8) §b§l" + e.getPlayer().getName() + " §8: §c§l" + e.getMessage());
						e.setCancelled(true);
					}
				}else {
					return;
				}
			}
		}
		
		@EventHandler(priority = EventPriority.HIGHEST)
		public void anwbRadio(AsyncPlayerChatEvent e) {
			if(e.getPlayer().hasPermission("anwb.radio")) {
				if(Commands.anwbRadio.contains(e.getPlayer())) {
					for(Player p : Commands.anwbRadio) {
						p.sendMessage("§8(§6ANWB Radio§8) §b§l" + e.getPlayer().getName() + " §8: §c§l" + e.getMessage());
						e.setCancelled(true);
					}
				}else {
					return;
				}
			}
		}
		
		public void onMessageReceived(MessageReceivedEvent event) {


			TextChannel pChannel = Main.jda.getTextChannelById(config.getPolitieRadioChannel());
			TextChannel aChannel = Main.jda.getTextChannelById(config.getAmbulanceRadioChannel());
			TextChannel bChannel = Main.jda.getTextChannelById(config.getBrandweerRadioChannel());
			TextChannel anwbChannel = Main.jda.getTextChannelById(config.getAnwbRadioChannel());		
			
	    	if(event.getTextChannel() == pChannel) {
	            if (event.getAuthor().isBot()) return;
		            for(Player p : Commands.politieRadio) {
		            	if(p.hasPermission("politie.radio")) {
			            	p.sendMessage("§8[§9Discord Radio Politie§8] §b" + event.getMember().getUser().getName()  + "§8: §c§l" + event.getMessage().getContentRaw());
		            	} else {
							return;
						}
		            }
	    	}
	    	
	    	if(event.getChannel() == aChannel) {
	    		if(event.getAuthor().isBot()) return;
	    		for(Player p : Commands.ambulanceRadio) {
	    			if(p.hasPermission("ambulance.radio")) {
		            	p.sendMessage("§8[§eDiscord Radio Ambulance§8] §b" + event.getMember().getUser().getName()  + "§8: §c§l" + event.getMessage().getContentRaw());
	    			} else {
	    				return;
	    			}
	    		}
	    	}
	    	
	    	if(event.getChannel() == bChannel) {
	    		if(event.getAuthor().isBot()) return;
	    		for(Player p : Commands.brandweerRadio) {
	    			if(p.hasPermission("brandweer.radio")) {
		            	p.sendMessage("§8[§cDiscord Radio Brandweer§8] §b" + event.getMember().getUser().getName()  + "§8: §c§l" + event.getMessage().getContentRaw());
	    			} else {
	    				return;
	    			}
	    		}
	    	}
	    	
	    	if(event.getChannel() == anwbChannel) {
	    		if(event.getAuthor().isBot()) return;
	    		for(Player p : Commands.anwbRadio) {
	    			if(p.hasPermission("anwb.radio")) {
		            	p.sendMessage("§8[§6Discord Radio ANWB§8] §b" + event.getMember().getUser().getName()  + "§8: §c§l" + event.getMessage().getContentRaw());
	    			} else {
	    				return;
	    			}
	    		}
	    	}
    	}
}