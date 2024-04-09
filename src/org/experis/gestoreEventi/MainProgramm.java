package org.experis.gestoreEventi;

import org.experis.gestoreEventi.exception.EventManagerException;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainProgramm {
    public static void main(String[] args) {
        ArrayList<Event> list = new ArrayList<>();

        try {
            list.add(new Event("event1", LocalDate.parse("2030-02-02"),50));
            list.add(new Event("event2", LocalDate.parse("2027-07-10"),250));
            list.add(new Event("event3", LocalDate.parse("2024-05-15"),150));
            list.add(new Event("event4", LocalDate.parse("2027-07-10"),350));
            list.add(new Event("event5", LocalDate.parse("2025-11-05"),750));

            ProgrammEvents progEvents = new ProgrammEvents("Programm1",list);
            ProgrammEvents progEventsNULL = new ProgrammEvents("Programm");

            System.out.println("Number Events: " + progEvents.getNumberOfEvents());
            System.out.println("Number Events: " + progEventsNULL.getNumberOfEvents());

            progEvents.addEvent(new Event("event6", LocalDate.parse("2033-05-05"),500));
            progEventsNULL.addEvent(new Event("event6", LocalDate.parse("2033-05-05"),500));

            System.out.println("Number Events: " + progEvents.getNumberOfEvents());
            System.out.println("Number Events: " + progEventsNULL.getNumberOfEvents());

            System.out.println(progEvents.getEventsOnDate(LocalDate.parse("2027-07-10")));
            System.out.println(progEvents.getEventsOnDate(LocalDate.parse("2026-07-10")));

        } catch (EventManagerException e) {
            throw new RuntimeException(e);
        }

    }
}
