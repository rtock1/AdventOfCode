package Year_2021.Day1;

public class Part1 {
    public String start(String input) {
        String[] d = input.split("\\n");

        int prev = Integer.parseInt(d[0]);
        int total = 0;
        for (int i=1;i<d.length;i++){
            if (Integer.parseInt(d[i])>prev){
                total++;
            }
            prev = Integer.parseInt(d[i]);
        }
        return total + "";
    }
}
