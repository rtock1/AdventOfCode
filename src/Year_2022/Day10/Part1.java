package Year_2022.Day10;

public class Part1 {
    public String start(String input) {
        String[] lines = input.split("\\n");
        int cycle = 0;
        int reg = 1;
        int signalStrength = 0;
        for (String curr:lines) {
            //System.out.println(cycle + ": "+reg);
            String op = curr.split(" ")[0];
            if (op.equals("addx")) {
                int num = Integer.parseInt(curr.split(" ")[1]);
                if ((cycle+20)%40 >= 38 && (cycle+22)%40 <= 2) {
                    signalStrength += ((cycle+2) / 10 * 10) * reg;
                    //System.out.println(((cycle+2) / 10 * 10) * reg);
                }
                cycle += 2;
                reg += num;
            } else {
                if ((cycle-20)%40 == 39) {
                    signalStrength += (cycle+1) * reg;
                    //System.out.println((cycle+1) * reg);
                }
                cycle ++;
            }

        }
        return signalStrength + "";
    }
}
