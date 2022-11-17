package agenda;

import java.util.*;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
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
<<<<<<< HEAD
=======
       

>>>>>>> refs/remotes/origin/master
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
<<<<<<< HEAD
        this.lRepEvent.add(date);
    }

    public HashSet<LocalDate> getException(){
        return this.lRepEvent;
=======
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
>>>>>>> refs/remotes/origin/master
    }

    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
        return this.frequency;

        
    }

    @Override
    public boolean isInDay(LocalDate aDay) {
        if(!(aDay.isBefore(this.getStart().toLocalDate()) || aDay.isAfter(this.getStart().plus(this.getDuration()).toLocalDate())))return true;
        LocalDateTime temp = this.getStart();
        while((temp.isBefore(aDay.atStartOfDay()))){
            temp = temp.plus(getFrequency().getDuration());
            if (!(aDay.isBefore(temp.toLocalDate()) || aDay.isAfter(temp.plus(this.getDuration()).toLocalDate()))) {
                if(!getException().contains(aDay)) return true;
            }
        }
        return false;
    }
}
