package assignment6.ParkingLot;

import assignment6.Graph.Graph;

import java.util.HashMap;
import java.util.Set;

public class ParkingLotMoveService
{
   private Graph<ParkingLot> parkingOrderPossible;
   private int counterRecur;
   private int counterEdges;
   private int counterLoop;

    public ParkingLotMoveService() {
        parkingOrderPossible = new Graph<>();
    }


    public void updateParkingOrderPossible(ParkingLot start) {
        updateRecursiveUtil1(start);
       System.out.println("\n counterRecur:"+counterRecur);
        System.out.println("\n counterEdges"+counterEdges);
        System.out.println("\n counterLoop"+counterLoop);
   }

   private void updateRecursiveUtil1(ParkingLot startPL) {
       counterRecur++;
       Set<Car> firstMoveOptions = startPL.getEmptySpace().getReservedFor();
       for(Car car : firstMoveOptions)
       {
           counterLoop++;
           ParkingLot afterMovePL = new ParkingLot(startPL.getSpaceByCarMap());
           afterMovePL.moveCarToEmptySpace(car);

           boolean isGraphContainsConfiguration = parkingOrderPossible.containsVertex(afterMovePL);

           if(parkingOrderPossible.addEdge(startPL, afterMovePL))
           {
               counterEdges++;
           }
           if(!isGraphContainsConfiguration)
           {
               updateRecursiveUtil1(afterMovePL);
           }
       }
   }
}
