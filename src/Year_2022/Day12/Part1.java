package Year_2022.Day12;

import Helper_Classes.Array;

public class Part1 {
    int[][] steps;
    int[][] heights;
    int[] start;
    int[] end;
    public String start(String input) {
        start = new int[2];
        end = new int[2];
        String[] lines = input.split("\\n");
        heights = new int[lines.length][lines[0].length()];
        for (int i=0;i<lines.length;i++) {
            for (int a=0;a<lines[i].length();a++) {
                if (lines[i].charAt(a) == 'S') {
                    start[0] = i;
                    start[1] = a;
                }
                if (lines[i].charAt(a) == 'E') {
                    end[0] = i;
                    end[1] = a;
                }
                heights[i][a] = lines[i].replaceAll("S","a").replaceAll("E","z").charAt(a) - 'a';
            }
        }
        steps = new int[heights.length][heights[0].length];
        for (int i=0;i<steps.length;i++) {
            for (int a=0;a<steps[i].length;a++ ) {
                if (i != start[0] || a!= start[1]) {
                    steps[i][a] = Integer.MAX_VALUE / 2;
                }
            }
        }
        //System.out.println(Array.toString(steps));
        for (int i=0;i<333;i++) {
            iter();
        }
        //System.out.println(Array.toString(steps));
        return steps[end[0]][end[1]] + "";
    }
    public void iter() {
        int[][] newSteps = new int[steps.length][steps[0].length];
        for (int i=0;i<newSteps.length;i++) {
            newSteps[i] = steps[i].clone();
        }
        for (int a=0;a<heights.length;a++) {
            for (int b=0;b<heights[a].length;b++) {
                if (steps[a][b] != Integer.MAX_VALUE) {
                    if (a!=0) {
                        if (heights[a][b] + 1 >= heights[a-1][b]) {
                            if (steps[a][b] + 1 < steps[a-1][b]) {
                                newSteps[a-1][b] = steps[a][b] + 1;
                            }
                        }
                    }
                    if (a!=heights.length-1) {
                        if (heights[a][b] + 1 >= heights[a+1][b]) {
                            if (steps[a][b] + 1 < steps[a+1][b]) {
                                newSteps[a+1][b] = steps[a][b] + 1;
                            }
                        }
                    }
                    if (b!=0) {
                        if (heights[a][b] + 1 >= heights[a][b-1]) {
                            if (steps[a][b] + 1 < steps[a][b-1]) {
                                newSteps[a][b-1] = steps[a][b] + 1;
                            }
                        }
                    }
                    if (b!=heights[a].length-1) {
                        if (heights[a][b] + 1 >= heights[a][b+1]) {
                            if (steps[a][b] + 1 < steps[a][b+1]) {
                                newSteps[a][b+1] = steps[a][b] + 1;
                            }
                        }
                    }
                }
            }
        }
        steps = newSteps;
    }
}
