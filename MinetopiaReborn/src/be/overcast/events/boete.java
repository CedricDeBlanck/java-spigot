package be.overcast.events;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;



public class boete implements Listener {
	
	public static HashMap<UUID, Integer> boetes = new HashMap<UUID, Integer>();

	
	public boete() {}

	public static void add(Player player, int i) {
		UUID uuid = player.getUniqueId();

		if(!boetes.containsKey(uuid)) {
			boetes.put(uuid, i);
		}else {
			int c = boetes.get(uuid);
			boetes.put(uuid, c+i);
		}
	}
	
	public static boolean filled(Player player) {
		UUID uuid = player.getUniqueId();

		if(boetes.containsKey(uuid)) {
			return boetes.get(uuid) >= 10;
		}else {
			return false;
		}
	}
	
	public static void remove(Player player) {
		UUID uuid = player.getUniqueId();
		boetes.remove(uuid);
	}
		
}
