package org.experis.gestoreEventi;

import org.experis.gestoreEventi.exception.EventManagerException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProgrammEvents {
    private String title;
    private ArrayList<Event> events;

    public ProgrammEvents(String title, ArrayList<Event> events) throws EventManagerException {
        if(title == null || title.isEmpty()){
            throw new EventManagerException("Event title cannot be null or empty. Please provide a valid title.");
        }
        this.title = title;
        this.events = events;
    }

    public ProgrammEvents(String title) throws EventManagerException {
        this(title,new ArrayList<>());
    }

    public void addEvent(Event event){
        this.events.add(event);
    }

    public ArrayList<Event> getEventsOnDate(LocalDate date){
        ArrayList<Event> list = new ArrayList<>();

//        for (Event event : this.events) {
//            if(event.getDate().isEqual(date)){
//                list.add(event);
//            }
//        }

        list = events.stream().filter(event -> event.getDate().isEqual(date)).collect(Collectors.toCollection(ArrayList::new));

        return list;
    }

    public int getNumberOfEvents(){
        return this.events.size();
    }


}
