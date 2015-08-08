package com.daexsys.warpl.commands;

import com.daexsys.warpl.WarpLocation;
import com.daexsys.warpl.Warpl;
import com.daexsys.warpl.WarpsManager;
import com.google.common.base.Optional;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandCallable;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.world.Location;

import java.util.List;
import java.util.Map;

public class WarpList implements CommandCallable {
    @Override
    public CommandResult process(CommandSource commandSource, String s) throws CommandException {
        WarpsManager warpsManager = Warpl.getWarpsManager();

        String output = Warpl.NAME + " Current warps: ";

        for(Map.Entry<String, WarpLocation> entry : warpsManager.getWarps().entrySet()) {
            if(!output.equalsIgnoreCase(Warpl.NAME + " Current warps: ")) {
                output += ", ";
            }

            String name = entry.getKey();

            output += name;
        }

        commandSource.sendMessage(Texts.of(output));

        return CommandResult.success();
    }

    @Override
    public List<String> getSuggestions(CommandSource commandSource, String s) throws CommandException {
        return null;
    }

    @Override
    public boolean testPermission(CommandSource commandSource) {
        return false;
    }

    @Override
    public Optional<Text> getShortDescription(CommandSource commandSource) {
        Text message = Texts.of("Get a list of loaded warps");
        return Optional.of(message);
    }

    @Override
    public Optional<Text> getHelp(CommandSource commandSource) {
        return null;
    }

    @Override
    public Text getUsage(CommandSource commandSource) {
        Text message = Texts.of("/warplist");
        return message;
    }
}
