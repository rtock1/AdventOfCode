package Year_2022.Day18;

import Helper_Classes.Array;
import Helper_Classes.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class Part2 {
    ArrayList<Position> cubeLocations;
    ArrayList<Position> xFaces;
    ArrayList<Position> yFaces;
    ArrayList<Position> zFaces;
    long minx;
    long miny;
    long minz;
    long maxx;
    long maxy;
    long maxz;
    ArrayList<Position> trappedAir;
    HashMap<Position, Boolean> visited;
    public String start(String input) {
        trappedAir = new ArrayList<>();
        minx = Integer.MAX_VALUE;
        miny = Integer.MAX_VALUE;
        minz = Integer.MAX_VALUE;
        String[] lines = input.split("\\n");
        xFaces = new ArrayList<>();
        yFaces = new ArrayList<>();
        zFaces = new ArrayList<>();
        cubeLocations = new ArrayList<>();
        for (String line:lines) {
            cubeLocations.add(new Position(Array.strToLong(line.split(","))));
        }
        for (Position a: cubeLocations) {
            if (a.x > maxx) {
                maxx = a.x;
            }
            if (a.y > maxy) {
                maxy = a.y;
            }
            if (a.z > maxz) {
                maxz = a.z;
            }
            if (a.x < minx) {
                minx = a.x;
            }
            if (a.y < miny) {
                miny = a.y;
            }
            if (a.z < minz) {
                minz = a.z;
            }

        }
        getTrappedAirPockets();
        System.out.println(trappedAir);
        for (Position curr: cubeLocations) {
            if (!isTrapped(new Position(curr.x - 1, curr.y, curr.z))){
                xFaces.add(new Position(curr.x, curr.y, curr.z));
            }
            if (!isTrapped(new Position(curr.x + 1, curr.y, curr.z))){
                xFaces.add(new Position(curr.x + 1, curr.y, curr.z));
            }
            if (!isTrapped(new Position(curr.x, curr.y - 1, curr.z))) {
                yFaces.add(new Position(curr.x, curr.y, curr.z));
            }
            if (!isTrapped(new Position(curr.x, curr.y + 1, curr.z))) {
                yFaces.add(new Position(curr.x, curr.y + 1, curr.z));
            }
            if (!isTrapped(new Position(curr.x, curr.y, curr.z-1))) {
                zFaces.add(new Position(curr.x, curr.y, curr.z));
            }
            if (!isTrapped(new Position(curr.x, curr.y, curr.z + 1))) {
                zFaces.add(new Position(curr.x, curr.y, curr.z + 1));
            }
        }
        ArrayList<Position> newx = new ArrayList<>();
        ArrayList<Position> newy = new ArrayList<>();
        ArrayList<Position> newz = new ArrayList<>();
        for (Position curr: xFaces) {
            if (newx.contains(curr)) {
                newx.remove(curr);
            } else {
                newx.add(curr);
            }
        }
        for (Position curr: yFaces) {
            if (newy.contains(curr)) {
                newy.remove(curr);
            } else {
                newy.add(curr);
            }
        }
        for (Position curr: zFaces) {
            if (newz.contains(curr)) {
                newz.remove(curr);
            } else {
                newz.add(curr);
            }
        }
        return (newx.size() + newy.size() + newz.size()) + "";
    }
    public boolean isTrapped(Position position) {
        return trappedAir.contains(position);
    }
    public void getTrappedAirPockets() {
        for (long x = minx; x<maxx; x++) {
            for (long y=miny; y<maxy; y++) {
                for (long z = minz; z < maxz; z++) {
                    Position thisPosition = new Position(x,y,z);
                    if (!trappedAir.contains(thisPosition) && isAir(thisPosition)) {
                        visited = new HashMap<>();
                        if (addIfTrapped(thisPosition)) {
                            for (Position a : visited.keySet()) {
                                trappedAir.add(a);
                            }
                        }
                    }
                }
            }
        }
    }
    public boolean addIfTrapped(Position position) {
        if (position.x > maxx || position.y > maxy || position.z > maxz || position.x < minx || position.y < miny || position.z < minz) {
            return false;
        }
        if (!isAir(position)) {
            return true;
        }
        if (visited.get(position) != null) {
            return true;
        }
        visited.put(position, true);

        if (visited.get(new Position(position.x + 1, position.y, position.z))!=null && visited.get(new Position(position.x - 1, position.y, position.z))!=null && visited.get(new Position(position.x, position.y+1, position.z))!=null && visited.get(new Position(position.x, position.y-1, position.z))!=null && visited.get(new Position(position.x, position.y, position.z+1))!=null && visited.get(new Position(position.x, position.y, position.z-1))!=null) {
            return true;
        } else {
            return addIfTrapped(new Position(position.x + 1, position.y, position.z)) && addIfTrapped(new Position(position.x - 1, position.y, position.z)) && addIfTrapped(new Position(position.x, position.y+1, position.z)) && addIfTrapped(new Position(position.x, position.y-1, position.z)) && addIfTrapped(new Position(position.x, position.y, position.z+1)) && addIfTrapped(new Position(position.x, position.y, position.z-1));
        }

    }

    public boolean isAir(Position position) {
        return !cubeLocations.contains(position);
    }
}
