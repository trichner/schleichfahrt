package ch.n1b.bukkit.schleichfahrt;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

/**
 * Created on 03.01.2015.
 *
 * @author Thomas
 */
public class Log4jWrapper implements Filter {


    public static void wrap(Matrose matrose){
        ((Logger) LogManager.getRootLogger()).addFilter(new Log4jWrapper(matrose));
    }

    private Matrose matrose;
    private Log4jWrapper(Matrose matrose){
        this.matrose = matrose;
    }

    private Result mapResult(boolean bool){
        return bool ? Result.ACCEPT : Result.DENY;
    }

    @Override
    public Result getOnMismatch() {
        return Result.NEUTRAL;
    }

    @Override
    public Result getOnMatch() {
        return Result.NEUTRAL;
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String s, Object... objects) {
        return mapResult(matrose.test(s));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Object o, Throwable throwable) {
        return mapResult(matrose.test(o.toString()));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Message message,
                         Throwable throwable) {
        return mapResult(matrose.test(message.getFormattedMessage()));
    }

    @Override
    public Result filter(LogEvent logEvent) {
        return mapResult(matrose.test(logEvent.getMessage().getFormattedMessage()));
    }

    private boolean isStarted = false;
    @Override
    public void start() {
        isStarted = true;
    }

    @Override
    public void stop() {
        isStarted = false;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public boolean isStopped() {
        return !isStarted;
    }
}
