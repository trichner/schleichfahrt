package ch.n1b.bukkit.schleichfahrt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 03.01.2015.
 *
 * inspiration by obu.ObuFilter
 *
 * @author n1b
 */
public class Offiziersmesse implements Matrose{

    private List<Matrose> filters = new ArrayList<>();

    public void add(Matrose filter){
        filters.add(filter);
    }

    public void flush(){
        filters.clear();
    }

    @Override
    public boolean test(String t) {
        // apply filters
        for (Matrose filter : filters) {
            if(!filter.test(t)){
                return false;
            }
        }
        return true;
    }
}
