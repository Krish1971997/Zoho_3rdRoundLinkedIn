package movieTicketBookingSystem;

public class Movie {
    private String id;
    private String title;
    private String description;
    private int durationMinutes;

    public Movie(String id, String title, String description, int durationMinutes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationInMinutes() {
        return durationMinutes;
    }
}
