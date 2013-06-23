
package com.thezomg.bungee.serverkeeper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import net.craftminecraft.bungee.bungeeyaml.bukkitapi.InvalidConfigurationException;
import net.craftminecraft.bungee.bungeeyaml.supereasyconfig.Config;

public class MainConfig extends Config {
    
    ServerKeeper plugin;
    
    public MainConfig(ServerKeeper plugin) {
	this.plugin = plugin;
        CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
        CONFIG_HEADER = "ServerKeeper Config file";
        try {
            this.init();
        } catch (InvalidConfigurationException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not load config!", e);
        }
    }
    
    public Map<String, String> players = new HashMap<String, String>();
    
    public String defaultserver = "lobby";
}
