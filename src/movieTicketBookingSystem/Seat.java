package movieTicketBookingSystem;

public class Seat {
    private String id;
    private int row;
    private int column;
    private SeatType type;
    private double price;
    private SeatStatus status;

    public Seat(String id, int row, int column, SeatType type, double price, SeatStatus status) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.type = type;
        this.price = price;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public SeatType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
