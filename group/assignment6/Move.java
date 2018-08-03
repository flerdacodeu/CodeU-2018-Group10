/**
 * Represents a move from a parking space to another by a car.
 */
public class Move {
  private int carNum;
  
  public Move(int carNum) {
    this.carNum = carNum;
  }

  @Override
  public boolean equals(Object arg0) {
    
    if (arg0 == this) {
      return true;
    }
    
    if (arg0 == null || !(arg0 instanceof Move)) {
      return false;
    }
    
    Move move = (Move) arg0;
    
    return (this.carNum == move.carNum);
  }

  @Override
  public String toString() {
    return "Car " + this.carNum;
  }
}
