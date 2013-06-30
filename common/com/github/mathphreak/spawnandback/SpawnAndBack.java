package com.github.mathphreak.spawnandback;

import java.io.File;
import java.util.HashMap;

import net.minecraftforge.common.Configuration;

import com.github.mathphreak.spawnandback.command.CommandBack;
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
    
    public double spawnX = Reference.CONFIG.INVALID_SPAWN_VALUE;
    public double spawnY = Reference.CONFIG.INVALID_SPAWN_VALUE;
    public double spawnZ = Reference.CONFIG.INVALID_SPAWN_VALUE;
    public boolean forgetBackPositionAfterUse = false;
    
    private File configFile;
    
    @Init
    public void init(final FMLInitializationEvent event) {
        lastPositions = new HashMap<String, Vector3>();
        
        NetworkRegistry.instance().registerConnectionHandler(new PlayerConnectionMovifier());
    }
    
    public boolean isSpawnValid() {
        return !(spawnX == spawnY && spawnY == spawnZ && spawnZ == Reference.CONFIG.INVALID_SPAWN_VALUE);
    }
    
    @PostInit
    public void postInit(final FMLPostInitializationEvent event) {
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
        spawnX = config.get(Configuration.CATEGORY_GENERAL, Reference.CONFIG.SPAWN_X_KEY, spawnX).getDouble(spawnX);
        spawnY = config.get(Configuration.CATEGORY_GENERAL, Reference.CONFIG.SPAWN_Y_KEY, spawnY).getDouble(spawnY);
        spawnZ = config.get(Configuration.CATEGORY_GENERAL, Reference.CONFIG.SPAWN_Z_KEY, spawnZ).getDouble(spawnZ);
        forgetBackPositionAfterUse = config.get(Configuration.CATEGORY_GENERAL, Reference.CONFIG.FORGET_KEY, forgetBackPositionAfterUse,
                "Keep people from abusing /back to teleport home whenever they feel like it").getBoolean(forgetBackPositionAfterUse);
        config.save();
    }
    
    public void saveSpawnInConfig() {
        configFile.delete();
        saveAndOrLoadConfig();
    }
    
    @ServerStarting
    public void serverStarting(final FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandBack());
        event.registerServerCommand(new CommandSetSpawn());
        event.registerServerCommand(new CommandSpawn());
    }
}
