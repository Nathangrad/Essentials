package com.core.teleportation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.main.Essentials;
import com.core.utils.PermissionCheck;
import com.core.utils.TptoggleUtil;

public class TptoggleCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "§cUsage: /tptoggle";

	public TptoggleCommand(Essentials e) {
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
		if (!PermissionCheck.check(player, "core.tptoggle")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		if (TptoggleUtil.isDenying(player)) {
			TptoggleUtil.setAllow(player);
			player.sendMessage(essentials.prefix
					+ "§aTeleportation is now allowed for you");
		} else {
			TptoggleUtil.setDeny(player);
			player.sendMessage(essentials.prefix
					+ "§cTeleportation is now disabled for you");
		}
		return true;
	}
}