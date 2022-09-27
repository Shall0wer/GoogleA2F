package fr.shall0wer.googleauth.commands;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import fr.shall0wer.googleauth.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

public class CodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        FileConfiguration config = YamlConfiguration.loadConfiguration(Main.getAuthFile());

        if(config.getBoolean("authcodes." + player.getUniqueId() + ".enable")){
            if(args.length == 1){
                int code;
                try {
                    code = Integer.parseInt(args[0]);
                    GoogleAuthenticator gauth = new GoogleAuthenticator();
                    if(gauth.authorize(config.getString("authcodes." + player.getUniqueId() + ".key"), code)){
                        player.sendMessage("§aCode is valid, welcome !");
                        Bukkit.getScheduler().runTaskLater(Main.getINSTANCE(), () -> {
                            Main.getCancelMove().remove(player.getUniqueId());
                        }, 2);
                    } else {
                        player.sendMessage("§cInvalid code.");
                    }
                } catch (NumberFormatException e){
                    player.sendMessage("§cInvalid code format.");
                    e.printStackTrace();
                }
            } else {
                player.sendMessage("§cSyntax: /code <your code>");
            }
        } else {
            player.sendMessage("§cError: 2FA not enabled. Please contact server administrator.");
        }
        return false;
    }
}
