
package com.thezomg.bungee.serverkeeper;

import net.md_5.bungee.api.plugin.Plugin;

public class ServerKeeper extends Plugin {
    MainConfig config;

    @Override
    public void onEnable() {
	config = new MainConfig(this);
        getProxy().getPluginManager().registerListener(this, new ServerJoinListener(this));
	super.onEnable();
    }
    
    
}
