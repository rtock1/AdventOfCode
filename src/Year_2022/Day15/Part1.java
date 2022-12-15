package Year_2022.Day15;

import Helper_Classes.Position;

import java.util.Arrays;
import java.util.HashMap;

public class Part1 {
    public String start(String input) {
        String[] sensors = input.split("\\n");
        Position[] sensorLoc = new Position[sensors.length];
        long[] distances = new long[sensorLoc.length];
        HashMap<Position, Boolean> beacons = new HashMap<>();
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long maxDistance = Long.MIN_VALUE;
        for (int i=0;i<sensors.length;i++) {
            long x = Long.parseLong(sensors[i].split("=")[1].split(",")[0]);
            long y = Long.parseLong(sensors[i].split("=")[2].split(":")[0]);
            if (x < minX) {
                minX = x;
            }
            if (x > maxX) {
                maxX = x;
            }
            long beaconX = Long.parseLong(sensors[i].split("=")[3].split(",")[0]);
            long beaconY = Long.parseLong(sensors[i].split("=")[4]);
            Position beaconPosition = new Position(beaconX, beaconY);
            if (beacons.get(beaconPosition) == null) {
                beacons.put(beaconPosition, true);
            }
            distances[i] = Math.abs(beaconX-x) + Math.abs(beaconY-y);
            if (distances[i] > maxDistance) {
                maxDistance = distances[i];
            }
            sensorLoc[i] = new Position(x,y);
        }
        long rowCheck = 2000000;
        long totalSquares = 0;
        for (long i=minX-maxDistance;i<maxX+maxDistance;i++) {
            boolean contain = true;
            for (int a=0;a<sensors.length;a++) {
                if (Math.abs(sensorLoc[a].x - i) + Math.abs(sensorLoc[a].y-rowCheck) <= distances[a]) {
                    if (beacons.get(new Position(i, rowCheck)) == null) {
                        contain = false;
                        break;
                    }
                }
            }
            if (!contain) {
                totalSquares++;
            }
        }
        return totalSquares + "";
    }
}
