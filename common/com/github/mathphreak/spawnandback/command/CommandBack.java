package com.github.mathphreak.spawnandback.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

import org.lwjgl.util.vector.Vector2f;

import com.github.mathphreak.spawnandback.SpawnAndBack;
import com.github.mathphreak.spawnandback.util.Vector3;

public class CommandBack extends CommandBase {
    
    @Override
    public boolean canCommandSenderUseCommand(final ICommandSender par1iCommandSender) {
        // TODO Auto-generated method stub
        return super.canCommandSenderUseCommand(par1iCommandSender);
    }
    
    @Override
    public String getCommandName() {
        return "back";
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
            final Vector3 position = SpawnAndBack.instance.lastPositions.get(username);
            final Vector2f look = SpawnAndBack.instance.lastLookingThings.get(username);
            player.setAngles(look.x, look.y);
            player.setPositionAndUpdate(position.x, position.y, position.z);
            player.sendChatToPlayer("You are back!");
        }
    }
}
