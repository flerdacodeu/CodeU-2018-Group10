package assignment6;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class RearrangementServiceTest {

    @Test
   public void test() {
    CarRearrangmentService service = new CarRearrangmentService();
        HashMap<Space, Car> before = new HashMap<>();
        HashMap<Space, Car> after = new HashMap<>();
        before.put(new Space('a', SpaceState.EMPTY), null);
        before.put(new Space('b'), new Car(1));
        before.put(new Space('c'), new Car(2));
        before.put(new Space('d'), new Car(3));
        before.put(new Space('e'), new Car(4));

        after.put(new Space('a', SpaceState.EMPTY), null);
        after.put(new Space('b'), new Car(3));
        after.put(new Space('c'), new Car(2));
        after.put(new Space('d'), new Car(4));
        after.put(new Space('e'), new Car(1));
        List<CarMove> moves = service.rearrangeCars(before, after);
        moves.forEach(System.out::println);
   }
}