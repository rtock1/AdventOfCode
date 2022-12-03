package Year_2022.Day2;

public class Part1 {
    String[] parsedInput;
    public String start(String input) {
        parsedInput = input.split("\\n");
        //System.out.println('X' - 'A');
        int points = 0;
        for (String a:parsedInput) {
            String[] plays = a.split(" ");
            if (plays[1].charAt(0) - plays[0].charAt(0) == 24 || plays[1].charAt(0) - plays[0].charAt(0) == 21) {
                points += 6;
            } else if (plays[1].charAt(0) - plays[0].charAt(0) == 23) {
                points += 3;
            }
            points += plays[1].charAt(0) - 'W';
        }
        return points + "";
    }
}
