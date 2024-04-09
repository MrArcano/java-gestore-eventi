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

            LocalDate date = setDate(scan);

            // TODO: nextInt Exception
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

        String choice;
        do {
            choice = getChoice("Do you want to make a booking for this event? (y/n): ", scan);

            if (choice.equalsIgnoreCase("y")) {
                System.out.print("Enter the number of seats to book: ");
                int seatsToBook = scan.nextInt();
                try {
                    event.addBooking(seatsToBook);
                    System.out.println("Booking successful!");
                    stampInfoSeats(event);
                } catch (EventManagerException e) {
                    System.out.println("Booking failed: " + e.getMessage());
                }
            }
        }while (choice.equalsIgnoreCase("y") && event.getFreeSeats() > 0);

        choice = "y";
        while (choice.equalsIgnoreCase("y") && event.getSeats_booked() > 0){
            choice = getChoice("Do you want to cancel a booking for this event? (y/n): ", scan);

            if (choice.equalsIgnoreCase("y")) {
                System.out.print("Enter the number of seats to cancel: ");
                int seatsToCancel = scan.nextInt();
                try {
                    event.cancelBooking(seatsToCancel);
                    System.out.println("Booking cancellation successful!");
                    stampInfoSeats(event);
                } catch (EventManagerException e) {
                    System.out.println("Booking cancellation failed: " + e.getMessage());
                }
            }
        }

        scan.close();
    }

    private static String getChoice(String s, Scanner scan) {
        String choice;
        do {
            System.out.println("===========================================");
            System.out.print(s);
            choice = scan.next();
        } while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
        return choice;
    }

    private static void stampInfoSeats(Event event) {
        System.out.println("===========================================");
        System.out.printf("Seats booked: %d%n", event.getSeats_booked());
        System.out.printf("Free seats: %d%n", event.getFreeSeats());
    }

    private static LocalDate setDate(Scanner scan) {
        LocalDate date = null;
        do {
            try {
                System.out.print("Date (YYYY-MM-DD): ");
                date = LocalDate.parse(scan.nextLine());
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }while (date == null);
        return date;
    }
}
