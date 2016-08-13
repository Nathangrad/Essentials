package com.core.teleportation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.listeners.BackListener;
import com.core.main.Essentials;
import com.core.utils.PermissionCheck;

public class BackCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "�cUsage: /back";

	public BackCommand(Essentials e) {
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
		if (!PermissionCheck.check(player, "core.back")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		if (BackListener.getBackLocation(player) != null) {
			player.teleport(BackListener.getBackLocation(player));
			player.sendMessage(essentials.prefix
					+ "�aTeleported to your previous location");
		} else {
			player.sendMessage(essentials.prefix
					+ "�cYou don't have a previous location set");
		}
		return true;
	}
}