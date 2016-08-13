package com.core.listeners;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BackListener implements Listener {

	public static Map<Player, Location> backLocations = new HashMap<Player, Location>();

	@EventHandler
	public void onPlayersTeleport(PlayerTeleportEvent e) {
		backLocations.put(e.getPlayer(), e.getFrom());
	}

	public static Location getBackLocation(Player player) {
		if (backLocations.containsKey(player)) {
			return backLocations.get(player);
		} else {
			return null;
		}
	}
}
