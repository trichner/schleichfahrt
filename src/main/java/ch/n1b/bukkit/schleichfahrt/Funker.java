package ch.n1b.bukkit.schleichfahrt;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created on 03.01.2015.
 *
 * @author Thomas
 */
public class Funker implements Listener{

    private Matrose matrose;

    public Funker(Matrose matrose) {
        this.matrose = matrose;
    }

    @SuppressWarnings("deprecation")
    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if(!matrose.test(e.getMessage())){
            e.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if(!matrose.test(e.getMessage())){
            e.setCancelled(true);
        }
    }
}
