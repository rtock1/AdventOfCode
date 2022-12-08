package Year_2022.Day8;
import java.util.*;
import Helper_Classes.*;

public class Part1 {
    int[][] trees;
    public String start(String input) {
        String[] lines = input.split("\\n");
        trees = new int[lines.length][lines[0].length()];
        for (int i=0;i<lines.length;i++){
            trees[i] = Array.strToInt(lines[i].split(""));
            System.out.println(Arrays.toString(trees[i]));
        }
        int total = 0;
        for (int y=0;y<trees.length;y++) {
            for (int x=0;x<trees[y].length;x++) {
                if (checkVisible(x, y)) {
                    //System.out.println(x + ", " + y);
                    total++;
                }
            }
        }
        return total + "";
    }

    public boolean checkVisible(int x, int y) {
        int height = trees[y][x];
        boolean visiblexm = true;
        boolean visiblexp = true;
        boolean visibleym = true;
        boolean visibleyp = true;
        for (int i=x-1;i>=0;i--) {
            if (trees[y][i] >= height) {
                visiblexm = false;
                break;
            }
        }
        for (int i=x+1;i<trees[y].length;i++) {
            if (trees[y][i] >= height) {
                visiblexp = false;
                break;
            }
        }
        for (int i=y-1;i>=0;i--) {
            if (trees[i][x] >= height) {
                visibleym = false;
                break;
            }
        }
        for (int i=y+1;i<trees.length;i++) {
            if (trees[i][x] >= height) {
                visibleyp = false;
                break;
            }
        }
        return visiblexm || visibleym || visiblexp || visibleyp;
    }

}
