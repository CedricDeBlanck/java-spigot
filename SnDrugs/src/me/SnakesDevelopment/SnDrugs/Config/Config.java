package me.SnakesDevelopment.SnDrugs.Config;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {

	public static Plugin p;
	
	public Config() {
		
	}
	
	public Plugin getPlugin() {
		return p;
	}
	
	public void start(Plugin pl) {
		Config.p = pl;
		createConfig(pl);
	}
	
	public void init(Plugin pl) {
		if(!pl.getDataFolder().exists()) {
			pl.getDataFolder().mkdir();
			Bukkit.getServer().getConsoleSender().sendMessage("[SnDrugs] Creating SnDrugs folder...");
			Bukkit.getServer().getConsoleSender().sendMessage("[SnDrugs] Succesfully created folder");
		}
	}
	
	public static File f;
	
	
	File file;
	
	public static void createConfig(Plugin pl) {
		File configFile = new File(pl.getDataFolder(), "config.yml");

		if(!configFile.exists()) {
			p.saveResource("config.yml", false);
			Bukkit.getServer().getConsoleSender().sendMessage("[SnDrugs] Creating config file...");
			Bukkit.getServer().getConsoleSender().sendMessage("[SnDrugs] Succesfully created config file");
		} else {
			return;
		}
		
		
	}

	
	public static FileConfiguration readConfig(Plugin pl) {
		
		File res = new File(pl.getDataFolder(), "config.yml");

		if(res.exists()) {
			if(writeCheck(res)) {
				FileConfiguration resconf = YamlConfiguration.loadConfiguration(res);
				return resconf;
			}else {
				return null;
			}
		} else {
			return null;
		}
		
		
		
	}
	
	public  void reload(FileConfiguration conf) {
		try {
			conf.save(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		YamlConfiguration.loadConfiguration(file);
	}
	
	public void save(FileConfiguration conf) {
		try {
			conf.save(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// GET REGIONS
	
	public File getFile(Plugin p) {
		return new File(p.getDataFolder(), "config.yml");
	}
	
	public File getMessages(Plugin p) {
		return new File(p.getDataFolder(), "messages.yml");
	}
	
	public String getWeedGatherRegion() {
		return readConfig(p).getString("WeedGatherRegion");
	}
	
	public String getWeedProcessRegion() {
		return readConfig(p).getString("WeedProcessRegion");
	}
	
	public String getWeedSellRegion() {
		return readConfig(p).getString("WeedSellRegion");
	}
	
	public String getCokeGatherRegion() {
		return readConfig(p).getString("CokeGatherRegion");
	}
	
	public String getCokeProcessRegion() {
		return readConfig(p).getString("CokeProcessRegion");
	}
	
	public String getCokeSellRegion() {
		return readConfig(p).getString("CokeSellRegion");
	}
	
	public String getMethGatherRegion() {
		return readConfig(p).getString("MethGatherRegion");
	}
	
	public String getMethProcessRegion() {
		return readConfig(p).getString("MethProcessRegion");
	}
	
	public String getMethSellRegion() {
		return readConfig(p).getString("MethSellRegion");
	}
	
	public String getMoneyWashLocation() {
		return readConfig(p).getString("MoneyLaunderingRegion");
	}
	
	// GET ITEMS
	
	// WEED
	public String getWeedSeedItemName() {
		return readConfig(p).getString("WeedSeedName");
	}
	
	public String getWeedSeedLore() {
		return readConfig(p).getString("WeedSeedLore");
	}
	
	public String getWeedItemName() {
		return readConfig(p).getString("WeedName");
	}
	
	public String getWeedItemLore() {
		return readConfig(p).getString("WeedLore");
	}
	
	public String getWeedProcessedItemName() {
		return readConfig(p).getString("ProcessedWeedName");
	}
	
	public String getWeedProcessedLore() {
		return readConfig(p).getString("ProcessedWeedLore");
	}
	
	// COKE
	
	public String getCokeSeedItemName() {
		return readConfig(p).getString("CokeSeedName");
	}
	
	public String getCokeSeedLore() {
		return readConfig(p).getString("CokeSeedLore");
	}
	
	public String getCokeItemName() {
		return readConfig(p).getString("CokeName");
	}
	
    public String getCokeItemLore() {
		return readConfig(p).getString("CokeLore");
	}
	
	public String getCokeProcessedItem() {
		return readConfig(p).getString("ProcessedCokeName");
	}
	
	public String getCokeProcessedLore() {
		return readConfig(p).getString("ProcessedCokeLore");
	}
	
	// METH
	
	public String getMethSeedItemName() {
		return readConfig(p).getString("MethSeedName");
	}
	
	public String getMethSeedLore() {
		return readConfig(p).getString("MethSeedName");
	}
	
	public String getMethItemName() {
		return readConfig(p).getString("MethName");
	}
	
	public String getMethItemLore() {
		return readConfig(p).getString("MethLore");
	}
	
	public String getMethProcessedItem() {
		return readConfig(p).getString("MethProcessedName");
	}
	
	public String getMethProcessedLore() {
		return readConfig(p).getString("MethProcessedLore");
	}
	
	// BLACK MONEY
	
	public String getBlackMoneyItemName() {
		return readConfig(p).getString("BlackMoneyName");
	}
	
	public String getBlackMoneyItemLore() {
		return readConfig(p).getString("BlackMoneyLore");
	}
	
	// MESSAGES
	
	public String getFullInventory() {
		return readConfig(p).getString("Full_Inventory");
	}
	
	public String getWashLaundryMessage() {
		return readConfig(p).getString("Wash_Laundry");
	}
	
	
	// SEEDS
	public String getNoWeedSeeds() {
		return readConfig(p).getString("No_Weed_Seeds");
	}
	
	public String getNoCokeSeeds() {
		return readConfig(p).getString("No_Coke_Seeds");
	}
	
	public String getNoMethSeeds() {
		return readConfig(p).getString("No_Meth_Seeds");
	}
	
	// NO ITEM
	public String getNoWeed() {
		return readConfig(p).getString("No_Weed");
	}
	
	public String getNoCoke() {
		return readConfig(p).getString("No_Coke");
	}
	
	public String getNoMeth() {
		return readConfig(p).getString("No_Meth");
	}
	
	// NO PROCESSED
	public String getNoProcessedWeed() {
		return readConfig(p).getString("No_Processed_Weed");
	}
	
	public String getNoProcessedCoke() {
		return readConfig(p).getString("No_Processed_Coke");
	}
	
	public String getNoProcessedMeth() {
		return readConfig(p).getString("No_Processed_Meth");
	}
	
	// GATHER
	public String getGatheringWeedMessage() {
		return readConfig(p).getString("Gathering_Weed");
	}
	
	public String getGatheringCokeMessage() {
		return readConfig(p).getString("Gathering_Coke");
	}
	
	public String getGatheringMethMessage() {
		return readConfig(p).getString("Gathering_Meth");
	}
	
	// PROCESS
	public String getProcessingWeedMessage() {
		return readConfig(p).getString("Processing_Weed");
	}
	
	public String getProcessingCokeMessage() {
		return readConfig(p).getString("Processing_Coke");
	}
	
	public String getProcessingMethMessage() {
		return readConfig(p).getString("Processing_Meth");
	}
	
	// SELLING
	public String getSellingWeedMessage() {
		return readConfig(p).getString("Selling_Weed");
	}
	
	public String getSellingCokeMessage() {
		return readConfig(p).getString("Selling_Coke");
	}
	
	public String getSellingMethMessage() {
		return readConfig(p).getString("Selling_Meth");
	}
	
	// Black
	public String getNoBlackMoney() {
		return readConfig(p).getString("No_Black_Money");
	}
	
	public String getTransactionFail() {
		return readConfig(p).getString("Transaction_Fail");
	}
	
	// Timers
	
	public Integer getGatherTimer() {
		return readConfig(p).getInt("Gather_Timer");
	}
	
	public Integer getProcessTimer() {
		return readConfig(p).getInt("Process_Timer");
	}
	
	public Integer getSellTimer() {
		return readConfig(p).getInt("Sell_Timer");
	}
	
	public Integer getLaundryTimer() {
		return readConfig(p).getInt("Laundry_Timer");
	}
	
	// ITEMS
	
	// WEED SEEDS
	
	public String getWeedSeedItem() {
		return readConfig(p).getString("Weed_Seed_Item");
	}
	
	public String getWeedSeedDataItem() {
		return readConfig(p).getString("Weed_Seed_Data");
	}
	
	// WEED ITEM
	
	public String getWeedItem() {
		return readConfig(p).getString("Weed_Item");
	}
	
	public String getWeedItemData() {
		return readConfig(p).getString("Weed_Item_Data");
	}
	
	// WEED PROCESSED ITEM
	
	public String getWeedProcessedItem() {
		return readConfig(p).getString("Weed_Processed_Item");
	}
		
	public String getWeedProcessedItemData() {
		return readConfig(p).getString("Weed_Processed_Data");
	}
	
	// COKE SEEDS
	
	public String getCokeSeedItem() {
		return readConfig(p).getString("Coke_Seed_Item");
	}
	
	public String getCokeSeedItemData() {
		return readConfig(p).getString("Coke_Seed_Data");
	}
	
	// COKE ITEM
	
	public String getCokeItem() {
		return readConfig(p).getString("Coke_Item");
	}
	
	public String getCokeItemData() {
		return readConfig(p).getString("Coke_Item_Data");
	}
	
	// COKE PROCESSED ITEM
	
	public String getCokeProcessedItem1() {
		return readConfig(p).getString("Coke_Processed_Item");
	}
	
	public String getCokeProcessedItemData() {
		return readConfig(p).getString("Coke_Processed_Data");
	}
	
	// METH SEEDS
	
	public String getMethSeedItemID() {
		return readConfig(p).getString("Meth_Seed_Item");
	}
		
	public String getMethSeedItemDataID() {
		return readConfig(p).getString("Meth_Seed_Data");
	}
	
	// METH ITEM
	
	public String getMethItemID() {
		return readConfig(p).getString("Meth_Item");
	}
			
	public String getMethItemData() {
		return readConfig(p).getString("Meth_Item_Data");
	}
		
	// METH ITEM
		
	public String getMethProcessedItemID() {
		return readConfig(p).getString("Meth_Processed_Item");
	}
			
	public String getMethProcessedItemData() {
		return readConfig(p).getString("Meth_Processed_Data");
	}
	
	// BLACK MONEY ITEM
	
	public String getBlackMoneyItemID() {
		return readConfig(p).getString("Black_Money_Item");
	}
			
	public String getBlackMoneyItemData() {
		return readConfig(p).getString("Black_Money_Data");
	}
	
	public static Integer getBlackMoneyAmount() {
		return readConfig(p).getInt("Black_Money_Per_Item");
	}
	
	
	public static boolean writeCheck(File file){
	     boolean f = true;
	     if(!file.exists()) try
	     {
	       if(!file.getParentFile().exists() && !file.getParentFile().mkdirs())
	       {
	         System.err.println(
	             "writeCheck error: Cannot create parent file " + file.getParentFile().getAbsolutePath());
	         f = false;
	       }
	       if(!file.createNewFile())
	       {
	         System.err.println("writeCheck error: Cannot create file " + file.getAbsolutePath());
	         f = false;
	       }
	     }
	     catch(IOException e)
	     {
	       e.printStackTrace();
	       f = false;
	     }
	     if(!file.canWrite())
	       f = false;
	     return f;
	}
	
}

