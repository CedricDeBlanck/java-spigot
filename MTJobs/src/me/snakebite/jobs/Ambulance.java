package me.snakebite.jobs;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;

public class Ambulance implements Listener {
		
	    private ProtocolManager manager;
	 
	    // Just a way to record if a player is asleep
	    private Set<Player> sleeping = Collections.newSetFromMap(new WeakHashMap<Player, Boolean>());
	 
	    public void onEnable() {
	        manager = ProtocolLibrary.getProtocolManager();
	    }
	 
	    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	        if (sender instanceof Player) {
	            Player player = (Player) sender;
	 
	            if (sleeping.add(player)) {
	                playSleepAnimation(player);
	            } else {
	                stopSleepAnimation(player);
	                sleeping.remove(player);
	            }  
	        }
	        return true;
	    }
	 
	    /**
	    * Play the sleep animation for every nearby player.
	    * @param alseep - the player asleep.
	    */
	    private void playSleepAnimation(Player asleep) {
	        @SuppressWarnings("deprecation")
			final PacketContainer bedPacket = manager.createPacket(PacketType.Play.Server.BED, false);
	        final Location loc = asleep.getLocation();
	 
	        // [url]http://wiki.vg/Protocol#Use_Bed[/url]
	        bedPacket.getEntityModifier(asleep.getWorld()).
	            write(0, asleep);
	        bedPacket.getIntegers().
	            write(1, loc.getBlockX()).
	            write(2, loc.getBlockY() + 1).
	            write(3, loc.getBlockZ());
	 
	        broadcastNearby(asleep, bedPacket);
	    }
	 
	    private void stopSleepAnimation(Player sleeping) {
	        final PacketContainer animation = manager.createPacket(PacketType.Play.Server.ANIMATION, false);
	 
	        // [url]http://wiki.vg/Protocol#Animation[/url]
	        animation.getEntityModifier(sleeping.getWorld()).
	            write(0, sleeping);
	        animation.getIntegers().
	            write(1, 2);
	 
	        broadcastNearby(sleeping, animation);
	    }
	 
	    private void broadcastNearby(Player asleep, PacketContainer bedPacket) {
	        for (Player observer : manager.getEntityTrackers(asleep)) {
	            try {
	                manager.sendServerPacket(observer, bedPacket);
	            } catch (InvocationTargetException e) {
	                throw new RuntimeException("Cannot send packet.", e);
	            }
	        }
	    }
	    
	    @EventHandler
	    public void onUserClick(PlayerInteractEntityEvent e) {
	    	
	    	ItemStack healChecker = new ItemStack(Material.CHORUS_FRUIT_POPPED, 1);
	    	ItemMeta healMeta = healChecker.getItemMeta();
	    	healMeta.setDisplayName("§5Stethoscoop");
	    	healChecker.setItemMeta(healMeta);
	    	
	    	Player player = e.getPlayer();
	    	
	    	if(e.getPlayer().getInventory().getItemInMainHand().equals(healChecker)) {
	    		if(e.getPlayer().hasPermission("revive.use")) {
	    				if(e.getRightClicked() instanceof Player) {
	    			    	Player target = (Player) e.getRightClicked();

	    					player.sendMessage("§a" + e.getRightClicked().getName() + " : §f" + target.getHealth() + "§c❤");
	    				}
	    		}
	    	}
	    }
	
}
