package Year_2022.Day14;

import Helper_Classes.Array;

import java.util.ArrayList;
import java.util.HashMap;

public class Part1 {
    HashMap<ArrayList<Integer>, Boolean> rocks;
    int maxY;
    public String start(String input) {
        String[] formations = input.split("\\n");
        rocks = new HashMap<>();
        for (String formation:formations) {
            String[] locations = formation.split(" -> ");
            int[][] values = new int[locations.length][2];
            for (int i=0;i< locations.length;i++) {
                values[i] = Array.strToInt(locations[i].split(","));
            }
            for (int i = 0;i<locations.length-1;i++) {
                if (values[i][0] == values[i+1][0]) {
                    int currY = values[i][1];
                    if (values[i][1] > values[i+1][1]) {
                        while (currY >= values[i+1][1]) {
                            ArrayList<Integer> toAdd = new ArrayList<>();
                            toAdd.add(values[i][0]);
                            toAdd.add(currY);
                            if (rocks.get(toAdd) == null) {
                                rocks.put(toAdd, true);
                            }
                            currY--;
                        }
                    } else {
                        while (currY <= values[i+1][1]) {
                            ArrayList<Integer> toAdd = new ArrayList<>();
                            toAdd.add(values[i][0]);
                            toAdd.add(currY);
                            if (rocks.get(toAdd) == null) {
                                rocks.put(toAdd, true);
                            }
                            currY++;
                        }
                    }
                } else {
                    int currX = values[i][0];
                    if (values[i][0] > values[i+1][0]) {
                        while (currX >= values[i+1][0]) {
                            ArrayList<Integer> toAdd = new ArrayList<>();
                            toAdd.add(currX);
                            toAdd.add(values[i][1]);
                            if (rocks.get(toAdd) == null) {
                                rocks.put(toAdd, true);
                            }
                            currX--;
                        }
                    } else {
                        while (currX <= values[i+1][0]) {
                            ArrayList<Integer> toAdd = new ArrayList<>();
                            toAdd.add(currX);
                            toAdd.add(values[i][1]);
                            if (rocks.get(toAdd) == null) {
                                rocks.put(toAdd, true);
                            }
                            currX++;
                        }
                    }
                }
            }
        }
        maxY = getMaxY();
        int sand = 0;
        while (placeSand() == false) {
            sand++;
        }


        return sand + "";
    }
    public int getMaxY() {
        int currMaxY = 0;
        for (ArrayList<Integer> currKey:rocks.keySet()) {
            int currY = currKey.get(1);
            if (currY > currMaxY) {
                currMaxY = currY;
            }
        }
        return currMaxY;
    }
    public boolean placeSand() {
        ArrayList<Integer> currPosition = new ArrayList<>();
        currPosition.add(500);
        currPosition.add(0);
        while (true) {
            if (currPosition.get(1) >= maxY) {
                return true;
            }
            currPosition.set(1, currPosition.get(1) +1);
            if (rocks.get(currPosition) == null) {
                continue;
            }
            currPosition.set(0, currPosition.get(0) - 1);
            if (rocks.get(currPosition) == null) {
                continue;
            }
            currPosition.set(0, currPosition.get(0) + 2);
            if (rocks.get(currPosition) == null) {
                continue;
            }
            currPosition.set(1, currPosition.get(1) - 1);
            currPosition.set(0, currPosition.get(0) - 1);
            rocks.put(currPosition, false);
            //System.out.println(currPosition);
            return false;
        }
    }

}
