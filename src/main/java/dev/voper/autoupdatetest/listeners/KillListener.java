package dev.voper.autoupdatetest.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillListener implements Listener {

    @EventHandler
    public void onKill(EntityDeathEvent e) {
        e.getDrops().forEach(d -> d.setAmount((d.getAmount() * 2) % 64));
    }

}
