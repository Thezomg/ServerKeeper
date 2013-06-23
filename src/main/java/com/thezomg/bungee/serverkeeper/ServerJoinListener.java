
package com.thezomg.bungee.serverkeeper;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.craftminecraft.bungee.bungeeyaml.bukkitapi.InvalidConfigurationException;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;

public class ServerJoinListener implements Listener {

    ServerKeeper plugin;
    
    public ServerJoinListener(ServerKeeper plugin) {
        this.plugin = plugin;
    }
    
    void onLogin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        ServerInfo server = plugin.getProxy().getServerInfo("Lobby");
        if (plugin.config.players.keySet().contains(player.getName().toLowerCase())) {
            server = plugin.getProxy().getServers().get(plugin.config.players.get(player.getName().toLowerCase()));
        }
        event.getPlayer().connect(server);
    }
    
    void onServerChange(ServerConnectedEvent event) {
        plugin.config.players.put(event.getPlayer().getName().toLowerCase(), event.getServer().getInfo().getName());
        try {
            plugin.config.save();
        } catch (InvalidConfigurationException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save the config", ex);
        }
    }
    
}
