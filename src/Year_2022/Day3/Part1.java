package Year_2022.Day3;
import Helper_Classes.*;

import java.util.Locale;

public class Part1 {
    String[] parsedInput;
    public String start(String input) {
        parsedInput = input.split("\\n");
        int points = 0;
        for (String a:parsedInput) {
            String compart2 = a.substring(a.length()/2, a.length());
            String compart1 = a.substring(0, a.length() / 2);
            for (String b: compart1.split("")) {
                if (compart2.contains(b)) {
                    //System.out.println(b);
                    if (b.toLowerCase().equals(b)) {
                        points += b.charAt(0) - 'a' + 1;
                    } else {
                        points += b.charAt(0) - 'A' + 27;
                    }
                    break;
                }
            }
        }
        return points + "";
    }
}
