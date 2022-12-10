package Year_2019.Day1;

public class Part2 {
    public String start(String input) {
        String[] data = input.split("\n");
        int total = 0;
        for (String a:data){
            total += Integer.parseInt(a)/3 - 2;
            int current  = Integer.parseInt(a)/3-2;
            while (current/3-2>0){
                total += current/3 - 2;
                current = current/3 - 2;
            }
        }
        return total + "";
    }
}
