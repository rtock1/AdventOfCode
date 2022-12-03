package Helper_Classes;

public class ArrayConvert {
    private ArrayConvert() {}
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
}
