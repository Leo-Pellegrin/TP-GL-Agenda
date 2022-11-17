package agenda;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.chrono.ChronoLocalDate;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {
    /**
     * Constructs a repetitive event
     *
     * @param title the title of this event
     * @param start the start of this event
     * @param duration myDuration in seconds
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */
    private String title;
    private LocalDateTime start;
    private Duration duration;
    private ChronoUnit frequency;
    private HashSet<LocalDate> lRepEvent;

    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        this.frequency = frequency;
        this.lRepEvent = new HashSet<LocalDate>();
       

    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
       /*  LocalDate tmp = this.start.toLocalDate();
        Duration du = frequency.getDuration(); */
        this.lRepEvent.add(date);
    }

    @Override
    public boolean isInDay(LocalDate aDay) {
        
        if(this.lRepEvent.contains(aDay)){
            return false;
        }
        if(this.frequency == ChronoUnit.DAYS){
           return true; 
        }
        if(this.frequency == ChronoUnit.WEEKS){
            
            
         }
         
        if((aDay.getDayOfYear() == this.getStart().getDayOfYear() &&  aDay.getYear() == this.getStart().getYear()) 
        || (aDay.isAfter(ChronoLocalDate.from(this.getStart())) && aDay.isBefore(ChronoLocalDate.from(this.getStart().plus(this.getDuration())))) 
        || (aDay.getDayOfYear() == this.getStart().plus(this.getDuration()).getDayOfYear() && aDay.getYear() == this.getStart().plus(this.getDuration()).getYear())){
        return true;
    }

    return false;
    }

    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
        return this.frequency;

        
    }

}
