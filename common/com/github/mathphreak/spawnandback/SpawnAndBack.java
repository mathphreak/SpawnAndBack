package com.github.mathphreak.spawnandback;

import java.io.File;
import java.util.HashMap;

import net.minecraftforge.common.Configuration;

import org.lwjgl.util.vector.Vector2f;

import com.github.mathphreak.spawnandback.command.CommandBack;
import com.github.mathphreak.spawnandback.command.CommandSetSpawn;
import com.github.mathphreak.spawnandback.command.CommandSpawn;
import com.github.mathphreak.spawnandback.lib.Reference;
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

@Mod(modid = Reference.MOD_INFO.ID, name = Reference.MOD_INFO.NAME, version = Reference.MOD_INFO.VERSION)
public class SpawnAndBack {
    
    @Instance(Reference.MOD_INFO.ID)
    public static SpawnAndBack instance;
    
    public HashMap<String, Vector3> lastPositions;
    public HashMap<String, Vector2f> lastLookingThings;
    
    public double spawnX = Reference.CONFIG.DEFAULT_VALUE;
    public double spawnY = Reference.CONFIG.DEFAULT_VALUE;
    public double spawnZ = Reference.CONFIG.DEFAULT_VALUE;
    
    private File configFile;
    
    @Init
    public void init(final FMLInitializationEvent event) {
        // do stuff
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
        final Configuration config = new Configuration(configFile);
        config.load();
        final double defaultValue = Reference.CONFIG.DEFAULT_VALUE;
        spawnX = config.get(Configuration.CATEGORY_GENERAL, Reference.CONFIG.SPAWN_X_KEY, spawnX).getDouble(defaultValue);
        spawnY = config.get(Configuration.CATEGORY_GENERAL, Reference.CONFIG.SPAWN_Y_KEY, spawnY).getDouble(defaultValue);
        spawnZ = config.get(Configuration.CATEGORY_GENERAL, Reference.CONFIG.SPAWN_Z_KEY, spawnZ).getDouble(defaultValue);
        config.save();
    }
    
    public void saveSpawnInConfig() {
        configFile.delete();
    }
    
    @ServerStarting
    public void serverStarting(final FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandBack());
        event.registerServerCommand(new CommandSetSpawn());
        event.registerServerCommand(new CommandSpawn());
    }
}
