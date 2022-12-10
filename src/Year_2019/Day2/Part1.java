package Year_2019.Day2;

import Helper_Classes.Array;

public class Part1 {
    public String start(String input) {
        int[] data = Array.strToInt(input.split(","));
        boolean end = false;
        data[1] = 12;
        data[2] = 2;
        for (int i=0;i<data.length;i+=4) {
            switch (data[i]) {
                case 1:
                    data[data[i+3]] = data[data[i+1]] + data[data[i+2]];
                    break;
                case 2:
                    data[data[i+3]] = data[data[i+1]] * data[data[i+2]];
                    break;
                case 99:
                    end = true;
                    break;
            }
            if (end) {
                break;
            }
        }
        return data[0] + "";
    }
}