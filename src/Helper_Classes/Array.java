package Helper_Classes;

import java.util.Arrays;

public class Array {
    private Array() {}
    public static int[] strToInt(String[] input) {
        int[] output = new int[input.length];
        for (int i=0;i<input.length;i++) {
            output[i] = Integer.parseInt(input[i]);
        }
        return output;
    }
    public static String[] intToStr(int[] input) {
        String[] output = new String[input.length];
        for (int i=0;i<input.length;i++) {
            output[i] = input[i] + "";
        }
        return output;
    }

    public static int[] slice(int[] list, int begin, int end) {
        int[] output = new int[end-begin];
        for (int i=begin;i<end;i++) {
            output[i-begin] = list[i];
        }
        return output;
    }
    public static String[] slice(String[] list, int begin, int end) {
        String[] output = new String[end-begin];
        for (int i=begin;i<end;i++) {
            output[i-begin] = list[i];
        }
        return output;
    }
    public static String join(String[] list, String joiner) {
        String output = "";
        for (int i=0;i<list.length;i++){
            output += list[i];
            if (i!=list.length-1) {
                output += joiner;
            }
        }
        return output;
    }
}
