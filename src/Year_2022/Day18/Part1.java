package Year_2022.Day18;

import Helper_Classes.Array;
import Helper_Classes.Position;

import java.util.ArrayList;

public class Part1 {
    ArrayList<Position> cubeLocations;
    ArrayList<Position> xFaces;
    ArrayList<Position> yFaces;
    ArrayList<Position> zFaces;
    public String start(String input) {
        String[] lines = input.split("\\n");
        xFaces = new ArrayList<>();
        yFaces = new ArrayList<>();
        zFaces = new ArrayList<>();
        cubeLocations = new ArrayList<>();
        for (String line:lines) {
            cubeLocations.add(new Position(Array.strToLong(line.split(","))));
        }
        for (Position curr: cubeLocations) {
            xFaces.add(new Position(curr.x, curr.y, curr.z));
            xFaces.add(new Position(curr.x+1, curr.y, curr.z));
            yFaces.add(new Position(curr.x, curr.y, curr.z));
            yFaces.add(new Position(curr.x, curr.y+1, curr.z));
            zFaces.add(new Position(curr.x, curr.y, curr.z));
            zFaces.add(new Position(curr.x, curr.y, curr.z+1));
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
}
