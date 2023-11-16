package dev.voper.autoupdatetest;

import org.bukkit.plugin.java.JavaPlugin;

public final class AutoUpdateTest extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("This is the version 1.1.0");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("something");
    }
}
