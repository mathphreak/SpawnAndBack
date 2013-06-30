package com.github.mathphreak.spawnandback.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

import com.github.mathphreak.spawnandback.SpawnAndBack;

public class CommandSetSpawn extends CommandBase {
    
    @Override
    public boolean canCommandSenderUseCommand(final ICommandSender par1iCommandSender) {
        return par1iCommandSender instanceof EntityPlayerMP;
    }
    
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
        if (var1 instanceof EntityPlayerMP) {
            final EntityPlayerMP player = (EntityPlayerMP) var1;
            SpawnAndBack.instance.spawnX = player.posX;
            SpawnAndBack.instance.spawnY = player.posY;
            SpawnAndBack.instance.spawnZ = player.posZ;
            player.sendChatToPlayer("\u00A7oSpawn has been set to your current position.");
            SpawnAndBack.instance.saveSpawnInConfig();
        }
    }
}
