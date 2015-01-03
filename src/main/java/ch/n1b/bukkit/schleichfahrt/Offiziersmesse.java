package ch.n1b.bukkit.schleichfahrt;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * Created on 03.01.2015.
 *
 * inspiration by obu.ObuFilter
 *
 * @author n1b
 */
public class Offiziersmesse implements Filter{

    private Set<Filter> filters = new HashSet<>();

    public boolean isLoggable(LogRecord record)
    {
        // apply filters
        for (Filter filter : filters) {
            if(!filter.isLoggable(record)){
                return false;
            }
        }
        return true;
    }

    public void add(Filter filter){
        filters.add(filter);
    }

    public void flush(Filter filter){
        filters.clear();
    }
}
