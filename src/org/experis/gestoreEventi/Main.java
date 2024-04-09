package org.experis.gestoreEventi;

import org.experis.gestoreEventi.exception.EventManagerException;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Event event = null;

        System.out.println("===========================================");
        System.out.println("================ ADD EVENT ================");
        System.out.println("===========================================");

        do {
            System.out.println("Enter details of the new event:");
            System.out.print("Title: ");
            String title = scan.nextLine();
            // TODO: vedere se far inserire la data separata usando LocalDate.of(year,month,day)

            System.out.print("Date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scan.nextLine());
            System.out.print("Location capacity: ");
            int locationCapacity = scan.nextInt();

            try {
                event = new Event(title,date,locationCapacity);
            } catch (EventManagerException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (event == null);

        System.out.println("===========================================");
        System.out.println(event);

        System.out.println("===========================================");
        System.out.print("Do you want to make a booking for this event? (y/n): ");
        String makeBookingChoice = scan.next();

        if (makeBookingChoice.equalsIgnoreCase("y")) {
            System.out.print("Enter the number of seats to book: ");
            int seatsToBook = scan.nextInt();
            try {
                event.addBooking(seatsToBook);
                System.out.println("Booking successful!");
            } catch (EventManagerException e) {
                System.out.println("Booking failed: " + e.getMessage());
            }
        }

        System.out.println("===========================================");
        System.out.printf("Seats booked: %d%n", event.getSeats_booked());
        System.out.printf("Free seats: %d%n", event.getFreeSeats());
        System.out.println("===========================================");

        scan.close();
    }
}
