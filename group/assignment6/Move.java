/**
 * Represents a move from a parking space to another by a car.
 */
public class Move {
  private int carNum;
  private int fromSpaceNum;
  private int toSpaceNum;
  
  public Move(int carNum, int fromSpaceNum, int toSpaceNum) {
    this.carNum = carNum;
    this.fromSpaceNum = fromSpaceNum;
    this.toSpaceNum = toSpaceNum;
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
    
    return (this.carNum == move.carNum
            && this.fromSpaceNum == move.fromSpaceNum
            && this.toSpaceNum == move.toSpaceNum);
  }

  @Override
  public String toString() {
    return "Car: " + this.carNum + ", from: " + this.fromSpaceNum + ", to: " + this.toSpaceNum;
  }
}
