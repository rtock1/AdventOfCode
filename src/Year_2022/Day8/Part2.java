package Year_2022.Day8;
import Helper_Classes.Array;
import java.util.Arrays;
public class Part2 {
    int[][] trees;
    public String start(String input) {
        String[] lines = input.split("\\n");
        trees = new int[lines.length][lines[0].length()];
        for (int i=0;i<lines.length;i++){
            trees[i] = Array.strToInt(lines[i].split(""));
            System.out.println(Arrays.toString(trees[i]));
        }
        int max = 0;
        for (int y=0;y<trees.length;y++) {
            for (int x=0;x<trees[y].length;x++) {
                if (checkVisible(x,y) > max) {
                    max = checkVisible(x,y);
                }
            }
        }
        return max + "";
    }

    public int checkVisible(int x, int y) {
        int height = trees[y][x];
        int visxp = 0;
        int visxm = 0;
        int visyp = 0;
        int visym = 0;
        for (int i=x-1;i>=0;i--) {
            visxm += 1;
            if (trees[y][i] >= height) {
                break;
            }
        }
        for (int i=x+1;i<trees[y].length;i++) {
            visxp += 1;
            if (trees[y][i] >= height) {
                break;
            }
        }
        for (int i=y-1;i>=0;i--) {
            visym += 1;
            if (trees[i][x] >= height) {
                break;
            }
        }
        for (int i=y+1;i<trees.length;i++) {
            visyp += 1;
            if (trees[i][x] >= height) {
                break;
            }
        }
        return visxm * visxp * visym * visyp;
    }

}
