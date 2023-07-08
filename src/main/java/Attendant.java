import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Attendant {
    private Set<ParkingLot> parkingLots;
    public Attendant() {
        this.parkingLots = new HashSet<>();
    }

    public void addParkingLot(ParkingLot parkingLot){
        if(this.parkingLots.contains(parkingLot) == false)
            this.parkingLots.add(parkingLot);
    }

    public ParkingSlip parkInFirstParkingLotWithFreeSpace(Parkable vehicle){
        for(ParkingLot firstVacantarkingLot : parkingLots) {
            if (firstVacantarkingLot.getOwner().getParkingLotStatus(firstVacantarkingLot).equalsIgnoreCase("vacant")) {
                return new ParkingSlip(firstVacantarkingLot, firstVacantarkingLot.park(vehicle), vehicle);
            }
        }
        return new ParkingSlip(null, 0, null);
    }

    public ParkingSlip parkInParkingLotWithMinimumParkedCars(Parkable vehicle){
        ParkingLot parkingLotWithMinimumParkedCars = null;
        for(ParkingLot parkingLot : parkingLots) {
            if(parkingLotWithMinimumParkedCars==null){
                parkingLotWithMinimumParkedCars = parkingLot;
                continue;
            }
            if (parkingLot.getOwner().getParkingLotStatus(parkingLot).equalsIgnoreCase("vacant") &&
                    parkingLot.getExistingParkedCount() < parkingLotWithMinimumParkedCars.getExistingParkedCount()) {
                parkingLotWithMinimumParkedCars=parkingLot;
            }
        }
        if(parkingLotWithMinimumParkedCars != null)
            return new ParkingSlip(parkingLotWithMinimumParkedCars, parkingLotWithMinimumParkedCars.park(vehicle), vehicle);
        else
            return new ParkingSlip(null, 0, null);
    }

    public Parkable unpark(ParkingSlip parkingSlip){
        if(parkingLots.contains(parkingSlip.getParkingLot())){
            return parkingSlip.getParkingLot().unpark(parkingSlip.getAllocation(), parkingSlip.getVehicle());
        }
        return null;
    }
}
