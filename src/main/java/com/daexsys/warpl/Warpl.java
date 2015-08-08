package com.daexsys.warpl;

import com.daexsys.warpl.commands.RemoveWarp;
import com.daexsys.warpl.commands.SetWarp;
import com.daexsys.warpl.commands.Warp;
import com.daexsys.warpl.commands.WarpList;
import com.google.gson.Gson;
import com.google.inject.Inject;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Subscribe;
import org.spongepowered.api.event.state.InitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.*;
import java.util.logging.Logger;

@Plugin(id = "warpl", name = "Warpl", version = "1.1")
public class Warpl {
    public static Game game;

    public static final String FILE_LOCATION = "mods/warpl/warps.dat";
    public static final String NAME = "[Warpl]";

    @Inject
    private Logger logger;

    private static WarpsManager warpsManager;

    @Subscribe
    public void init(InitializationEvent event) {

        logger.info("Warpl active");
        logger.info("Plugin by Matthew Hebert (@Cactose)");

        game = event.getGame();

        warpsManager = loadWarps();

        // Register menger command
        event.getGame().getCommandDispatcher().register(this, new Warp(), "warp");
        event.getGame().getCommandDispatcher().register(this, new SetWarp(), "setwarp");
        event.getGame().getCommandDispatcher().register(this, new WarpList(), "warplist");
        event.getGame().getCommandDispatcher().register(this, new RemoveWarp(), "removewarp");
    }

    public static WarpsManager getWarpsManager() {
        return warpsManager;
    }

    public static void saveWarps() {
        File file = new File(FILE_LOCATION);

        File directory = new File("mods/warpl");
        directory.mkdirs();

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(warpsManager.toJson());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WarpsManager loadWarps() {
        WarpsManager warpsManager1 = new WarpsManager();

        File file = new File(FILE_LOCATION);

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            DataInputStream printStream = new DataInputStream(fileInputStream);

            String data = printStream.readLine();

            Gson gson = new Gson();

            WarpsManager warpsManager2 = gson.fromJson(data, WarpsManager.class);

            warpsManager1.setLocations(warpsManager2.getWarps());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return warpsManager1;
    }
}
