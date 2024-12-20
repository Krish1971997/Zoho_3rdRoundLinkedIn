package movieTicketBookingSystem;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        MovieTicketBookingSystem system = MovieTicketBookingSystem.getInstance();

        // Creating Movies
        Movie movie1 = new Movie("M1", "Inception", "A mind-bending thriller", 148);
        system.addMovie(movie1);

        // Creating Theaters
        Theater theater1 = new Theater("T1", "Grand Theater", "Downtown", new ArrayList<>());
        system.addTheater(theater1);

        // Creating Seats
        Map<String, Seat> seats = new HashMap<>();
        seats.put("S1", new Seat("S1", 1, 1, SeatType.NORMAL, 10.0, SeatStatus.AVAILABLE));
        seats.put("S2", new Seat("S2", 1, 2, SeatType.PREMIUM, 15.0, SeatStatus.AVAILABLE));

        // Creating Shows
        Show show1 = new Show("SH1", movie1, theater1, LocalDateTime.now(), LocalDateTime.now().plusHours(2), seats);
        system.addShow(show1);

        // Creating a User
        User user1 = new User("U1", "John Doe", "john@example.com");

        // Booking Tickets
        List<Seat> selectedSeats = new ArrayList<>();
        selectedSeats.add(seats.get("S1"));
        Booking booking1 = system.bookTickets(user1, show1, selectedSeats);

        // Confirm Booking
        system.confirmBooking(booking1.getId());

        // Cancel Booking
        // system.cancelBooking(booking1.getId());

        // Print booking details
        System.out.println("Booking ID: " + booking1.getId());
        System.out.println("Booking Status: " + booking1.getStatus());
        System.out.println("Total Price: $" + booking1.getTotalPrice());
    }
}
