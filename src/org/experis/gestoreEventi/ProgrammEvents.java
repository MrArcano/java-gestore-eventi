package org.experis.gestoreEventi;

import org.experis.gestoreEventi.exception.EventManagerException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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

    public void clearEvents() {
        events.clear();
    }

    public String getProgramSummary() {
        String output = "";

//        ArrayList<Event> list = events;
//
//        list.sort(Comparator.comparing(Event::getDate));
//        // list.sort(Comparator.comparing(ev -> ev.getDate()));
//
//        for(Event event : list){
//            output += event.getDate() + " - " + event.getTitle() + "\n";
//        }

        output = events.stream()
                .sorted(Comparator.comparing(Event::getDate))
                .map(event -> event.getDate() + " - " + event.getTitle())
                .collect(Collectors.joining("\n"));

        return output;
    }
}
