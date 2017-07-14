package perso.renaud.com.myfirstrxapp.data.helper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by renaud on 14/07/17.
 */

public class Ephemerical<T> {

    public static final String TAG = "Ephemerical";

    public final static long PERRISHMENT_TIME_IN_MILLIS = 1000 * 20;

    private final T value;
    private final Date deathDate;
    private final Calendar calendar;

    public Ephemerical(T value) {
        calendar = Calendar.getInstance();
        this.value = value;
        deathDate = new Date(calendar.getTimeInMillis() + PERRISHMENT_TIME_IN_MILLIS);
    }


    public boolean isAlive() {

        return calendar.getTime().before(deathDate);
    }

    public T get() {
        return value;
    }



}
