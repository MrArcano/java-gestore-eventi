package org.experis.gestoreEventi;

import org.experis.gestoreEventi.exception.EventManagerException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event{

    // ATTRIBUTES
    private LocalTime hours;
    private BigDecimal price;

    // CONSTRUCTOR
    public Concert(String title, LocalDate date, int location_capacity, LocalTime hours, BigDecimal price) throws EventManagerException {
        super(title, date, location_capacity);

        checkHours(hours);

        checkPrice(price);

        this.hours = hours;
        this.price = price;
    }

    private static void checkPrice(BigDecimal price) throws EventManagerException {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new EventManagerException("Price cannot be negative.");
        }
    }

    private void checkHours(LocalTime hours) throws EventManagerException {
        if (getDate().isEqual(LocalDate.now())) {
            if (hours.isBefore(LocalTime.now())) {
                throw new EventManagerException("Event cannot be scheduled for past hours.");
            }
        }
    }

    // GETTER
    public LocalTime getHours() {
        return hours;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    // SETTER

    public void setHours(LocalTime hours) throws EventManagerException {
        checkHours(hours);
        this.hours = hours;
    }

    public void setPrice(BigDecimal price) throws EventManagerException {
        checkPrice(price);
        this.price = price;
    }

    // METHODS
    public String getFormattedDataHours() {
        return getStringDate() + " " + getHours();
    }

    @Override
    public String toString() {
        return "Concert [ " +
                "Title: '" + getTitle() + "' - " +
                "Date: " + getFormattedDataHours() + " - " +
                "Price: " + getPrice() +
                "€ ]";
    }
}
