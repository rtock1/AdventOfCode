package Year_2015.Day2;

import Helper_Classes.ArrayConvert;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Part1 {
    public String start(String input) {
        int total = 0;
        String[] parsed = input.split("\\n");
        for (String a: parsed) {
            int[] edge = ArrayConvert.strToInt(a.split("x"));
            int[] sides = new int[]{edge[0]*edge[1], edge[1]*edge[2], edge[0]*edge[2]};
            Arrays.sort(sides);
            total += 2 * IntStream.of(sides).sum() + sides[0];
        }
        return total + "";
    }
}
