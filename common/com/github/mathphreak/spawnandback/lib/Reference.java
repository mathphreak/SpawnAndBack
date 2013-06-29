package com.github.mathphreak.spawnandback.lib;

public abstract class Reference {
    // config file details
    public static abstract class CONFIG {
        public static final String SPAWN_X_KEY = "spawnX";
        public static final String SPAWN_Y_KEY = "spawnY";
        public static final String SPAWN_Z_KEY = "spawnZ";
        public static final double DEFAULT_VALUE = 0;
    }
    
    // mod info
    public static abstract class MOD_INFO {
        public static final String ID = "math_sab";
        public static final String NAME = "Spawn and Back";
        public static final String VERSION = "0.0.1";
    }
}
