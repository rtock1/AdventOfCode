package Year_2022.Day5;
import java.util.*;
import Helper_Classes.*;

public class Part2 {
    public String start(String input) {
        String[] sections = input.split("\\n\\n");
        String[] stackRows = sections[0].split("\\n");
        String[] tempInstructions = sections[1].split("\\n");
        int[][] instructions = new int[tempInstructions.length][3];
        for (int i=0;i<instructions.length;i++) {
            instructions[i] = ArrayConvert.strToInt(tempInstructions[i].replaceAll("move (\\d+) from (\\d) to (\\d)", "$1|$2|$3").split("\\|"));
            //System.out.println(Arrays.toString(instructions[i]));
        }
        ArrayList<String>[] stacks = new ArrayList[stackRows[stackRows.length-2].split("\\s").length];
        for (int i=0;i<stacks.length;i++) {
            stacks[i] = new ArrayList<>();
        }
        for (int i=0;i<stackRows.length-1;i++) {
            //System.out.println(stackRows[i]);
            for (int a=1;a<stackRows[i].length();a+=4) {
                if (!(stackRows[i].charAt(a) == ' ')) {
                    stacks[(a - 1) / 4].add(0, (stackRows[i].charAt(a) + ""));
                }
            }
        }
        for (int[] curr:instructions) {
            int initialSize = stacks[curr[2]-1].size();
            for (int move=0;move<curr[0];move++) {
                stacks[curr[2]-1].add(initialSize, stacks[curr[1]-1].get(stacks[curr[1]-1].size()-1));
                stacks[curr[1]-1].remove(stacks[curr[1]-1].size()-1);
            }
        }
        String output = "";
        for (ArrayList<String> a: stacks) {
            output += a.get(a.size()-1);
        }

        return output;
    }
}
