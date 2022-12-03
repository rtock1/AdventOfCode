package Helper_Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadInput {
    public static final String yearFolderFormat = "Year_[year]";
    public static final String dayFolderFormat = "Day[day]";
    public static String getString(int year, int day, String filename) throws IOException {
        File file = new File("src/" + yearFolderFormat.replaceAll("\\[year]",year+"") + "/" + dayFolderFormat.replaceAll("\\[day]",day+"") + "/" + filename + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }
        reader.close();
        String output = content.toString();
        if (output.length() > 0 && output.charAt(output.length() - 1) == '\n') {
            output = output.substring(0, output.length() - 1);
        }
        return output;
    }
}