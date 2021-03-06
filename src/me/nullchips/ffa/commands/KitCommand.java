package me.nullchips.ffa.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nullchips.ffa.utils.ArenaManager;
import me.nullchips.ffa.utils.ChatUtils;
import me.nullchips.ffa.utils.KitManager;

public class KitCommand implements CommandExecutor {
	
	ArenaManager arenaManager = ArenaManager.getArenaManager();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("kit")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "The console cannot use this command.");
				return false;
			}
			
			Player p = (Player) sender;
			
			
			if(!arenaManager.arena1Players.contains(p)) {
				ChatUtils.message(p, "You cannot select a kit if you're not in an arena!");
			}
			
			p.openInventory(KitManager.getInstance().getKitMenu());

		}
		
		return false;
	}

}
