package Year_2022.Day6;

public class Part2 {
    public String start(String input) {
        String[] chars = input.split("");
        for (int i=0;i<chars.length-14;i++) {
            boolean skip = false;
            for (int a=0;a<14;a++) {
                for (int b=a+1;b<14;b++) {
                    if (chars[i+a].equals(chars[i+b])) {
                        skip = true;
                    }
                }
            }
            if (!skip) {
                return (i+14)+"";
            }
        }
        return "";
    }
}
