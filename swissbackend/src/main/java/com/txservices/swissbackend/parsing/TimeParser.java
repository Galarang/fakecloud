package com.txservices.swissbackend.parsing;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

/**
 * TimeParser is used to parse and calculate duration provided by Swiss public transport API, calculating the average
 * of all durations received (list of different departure times received that have different approximates for arrival time)
 *
 * @author Dusan Batinica
 *
 */

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TimeParser {

    private int days;

    private int tempDays;

    private int hours;

    private int tempHours;

    private int minutes;

    private int tempMinutes;

    private int seconds;

    private int tempSeconds;

    private boolean initialized;

    /**
     * Method that loops through all arrival-departure pair durations, combines parse and average method,
     * initializes respective time units (days, minutes, hours, seconds), or adds time to each if already initialized
     *
     * @return returns duration in easily readable String format.
     *
     */
    public String getDuration(List<String> durations) {
            for (String duration : durations) {
                parseDuration(duration);
                if (!initialized) {
                    this.days = tempDays;
                    this.hours = tempHours;
                    this.minutes = tempMinutes;
                    this.seconds = tempSeconds;
                    initialized = true;
                } else {
                    addDuration();
                }
            }
            average(durations.size());
            return this.toString();
    }

    /**
     * Method used to calculate the average duration, using BigDecimal to convert remaining part (decimals) into units
     * "bellow" current unit (eg. .5 hours is converted into 30 minutes by multiplying by 60 etc)
     */
    private void average(int numberOfDurations) {
        Double avgDay = (double) this.days / numberOfDurations;
        Double avgHour = (double) this.hours / numberOfDurations;
        Double avgMinute = (double) this.minutes / numberOfDurations;
        Double avgSecond = (double) this.seconds / numberOfDurations;

        BigDecimal bigDay = new BigDecimal(avgDay.toString());
        BigDecimal bigHour = new BigDecimal(avgHour.toString());
        BigDecimal bigMinute = new BigDecimal(avgMinute);
        BigDecimal bigSecond = new BigDecimal(avgSecond);

        this.days = bigDay.intValue();
        BigDecimal remainderDay = bigDay.remainder(BigDecimal.ONE);
        bigHour = bigHour.add(remainderDay.multiply(new BigDecimal(24)));
        this.hours = bigHour.intValue();
        BigDecimal remainderHour = bigHour.remainder(BigDecimal.ONE);
        bigMinute = bigMinute.add(remainderHour.multiply(new BigDecimal(60)));
        this.minutes = bigMinute.intValue();
        BigDecimal remainderMinute = bigMinute.remainder(BigDecimal.ONE);
        bigSecond = bigSecond.add(remainderMinute.multiply(new BigDecimal(60)));
        this.seconds = bigSecond.intValue();
    }

    /**
     * Method that is adding time to each time unit, if unit has "more" time than possible it is converted into next unit and
     * added to initialized variable representing that unit
     * Example -> (68 minutes becomes 8 minutes and 1 hour is added to hours)
     *
     */
    private void addDuration() {
        this.seconds += this.tempSeconds;
        int of = 0;
        while (this.seconds >= 60) {
            of++;
            this.seconds -= 60;
        }
        this.minutes += this.tempMinutes + of;
        of = 0;
        while (this.minutes >= 60) {
            of++;
            this.minutes -= 60;
        }
        this.hours += this.tempHours + of;
        of = 0;
        while (this.hours >= 24) {
            of++;
            this.hours -= 24;
        }
        this.days += this.tempDays + of;
    }

    /**
     * Method that is used to split the format received representing duration ("00d03:20:10")
     * First split by "d", getting array of 2 elements (00 and 03:20:10), then split by : and get individual remaining elements
     * Parsing through all of them initializing temporaryDays in order not to override original values allowing time to be added.
     */
    private void parseDuration(String duration) {
        String[] splitedByLetterD = duration.split("d");
        String[] splittedByColon = splitedByLetterD[1].split(":");
        this.tempDays = ((splitedByLetterD.length >= 1) ? Integer.parseInt(splitedByLetterD[0]) : 0);
        this.tempHours = ((splittedByColon.length >= 1) ? Integer.parseInt(splittedByColon[0]) : 0);
        this.tempMinutes = ((splittedByColon.length >= 2) ? Integer.parseInt(splittedByColon[1]) : 0);
        this.tempSeconds = ((splittedByColon.length >= 3) ? Integer.parseInt(splittedByColon[2]) : 0);
    }

    @Override
    public String toString() {
        return "Days:" + this.days + ", Hours:" + hours + ", Minutes:" + minutes + ", Seconds:" + seconds;
    }
}
