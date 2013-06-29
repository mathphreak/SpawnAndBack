package com.github.mathphreak.spawnandback.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

import com.github.mathphreak.spawnandback.SpawnAndBack;
import com.github.mathphreak.spawnandback.util.Vector3;

public class CommandSpawn extends CommandBase {
    
    @Override
    public boolean canCommandSenderUseCommand(final ICommandSender par1iCommandSender) {
        return par1iCommandSender instanceof EntityPlayerMP;
    }
    
    @Override
    public String getCommandName() {
        return "spawn";
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
    @Override
    public void processCommand(final ICommandSender var1, final String[] var2) {
        if (var1 instanceof EntityPlayerMP) {
            if (!SpawnAndBack.instance.isSpawnValid()) {
                var1.sendChatToPlayer("Spawn has not yet been set.");
                return;
            }
            final EntityPlayerMP player = (EntityPlayerMP) var1;
            final String username = player.username;
            final Vector3 position = new Vector3(player.posX, player.posY, player.posZ);
            SpawnAndBack.instance.lastPositions.put(username, position);
            player.setPositionAndUpdate(SpawnAndBack.instance.spawnX, SpawnAndBack.instance.spawnY, SpawnAndBack.instance.spawnZ);
            player.sendChatToPlayer("You are at spawn.");
        }
    }
    
}
