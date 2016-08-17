package com.above.essentials.teleportation;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.above.essentials.main.Essentials;
import com.above.essentials.utils.PermissionCheck;
import com.above.essentials.utils.TptoggleUtil;

public class TphereCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "§cUsage: /tphere <Player>";

	public TphereCommand(Essentials e) {
		essentials = e;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String comandlbl, String[] args) {
		if (!(sender instanceof Player)) {
			essentials.getLogger().info(essentials.conserror);
			return true;
		}
		Player player = (Player) sender;
		if (!PermissionCheck.check(player, "core.tphere")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		if (args.length == 0) {
			player.sendMessage(essentials.prefix + usage);
		} else {
			Player target = Bukkit.getServer().getPlayer(args[0].toString());
			if (target != null) {
				if (!TptoggleUtil.isDenying(target)) {
					target.teleport(player.getLocation());
					player.sendMessage(
							essentials.prefix + "§aSuccessfully teleported §e" + target.getName() + "§a to you");
					target.sendMessage(essentials.prefix + "§aYou have been teleported to §e" + player.getName());
				} else {
					player.sendMessage(essentials.prefix + "§e" + target.getName() + "§c has disabled teleportation");
				}
			} else {
				player.sendMessage(essentials.prefix + essentials.playerror);
			}
		}
		return true;
	}
}