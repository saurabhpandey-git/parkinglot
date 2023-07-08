public class ParkingLot {
    private Parkable[] parkingSpaces ;
    private int maxCapacity;
    private int existingParkedCount;

    private ParkingLotOwner owner;
    public ParkingLot(ParkingLotOwner owner, int capacity) {
        this.maxCapacity = capacity;
        this.parkingSpaces = new Parkable[capacity];
        this.existingParkedCount=0;
        this.owner = owner;
    }

//    public ParkingLot(int capacity, int existingParkedCount) {
//        this.maxCapacity = capacity;
//        this.parkingSpaces = new Car[capacity];
//        this.existingParkedCount=existingParkedCount;
//    }

    public int park(Parkable vehicle){
        if(vehicle == null){
            return -2;// signifying not available space so cant park
        }
        if(this.existingParkedCount >= this.maxCapacity){
            return 0;// signifying not available space so cant park
        }
        for(int i=0; i<this.maxCapacity; i++)
        {
            if(this.parkingSpaces[i]==null)
            {
                continue;
            }
            if(System.identityHashCode(this.parkingSpaces[i]) == System.identityHashCode(vehicle))
            {
                return -1;
            }
        }
        existingParkedCount += 1;
        for(int i=0; i<maxCapacity; i++){
            if(parkingSpaces[i] == null){
                parkingSpaces[i] = vehicle;
                if(this.existingParkedCount == this.maxCapacity){
                    this.owner.requestToUpdateLotFullStatus(this, true);
                }
                return i+1;
            }
        }
        return 0;
    }

    public Parkable unpark(int parkedSpot, Parkable carClaimed){
        if(this.parkingSpaces[parkedSpot-1] == null) {
            return null;
        }
        if(!(System.identityHashCode(this.parkingSpaces[parkedSpot-1]) == System.identityHashCode(carClaimed))){
            return null;
        }
        Parkable vehicle = this.parkingSpaces[parkedSpot-1];
        this.parkingSpaces[parkedSpot-1] = null;
        this.owner.requestToUpdateLotFullStatus(this,false);
        return vehicle;
    }

    public ParkingLotOwner getOwner() {
        return this.owner;
    }

    public int getExistingParkedCount() {
        return this.existingParkedCount;
    }
}
