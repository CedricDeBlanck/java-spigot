package me.snakebite.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ch.dempsey.mt.api.MTPlayer;
import me.snakebite.selector.main.Main;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;

public class Functions {
	
	
	public static void openMap(Player p) {
		Inventory map = Bukkit.createInventory(p, 45, "§0Kies jou spawn locatie!");
		
		ItemStack water = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)3);
		ItemMeta waterMeta = water.getItemMeta();
		waterMeta.setDisplayName("§0");
		water.setItemMeta(waterMeta);
		
		ItemStack point1 = new ItemStack(Material.WOOL, 1, (short)14);
		ItemMeta pointMeta = point1.getItemMeta();
		pointMeta.setDisplayName("§aGemeentehuis");
		point1.setItemMeta(pointMeta);
		
		ItemStack point2 = new ItemStack(Material.WOOL, 1, (short)14);
		ItemMeta point2Meta = point2.getItemMeta();
		point2Meta.setDisplayName("§aStation");
		point2.setItemMeta(point2Meta);
		
		ItemStack point3 = new ItemStack(Material.WOOL, 1, (short)14);
		ItemMeta point3Meta = point3.getItemMeta();
		point3Meta.setDisplayName("§aHaven");
		point3.setItemMeta(point3Meta);
		
		ItemStack point4 = new ItemStack(Material.WOOL, 1, (short)14);
		ItemMeta point4Meta = point4.getItemMeta();
		point4Meta.setDisplayName("§aIndustrie");
		point4.setItemMeta(point4Meta);
		
		ItemStack point5 = new ItemStack(Material.WOOL, 1, (short)14);
		ItemMeta point5Meta = point5.getItemMeta();
		point5Meta.setDisplayName("§aOCPlein");
		point5.setItemMeta(point5Meta);
		
		map.setItem(0, water);
		map.setItem(1, water);
		map.setItem(2, water);
		map.setItem(3, water);
		map.setItem(4, water);
		map.setItem(5, water);
		map.setItem(6, water);
		map.setItem(7, water);
		map.setItem(8, water);
		map.setItem(9, water);
		map.setItem(10, point1);
		map.setItem(11, water);
		map.setItem(12, water);
		map.setItem(13, water);
		map.setItem(14, water);
		map.setItem(15, water);
		map.setItem(16, point2);
		map.setItem(17, water);
		map.setItem(18, water);
		map.setItem(19, water);
		map.setItem(20, water);
		map.setItem(21, water);
		map.setItem(22, point3);
		map.setItem(23, water);
		map.setItem(24, water);
		map.setItem(25, water);
		map.setItem(26, water);
		map.setItem(27, water);
		map.setItem(28, point4);
		map.setItem(29, water);
		map.setItem(30, water);
		map.setItem(31, water);
		map.setItem(32, water);
		map.setItem(33, water);
		map.setItem(34, point5);
		map.setItem(35, water);
		map.setItem(36, water);
		map.setItem(37, water);
		map.setItem(38, water);
		map.setItem(39, water);
		map.setItem(40, water);
		map.setItem(41, water);
		map.setItem(42, water);
		map.setItem(43, water);
		map.setItem(44, water);
		
		p.openInventory(map);
	}
	
	public static void Slager(Player p) {
		// mtselector.selected
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Slager");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Bakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Mijner");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Houthakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Smid");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Boer");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Imker");

		Bukkit.getScheduler().runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("JobSelectorMT"), new Runnable() {

			@Override
			public void run() {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group add Slager");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mtselector.selected");
		
		ItemStack sword = new ItemStack(Material.WOOD_SWORD, 1);
		ItemStack food = new ItemStack(Material.COOKED_CHICKEN, 32);
		
		p.getInventory().addItem(sword);
		p.getInventory().addItem(food);

		openMap(p);	
		
			}
		}, 20);
	}
	
	public static void Bakker(Player p) {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Slager");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Bakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Mijner");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Houthakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Smid");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Boer");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Imker");

		Bukkit.getScheduler().runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("JobSelectorMT"), new Runnable() {

			@Override
			public void run() {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group add Bakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mtselector.selected");
		
		ItemStack seeds = new ItemStack(Material.WHEAT, 32);
		ItemStack food = new ItemStack(Material.BREAD, 32);
		
		p.getInventory().addItem(seeds);
		p.getInventory().addItem(food);
		
		openMap(p);
		
			}
		}, 20);
	}
	
	public static void Mijner(Player p) {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Slager");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Bakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Mijner");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Houthakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Smid");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Boer");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Imker");

		Bukkit.getScheduler().runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("JobSelectorMT"), new Runnable() {

			@Override
			public void run() {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group add Mijner");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mtselector.selected");
		
		ItemStack seeds = new ItemStack(Material.STONE_PICKAXE, 1);
		ItemStack food = new ItemStack(Material.COOKED_MUTTON, 32);
		
		p.getInventory().addItem(seeds);
		p.getInventory().addItem(food);
		
		openMap(p);
		
			}
		}, 20);
	}
	
	public static void Houthakker(Player p) {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Slager");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Bakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Mijner");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Houthakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Smid");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Boer");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Imker");

		Bukkit.getScheduler().runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("JobSelectorMT"), new Runnable() {

			@Override
			public void run() {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group add Houthakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mtselector.selected");
		
		ItemStack seeds = new ItemStack(Material.STONE_AXE, 1);
		ItemStack food = new ItemStack(Material.BAKED_POTATO, 32);
		
		p.getInventory().addItem(seeds);
		p.getInventory().addItem(food);
		
		openMap(p);
		
			}
		}, 20);
	}
	
	public static void Smid(Player p) {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Slager");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Bakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Mijner");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Houthakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Smid");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Boer");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Imker");

		Bukkit.getScheduler().runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("JobSelectorMT"), new Runnable() {

			@Override
			public void run() {
		
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group add Smid");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mtselector.selected");
			
			ItemStack seeds = new ItemStack(Material.COBBLESTONE, 32);
			ItemStack food = new ItemStack(Material.STRING, 32);
			ItemStack stickItemStack = new ItemStack(Material.STICK, 16);
			ItemStack foodItem = new ItemStack(Material.BAKED_POTATO, 32);
	
			p.getInventory().addItem(seeds);
			p.getInventory().addItem(food);
			p.getInventory().addItem(stickItemStack);
			p.getInventory().addItem(foodItem);
	
			openMap(p);
		
			}
		}, 20);
	}
	
	public static void Boer(Player p) {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Slager");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Bakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Mijner");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Houthakker");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Smid");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Boer");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Imker");

		Bukkit.getScheduler().runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("JobSelectorMT"), new Runnable() {

				@Override
				public void run() {
			
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group add Boer");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mtselector.selected");
					
					ItemStack seeds = new ItemStack(Material.STONE_HOE, 1);
					ItemStack food = new ItemStack(Material.SEEDS, 16);
					ItemStack stickItemStack = new ItemStack(Material.CARROT_ITEM, 16);
					ItemStack foodItem = new ItemStack(Material.POTATO_ITEM, 16);
			
					p.getInventory().addItem(seeds);
					p.getInventory().addItem(food);
					p.getInventory().addItem(stickItemStack);
					p.getInventory().addItem(foodItem);
					
					openMap(p);
				}
		}, 20);
		
	}
	
	public static void Imker(Player p) {
		
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Slager");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Bakker");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Mijner");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Houthakker");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Smid");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Boer");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group remove Imker");

			Bukkit.getScheduler().runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("JobSelectorMT"), new Runnable() {

				@Override
				public void run() {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " group add Imker");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mtselector.selected");
					
					ItemStack stickItemStack = new ItemStack(Material.STICK, 1);
					ItemMeta stickItemMeta = stickItemStack.getItemMeta();
					stickItemMeta.setDisplayName("§eHoningstok");
					stickItemStack.setItemMeta(stickItemMeta);
					
					ItemStack foodItem = new ItemStack(Material.CARROT_ITEM, 64);
					ItemMeta foodItemMeta = foodItem.getItemMeta();
					foodItemMeta.setDisplayName("§eHoning");
					foodItem.setItemMeta(foodItemMeta);
					
					p.getInventory().addItem(stickItemStack);
					p.getInventory().addItem(foodItem);
					
					openMap(p);
				}
				
			}, 20);
			
	}
	
	public static void Solliciteren(Player p) {
		String politie = "{\"text\":\"[Politie]\",\"color\":\"blue\",\"bold\":\"true\",\"underlined\":\"false\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://discord.gg/SnstaTE\"}}]";
		String ambulance = "{\"text\":\"[Ambulance]\",\"color\":\"yellow\",\"bold\":\"true\",\"underlined\":\"false\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://discord.gg/SnstaTE\"}}]";		
		String anwb = "{\"text\":\"[Anwb]\",\"color\":\"orange\",\"bold\":\"true\",\"underlined\":\"false\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://discord.gg/SnstaTE\"}}]";
		String brandweer = "{\"text\":\"[Brandweer]\",\"color\":\"red\",\"bold\":\"true\",\"underlined\":\"false\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://discord.gg/SnstaTE\"}}]";
		String advocaat = "{\"text\":\"[Advocaat]\",\"color\":\"green\",\"bold\":\"true\",\"underlined\":\"false\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://discord.gg/SnstaTE\"}}]";
		p.sendMessage("§9Solliciteren §f| §9Klik op de dienst die je graag voor wilt solliciteren!");
		p.sendMessage("§1-------------");
		PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a(politie));
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
		
		PacketPlayOutChat packet1 = new PacketPlayOutChat(ChatSerializer.a(ambulance));
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet1);
		
		PacketPlayOutChat packet2 = new PacketPlayOutChat(ChatSerializer.a(anwb));
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet2);
		
		PacketPlayOutChat packet3 = new PacketPlayOutChat(ChatSerializer.a(brandweer));
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet3);
		
		PacketPlayOutChat packet4 = new PacketPlayOutChat(ChatSerializer.a(advocaat));
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet4);
		
		p.sendMessage("§c§lSelecteer eerst een normale job vooraleer je solliciteert bij een overheidsfunctie! Tot die tijd kan je dan geld verdienen!");
	}

	
	
	
}
