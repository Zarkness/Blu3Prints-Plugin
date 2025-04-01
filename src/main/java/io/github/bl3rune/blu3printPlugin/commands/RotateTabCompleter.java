package io.github.bl3rune.blu3printPlugin.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import io.github.bl3rune.blu3printPlugin.enums.Rotation;

public class RotateTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList(Rotation.values()).stream().map(Rotation::toString).toList();
        }
        return null;
    }

}
