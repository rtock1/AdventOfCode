package Year_2022.Day1;

public class Part1 {
    String[] elves;
    public String start(String input) {
        elves = input.split("\\n\\n");
        int max = 0;
        for (String a: elves) {
            int total = 0;
            String[] tempLines = a.split("\\n");
            for (String b: tempLines) {
                total += Integer.parseInt(b);
            }
            if (total > max) {
                max = total;
            }
        }
        return max + "";
    }
}
