import java.util.HashMap;
import java.util.Map;

public class ParkingLotOwner {
    private Map<ParkingLot, Boolean> parkingLots = new HashMap<ParkingLot, Boolean>();//<parkingLot, isParkingLotFull>

    public ParkingLotOwner() {
    }

    public boolean addParkingLot(ParkingLot parkingLot){
        Object alreadyexists = this.parkingLots.get(parkingLot);
        if(alreadyexists == null) {
            this.parkingLots.put(parkingLot, false);
            return true;
        }
        return false;
    }

    public void requestToUpdateLotFullStatus(ParkingLot parkingLot, boolean isParkingLotFull) {
        this.parkingLots.put(parkingLot, isParkingLotFull);
    }

    public String getParkingLotStatus(ParkingLot parkingLot){
        return this.parkingLots.get(parkingLot) ? "Full" : "Vacant";
    }
}
