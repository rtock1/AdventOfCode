package Year_2019.Day1;

public class Part1 {
    public String start(String input) {
        String[] data = input.split("\n");
        int total = 0;
        for (String a:data){
            total += Integer.parseInt(a)/3 - 2;
        }
        return total + "";
    }
}
