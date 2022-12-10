package Year_2022.Day10;

public class Part2 {
    public String start(String input) {
        String[] lines = input.split("\\n");
        int cycle = 0;
        int reg = 1;
        String display = "\n";
        for (String curr:lines) {
            //System.out.println(cycle + ": "+reg);
            String op = curr.split(" ")[0];
            int run = 1;
            int num = 0;
            if (op.equals("addx")) {
                num = Integer.parseInt(curr.split(" ")[1]);
                run = 2;
            }
            for (int i=0;i<run;i++) {
                if (Math.abs((cycle%40) - reg) <= 1) {
                    display += "#";
                } else {
                    display += ".";
                }
                cycle++;
                if (cycle % 40 == 0) {
                    display += "\n";
                }
            }
            reg += num;


        }
        return display;
    }
}
