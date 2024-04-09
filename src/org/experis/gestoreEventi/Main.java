package org.experis.gestoreEventi;

import org.experis.gestoreEventi.exception.EventManagerException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        try {
//            Concert concert = new Concert("aaa",LocalDate.parse("2030-02-02"),50, LocalTime.parse("12:10"),new BigDecimal("30.555"));
//            System.out.println(concert.getFormattedDataHours());
//            System.out.println(concert.getPrice());
//            System.out.println(concert);
//
//        } catch (EventManagerException e) {
//            System.out.println("Error: " + e.getMessage());
//        }


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

            try {

                System.out.print("Location capacity: ");
                int locationCapacity = Integer.parseInt(scan.nextLine());
                event = new Event(title,date,locationCapacity);

            } catch (EventManagerException e) {
                System.out.println("===========================================");
                System.out.println("Error: " + e.getMessage());
                System.out.println("===========================================");
            } catch (NumberFormatException e) {
                System.out.println("===========================================");
                System.out.println("Error: Invalid input. Please enter a valid number.");
                System.out.println("===========================================");
            }

        } while (event == null);

        System.out.println("===========================================");
        System.out.println(event);

        String choice;
        do {
            choice = getChoice("Do you want to make a booking for this event? (y/n): ", scan);

            if (choice.equalsIgnoreCase("y")) {
                try {
                    System.out.print("Enter the number of seats to book: ");
                    int seatsToBook = Integer.parseInt(scan.nextLine());
                    event.addBooking(seatsToBook);
                    System.out.println("===========================================");
                    System.out.println("Booking successful!");
                    System.out.println("===========================================");
                    stampInfoSeats(event);
                } catch (EventManagerException e) {
                    System.out.println("===========================================");
                    System.out.println("Booking failed: " + e.getMessage());
                    System.out.println("===========================================");
                } catch (NumberFormatException e) {
                    System.out.println("===========================================");
                    System.out.println("Error: Invalid input. Please enter a valid number.");
                    System.out.println("===========================================");
                }
            }
        }while (choice.equalsIgnoreCase("y") && event.getFreeSeats() > 0);

        choice = "y";
        while (choice.equalsIgnoreCase("y") && event.getSeats_booked() > 0){
            choice = getChoice("Do you want to cancel a booking for this event? (y/n): ", scan);

            if (choice.equalsIgnoreCase("y")) {
                try {
                    System.out.print("Enter the number of seats to cancel: ");
                    int seatsToCancel = Integer.parseInt(scan.nextLine());
                    event.cancelBooking(seatsToCancel);
                    System.out.println("===========================================");
                    System.out.println("Booking cancellation successful!");
                    System.out.println("===========================================");
                    stampInfoSeats(event);
                } catch (EventManagerException e) {
                    System.out.println("===========================================");
                    System.out.println("Booking cancellation failed: " + e.getMessage());
                    System.out.println("===========================================");
                } catch (NumberFormatException e) {
                    System.out.println("===========================================");
                    System.out.println("Error: Invalid input. Please enter a valid number.");
                    System.out.println("===========================================");
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
            choice = scan.nextLine();
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
