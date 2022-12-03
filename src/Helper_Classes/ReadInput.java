package Helper_Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadInput {
    public static final String yearFolderFormat = "Year_[year]";
    public static final String dayFolderFormat = "Day[day]";
    public static String getString(int year, int day){
        String data = new String();
        try {
            File file = new File("src/" + yearFolderFormat.replaceAll("\\[year]",year+"") + "/" + dayFolderFormat.replaceAll("\\[day]",day+"") + "/puzzleInput.txt");
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                data += reader.nextLine() + "\n";
            }
            data = data.substring(0,data.length()-1);
            reader.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return data;
    }
}