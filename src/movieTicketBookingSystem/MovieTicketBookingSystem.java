package movieTicketBookingSystem;

import java.util.*;

public class MovieTicketBookingSystem {
    private List<Movie> movies = new ArrayList<>();
    private List<Theater> theaters = new ArrayList<>();
    private Map<String, Show> shows = new HashMap<>();
    private Map<String, Booking> bookings = new HashMap<>();
    
    private static MovieTicketBookingSystem instance;

    private MovieTicketBookingSystem() {}

    public static synchronized MovieTicketBookingSystem getInstance() {
        if (instance == null) {
            instance = new MovieTicketBookingSystem();
        }
        return instance;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addTheater(Theater theater) {
        theaters.add(theater);
    }

    public void addShow(Show show) {
        shows.put(show.getId(), show);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Theater> getTheaters() {
        return theaters;
    }

    public Show getShow(String id) {
        return shows.get(id);
    }

    public Booking bookTickets(User user, Show show, List<Seat> seats) {
        double totalPrice = 0;
        for (Seat seat : seats) {
            totalPrice += seat.getPrice();
            seat.setStatus(SeatStatus.BOOKED);
        }
        Booking booking = new Booking(UUID.randomUUID().toString(), user, show, seats, totalPrice, BookingStatus.PENDING);
        bookings.put(booking.getId(), booking);
        return booking;
    }

    public void confirmBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            booking.setStatus(BookingStatus.CONFIRMED);
        }
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            booking.setStatus(BookingStatus.CANCELLED);
            for (Seat seat : booking.getSeats()) {
                seat.setStatus(SeatStatus.AVAILABLE);
            }
        }
    }
}
