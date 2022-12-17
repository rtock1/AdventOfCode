package Year_2022.Day17;

import Helper_Classes.Array;
import Helper_Classes.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Part2 {
    long highestRock = -1;
    long cycle = 0;
    Shape shape = Shape.HORIZONTAL;
    Position rockPosition;
    ArrayList<char[]> board = new ArrayList<>();
    long cutRows = 0;
    int numWind;
    long rocksStopped = 0;
    HashMap<ArrayList<Long>, ArrayList<Long>> seenBefore = new HashMap<>();
    public String start(String input) {
        char[] instructions = Array.strToChar(input.split(""));
        numWind = instructions.length;
        newRock();
        while (true) {
            wind(instructions[(int)(cycle % instructions.length)]);
            //System.out.println(rockPosition);
            if (checkCollision()) {
                drawShape();
                shape = Shape.getFromCode((shape.getCode() + 1) % 5);
                rocksStopped++;
                newRock();
            } else {
                rockPosition.y -= 1;
            }
            cycle++;
            if (rocksStopped == (long)(1000000) * (long)(1000000)) {
                break;
            }
        }
        return (highestRock+1+cutRows) + "";
    }
    public boolean isFullRow(long y) {
        char[] row = board.get((int)y);
        if (row[0] == '#' && row[1] == '#' && row[2] == '#' && row[3] == '#' && row[4] == '#' && row[5] == '#' && row[6] == '#') {
            return true;
        }
        return false;
    }
    public boolean isEmptyRow(long y) {
        char[] row = board.get((int)y);
        if (row[0] != '#' && row[1] != '#' && row[2] != '#' && row[3] != '#' && row[4] != '#' && row[5] != '#' && row[6] != '#') {
            return true;
        }
        return false;
    }
    public void drawShape() {

        //System.out.println("Rock finalized");
        board.get((int)rockPosition.y)[(int)rockPosition.x] = '#';
        switch (shape) {
            case HORIZONTAL:
                board.get((int)rockPosition.y)[(int)rockPosition.x+1] = '#';
                board.get((int)rockPosition.y)[(int)rockPosition.x+2] = '#';
                board.get((int)rockPosition.y)[(int)rockPosition.x+3] = '#';
                break;
            case PLUS:
                board.get((int)rockPosition.y)[(int)rockPosition.x+1] = '#';
                board.get((int)rockPosition.y)[(int)rockPosition.x+2] = '#';
                board.get((int)rockPosition.y+1)[(int)rockPosition.x+1] = '#';
                board.get((int)rockPosition.y-1)[(int)rockPosition.x+1] = '#';
                break;
            case LSHAPE:
                board.get((int)rockPosition.y)[(int)rockPosition.x+1] = '#';
                board.get((int)rockPosition.y)[(int)rockPosition.x+2] = '#';
                board.get((int)rockPosition.y+1)[(int)rockPosition.x+2] = '#';
                board.get((int)rockPosition.y+2)[(int)rockPosition.x+2] = '#';
                break;
            case VERTICAL:
                board.get((int)rockPosition.y+1)[(int)rockPosition.x] = '#';
                board.get((int)rockPosition.y+2)[(int)rockPosition.x] = '#';
                board.get((int)rockPosition.y+3)[(int)rockPosition.x] = '#';
                break;
            case SQUARE:
                board.get((int)rockPosition.y)[(int)rockPosition.x+1] = '#';
                board.get((int)rockPosition.y+1)[(int)rockPosition.x] = '#';
                board.get((int)rockPosition.y+1)[(int)rockPosition.x+1] = '#';
                break;
        }
        for (long i=rockPosition.y-1; i<=rockPosition.y+4;i++) {
            if (i < 0) {
                continue;
            }
            if (isFullRow(i)) {
                if (isEmptyRow(i+1)) {
                    ArrayList<Long> toAddLeft = new ArrayList<>();
                    toAddLeft.add(cycle % numWind);
                    toAddLeft.add((long)(shape.getCode()+1)%5);
                    ArrayList<Long> toAddRight = new ArrayList<>();
                    toAddRight.add(rocksStopped);
                    toAddRight.add(highestRock);
                    if (seenBefore.get(toAddLeft) == null) {
                        seenBefore.put(toAddLeft, toAddRight);
                    } else {
                        long cycleLength = rocksStopped - seenBefore.get(toAddLeft).get(0);
                        long highestRockAdd = highestRock - seenBefore.get(toAddLeft).get(1);
                        System.out.println("Found loop with cycle length: " + cycleLength);
                        while (rocksStopped + (cycleLength*1000000) < (long)(1000000) * (long)(1000000)) {
                            rocksStopped += cycleLength*1000000;
                            cutRows += highestRockAdd * 1000000;
                        }
                        System.out.println("Finished loop step 1");
                        while (rocksStopped + (cycleLength*1000) < (long)(1000000) * (long)(1000000)) {
                            rocksStopped += cycleLength*1000;
                            cutRows += highestRockAdd * 1000;
                        }
                        System.out.println("Finished loop step 2");
                        while (rocksStopped + (cycleLength) < (long)(1000000) * (long)(1000000)) {
                            rocksStopped += cycleLength;
                            cutRows += highestRockAdd;
                        }
                        System.out.println("finished loop");
                    }

                }
                break;
            }
        }
        for (int i=board.size()-1;i>=0;i--) {
            boolean foundRock = false;
            for (char c: board.get(i)) {
                if (c=='#') {
                    foundRock = true;
                }
            }
            if (foundRock) {
                highestRock = i;
                break;
            }
        }
    }
    public void addRow() {
        board.add(new char[]{'.','.','.','.','.','.','.'});
    }
    public void wind(char instruction) {
        boolean canMove = true;
        if (instruction == '<') {
            if (rockPosition.x > 0) {
                switch (shape) {
                    case HORIZONTAL:
                        if (board.get((int)rockPosition.y)[(int)rockPosition.x-1] == '#') {
                            canMove = false;
                        }
                        break;
                    case PLUS:
                        if (board.get((int)rockPosition.y)[(int)rockPosition.x-1] == '#' || board.get((int)rockPosition.y-1)[(int)rockPosition.x] == '#' || board.get((int)rockPosition.y+1)[(int)rockPosition.x] == '#') {
                            canMove = false;
                        }
                        break;
                    case LSHAPE:
                        if (board.get((int)rockPosition.y)[(int)rockPosition.x-1] == '#' || board.get((int)rockPosition.y+1)[(int)rockPosition.x+1] == '#' || board.get((int)rockPosition.y+2)[(int)rockPosition.x+1] == '#') {
                            canMove = false;
                        }
                        break;
                    case VERTICAL:
                        if (board.get((int)rockPosition.y)[(int)rockPosition.x-1] == '#' || board.get((int)rockPosition.y+1)[(int)rockPosition.x-1] == '#' || board.get((int)rockPosition.y+2)[(int)rockPosition.x-1] == '#' || board.get((int)rockPosition.y+3)[(int)rockPosition.x-1] == '#') {
                            canMove = false;
                        }
                        break;
                    case SQUARE:
                        if (board.get((int)rockPosition.y)[(int)rockPosition.x-1] == '#' || board.get((int)rockPosition.y+1)[(int)rockPosition.x-1] == '#') {
                            canMove = false;
                        }
                        break;
                }
                if (canMove) {
                    rockPosition.x -= 1;
                }
            }
        }
        else {
            if (rockPosition.x + shape.getWidth() - 1 < 6) {
                switch (shape) {
                    case HORIZONTAL:
                        if (board.get((int)rockPosition.y)[(int)rockPosition.x+4] == '#') {
                            canMove = false;
                        }
                        break;
                    case PLUS:
                        if (board.get((int)rockPosition.y)[(int)rockPosition.x+3] == '#' || board.get((int)rockPosition.y-1)[(int)rockPosition.x+2] == '#' || board.get((int)rockPosition.y+1)[(int)rockPosition.x+2] == '#') {
                            canMove = false;
                        }
                        break;
                    case LSHAPE:
                        if (board.get((int)rockPosition.y)[(int)rockPosition.x+3] == '#' || board.get((int)rockPosition.y+1)[(int)rockPosition.x+3] == '#' || board.get((int)rockPosition.y+2)[(int)rockPosition.x+3] == '#') {
                            canMove = false;
                        }
                        break;
                    case VERTICAL:
                        if (board.get((int)rockPosition.y)[(int)rockPosition.x+1] == '#' || board.get((int)rockPosition.y+1)[(int)rockPosition.x+1] == '#' || board.get((int)rockPosition.y+2)[(int)rockPosition.x+1] == '#' || board.get((int)rockPosition.y+3)[(int)rockPosition.x+1] == '#') {
                            canMove = false;
                        }
                        break;
                    case SQUARE:
                        if (board.get((int)rockPosition.y)[(int)rockPosition.x+2] == '#' || board.get((int)rockPosition.y+1)[(int)rockPosition.x+2] == '#') {
                            canMove = false;
                        }
                }
                if (canMove) {
                    rockPosition.x++;
                }
            }
        }
    }
    public boolean checkCollision() {
        if (shape == Shape.HORIZONTAL) {
            return !(canFall(rockPosition.x, rockPosition.y) && canFall(rockPosition.x+1, rockPosition.y) && canFall(rockPosition.x+2, rockPosition.y) && canFall(rockPosition.x+3, rockPosition.y));
        } else if (shape == Shape.PLUS) {
            return !(canFall(rockPosition.x, rockPosition.y) && canFall(rockPosition.x+1, rockPosition.y-1) && canFall(rockPosition.x+2, rockPosition.y));
        } else if (shape == Shape.LSHAPE) {
            return !(canFall(rockPosition.x, rockPosition.y) && canFall(rockPosition.x+1, rockPosition.y) && canFall(rockPosition.x+2, rockPosition.y));
        } else if (shape == Shape.VERTICAL) {
            return !(canFall(rockPosition.x, rockPosition.y));
        } else {
            return !(canFall(rockPosition.x, rockPosition.y) && canFall(rockPosition.x+1, rockPosition.y));
        }
    }

    public void newRock() {
        int drawY;
        switch (shape) {
            case HORIZONTAL:
            case VERTICAL:
            case SQUARE:
            case LSHAPE:
                drawY = (int)(highestRock + 4);
                break;
            default:
                drawY = (int)(highestRock + 5);
                break;
        }
        while (board.size() <= drawY + 4) {
            addRow();
        }
        rockPosition = new Position(2, drawY);
    }
    public boolean canFall(long x, long y) {
        int newX = (int)x;
        int newY = (int)y;
        if (newY==0) {
            return false;
        }
        if (board.get(newY-1)[newX] == '.') {
            return true;
        }
        return false;
    }
    public enum Shape {
        HORIZONTAL {
            @Override
            public int getCode() {
                return 0;
            }
            @Override
            public int getWidth() {
                return 4;
            }
        },
        PLUS {
            @Override
            public int getCode() {
                return 1;
            }
            @Override
            public int getWidth() {
                return 3;
            }
        },
        LSHAPE {
            @Override
            public int getCode() {
                return 2;
            }
            @Override
            public int getWidth() {
                return 3;
            }
        },
        VERTICAL {
            @Override
            public int getCode() {
                return 3;
            }
            @Override
            public int getWidth() {
                return 1;
            }
        },
        SQUARE{
            @Override
            public int getCode() {
                return 4;
            }
            @Override
            public int getWidth() {
                return 2;
            }
        };


        public static Shape getFromCode(int code) {
            switch (code) {
                case 0:
                    return HORIZONTAL;
                case 1:
                    return PLUS;
                case 2:
                    return LSHAPE;
                case 3:
                    return VERTICAL;
                case 4:
                    return SQUARE;
            }
            throw new IllegalArgumentException("Must be number between 0 and 4");
        }
        public abstract int getCode();
        public abstract int getWidth();
    }
    public void printBoard() {
        for (int i=board.size()-1;i>=0;i--) {
            System.out.println(Arrays.toString(board.get(i)));
        }
    }
}
