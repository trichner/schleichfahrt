package ch.n1b.bukkit.schleichfahrt;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Filter;
import java.util.logging.Logger;

/**
 * Created on 03.01.2015.
 *
 * inspiration by obu.ObuFilter and ShutTheHellUpPlugin
 *
 * @author n1b
 */
public class SchleichfahrtPlugin extends JavaPlugin {

    private static final String FILTERFILE = "filters.txt";
    private static final Logger log = Logger.getLogger("");


    @Override
    public void onEnable() {
        //--- set our filter
        log.setFilter(loadFilter());

        Filter messe = loadFilter();
        getServer().getLogger().setFilter(messe);
        Logger.getLogger("Minecraft").setFilter(messe);
        for (Plugin plugin : getServer().getPluginManager().getPlugins()) {
            plugin.getLogger().setFilter(messe);
        }
    }

    @Override
    public void onDisable() {
    }

    private Filter loadFilter(){
        Path filterfile = this.getDataFolder().toPath().resolve(FILTERFILE);
        Offiziersmesse messe = new Offiziersmesse();
        try {
            if(!Files.exists(filterfile)){
                this.getLogger().warning("No " + FILTERFILE + " creating an empty one.");
                Files.createDirectories(filterfile.getParent());
                Files.createFile(filterfile);
            }
            List<String> regexes = Files.readAllLines(filterfile, StandardCharsets.UTF_8);
            for (String regex : regexes) {
                if(!regex.startsWith("#")){
                    messe.add(new Wachoffizier(regex));
                }
            }
            this.getLogger().info("Loaded " + regexes.size() + " filters");
        } catch (IOException e) {
            this.getLogger().warning("Cannot read " + FILTERFILE);
        }
        return messe;
    }
}
