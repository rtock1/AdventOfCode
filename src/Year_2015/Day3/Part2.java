package Year_2015.Day3;

import java.util.ArrayList;

public class Part2 {
    public String start(String input) {
        int[] x = new int[]{0,0};
        int[] y = new int[]{0,0};
        int person = 0;
        ArrayList<ArrayList<Integer>> visited = new ArrayList<>();
        ArrayList<Integer> startingHouse = new ArrayList<>();
        startingHouse.add(0);
        startingHouse.add(0);
        visited.add(startingHouse);
        for (String a: input.split("")) {
            switch (a.charAt(0)) {
                case '^':
                    y[person] += 1;
                    break;
                case 'v':
                    y[person] -= 1;
                    break;
                case '>':
                    x[person] += 1;
                    break;
                default:
                    x[person] -= 1;
            }
            person = (person + 1)%2;
            ArrayList<Integer> curr = new ArrayList<>();
            curr.add(x[person]);
            curr.add(y[person]);
            if (!visited.contains(curr)) {
                visited.add(curr);
            }
        }
        return visited.size() + "";
    }
}
