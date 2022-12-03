package Year_2015.Day3;

import java.util.ArrayList;

public class Part1 {
    public String start(String input) {
        int x = 0;
        int y = 0;
        ArrayList<ArrayList<Integer>> visited = new ArrayList<>();
        for (String a: input.split("")) {
            ArrayList<Integer> curr = new ArrayList<>();
            curr.add(x);
            curr.add(y);
            if (!visited.contains(curr)) {
                visited.add(curr);
            }
            switch (a.charAt(0)) {
                case '^':
                    y += 1;
                    break;
                case 'v':
                    y -= 1;
                    break;
                case '>':
                    x += 1;
                    break;
                default:
                    x -= 1;
            }
        }
        return visited.size() + "";
    }
}
