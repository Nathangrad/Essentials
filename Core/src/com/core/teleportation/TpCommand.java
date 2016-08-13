package com.core.teleportation;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.main.Essentials;
import com.core.utils.PermissionCheck;
import com.core.utils.TptoggleUtil;

public class TpCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "§cUsage: /tp <Player>";

	public TpCommand(Essentials e) {
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
		if (!PermissionCheck.check(player, "core.tp")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		if (args.length == 0) {
			player.sendMessage(essentials.prefix + usage);
		} else {
			Player target = Bukkit.getServer().getPlayer(args[0].toString());
			if (target != null) {
				if (!TptoggleUtil.isDenying(target)) {
					player.teleport(target.getLocation());
					player.sendMessage(essentials.prefix
							+ "§aSuccessfully teleported to §e"
							+ target.getName());
					target.sendMessage(essentials.prefix + "§e"
							+ player.getName() + "§a has teleported to you");
				} else {
					player.sendMessage(essentials.prefix + "§e"
							+ target.getName()
							+ "§c has disabled teleportation");
				}
			} else {
				player.sendMessage(essentials.prefix + essentials.playerror);
			}
		}
		return true;
	}
}