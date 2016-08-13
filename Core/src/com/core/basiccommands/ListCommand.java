package com.core.basiccommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.main.Essentials;
import com.core.utils.AFKUtil;
import com.core.utils.PermissionCheck;

public class ListCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "�cUsage: /list";

	public ListCommand(Essentials e) {
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
		if (!PermissionCheck.check(player, "core.list")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
			if (AFKUtil.isAFK(onlinePlayer)) {
				builder.append("�7[AFK] "
						+ (PermissionCheck.check(onlinePlayer,
								"bifrost.core.staff") ? "�c[Staff] " : "")
						+ onlinePlayer.getName() + ", �7");
			} else {
				builder.append((PermissionCheck.check(onlinePlayer,
						"bifrost.core.staff") ? "�c[Staff] " : "")
						+ onlinePlayer.getName() + ", �7");
			}
		}
		builder.append(")");
		player.sendMessage(essentials.prefix + "�aPlayers Online:\n"
				+ builder.toString().replace(", �7)", "").replace("(", ""));
		return true;
	}
}