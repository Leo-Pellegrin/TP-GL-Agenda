package agenda;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.Objects;

/**
 * Description : A repetitive event that terminates after a given date, or after
 * a given number of occurrences
 */
public class FixedTerminationEvent extends RepetitiveEvent {

    
    /**
     * Constructs a fixed terminationInclusive event ending at a given date
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     */
    private String title;
    private LocalDateTime start;
    private Duration duration;
    private ChronoUnit frequency;
    private LocalDate terminationInclusive;
    private long numberOfOccurrences;

    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, LocalDate terminationInclusive) {
        super(title, start, duration, frequency);
        this.terminationInclusive = terminationInclusive;
    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, long numberOfOccurrences) {
        super(title, start, duration, frequency);
        this.numberOfOccurrences = numberOfOccurrences;
    }

    /**
     *
     * @return the termination date of this repetitive event
     */
    public LocalDate getTerminationDate() {
        if(Objects.isNull(terminationInclusive)){
            LocalDateTime tmp = this.getStart();
            for(int i = 1; i < this.getNumberOfOccurrences(); i++){
                tmp = tmp.plus(this.getFrequency().getDuration());
            }
            return tmp.toLocalDate();
        }
        return terminationInclusive;
    }

    /**
     * @return
     */
    public long getNumberOfOccurrences(){
        if(terminationInclusive == null){
            return numberOfOccurrences;
        }
        else {
            int tmp = 0;
            LocalDate date = this.getStart().toLocalDate();
            while(date.isBefore(ChronoLocalDate.from(this.terminationInclusive))){
                if(this.getFrequency() == ChronoUnit.DAYS){
                    date = date.plusDays(1);
                }
                else if(this.getFrequency() == ChronoUnit.WEEKS){
                    date = date.plusWeeks(1);
                }
                else if(this.getFrequency() == ChronoUnit.MONTHS){
                    date = date.plusYears(1);
                }
                tmp += 1;
            }
            return tmp;
        }
    }

    @Override
    public boolean isInDay(LocalDate day){
        LocalDate date = this.getStart().toLocalDate();
        while(date.isBefore(ChronoLocalDate.from(this.getTerminationDate()))){
            if(this.getException().contains(day)){
                return false;
            }
            if(date.equals(day)){
                return true;
            }
            if(this.getFrequency() == ChronoUnit.DAYS){
                date = date.plusDays(1);
            }
            else if(this.getFrequency() == ChronoUnit.WEEKS){
                date = date.plusWeeks(1);
            }
            else if(this.getFrequency() == ChronoUnit.MONTHS){
                date = date.plusMonths(1);
            }

        }
        return false;
    }
}
