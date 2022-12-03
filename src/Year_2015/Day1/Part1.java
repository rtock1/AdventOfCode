package Year_2015.Day1;

public class Part1 {
    public String start(String input) {
        int floor = 0;
        for (String a:input.split("")) {
            switch (a.charAt(0)) {
                case '(':
                    floor++;
                    break;
                default:
                    floor--;
            }
        }
        return floor + "";
    }
}
