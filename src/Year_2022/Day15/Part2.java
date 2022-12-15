package Year_2022.Day15;

import Helper_Classes.Position;

import java.util.Arrays;
import java.util.HashMap;

public class Part2 {
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
        int searchRadius = 4000000;
        long foundx = 0;
        long foundy = 0;
        for (int sensorBorderCheck = 0;sensorBorderCheck<sensors.length;sensorBorderCheck++) {
            Position[] startingPositions = new Position[]{
                    new Position(sensorLoc[sensorBorderCheck].x, sensorLoc[sensorBorderCheck].y+distances[sensorBorderCheck]+1),
                    new Position(sensorLoc[sensorBorderCheck].x+distances[sensorBorderCheck]+1, sensorLoc[sensorBorderCheck].y),
                    new Position(sensorLoc[sensorBorderCheck].x, -(sensorLoc[sensorBorderCheck].y+distances[sensorBorderCheck]+1)),
                    new Position(-(sensorLoc[sensorBorderCheck].x+distances[sensorBorderCheck]+1), sensorLoc[sensorBorderCheck].y)
            };
            int[] xMove = new int[]{1, -1, -1, 1};
            int[] yMove = new int[]{-1, -1, 1, 1};
            for (int iteration = 0; iteration < 4; iteration++) {
                Position currPosition = startingPositions[iteration];
                for (int position = 0;position < distances[sensorBorderCheck] + 1;position++) {
                    if (0 <= currPosition.x && currPosition.x <= searchRadius && 0 <= currPosition.y && currPosition.y <= searchRadius) {
                        boolean found = true;
                        for (int sensor = 0; sensor < sensors.length; sensor++) {
                            if (Math.abs(sensorLoc[sensor].x - currPosition.x) + Math.abs(sensorLoc[sensor].y - currPosition.y) <= distances[sensor]) {
                                found = false;
                            }
                        }
                        if (found) {
                            foundx = currPosition.x;
                            foundy = currPosition.y;
                            System.out.println("Found solution: "+ currPosition);
                        }
                    }
                    currPosition.x += xMove[iteration];
                    currPosition.y += yMove[iteration];
                }
            }
        }
        return (foundx * 4000000 + foundy) + "";
    }
}
