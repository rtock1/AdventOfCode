package Year_2015.Day5;

public class Part1 {
    public static final String[] notAllowed = new String[]{"ab","cd","pq","xy"};
    public String start(String input) {
        int vowels = 0;
        boolean nice = true;
        for (String curr:input.split("\\n")) {
            for (int i=0; i<curr.length(); i++) {
                if (i != curr.length()-1) {
                    for (String a:notAllowed) {
                        if (curr.substring(i, i+2).equals(a)) {
                            nice = false;
                        }
                    }
                }
            }
        }
        return "";
    }
    public boolean isVowel(char input) {
        switch (input) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }
}
