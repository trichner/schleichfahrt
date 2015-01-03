package ch.n1b.bukkit.schleichfahrt;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
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
 * @author Thomas
 */
public class SchleichfahrtPlugin extends JavaPlugin {

    private static final String FILTERFILE = "filters.txt";
    private static final Logger log = Logger.getLogger("Minecraft");

    private Filter oldFilter;

    @Override
    public void onEnable() {
        //--- set our filter
        oldFilter = log.getFilter();
        log.setFilter(loadFilter());
    }

    @Override
    public void onDisable() {
        log.setFilter(oldFilter);
    }

    private Filter loadFilter(){
        Path filterfile = this.getDataFolder().toPath().resolve(FILTERFILE);

        Offiziersmesse messe = new Offiziersmesse();
        try {
            List<String> regexes = Files.readAllLines(filterfile);
            for (String regex : regexes) {
                if(!regex.startsWith("#")){
                    messe.add(new Wachoffizier(regex));
                }
            }
        } catch (IOException e) {
            this.getLogger().warning("Cannot read " + FILTERFILE);
        }
        return messe;
    }
}
