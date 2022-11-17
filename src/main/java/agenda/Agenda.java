package agenda;

import java.time.LocalDate;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    private ArrayList<Event> lEvents;

    public Agenda(){
        lEvents = new ArrayList<Event>();
    }

    public void addEvent(Event e){
        lEvents.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return and iteraror to the events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        List<Event> lEventInDay = new ArrayList<Event>();
        for(int i = 0; i < lEvents.size(); i++){
            if(lEvents.get(i).isInDay(day)){
                lEventInDay.add(lEvents.get(i));
            }
        }
        return lEventInDay;
    }
}
