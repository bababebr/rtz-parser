package utils;

import lombok.RequiredArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
public class EtaCalculator {

    public ZonedDateTime calc(ZonedDateTime depDate, ZoneId arrivalTimeZne, double speed, double dist) {
        ZonedDateTime arrDate = depDate.withZoneSameInstant(arrivalTimeZne).plusMinutes((long) (dist / speed * 60));
        return arrDate;
    }
}
