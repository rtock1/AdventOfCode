package Year_2022.Day4;

import Helper_Classes.Array;

public class Part1 {
    public String start(String input) {
        String[] parsed = input.split("\\n");
        int total = 0;
        for (String a:parsed) {
            int[] elf1 = Array.strToInt(a.split(",")[0].split("-"));
            int[] elf2 = Array.strToInt(a.split(",")[1].split("-"));
            if (elf1[0] >= elf2[0] && elf1[1] <= elf2[1]) {
                total++;
            } else if (elf2[0] >= elf1[0] && elf2[1] <= elf1[1]) {
                total++;
            }
        }
        return total + "";
    }
}
