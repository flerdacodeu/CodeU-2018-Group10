/**
 * Represents a move from a parking space to another by a car.
 */
public class DetailedMove {
  private int carNum;
  private int fromSpaceNum;
  private int toSpaceNum;
  
  public DetailedMove(int carNum, int fromSpaceNum, int toSpaceNum) {
    this.carNum = carNum;
    this.fromSpaceNum = fromSpaceNum;
    this.toSpaceNum = toSpaceNum;
  }

  @Override
  public boolean equals(Object arg0) {
    
    if (arg0 == this) {
      return true;
    }
    
    if (arg0 == null || !(arg0 instanceof DetailedMove)) {
      return false;
    }
    
    DetailedMove move = (DetailedMove) arg0;
    
    return (this.carNum == move.carNum
            && this.fromSpaceNum == move.fromSpaceNum
            && this.toSpaceNum == move.toSpaceNum);
  }

  @Override
  public String toString() {
    return "Car: " + this.carNum + ", from: " + this.fromSpaceNum + ", to: " + this.toSpaceNum;
  }
}
