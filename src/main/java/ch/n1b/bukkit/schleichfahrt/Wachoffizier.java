package ch.n1b.bukkit.schleichfahrt;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 03.01.2015.
 *
 *
 *
 * @author n1b
 */
public class Wachoffizier implements Filter{

    private final Pattern pattern;

    public Wachoffizier(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public boolean isLoggable(LogRecord record)
    {
        if (record.getMessage() == null) {
            return true;
        }
        Matcher matcher = pattern.matcher(record.getMessage());

        return !matcher.find();
    }
}
