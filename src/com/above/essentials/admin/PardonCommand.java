package com.above.essentials.admin;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.above.core.data.MySqlManager;
import com.above.essentials.main.Essentials;
import com.above.essentials.utils.PermissionCheck;

public class PardonCommand implements CommandExecutor {

	Essentials essentials;
	String usage = "§cUsage: /unban <Player>";

	public PardonCommand(Essentials e) {
		essentials = e;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String comandlbl, String[] args) {
		if (!(sender instanceof Player)) {
			essentials.getLogger().info(essentials.conserror);
			return true;
		}
		Player player = (Player) sender;
		if (!PermissionCheck.check(player, "core.pardon")) {
			player.sendMessage(essentials.prefix + essentials.permerror);
			return true;
		}
		if (args.length != 0) {
			OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0].toString());
			if (target == null) {
				player.sendMessage(essentials.prefix + usage);
				return true;
			}
			if (!target.isBanned()) {
				player.sendMessage(essentials.prefix + "§e" + target.getName() + "§c is not currently banned");
				return true;
			}
			target.setBanned(false);
			player.sendMessage(essentials.prefix + "§e" + target.getName() + "§a successfully unbanned");
			MySqlManager sql = new MySqlManager();
			try {
				sql.open();
				sql.executeNonQuery(
						"INSERT INTO PunishmentHistory (Player, Staff, " + "Type, Length, Reason, Date) VALUES ('"
								+ target.getUniqueId() + "','" + player.getUniqueId() + "','Unban',NULL,NULL, NOW())");
				sql.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			player.sendMessage(essentials.prefix + usage);
		}
		return true;
	}
}