package com.above.essentials.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.above.essentials.main.Essentials;
import com.above.essentials.utils.AFKUtil;

public class AFKListener implements Listener {

	Essentials essentials;

	public AFKListener(Essentials e) {
		essentials = e;
	}

	@EventHandler
	public void onAFKMove(PlayerMoveEvent moveEvent) {
		if (moveEvent.getPlayer() != null
				&& AFKUtil.isAFK(moveEvent.getPlayer())) {
			AFKUtil.setNotAFK(moveEvent.getPlayer());
			moveEvent.getPlayer().sendMessage(
					essentials.prefix + "§cYou are no longer AFK");
		}
	}
}
