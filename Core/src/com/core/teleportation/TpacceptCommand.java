package com.core.teleportation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.core.main.Essentials;
import com.core.utils.PermissionCheck;

public class TpacceptCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "§cUsage: /tpaccept";

	public TpacceptCommand(Essentials e) {
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
		if (!PermissionCheck.check(player, "core.tpaccept")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		TeleportRequest request = TeleportRequest.getRequest(player);
		if (request != null) {
			request.getSender().sendMessage(
					essentials.prefix + "§e" + request.getReceiver().getName()
							+ "§a has accepted your request");
			request.getReceiver().sendMessage(
					essentials.prefix + "§aYou've accepted §e"
							+ request.getSender().getName() + "§a's request");
			request.acceptRequest();
		} else {
			player.sendMessage(essentials.prefix
					+ "§cTeleportation request not found");
		}
		return true;
	}
}