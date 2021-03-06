package com.github.mathphreak.spawnandback.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

import com.github.mathphreak.spawnandback.SpawnAndBack;

public class CommandSetSpawn extends CommandBase {
    
    @Override
    public String getCommandName() {
        return "setspawn";
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }
    
    @Override
    public void processCommand(final ICommandSender var1, final String[] var2) {
        final EntityPlayerMP player = getCommandSenderAsPlayer(var1);
        SpawnAndBack.instance.spawnX = player.posX;
        SpawnAndBack.instance.spawnY = player.posY;
        SpawnAndBack.instance.spawnZ = player.posZ;
        player.sendChatToPlayer("\u00A7oSpawn has been set to your current position.");
        SpawnAndBack.instance.saveConfig();
    }
}
