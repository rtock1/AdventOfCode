package Year_2022.Day9;
import Helper_Classes.*;
import java.util.*;

public class Part2 {
    ArrayList<ArrayList<Integer>> tailPositions;
    ArrayList<Integer>[] walkers;
    public String start(String input) {
        tailPositions = new ArrayList<>();
        String[] lines = input.split("\\n");
        walkers = new ArrayList[10];
        for (int i=0;i<10;i++){
            walkers[i] = new ArrayList<>();
            walkers[i].add(0);
            walkers[i].add(0);
        }
        tailPositions.add((ArrayList<Integer>) walkers[9].clone());
        for (String a:lines) {
            int num = Integer.parseInt(a.split(" ")[1]);
            String dir = a.split(" ")[0];
            for (int i=0;i<num;i++) {
                //System.out.println(Arrays.toString(walkers));
                if (moveHead(dir)) {
                    boolean tailMove = true;
                    int index = 1;
                    while (tailMove) {
                        tailMove = moveTail(index);
                        index++;
                    }
                }
                if (!tailPositions.contains(walkers[9])) {
                    tailPositions.add((ArrayList<Integer>) walkers[9].clone());
                }
            }
        }
        return tailPositions.size() + "";
    }
    public boolean moveHead(String dir) {
        switch (dir.charAt(0)) {
            case 'R':
                walkers[0].set(0, walkers[0].get(0) + 1);
                break;
            case 'L':
                walkers[0].set(0, walkers[0].get(0) - 1);
                break;
            case 'U':
                walkers[0].set(1, walkers[0].get(1) + 1);
                break;
            case 'D':
                walkers[0].set(1, walkers[0].get(1) - 1);
                break;
        }
        if (Math.abs(walkers[0].get(0)-walkers[1].get(0)) > 1 || Math.abs(walkers[0].get(1)-walkers[1].get(1)) > 1) {
            return true;
        }
        return false;
    }

    public boolean moveTail(int index) {
        if (walkers[index-1].get(0) > walkers[index].get(0)) {
            walkers[index].set(0, walkers[index].get(0) + 1);
        } else if (walkers[index-1].get(0) < walkers[index].get(0)){
            walkers[index].set(0, walkers[index].get(0) - 1);
        }
        if (walkers[index-1].get(1) > walkers[index].get(1)) {
            walkers[index].set(1, walkers[index].get(1) + 1);
        } else if (walkers[index-1].get(1) < walkers[index].get(1)) {
            walkers[index].set(1, walkers[index].get(1) - 1);
        }
        if (index != 9) {
            if (Math.abs(walkers[index].get(0) - walkers[index+1].get(0)) > 1 || Math.abs(walkers[index].get(1) - walkers[index+1].get(1)) > 1) {
                return true;
            }
        }
        return false;
    }
}
