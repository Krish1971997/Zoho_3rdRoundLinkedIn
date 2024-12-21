package parkingLot;


public class Main {
	public static void main(String[] args) {
		ParkingLot parkingLot = ParkingLot.getInstance();

		// Add levels and parking spots
		Level level1 = new Level(1, 10);
		Level level2 = new Level(2, 10);

		parkingLot.addLevel(level1);
		parkingLot.addLevel(level2);

		// Park vehicles
		Vehicle car = new Car("CAR123");
		Vehicle truck = new Truck("TRUCK456");
		Vehicle motorcycle = new Motorcycle("MOTO789");

		parkingLot.parkVehicle(car);
		parkingLot.parkVehicle(truck);
		parkingLot.parkVehicle(motorcycle);

		// Display availability
		parkingLot.displayAvailability();

		// Unpark a vehicle
		parkingLot.unParkVehicle(car);
		parkingLot.displayAvailability();
	}
}

