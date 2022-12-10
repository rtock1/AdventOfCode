package Year_2015.Day5;

public class Part2 {
    public String start(String input) {
        int totalNice = 0;
        for (String curr:input.split("\\n")) {
            boolean hasSandwich = false;
            boolean hasRepeatingDouble = false;
            for (int i=0; i<curr.length()-1; i++) {
                if (i != curr.length()) {
                    String currDouble = curr.substring(i, i + 2);
                    for (int a = 0; a < curr.length() - 1 && a != i && a != i + 1 && a != i - 1; a++) {
                        if (currDouble.equals(curr.substring(a, a + 2))) {
                            hasRepeatingDouble = true;
                        }
                    }
                }
                if (i != curr.length() - 2 && curr.charAt(i) == curr.charAt(i + 2)) {
                    hasSandwich = true;
                }
            }
            if (hasSandwich && hasRepeatingDouble) {
                totalNice++;
            }
        }
        return totalNice + "";
    }
}
