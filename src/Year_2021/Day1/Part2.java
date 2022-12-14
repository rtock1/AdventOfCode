package Year_2021.Day1;


public class Part2 {
    public String start(String input) {
        String[] d = input.split("\\n");

        int prev = Integer.parseInt(d[0]) + Integer.parseInt(d[1]) + Integer.parseInt(d[2]);
        int total = 0;
        for (int i=3;i<d.length;i++){
            if (Integer.parseInt(d[i])+prev - Integer.parseInt(d[i-3])>prev){
                total++;
            }
            prev += Integer.parseInt(d[i]);
            prev -= Integer.parseInt(d[i-3]);
        }
        return total + "";
    }
}
