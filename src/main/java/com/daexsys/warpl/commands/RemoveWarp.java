package com.daexsys.warpl.commands;

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

public class RemoveWarp implements CommandCallable {
    @Override
    public CommandResult process(CommandSource commandSource, String s) throws CommandException {
        if(commandSource.hasPermission("warpl.removewarp")) {
            WarpsManager warpsManager = Warpl.getWarpsManager();

            String[] args = s.split("\\s+");
            String warpName = args[0];

            warpsManager.removeWarp(warpName);

            commandSource.sendMessage(Texts.of(Warpl.NAME + " Warp " + warpName + " has been removed"));
            Warpl.saveWarps();
        } else {
            commandSource.sendMessage(Texts.of(Warpl.NAME + " You do not have permission to use this command!"));
        }

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
        Text message = Texts.of("Removes warp. Admin command");
        return Optional.of(message);
    }

    @Override
    public Optional<Text> getHelp(CommandSource commandSource) {
        return null;
    }

    @Override
    public Text getUsage(CommandSource commandSource) {
        Text message = Texts.of("/removewarp [name]");
        return message;
    }
}
