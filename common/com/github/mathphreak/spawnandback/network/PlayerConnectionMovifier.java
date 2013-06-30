package com.github.mathphreak.spawnandback.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;

import com.github.mathphreak.spawnandback.SpawnAndBack;

import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

public class PlayerConnectionMovifier implements IConnectionHandler {
    
    @Override
    public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
        System.out.println("Player logging in!!!!!!!!!!!!!!");
        EntityPlayerMP realPlayer = (EntityPlayerMP) player;
        if (realPlayer.getBedLocation() == null && SpawnAndBack.instance.isSpawnValid()) {
            double spawnX = SpawnAndBack.instance.spawnX;
            double spawnY = SpawnAndBack.instance.spawnY;
            double spawnZ = SpawnAndBack.instance.spawnZ;
            int intSpawnX = (int) Math.floor(spawnX);
            int intSpawnY = (int) Math.floor(spawnY);
            int intSpawnZ = (int) Math.floor(spawnZ);
            ChunkCoordinates coordinates = new ChunkCoordinates(intSpawnX, intSpawnY, intSpawnZ);
            realPlayer.setSpawnChunk(coordinates, true);
            realPlayer.setPositionAndUpdate(spawnX, spawnY, spawnZ);
        }
    }
    
    @Override
    public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void connectionClosed(INetworkManager manager) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
        // TODO Auto-generated method stub
        
    }
    
}
