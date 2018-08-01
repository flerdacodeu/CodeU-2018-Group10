import org.junit.Test;

public class ParkingLotTest {

  @Test
  public void rearrangesParkingLot() {
    ParkingLot parkingLot = new ParkingLot();
    parkingLot.addSpaceCarPairById('A', 2);
    parkingLot.addSpaceCarPairById('B', 0);
    parkingLot.addSpaceCarPairById('C', 3);
    parkingLot.addSpaceCarPairById('D', 1);

    ParkingLot parkingLotFinal = new ParkingLot();
    parkingLotFinal.addSpaceCarPairById('A', 3);
    parkingLotFinal.addSpaceCarPairById('B', 2);
    parkingLotFinal.addSpaceCarPairById('C', 1);
    parkingLotFinal.addSpaceCarPairById('D', 0);

    parkingLot.rearrangeParkingLot(parkingLot, parkingLotFinal);
  }
}
