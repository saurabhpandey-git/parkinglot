public class ParkingSlip{
    private final ParkingLot parkingLot;
    private final int allocation;
    private final Parkable vehicle;

    public ParkingSlip(ParkingLot parkingLot, int allocation, Parkable vehicle) {
        this.parkingLot = parkingLot;
        this.allocation = allocation;
        this.vehicle = vehicle;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public int getAllocation() {
        return allocation;
    }

    public Parkable getVehicle() {
        return this.vehicle;
    }

}
