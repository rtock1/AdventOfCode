package Year_2022.Day1;

import java.util.Arrays;

public class Part2 {
    String input;
    String[] elves;
    public String start(String input) {
        elves = input.split("\\n\\n");
        int[] numbers = new int[elves.length];
        int count = 0;
        for (String a: elves) {
            int total = 0;
            String[] tempLines = a.split("\\n");
            for (String b: tempLines) {
                total += Integer.parseInt(b);
            }
            numbers[count] = total;
            count++;
        }
        Arrays.sort(numbers);
        return (numbers[numbers.length-1] + numbers[numbers.length-2] + numbers[numbers.length-3]) + "";
    }
}
