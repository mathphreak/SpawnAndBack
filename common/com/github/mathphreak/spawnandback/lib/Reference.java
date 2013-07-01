package com.github.mathphreak.spawnandback.lib;

public abstract class Reference {
    // config file details - first spawn
    public static abstract class FIRST_SPAWN_CONFIG {
        public static final String CATEGORY = "FirstSpawn";
        public static final String ENABLED_KEY = "enabled";
        public static final String X_KEY = "x";
        public static final String Y_KEY = "y";
        public static final String Z_KEY = "z";
        public static final double INVALID_VALUE = -1;
    }
    
    // mod info
    public static abstract class MOD_INFO {
        public static final String ID = "math_sab";
        public static final String NAME = "Spawn and Back";
        public static final String VERSION = "0.7.2";
    }
    
    // config file details - spawn
    public static abstract class SPAWN_CONFIG {
        public static final String CATEGORY = "Spawn";
        public static final String ENABLED_KEY = "enabled";
        public static final String X_KEY = "x";
        public static final String Y_KEY = "y";
        public static final String Z_KEY = "z";
        public static final String FORGET_KEY = "forgetBackPositionAfterUse";
        public static final String FIRST_KEY = "useAsFirstSpawn";
        public static final double INVALID_VALUE = -1;
    }
}
