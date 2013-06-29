package com.github.mathphreak.spawnandback.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandSetSpawn extends CommandBase {
    
    @Override
    public boolean canCommandSenderUseCommand(final ICommandSender par1iCommandSender) {
        // TODO Auto-generated method stub
        return super.canCommandSenderUseCommand(par1iCommandSender);
    }
    
    @Override
    public String getCommandName() {
        return "setspawn";
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
    @Override
    public void processCommand(final ICommandSender var1, final String[] var2) {
        
    }
}
