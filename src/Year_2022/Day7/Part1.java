package Year_2022.Day7;

import Helper_Classes.Array;

import java.util.ArrayList;
import java.util.HashMap;

public class Part1 {
    HashMap<String, Long> sizes = new HashMap<>();
    HashMap<String, Integer> cdLine = new HashMap<>();
    String[] lines;
    public String start(String input) {
        String currPath = "/";
        lines = input.split("\\n");
        for (int i=1;i<lines.length;i++) {
                if (lines[i].startsWith("$ cd") && !lines[i].equals("$ cd ..")) {
                    currPath += lines[i].split("\\$ cd ")[1];
                    lines[i] = lines[i].substring(0, 5) + currPath;
                    currPath += "/";
                }
                if (lines[i].startsWith("dir ")) {
                    lines[i] = lines[i].substring(0,4) + currPath + lines[i].substring(4);
                }
                if (lines[i].startsWith("$ cd ..")) {
                    String[] folders = currPath.split("/");
                    currPath = Array.join(Array.slice(folders, 0, folders.length-1), "/") + "/";
                    //System.out.println(currPath);
                }
        }
        //System.out.println(Arrays.toString(lines));
        for (int i=0;i<lines.length;i++) {
            if (lines[i].startsWith("$ cd ") && !lines[i].equals("$ cd ..")) {
                cdLine.put(lines[i].split("\\$ cd ")[1], i);
            }
        }

        getSize("/");
        //System.out.println(sizes);
        long total = 0;
        for (String a:sizes.keySet()) {
            if (sizes.get(a) <= 100000 && sizes.get(a) > 0) {
                total += sizes.get(a);
            }
        }
        return total + "";
    }

    public long getSize(String name) {
        ArrayList<String> toBeExecuted = new ArrayList<>();
        long thisSize = 0;
        try {
            long size = Integer.parseInt(name.split(" ")[0]);
            return size;
        } catch (NumberFormatException e) {}
        int line = cdLine.get(name);
        if (lines[line+1].equals("$ ls")) {
            String currLine = lines[line+2];
            int i=2;
            while (!currLine.startsWith("$")) {
                //System.out.println(currLine);
                if (currLine.startsWith("dir ")) {
                    toBeExecuted.add(currLine.split("dir ")[1]);
                } else {
                    thisSize += Integer.parseInt(currLine.split(" ")[0]);
                }
                i++;
                try {
                    currLine = lines[line + i];
                } catch (Exception e) {
                    //System.out.println(currLine);
                    break;
                }
            }
        } else {
            System.out.println(name);
            throw new IllegalStateException("No ls");
        }
        for (String curr: toBeExecuted) {
            thisSize += getSize(curr);
        }
        sizes.put(name, thisSize);
        return thisSize;
    }
}
