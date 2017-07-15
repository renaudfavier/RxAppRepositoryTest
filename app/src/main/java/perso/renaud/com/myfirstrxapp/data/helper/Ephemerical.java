package perso.renaud.com.myfirstrxapp.data.helper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by renaud on 14/07/17.
 */

public class Ephemerical<T> {

    public static final String TAG = "Ephemerical";

    public final static long PERRISHMENT_TIME_IN_MILLIS = 1000 * 50;

    private final T value;
    private final Date deathDate;
    private final Calendar calendar;

    public Ephemerical(T value) {
        calendar = Calendar.getInstance();
        this.value = value;

        long timeiInMillis = calendar.getTimeInMillis();
        deathDate = new Date(timeiInMillis + PERRISHMENT_TIME_IN_MILLIS);
    }

    public boolean isAlive() {

        boolean isAlive = Calendar.getInstance().getTime().before(deathDate);

        return isAlive;
    }

    public T get() {
        return value;
    }
}
