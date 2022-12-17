package Year_2022.Day17;

import Helper_Classes.Array;
import Helper_Classes.Position;

import java.util.ArrayList;
import java.util.Arrays;

public class Part1 {
    int highestRock = -1;
    int cycle = 0;
    Shape shape = Shape.HORIZONTAL;
    Position rockPosition;
    ArrayList<char[]> board = new ArrayList<>();
    public String start(String input) {
        char[] instructions = Array.strToChar(input.split(""));
        newRock();
        int rocksStopped = 0;
        while (true) {
            wind(instructions[cycle % instructions.length]);
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
            if (rocksStopped == 2022) {
                break;
            }
        }
        //printBoard();
        return (highestRock+1) + "";
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
                drawY = highestRock + 4;
                break;
            default:
                drawY = highestRock + 5;
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
