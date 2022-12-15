package Helper_Classes;

public class Position {
    public long x;
    public long y;
    public Position(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position compare = (Position) o;
            if (compare.x == x && compare.y == y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) Math.pow(x,y);
    }
}
