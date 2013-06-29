package com.github.mathphreak.spawnandback.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

import com.github.mathphreak.spawnandback.SpawnAndBack;
import com.github.mathphreak.spawnandback.util.Vector3;

public class CommandBack extends CommandBase {
    
    @Override
    public boolean canCommandSenderUseCommand(final ICommandSender par1iCommandSender) {
        return par1iCommandSender instanceof EntityPlayerMP;
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
            if (position == null) {
                player.sendChatToPlayer("You haven't used /spawn yet, so there's nowhere for you to go back to!");
                return;
            }
            player.setPositionAndUpdate(position.x, position.y, position.z);
            player.sendChatToPlayer("You are back!");
            if (SpawnAndBack.instance.forgetBackPositionAfterUse) {
                SpawnAndBack.instance.lastPositions.remove(username);
            }
        }
    }
}
