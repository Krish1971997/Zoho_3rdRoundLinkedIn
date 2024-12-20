package parkingLot;

public class ParkingSpot {
    private int number;
    private Vehicle parkedVehicle;
    private VehicleType vehicleType;

    public ParkingSpot(int number) {
        this.number = number;
    }

    public synchronized boolean isAvailable() {
        return parkedVehicle == null;
    }

    public synchronized void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
    }

    public synchronized void unParkVehicle() {
        this.parkedVehicle = null;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public int getSpotNumber() {
        return number;
    }
}
