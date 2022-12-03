package Year_2022.Day3;
import Helper_Classes.*;

import java.util.Arrays;
import java.util.Locale;

public class Part2 {
    String[] parsedInput;
    public String start(String input) {
        parsedInput = input.split("\\n");
        int points = 0;
        int total = 0;
        for (int i=0;i<parsedInput.length; i+= 3) {
            for (String b: parsedInput[i].split("")) {
                //System.out.println(b);
                if (parsedInput[i+1].contains(b) && parsedInput[i+2].contains(b)) {
                    if (b.toLowerCase().equals(b)) {
                        points += b.charAt(0) - 'a' + 1;
                        total++;
                    } else {
                        points += b.charAt(0) - 'A' + 27;
                        total++;
                    }
                    break;
                }
            }
        }
        System.out.println("total matches found: "+total);
        return points + "";
    }
}
