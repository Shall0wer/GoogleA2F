package fr.shall0wer.googleauth.listeners;

import fr.shall0wer.googleauth.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        if(Main.getCancelMove().contains(event.getPlayer().getUniqueId())){
            event.setTo(event.getFrom());
        }
    }
}
