package assignment6Package.ParkingLot;

import assignment6Package.Graph.Graph;

import java.util.HashMap;
import java.util.Set;

public class ParkingLotMoveService
{
   private Graph<ParkingLot> parkingOrderPossible;
   int counterRecur;
    int counterEdges;
    int counterLoop;

    public ParkingLotMoveService() {
        parkingOrderPossible = new Graph<>();
    }


    public void updateParkingOrderPossible(ParkingLot start) {
        parkingOrderPossible.addVertex(start);
        updateRecursiveUtil(start);
       System.out.println("\n counterRecur:"+counterRecur);
        System.out.println("\n counterEdges"+counterEdges);
        System.out.println("\n counterLoop"+counterLoop);
   }

   private void updateRecursiveUtil(ParkingLot start) {
       counterRecur++;
       Set<Car> lastMoveOptions = start.getEmptySpace().getReservedFor();
       for(Car car : lastMoveOptions)
       {
           counterLoop++;
           HashMap<Space,Car> endSpaceToCars = new HashMap<>(start.getSpaceByCarMap());
           ParkingLot afterMoveParkingLot = new ParkingLot(endSpaceToCars);
           afterMoveParkingLot.moveCarToEmptySpace(car);

           if(parkingOrderPossible.containsVertex(afterMoveParkingLot)) {
               parkingOrderPossible.addEdge(start, afterMoveParkingLot);
               System.out.println("\nfrom: " + start + " to " + afterMoveParkingLot);
               counterEdges++;
               continue;
           }
           else {
               parkingOrderPossible.addEdge(start, afterMoveParkingLot);
               updateRecursiveUtil(afterMoveParkingLot);
               System.out.println("\nfrom: " + start + " to " + afterMoveParkingLot);
               counterEdges++;
           }
       }
   }
}
