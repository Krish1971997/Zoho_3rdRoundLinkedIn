package parkingLot;

public abstract class Vehicle {
	
	protected String licensePlate;
	protected VehicleType type;

	public Vehicle(String licensePlate, VehicleType type) {
		this.licensePlate = licensePlate;
		this.type = type;
	}
	public VehicleType getType() {
		return type;
	}
}

class Car extends Vehicle {
	public Car(String licensePlate) {
		super(licensePlate, VehicleType.CAR);
	}
}

class Truck extends Vehicle {
	public Truck(String licensePlate) {
		super(licensePlate, VehicleType.TRUCK);
	}
}

class Motorcycle extends Vehicle {
	public Motorcycle(String licensePlate) {
		super(licensePlate, VehicleType.MOTORCYCLE);
	}
}
