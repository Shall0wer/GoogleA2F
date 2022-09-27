package fr.shall0wer.googleauth.managers;

import fr.shall0wer.googleauth.listeners.PlayerJoinListener;
import fr.shall0wer.googleauth.listeners.PlayerMoveListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerManager {

    public ListenerManager(Plugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), plugin);
        pm.registerEvents(new PlayerMoveListener(), plugin);
    }
}
