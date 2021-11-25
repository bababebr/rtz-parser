package Main.Tools;

import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class ETA_Calculator implements Serializable {
    private static final long serialVersionUID = -4982444376385756354L;
    public ETA_Calculator(){

    }

    public ZonedDateTime calc(ZonedDateTime depDate, ZoneId arrivalTimeZne , double speed, double dist){
        ZonedDateTime arrDate = depDate.withZoneSameInstant(arrivalTimeZne).plusMinutes((long) (dist/speed*60));
        return arrDate;
    }
}
