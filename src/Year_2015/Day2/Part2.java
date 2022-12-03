package Year_2015.Day2;

import Helper_Classes.ArrayConvert;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Part2 {
    public String start(String input) {
        int total = 0;
        String[] parsed = input.split("\\n");
        for (String a: parsed) {
            int[] edge = ArrayConvert.strToInt(a.split("x"));
            Arrays.sort(edge);
            total += (edge[0] + edge[1])*2;
            total += edge[0] * edge[1] * edge[2];
        }
        return total + "";
    }
}
