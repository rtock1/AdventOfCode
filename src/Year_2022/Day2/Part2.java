package Year_2022.Day2;

public class Part2 {
    String[] parsedInput;
    public String start(String input) {
        parsedInput = input.split("\\n");
        //System.out.println('X' - 'A');
        int points = 0;
        for (String a:parsedInput) {
            String[] plays = a.split(" ");
            if (plays[1].charAt(0) == 'X') {
                points += (plays[0].charAt(0) + 2 - 'A') % 3 + 1;
            } else if (plays[1].charAt(0) == 'Y') {
                points += plays[0].charAt(0) + 1 - 'A';
            } else {
                points += (plays[0].charAt(0) + 1 - 'A') % 3 + 1;
            }
            //System.out.println(points);
            points += (plays[1].charAt(0) - 'X') * 3;
            //System.out.println(points + "\n");
        }

        return points + "";
    }
}
