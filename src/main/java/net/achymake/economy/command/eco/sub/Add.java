package net.achymake.economy.command.eco.sub;

import net.achymake.economy.api.EconomyProvider;
import net.achymake.economy.command.eco.EcoSubCommand;
import net.achymake.economy.config.Message;
import net.achymake.economy.config.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Add extends EcoSubCommand {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add eco to player account";
    }

    @Override
    public String getSyntax() {
        return "/eco add player amount";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("economy.eco.add")){
            if (args.length == 3){
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1]);
                if (PlayerConfig.exist(offlinePlayer)){
                    double amount = Double.parseDouble(args[2]);
                    EconomyProvider.addEconomy(offlinePlayer,amount);
                    player.sendMessage(Message.commandEcoAdd(offlinePlayer,amount));
                    player.sendMessage(Message.commandBalanceOther(offlinePlayer));
                }else{
                    player.sendMessage(Message.commandErrorTargetNull(args[0]));
                }
            }
        }
    }
}