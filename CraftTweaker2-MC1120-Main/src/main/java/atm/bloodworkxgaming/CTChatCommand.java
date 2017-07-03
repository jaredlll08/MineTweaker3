package atm.bloodworkxgaming;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by jonas on 03.07.2017.
 */
public class CTChatCommand extends CommandBase{

    public static Map<String, CraftTweakerCommand> craftTweakerCommands = new TreeMap<>();

    public static final List<String> aliases = new ArrayList<>();
    static {
        aliases.add("ct");
    }

    public static void register(FMLServerStartingEvent ev){
        Commands.registerCommands();
        ev.registerServerCommand(new CTChatCommand());
    }

    @Override
    public String getName() {
        return "crafttweaker";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return getUsageStatic();
    }

    public static String getUsageStatic() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, CraftTweakerCommand> entry : craftTweakerCommands.entrySet()) {
            for (String s : entry.getValue().getDescription()) {
                sb.append(s);
                sb.append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }



    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length <= 0){
            sender.sendMessage(new TextComponentString(getUsage(sender)));
            return;
        }

        if (craftTweakerCommands.containsKey(args[0])){
            if (sender.getCommandSenderEntity() instanceof EntityPlayer){
                craftTweakerCommands.get(args[0]).executeCommand(
                        server, sender, ArrayUtils.subarray(args, 1, args.length - 1));
            }else {
                craftTweakerCommands.get(args[0]).executeCommand(
                        server, sender, ArrayUtils.subarray(args, 1, args.length - 1));
            }
        }
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        Set<String> keys = craftTweakerCommands.keySet();
        List<String> currentPossibleCommands = new ArrayList<>();

        if (args.length <= 0){
            return new ArrayList<>(keys);
        }

        // First sub-command
        if (args.length == 1){
            for (String cmd: keys) {
                if (cmd.startsWith(args[0])){
                    currentPossibleCommands.add(cmd);
                }
            }
            return currentPossibleCommands;
        }
        // gives subcommands of the subcommand
        if (args.length == 2){
            CraftTweakerCommand subCommand = craftTweakerCommands.get(args[0]);
            if (subCommand != null && subCommand.subSubCommands.length > 0){

                for (String cmd: subCommand.subSubCommands) {
                    System.out.println("Trying " + cmd);

                    if (cmd.startsWith(args[1])){
                        currentPossibleCommands.add(cmd);
                    }
                }
                return currentPossibleCommands;
            }
        }

        // returns empty by default
        return currentPossibleCommands;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }

    public static void registerCommand(CraftTweakerCommand command){
        craftTweakerCommands.put(command.getSubCommandName(), command);
    }
}
