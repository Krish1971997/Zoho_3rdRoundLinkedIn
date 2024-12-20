package parkingLot;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private int floor;
    private List<ParkingSpot> parkingSpots;

    public Level(int floor, int numSpots) {
        this.floor = floor;
        parkingSpots = new ArrayList<>();
        for (int i = 0; i < numSpots; i++) {
            parkingSpots.add(new ParkingSpot(i));
        }
    }

    public synchronized boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isAvailable() && spot.getVehicleType() == vehicle.getType()) {
                spot.parkVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean unParkVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isAvailable() && spot.getParkedVehicle().equals(vehicle)) {
                spot.unParkVehicle();
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        System.out.println("Level " + floor + " availability:");
        for (ParkingSpot spot : parkingSpots) {
            System.out.println("Spot " + spot.getSpotNumber() + ": " + (spot.isAvailable() ? "Available" : "Occupied"));
        }
    }
}
