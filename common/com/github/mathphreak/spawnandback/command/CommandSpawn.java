package com.github.mathphreak.spawnandback.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

import org.lwjgl.util.vector.Vector2f;

import com.github.mathphreak.spawnandback.SpawnAndBack;
import com.github.mathphreak.spawnandback.util.Vector3;

public class CommandSpawn extends CommandBase {
    
    @Override
    public boolean canCommandSenderUseCommand(final ICommandSender par1iCommandSender) {
        // TODO Auto-generated method stub
        return super.canCommandSenderUseCommand(par1iCommandSender);
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
            final EntityPlayerMP player = (EntityPlayerMP) var1;
            final String username = player.username;
            final Vector3 position = new Vector3(player.posX, player.posY, player.posZ);
            final Vector2f look = new Vector2f(player.cameraPitch, player.cameraYaw);
            SpawnAndBack.instance.lastPositions.put(username, position);
            SpawnAndBack.instance.lastLookingThings.put(username, look);
            player.setAngles(37.1f, 89.25f);
            player.setPositionAndUpdate(30, 30, 30);
            player.sendChatToPlayer("You are at spawn.");
        }
    }
    
}
