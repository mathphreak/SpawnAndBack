package com.github.mathphreak.spawnandback.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

import com.github.mathphreak.spawnandback.SpawnAndBack;

public class CommandSetFirstSpawn extends CommandBase {
    
    @Override
    public String getCommandName() {
        return "setfirstspawn";
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }
    
    @Override
    public void processCommand(final ICommandSender var1, final String[] var2) {
        final EntityPlayerMP player = getCommandSenderAsPlayer(var1);
        SpawnAndBack.instance.firstSpawnX = player.posX;
        SpawnAndBack.instance.firstSpawnY = player.posY;
        SpawnAndBack.instance.firstSpawnZ = player.posZ;
        player.sendChatToPlayer("\u00A7oFirst spawn has been set to your current position.");
        if (!SpawnAndBack.instance.firstSpawnEnabled) {
            player.sendChatToPlayer("\u00A7e\u00A7oWARNING: First spawn is disabled in " + SpawnAndBack.instance.configFile.getName());
        }
        SpawnAndBack.instance.saveConfig();
    }
}
