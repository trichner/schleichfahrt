package ch.n1b.bukkit.schleichfahrt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 03.01.2015.
 *
 *
 *
 * @author n1b
 */
public class Wachoffizier implements Matrose {

    private final Pattern pattern;

    public Wachoffizier(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean test(String t) {
        Matcher matcher = pattern.matcher(t);
        return !matcher.find();
    }
}
