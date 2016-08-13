package com.core.basiccommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.main.Essentials;
import com.core.utils.AFKUtil;
import com.core.utils.PermissionCheck;

public class AFKCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "�cUsage: /afk";

	public AFKCommand(Essentials e) {
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
		if (!PermissionCheck.check(player, "core.afk")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		if (AFKUtil.isAFK(player)) {
			AFKUtil.setNotAFK(player);
			player.sendMessage(essentials.prefix + "�cYou are no longer AFK");
		} else {
			AFKUtil.setAFK(player);
			player.sendMessage(essentials.prefix + "�aYou are now AFK");
		}
		return true;
	}
}