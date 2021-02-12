package be.overcast.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetItem implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("getitem")) {
				if(player.isOp()) {
					ItemStack item = player.getInventory().getItemInMainHand();
					player.getInventory().addItem(item);
					
					player.sendRawMessage(item.toString());
				}
			}
		}
		return false;
	}

}
