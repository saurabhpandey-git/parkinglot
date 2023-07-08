import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class Car implements Parkable {
    public final String regId;

    public Car(String regId) {
        this.regId = regId;
    }
}



class ParkingLotTest {

//    @Test
//    @DisplayName("Should be able to park for No car parked yet and capacity 10")
//    public void carParked0AndCapacity10(){
//        ParkingLot parkingLot = new ParkingLot(10,0);
//        int allocation = parkingLot.park(new Car("abc"));
//        assertTrue(allocation>0 && allocation<=10);
//    }
//
//    @Test
//    @DisplayName("Should be able to park for 1 car parked yet and capacity 10")
//    public void carParked1AndCapacity10(){
//        ParkingLot parkingLot = new ParkingLot(10,1);
//        int allocation = parkingLot.park(new Car("abc"));
//        boolean validAllocation = allocation>0 && allocation<=10;
//        assertTrue(validAllocation);
//    }
//
//    @Test
//    @DisplayName("Should be able to park for 9 car parked yet and capacity 10")
//    public void carParked9AndCapacity10(){
//        ParkingLot parkingLot = new ParkingLot(10,9);
//        int allocation = parkingLot.park(new Car("abc"));
//        assertTrue(allocation>0 && allocation<=10);
//    }
//

//
//    @Test
//    @DisplayName("Should be able to park for 11 car parked yet and capacity 10")
//    public void carParked11AndCapacity10(){
//        ParkingLot parkingLot = new ParkingLot(10,11);
//        int allocation = parkingLot.park(new Car("abc"));
//        assertTrue(allocation==0);
//    }
//
//    @Test
//    @DisplayName("Should be able to park for 11 car parked yet and capacity 10")
//    public void carParked11AndCapacity10(){
//        ParkingLot parkingLot = new ParkingLot(10,11);
//        int allocation = parkingLot.park(new Car("abc"));
//        assertTrue(allocation==0);
//    }

//    @Test
//    @DisplayName("Should be able to parkfor No car parked yet and capacity 10")
//    public void carParked0TestAnCapacity10(){
//        ParkingLot parkingLot = new ParkingLot(2,0);
//        boolean status = parkingLot.park();
//        assertTrue(status);
//    }

    @Test
    @DisplayName("Should be able to unpark a car when there is only one car")
    public void unparkCar(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(owner, 10);
        owner.addParkingLot(parkingLot);
        Parkable parkedVehicle = new Car("hgsd");
        int allocatedSpace = parkingLot.park(parkedVehicle);
        Parkable unparkedCar = parkingLot.unpark(allocatedSpace, parkedVehicle);
        assertEquals(parkedVehicle, unparkedCar);
    }

    @Test
    @DisplayName("Should be able to unpark the same car that is already parked and is the only car in the parking lot")
    public void unparkCarwhen1CarParked(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(owner, 10);
        owner.addParkingLot(parkingLot);
        Car parkedCar = new Car("hgsd");
        int allocatedSpace = parkingLot.park(parkedCar);
        Parkable unparkedCar = parkingLot.unpark(allocatedSpace, parkedCar);
        assertEquals(parkedCar, unparkedCar);
    }

    @Test
    @DisplayName("Should be able to unpark the same car that is already parked and there are 2 cars in the parking lot")
    public void unparkCarwhen2CarParked(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(owner, 10);
        owner.addParkingLot(parkingLot);
        Car parkedCar1 = new Car("hgsd");
        Car parkedCar2 = new Car("abc");
        int allocatedSpace1 = parkingLot.park(parkedCar1);
        int allocatedSpace2 = parkingLot.park(parkedCar2);
        Parkable unparkedCar = parkingLot.unpark(allocatedSpace1, parkedCar1);
        assertEquals(parkedCar1, unparkedCar);
    }

    @Test
    @DisplayName("Should not be able to unpark a car when there is no car parked in the parking lot")
    public void unparkCarwhenNoCarParked(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(owner, 10);
        owner.addParkingLot(parkingLot);
        Parkable unparkedCar = parkingLot.unpark(3, new Car("hgsd"));
        assertEquals(null, unparkedCar);
    }

    @Test
    @DisplayName("Should not be able to unpark a car when the same car is not parked in the parking lot or the allocation space is incorrect")
    public void unparkCarwhenCarNotParkedAtThePassedAllocatedSpace(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(owner, 10);
        owner.addParkingLot(parkingLot);
        Car parkedCar1 = new Car("hgsd");
        Car parkedCar2 = new Car("abc");
        int allocatedSpace1 = parkingLot.park(parkedCar1);
        int allocatedSpace2 = parkingLot.park(parkedCar2);
        Parkable unparkedCar = parkingLot.unpark(allocatedSpace2, new Car("abcd"));
        assertEquals(null, unparkedCar);
    }

    @Test
    @DisplayName("Should not be able to park as car with same Car Id exists")
    public void carShouldNotBeParkedAsCarWithSameRegnIDExists(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(owner, 10);
        owner.addParkingLot(parkingLot);
        Car parkedCar1 = new Car("hgsd");
        Car parkedCar2 = new Car("hgsd");
        int allocatedSpace1 = parkingLot.park(parkedCar1);
        int allocatedSpace2 = parkingLot.park(parkedCar1);
        assertEquals(-1, allocatedSpace2);
    }

    @Test
    @DisplayName("Should not be able to park as parking lot is full")
    public void carNotParkedAsparkingLotFull(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(owner, 2);
        owner.addParkingLot(parkingLot);
        Parkable car1 = new Car("mnb");
        Parkable car2 = new Car("zxc");
        Parkable car3 = new Car("bvc");
//        System.out.println(owner.getParkingLotStatus(parkingLot));
        int allocation1 = parkingLot.park(car1);
//        System.out.println(owner.getParkingLotStatus(parkingLot));
        int allocation2 = parkingLot.park(car2);
//        System.out.println(owner.getParkingLotStatus(parkingLot));
        int allocation3 = parkingLot.park(car3);
        boolean parkingOverflow = (allocation3==0);
        assertTrue(parkingOverflow);
    }

//    @Test
//    @DisplayName("Should not be able to park as parking lot is full")
//    public void carTest(){
//        ParkingLot parkingLot = new ParkingLot(2);
//        Car car1 = new Car("mnb");
//        Car car2 = car1;
//        Car car3 = new Car("mnb");
//        System.out.println("---------------------------");
//        System.out.println(System.identityHashCode(car1));
//        System.out.println(System.identityHashCode(car2));
//        System.out.println(System.identityHashCode(car3));
//        int allocation1 = parkingLot.park(car1);
//        int allocation2 = parkingLot.park(car2);
//        int allocation3 = parkingLot.park(car3);
//        boolean parkingOverflow = (allocation3==0);
//        assertTrue(parkingOverflow);
//    }



    @Test
    @DisplayName("Attendant should be able to park to the parking lot in place 1 for a parking lot that has no car yet")
    public void attendantParkTheCarInTheFirstPlaceWhenParkingLotHasNoCar(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(owner, 2);
        owner.addParkingLot(parkingLot);
        Attendant attendant = new Attendant();
        attendant.addParkingLot(parkingLot);

        Parkable car = new Car("mnb");

        ParkingSlip parkingSlip = attendant.parkInFirstParkingLotWithFreeSpace(car);
        assertEquals(parkingLot,parkingSlip.getParkingLot());
        assertEquals(1, parkingSlip.getAllocation());
        assertEquals(car, parkingSlip.getVehicle());
    }

    @Test
    @DisplayName("Attendant should be able to park to the parking lot in place 2 for a parking lot that has 1 car")
    public void attendantParkTheCarInTheSpace2WhenTheParkingLotHas1Car(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(owner, 2);
        owner.addParkingLot(parkingLot);

        Attendant attendant = new Attendant();
        attendant.addParkingLot(parkingLot);

        Parkable car1 = new Car("mnb");
        Parkable car2 = new Car("abc");

        ParkingSlip parkingSlip1 = attendant.parkInFirstParkingLotWithFreeSpace(car1);
        ParkingSlip parkingSlip2 = attendant.parkInFirstParkingLotWithFreeSpace(car2);
        assertEquals(parkingLot,parkingSlip2.getParkingLot());
        assertEquals(2, parkingSlip2.getAllocation());
        assertEquals(car2, parkingSlip2.getVehicle());
    }

    @Test
    @DisplayName("Attendant should not be able to park to the parking lot if the parking is full")
    public void attendantCannotParkTheCarWhenParkingLotIsFull(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot = new ParkingLot(owner, 1);
        owner.addParkingLot(parkingLot);
        Attendant attendant = new Attendant();
        attendant.addParkingLot(parkingLot);

        Parkable car1 = new Car("mnb");
        Parkable car2 = new Car("abc");

        ParkingSlip parkingSlip1 = attendant.parkInFirstParkingLotWithFreeSpace(car1);
        ParkingSlip parkingSlip2 = attendant.parkInFirstParkingLotWithFreeSpace(car2);
        assertEquals(null,parkingSlip2.getParkingLot());
        assertEquals(0, parkingSlip2.getAllocation());
        assertEquals(null, parkingSlip2.getVehicle());
    }


    @Test
    @DisplayName("Attendant managing multiple Parking Lots should be able to park the car in place 1 of the parking lot 2 is parking lot 1 already has a car and parking lot 2 in empty")
    public void attendantWithMultipleParkingLotParksTheCarinTheVacantParkingLotWhichHasMinimumParkedCars(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot1 = new ParkingLot(owner, 10);
        owner.addParkingLot(parkingLot1);
        ParkingLot parkingLot2 = new ParkingLot(owner, 5);
        owner.addParkingLot(parkingLot2);

        Attendant attendant = new Attendant();
        attendant.addParkingLot(parkingLot1);
        attendant.addParkingLot(parkingLot2);

        Parkable car1 = new Car("mnb");
        Parkable car2 = new Car("abc");

        ParkingSlip parkingSlip1 = attendant.parkInParkingLotWithMinimumParkedCars(car1);
        ParkingSlip parkingSlip2 = attendant.parkInParkingLotWithMinimumParkedCars(car2);

        assertEquals(1, parkingSlip2.getAllocation());
        assertEquals(car2, parkingSlip2.getVehicle());
    }

    @Test
    @DisplayName("Attendant who is not managing the given Parking Lot in parking slip should notbe able to unpark the car from it")
    public void attendantNotWorkingForParkingLotinParkingSlipShouldNotBeAbleToUnparkIt(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot1 = new ParkingLot(owner, 10);
        owner.addParkingLot(parkingLot1);
        ParkingLot parkingLot2 = new ParkingLot(owner, 5);
        owner.addParkingLot(parkingLot2);

        Attendant attendant1 = new Attendant();
        attendant1.addParkingLot(parkingLot1);

        Attendant attendant2 = new Attendant();
        attendant2.addParkingLot(parkingLot2);

        Parkable car1 = new Car("mnb");
        Parkable car2 = new Car("abc");

        ParkingSlip parkingSlip1 = attendant1.parkInParkingLotWithMinimumParkedCars(car1);
        ParkingSlip parkingSlip2 = attendant2.parkInParkingLotWithMinimumParkedCars(car2);

        Parkable vehicle = attendant2.unpark(parkingSlip1);

        assertEquals(null, vehicle);
    }

    @Test
    @DisplayName("Attendant who is managing the given Parking Lot in parking slip should be able to unpark the car from it")
    public void attendantNotWorkingForParkingLotinParkingSlipShouldBeAbleToUnparkIt(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot1 = new ParkingLot(owner, 10);
        owner.addParkingLot(parkingLot1);
        ParkingLot parkingLot2 = new ParkingLot(owner, 5);
        owner.addParkingLot(parkingLot2);

        Attendant attendant1 = new Attendant();
        attendant1.addParkingLot(parkingLot1);

        Attendant attendant2 = new Attendant();
        attendant2.addParkingLot(parkingLot2);

        Parkable car1 = new Car("mnb");
        Parkable car2 = new Car("abc");

        ParkingSlip parkingSlip1 = attendant1.parkInParkingLotWithMinimumParkedCars(car1);
        ParkingSlip parkingSlip2 = attendant2.parkInParkingLotWithMinimumParkedCars(car2);

        Parkable vehicle = attendant2.unpark(parkingSlip2);

        assertEquals(car2, vehicle);


    }


    @Test
    @DisplayName("Attendant who is managing the given Parking Lot in parking slip should be able to unpark the car from it")
    public void persistParkingLot2(){
        ParkingLotOwner owner = new ParkingLotOwner();
        ParkingLot parkingLot1 = new ParkingLot(owner, 10);
        owner.addParkingLot(parkingLot1);
        ParkingLot parkingLot2 = new ParkingLot(owner, 5);
        owner.addParkingLot(parkingLot2);

        Repository repository = mock(Repository.class);
        when(repository.persistParkingLot(parkingLot1)).thenReturn(true);

        when(repository.persistParkingLotOwner(owner)).thenReturn(true);

        Attendant attendant1 = new Attendant();
        attendant1.addParkingLot(parkingLot1);

        Attendant attendant2 = new Attendant();
        attendant2.addParkingLot(parkingLot2);

        Parkable car1 = new Car("mnb");
        Parkable car2 = new Car("abc");

        ParkingSlip parkingSlip1 = attendant1.parkInParkingLotWithMinimumParkedCars(car1);
        ParkingSlip parkingSlip2 = attendant2.parkInParkingLotWithMinimumParkedCars(car2);

        assertTrue(repository.persistParkingLot(parkingLot1));
        verify(repository).persistParkingLot(parkingLot1);
//        verify(repository).persistParkingLot(parkingLot2);

    }


}