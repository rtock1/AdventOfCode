import Helper_Classes.ReadInput;
import Year_2022.Day3.*;

public class Runner {
    private static int year;
    private static int day;
    public static void main(String[] args) {
        String classPathname = Part1.class.getPackage().toString();
        year = Integer.parseInt(classPathname.split("Year_")[1].split("\\.")[0]);
        day = Integer.parseInt(classPathname.split("Day")[1]);

        String inputString = ReadInput.getString(year, day);
        Part1 part1Runner = new Part1();
        System.out.println("Part1:");
        String output = part1Runner.start(inputString);
        System.out.println("Final answer: "+output);
        Part2 part2Runner = new Part2();
        System.out.println("\nPart2:");
        output = part2Runner.start(inputString);
        System.out.println("Final answer: "+output);
    }
}
