package fr.shall0wer.googleauth.listeners;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import fr.shall0wer.googleauth.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        FileConfiguration config = YamlConfiguration.loadConfiguration(Main.getAuthFile());

        if(config.getBoolean("authcodes." + player.getUniqueId() + ".enable")){
            Main.getCancelMove().add(player.getUniqueId());

            if(config.getString("authcodes." + player.getUniqueId() + ".key") == null){
                GoogleAuthenticator gauth = new GoogleAuthenticator();
                GoogleAuthenticatorKey key = gauth.createCredentials();

                player.sendMessage("§aYour 2FA code is : §e" + key.getKey());
                player.sendMessage("§7Once you have entered the key in an application like GoogleAuthenticator, log out and log back in and follow the instructions.");

                config.set("authcodes." + player.getUniqueId() + ".key", key.getKey());
                config.save(Main.getAuthFile());
            } else {
                player.sendMessage("§cTo continue your connection, please enter your 2FA code with the command '/code <your code>'.");
            }
        }
    }
}
