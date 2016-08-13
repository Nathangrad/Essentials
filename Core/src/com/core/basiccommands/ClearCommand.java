package com.core.basiccommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.main.Essentials;
import com.core.utils.PermissionCheck;

public class ClearCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "�cUsage: /clear �o<Player>";

	public ClearCommand(Essentials e) {
		essentials = e;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String comandlbl, String[] args) {
		if (!(sender instanceof Player)) {
			essentials.getLogger().info(essentials.conserror);
			return true;
		}
		Player player = (Player) sender;
		if (args.length == 0) {
			if (!PermissionCheck.check(player, "core.clear")) {
				player.sendMessage(essentials.prefix + essentials.permerror);
				return true;
			}
			player.getInventory().clear();
			player.sendMessage(essentials.prefix
					+ "�aYou've cleared your inventory");
		} else if (args.length == 1) {
			if (!PermissionCheck.check(player, "core.clearothers")) {
				player.sendMessage(essentials.prefix + essentials.permerror);
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target != null) {
				target.getInventory().clear();
				target.sendMessage(essentials.prefix
						+ "�cYour inventory has been cleared");
				player.sendMessage(essentials.prefix
						+ "�aYou've cleared the inventory of �e"
						+ target.getName());
			} else {
				player.sendMessage(essentials.prefix + essentials.playerror);
				return true;
			}
		} else {
			player.sendMessage(essentials.prefix + usage);
			return true;
		}
		return true;
	}
}