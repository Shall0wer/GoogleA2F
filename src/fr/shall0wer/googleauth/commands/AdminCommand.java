package fr.shall0wer.googleauth.commands;

import fr.shall0wer.googleauth.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class AdminCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        if(player.hasPermission("*")){
            if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null){
                    player.sendMessage("§cPlayer must be online.");
                    return false;
                } else {
                    FileConfiguration config = YamlConfiguration.loadConfiguration(Main.getAuthFile());
                    boolean value = config.getBoolean("authcodes." + target.getUniqueId() + ".enable");
                    if(value){
                        config.set("authcodes." + target.getUniqueId() + ".enable", false);
                        player.sendMessage("§c2FA disable for player " + target.getName());
                    } else {
                        config.set("authcodes." + target.getUniqueId() + ".enable", true);
                        player.sendMessage("§a2FA enable for player " + target.getName());
                    }
                    try {
                        config.save(Main.getAuthFile());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else {
            player.sendMessage("§fUnknown command. Type /help for help.");
        }
        return false;
    }
}
