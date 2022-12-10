package Year_2022.Day6;

public class Part1 {
    public String start(String input) {
        String[] chars = input.split("");
        for (int i=3;i<chars.length;i++) {
            if (!chars[i].equals(chars[i-1]) && !chars[i].equals(chars[i-2]) && !chars[i].equals(chars[i-3])) {
                if (!chars[i-1].equals(chars[i-2]) && !chars[i-1].equals(chars[i-3])) {
                    if (!chars[i-2].equals(chars[i-3])) {
                        return (i+1)+"";
                    }
                }
            }
        }
        return "";
    }
}
