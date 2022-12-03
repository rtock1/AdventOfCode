package Year_2015.Day1;

public class Part2 {
    public String start(String input) {
        int floor = 0;
        for (int i=0;i<input.length();i++) {
            switch (input.charAt(i)) {
                case '(':
                    floor++;
                    break;
                default:
                    floor--;
            }
            if (floor < 0) {
                return (i+1) + "";
            }
        }
        return "No answer found";
    }
}
