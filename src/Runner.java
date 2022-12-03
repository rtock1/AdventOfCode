import Helper_Classes.ReadInput;
import Year_2022.Day1.*; //Change this to change which file is run

import java.io.IOException;

public class Runner {
    public static final inputFile whichRun = inputFile.PUZZLE; //Chooses which text file to take as input
    public static void main(String[] args) {
        String classPathname = Part1.class.getPackage().toString();
        int year = Integer.parseInt(classPathname.split("Year_")[1].split("\\.")[0]);
        int day = Integer.parseInt(classPathname.split("Day")[1]);
        String input;
        String output = "No Data";
        try {
            input = ReadInput.getString(year, day, whichRun.getFilename());
        } catch (IOException e) {
            throw new RuntimeException("IO exception while reading file");
        }
        Part1 part1Runner = new Part1();
        System.out.println("Part1:");
        if (input.length() > 0) output = part1Runner.start(input);
        System.out.println("Final answer: "+output);
        Part2 part2Runner = new Part2();
        System.out.println("\nPart2:");
        if (input.length() > 0) output = part2Runner.start(input);
        System.out.println("Final answer: "+output);
    }

    public enum inputFile {
        PUZZLE {@Override public String getFilename() {return "puzzleInput";}},
        EXAMPLE {@Override public String getFilename() {return "exampleInput";}},
        DEBUG{@Override public String getFilename() {return "debugInput";}};
        public abstract String getFilename();
    }
}
