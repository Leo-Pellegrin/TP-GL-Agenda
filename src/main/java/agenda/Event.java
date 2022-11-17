package agenda;

import java.sql.Date;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;

public class Event {

    /**
     * The myTitle of this event
     */
    private String myTitle;
    
    /**
     * The starting time of the event
     */
    private LocalDateTime myStart;

    /**
     * The durarion of the event 
     */
    private Duration myDuration;


    /**
     * Constructs an event
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     */
    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;
    }

    /**
     * Tests if an event occurs on a given day
     *
     * @param aDay the day to test
     * @return true if the event occurs on that day, false otherwise
     */
    public boolean isInDay(LocalDate aDay) {
        if((aDay.getDayOfYear() == myStart.getDayOfYear() &&  aDay.getYear() == myStart.getYear()) 
            || (aDay.isAfter(ChronoLocalDate.from(myStart)) && aDay.isBefore(ChronoLocalDate.from(myStart.plus(myDuration)))) 
            || (aDay.getDayOfYear() == myStart.plus(myDuration).getDayOfYear() && aDay.getYear() == myStart.plus(myDuration).getYear())
            ){
            return true;
        }
        return false;
    }

    /**
     * @return the myTitle
     */
    public String getTitle() {
        return myTitle;
    }

    /**
     * @return the myStart
     */
    public LocalDateTime getStart() {
        return myStart;
    }


    /**
     * @return the myDuration
     */
    public Duration getDuration() {
        return myDuration;
    }

    public String toString(){
        return myTitle;
    }

   
    
}
