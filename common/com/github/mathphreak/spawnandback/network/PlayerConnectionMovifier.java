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
    public void clientLoggedIn(final NetHandler clientHandler, final INetworkManager manager, final Packet1Login login) {
        // who cares
    }
    
    @Override
    public void connectionClosed(final INetworkManager manager) {
        // not me
    }
    
    @Override
    public void connectionOpened(final NetHandler netClientHandler, final MinecraftServer server, final INetworkManager manager) {
        // didn't think so
    }
    
    @Override
    public void connectionOpened(final NetHandler netClientHandler, final String server, final int port, final INetworkManager manager) {
        // me either
    }
    
    @Override
    public String connectionReceived(final NetLoginHandler netHandler, final INetworkManager manager) {
        // bah humbug
        return null;
    }
    
    @Override
    public void playerLoggedIn(final Player player, final NetHandler netHandler, final INetworkManager manager) {
        if (SpawnAndBack.instance.firstSpawnEnabled || (SpawnAndBack.instance.spawnEnabled && SpawnAndBack.instance.spawnIsFirstSpawn)) {
            final EntityPlayerMP realPlayer = (EntityPlayerMP) player;
            if (realPlayer.getBedLocation() == null && SpawnAndBack.instance.isSpawnValid()) {
                double spawnX = SpawnAndBack.instance.spawnX;
                double spawnY = SpawnAndBack.instance.spawnY;
                double spawnZ = SpawnAndBack.instance.spawnZ;
                if (SpawnAndBack.instance.firstSpawnEnabled) {
                    spawnX = SpawnAndBack.instance.firstSpawnX;
                    spawnY = SpawnAndBack.instance.firstSpawnY;
                    spawnZ = SpawnAndBack.instance.firstSpawnZ;
                }
                final int intSpawnX = (int) Math.floor(SpawnAndBack.instance.spawnX);
                final int intSpawnY = (int) Math.floor(SpawnAndBack.instance.spawnY);
                final int intSpawnZ = (int) Math.floor(SpawnAndBack.instance.spawnZ);
                final ChunkCoordinates coordinates = new ChunkCoordinates(intSpawnX, intSpawnY, intSpawnZ);
                realPlayer.setSpawnChunk(coordinates, true);
                realPlayer.setPositionAndUpdate(spawnX, spawnY, spawnZ);
            }
        }
    }
    
}
