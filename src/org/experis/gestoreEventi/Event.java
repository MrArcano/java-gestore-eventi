package org.experis.gestoreEventi;

import org.experis.gestoreEventi.exception.EventManagerException;

import java.time.LocalDate;

public class Event {

    // ATTRIBUTES
    private String title;
    private LocalDate date;
    private int location_capacity;
    private int seats_booked;

    private static int DEFAULT_BOOKED = 0;

    // CONSTRUCTOR
    public Event(String title, LocalDate date, int location_capacity) throws EventManagerException {

        checkTitle(title);
        checkDate(date);
        checkLocationCapacity(location_capacity);

        this.title = title;
        this.date = date;
        this.location_capacity = location_capacity;
        this.seats_booked = DEFAULT_BOOKED;
    }

    private static void checkLocationCapacity(int location_capacity) throws EventManagerException {
        if (location_capacity <= 0) {
            throw new EventManagerException("Location capacity must be greater than 0. Please provide a valid capacity.");
        }
    }

    private static void checkDate(LocalDate date) throws EventManagerException {
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new EventManagerException("Event date cannot be null or in the past. Please provide a valid date.");
        }
    }

    private static void checkTitle(String title) throws EventManagerException {
        if (title == null || title.isEmpty()) {
            throw new EventManagerException("Event title cannot be null or empty. Please provide a valid title.");
        }
    }

    // GETTER
    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getLocation_capacity() {
        return location_capacity;
    }

    public int getSeats_booked() {
        return seats_booked;
    }

    public int getFreeSeats (){
        return this.location_capacity - this.seats_booked;
    }

    // SETTER
    public void setTitle(String title) throws EventManagerException {
        checkTitle(title);
        this.title = title;
    }

    public void setDate(LocalDate date) throws EventManagerException {
        checkDate(date);
        this.date = date;
    }

    // METHODS
    public void addBooking(int value) throws EventManagerException {
        if (this.date.isBefore(LocalDate.now())) {
            throw new EventManagerException("Cannot add booking for past events.");
        }
        if (this.seats_booked + value > this.location_capacity) {
            throw new EventManagerException("Exceeds location capacity. Cannot add booking.");
        }
        this.seats_booked += value;
    }

    public void cancelBooking(int value) throws EventManagerException {
        if (this.date.isBefore(LocalDate.now())) {
            throw new EventManagerException("Cannot cancel booking for past events.");
        }
        if (this.seats_booked - value < 0) {
            throw new EventManagerException("Cannot cancel more bookings than what's been made.");
        }
        this.seats_booked -= value;
    }

    @Override
    public String toString() {
        return "Event [ " +
                "Title: '" + title + "' - " +
                "Date: " + getStringDate() +
                " ]";
    }

    public String getStringDate() {
        return date.getDayOfWeek() + " " + date.getDayOfMonth() + " " + date.getMonth() + " " + date.getYear();
    }

}
