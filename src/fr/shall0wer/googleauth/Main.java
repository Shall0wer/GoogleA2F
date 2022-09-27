package fr.shall0wer.googleauth;

import com.sun.org.apache.bcel.internal.classfile.Code;
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
        authFile = new File(Main.INSTANCE.getDataFolder(), "auth.yml");

        new ListenerManager(this);
        getCommand("code").setExecutor(new CodeCommand());
    }

    @Override
    public void onDisable() {

    }

    public void checkFile() {
        if(authFile.exists()) return;
        try {
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
