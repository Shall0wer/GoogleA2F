package fr.shall0wer.googleauth;

import fr.shall0wer.googleauth.commands.AdminCommand;
import fr.shall0wer.googleauth.commands.CodeCommand;
import fr.shall0wer.googleauth.managers.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Main extends JavaPlugin {

    private static Main INSTANCE;
    private static File authFile;
    private static ArrayList<UUID> cancelMove = new ArrayList<>();

    @Override
    public void onEnable() {
        INSTANCE = this;
        authFile = new File(Main.INSTANCE.getDataFolder() + "/auth.yml");

        checkFile();
        new ListenerManager(this);

        getCommand("2fa").setExecutor(new AdminCommand());
        getCommand("code").setExecutor(new CodeCommand());
    }

    @Override
    public void onDisable() {
        // Some good code :)
    }

    public void checkFile() {
        if(authFile.exists()) return;
        try {
            File dataFolder = new File(getDataFolder() + "/");
            if(!dataFolder.exists()) dataFolder.mkdir();

            authFile.createNewFile();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Main getINSTANCE() {
        return INSTANCE;
    }

    public static File getAuthFile() {
        return authFile;
    }

    public static ArrayList<UUID> getCancelMove() {
        return cancelMove;
    }
}
