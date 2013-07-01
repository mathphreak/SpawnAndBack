package com.github.mathphreak.spawnandback;

import java.io.File;
import java.util.HashMap;

import net.minecraftforge.common.Configuration;

import com.github.mathphreak.spawnandback.command.CommandBack;
import com.github.mathphreak.spawnandback.command.CommandSetFirstSpawn;
import com.github.mathphreak.spawnandback.command.CommandSetSpawn;
import com.github.mathphreak.spawnandback.command.CommandSpawn;
import com.github.mathphreak.spawnandback.lib.Reference;
import com.github.mathphreak.spawnandback.network.PlayerConnectionMovifier;
import com.github.mathphreak.spawnandback.util.Vector3;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_INFO.ID, name = Reference.MOD_INFO.NAME, version = Reference.MOD_INFO.VERSION)
public class SpawnAndBack {
    
    @Instance(Reference.MOD_INFO.ID)
    public static SpawnAndBack instance;
    
    public HashMap<String, Vector3> lastPositions;
    
    public boolean spawnEnabled = true;
    public double spawnX = Reference.SPAWN_CONFIG.INVALID_VALUE;
    public double spawnY = Reference.SPAWN_CONFIG.INVALID_VALUE;
    public double spawnZ = Reference.SPAWN_CONFIG.INVALID_VALUE;
    public boolean forgetBackPositionAfterUse = false;
    public boolean spawnIsFirstSpawn = true;
    
    public boolean firstSpawnEnabled = false;
    public double firstSpawnX = Reference.FIRST_SPAWN_CONFIG.INVALID_VALUE;
    public double firstSpawnY = Reference.FIRST_SPAWN_CONFIG.INVALID_VALUE;
    public double firstSpawnZ = Reference.FIRST_SPAWN_CONFIG.INVALID_VALUE;
    
    public File configFile;
    
    @Init
    public void init(@SuppressWarnings("unused") final FMLInitializationEvent event) {
        lastPositions = new HashMap<String, Vector3>();
        
        NetworkRegistry.instance().registerConnectionHandler(new PlayerConnectionMovifier());
    }
    
    public boolean isFirstSpawnValid() {
        return firstSpawnY != Reference.FIRST_SPAWN_CONFIG.INVALID_VALUE;
    }
    
    public boolean isSpawnValid() {
        return spawnY != Reference.SPAWN_CONFIG.INVALID_VALUE;
    }
    
    @PostInit
    public void postInit(@SuppressWarnings("unused") final FMLPostInitializationEvent event) {
        // interact with other mods
    }
    
    @PreInit
    public void preInit(final FMLPreInitializationEvent event) {
        // register blocks and items
        
        // also, mess with config
        configFile = event.getSuggestedConfigurationFile();
        saveAndOrLoadConfig();
    }
    
    private void saveAndOrLoadConfig() {
        final Configuration config = new Configuration(configFile);
        config.load();
        final String spawnCategory = Reference.SPAWN_CONFIG.CATEGORY;
        spawnEnabled = config.get(spawnCategory, Reference.SPAWN_CONFIG.ENABLED_KEY, spawnEnabled).getBoolean(spawnEnabled);
        spawnX = config.get(spawnCategory, Reference.SPAWN_CONFIG.X_KEY, spawnX).getDouble(spawnX);
        spawnY = config.get(spawnCategory, Reference.SPAWN_CONFIG.Y_KEY, spawnY).getDouble(spawnY);
        spawnZ = config.get(spawnCategory, Reference.SPAWN_CONFIG.Z_KEY, spawnZ).getDouble(spawnZ);
        forgetBackPositionAfterUse = config.get(spawnCategory, Reference.SPAWN_CONFIG.FORGET_KEY, forgetBackPositionAfterUse,
                "Keep people from abusing /back to teleport home whenever they feel like it").getBoolean(forgetBackPositionAfterUse);
        spawnIsFirstSpawn = config.get(spawnCategory, Reference.SPAWN_CONFIG.FIRST_KEY, spawnIsFirstSpawn,
                "Teleport players to spawn when they first connect").getBoolean(spawnIsFirstSpawn);
        final String firstSpawnCategory = Reference.FIRST_SPAWN_CONFIG.CATEGORY;
        firstSpawnEnabled = config.get(firstSpawnCategory, Reference.FIRST_SPAWN_CONFIG.ENABLED_KEY, firstSpawnEnabled).getBoolean(firstSpawnEnabled);
        firstSpawnX = config.get(firstSpawnCategory, Reference.FIRST_SPAWN_CONFIG.X_KEY, firstSpawnX).getDouble(firstSpawnX);
        firstSpawnY = config.get(firstSpawnCategory, Reference.FIRST_SPAWN_CONFIG.Y_KEY, firstSpawnY).getDouble(firstSpawnY);
        firstSpawnZ = config.get(firstSpawnCategory, Reference.FIRST_SPAWN_CONFIG.Z_KEY, firstSpawnZ).getDouble(firstSpawnZ);
        config.save();
    }
    
    public void saveConfig() {
        configFile.delete();
        saveAndOrLoadConfig();
    }
    
    @ServerStarting
    public void serverStarting(final FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandBack());
        event.registerServerCommand(new CommandSetFirstSpawn());
        event.registerServerCommand(new CommandSetSpawn());
        event.registerServerCommand(new CommandSpawn());
    }
}
