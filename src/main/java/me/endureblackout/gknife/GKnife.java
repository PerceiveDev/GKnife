package me.endureblackout.gknife;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class GKnife extends JavaPlugin {

    public void onEnable() {
        getCommand("gknife").setExecutor(new CommandHandler(this));
        Bukkit.getServer().getPluginManager().registerEvents(new KnifeHandler(this), this);
    }

}
