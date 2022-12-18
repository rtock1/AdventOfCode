package Helper_Classes;

public class Position {
    //Redesign to use an Arraylist with an unspecified amount of dimensions
    public long x;
    public long y;
    public long z;
    boolean hasZ;
    public Position(long x, long y) {
        this.x = x;
        this.y = y;
        hasZ = false;
    }
    public Position(long x, long y, long z) {
        this(x,y);
        this.z = z;
        hasZ = true;
    }
    public Position(long[] values) {
        this(values[0], values[1], values[2]);
    }

    @Override
    public String toString() {
        if (hasZ) {
            return "(" + x + "," + y + "," + z + ")";
        } else {
            return "(" + x + "," + y + ")";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position compare = (Position) o;
            if (compare.x == x && compare.y == y && compare.z == z) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int)(((x*101)+y)*101 + z);
    }
}
