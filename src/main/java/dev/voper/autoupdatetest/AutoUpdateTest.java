package dev.voper.autoupdatetest;

import org.bukkit.plugin.java.JavaPlugin;

public final class AutoUpdateTest extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("This is the version 1.1.0");
        AutoUpdater autoUpdater = new AutoUpdater(this, this.getFile());
        new Thread(autoUpdater).start();
        System.out.println("A random message");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("something another random feature");
    }
}
