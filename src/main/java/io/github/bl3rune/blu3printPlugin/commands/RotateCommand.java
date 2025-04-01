package io.github.bl3rune.blu3printPlugin.commands;

import java.util.Arrays;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import io.github.bl3rune.blu3printPlugin.Blu3PrintPlugin;
import io.github.bl3rune.blu3printPlugin.data.Blu3printData;
import io.github.bl3rune.blu3printPlugin.enums.Rotation;
import io.github.bl3rune.blu3printPlugin.items.Blu3printItem;
import io.github.bl3rune.blu3printPlugin.utils.InventoryUtils;

public class RotateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack item = InventoryUtils.getHeldBlu3print(player, false);
            if (item == null) {
                sender.sendMessage("You must be holding a blu3print to rotate it.");
                return true;
            }

            Blu3printData data = Blu3PrintPlugin.getBlu3PrintPlugin().getBlu3printFrpmCache(item, player);
            if (data == null) {
                player.sendMessage("Blu3print data not found");
            }

            Rotation rotation = data.getPosition().getRotation().getNextRotation();
            if (args.length > 0) {
                rotation = Rotation.valueOf(args[0]);
                if  (rotation == null)  {
                    sender.sendMessage("Invalid rotation argument, try one of the following: TOP/LEFT/RIGHT/BOTTOM");
                    return true;
                }
            }
            String newKey = data.updateRotation(rotation);
            Blu3printItem.updateLore(Arrays.asList("updated by " + player.getDisplayName(), newKey), item);
            player.getInventory().setItemInMainHand(item);
        }
        return true;
    }

}
