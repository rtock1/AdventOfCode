package Year_2022.Day9;
import java.util.ArrayList;

public class Part1 {
    ArrayList<ArrayList<Integer>> tailPositions;
    ArrayList<Integer> currHead;
    ArrayList<Integer> currTail;
    public String start(String input) {
        tailPositions = new ArrayList<>();
        String[] lines = input.split("\\n");
        currHead = new ArrayList<>();
        currTail = new ArrayList<>();
        currHead.add(0);
        currHead.add(0);
        currTail.add(0);
        currTail.add(0);
        tailPositions.add((ArrayList<Integer>) currTail.clone());
        for (String a:lines) {
            int num = Integer.parseInt(a.split(" ")[1]);
            String dir = a.split(" ")[0];
            for (int i=0;i<num;i++) {
                //System.out.println(currHead + ", " + currTail);
                if (moveHead(dir)) {
                    moveTail(dir);
                }
                if (!tailPositions.contains(currTail)) {
                    tailPositions.add((ArrayList<Integer>) currTail.clone());
                }
            }
        }
        return tailPositions.size() + "";
    }
    public boolean moveHead(String dir) {
        switch (dir.charAt(0)) {
            case 'R':
                currHead.set(0, currHead.get(0) + 1);
                break;
            case 'L':
                currHead.set(0, currHead.get(0) - 1);
                break;
            case 'U':
                currHead.set(1, currHead.get(1) + 1);
                break;
            case 'D':
                currHead.set(1, currHead.get(1) - 1);
                break;
        }
        if (Math.abs(currHead.get(0)-currTail.get(0)) > 1 || Math.abs(currHead.get(1)-currTail.get(1)) > 1) {
            return true;
        }
        return false;
    }

    public void moveTail(String dir) {
        switch (dir.charAt(0)) {
            case 'R':
                currTail.set(0, currHead.get(0)-1);
                currTail.set(1, currHead.get(1));
                break;
            case 'L':
                currTail.set(0, currHead.get(0)+1);
                currTail.set(1, currHead.get(1));
                break;
            case 'U':
                currTail.set(0, currHead.get(0));
                currTail.set(1, currHead.get(1)-1);
                break;
            case 'D':
                currTail.set(0, currHead.get(0));
                currTail.set(1, currHead.get(1)+1);
                break;
        }
    }
}
