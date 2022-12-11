package Year_2022.Day11;

import Helper_Classes.Array;

import java.util.ArrayList;
import java.util.Arrays;

public class Part2 {
    public String start(String input) {
        String[] monkeys = input.split("\\n\\n");
        ArrayList<Long>[] itemsByMonkey = new ArrayList[monkeys.length];
        String[] operationsByMonkey = new String[monkeys.length];
        int[] divisTestByMonkey = new int[monkeys.length];
        int[] trueCases = new int[monkeys.length];
        int[] falseCases = new int[monkeys.length];
        long[] examineNumbers = new long[monkeys.length];
        long modNumber = 1;
        for (int i=0;i<monkeys.length;i++){
            itemsByMonkey[i] = new ArrayList<>();
            for (long a: Array.strToInt(monkeys[i].split("\\n")[1].split(": ")[1].split(", "))) {
                itemsByMonkey[i].add(a);
            }
            operationsByMonkey[i] = monkeys[i].split("\\n")[2].split("new = ")[1];
            divisTestByMonkey[i] = Integer.parseInt(monkeys[i].split("\\n")[3].split("by ")[1]);
            modNumber *= divisTestByMonkey[i];
            trueCases[i] = Integer.parseInt(monkeys[i].split("\\n")[4].split("monkey ")[1]);
            falseCases[i] = Integer.parseInt(monkeys[i].split("\\n")[5].split("monkey ")[1]);
        }
        //System.out.println(modNumber);
        for (int cycle = 0; cycle<10000; cycle++){
            for (int a=0;a<monkeys.length;a++) {
                int repeatLength = itemsByMonkey[a].size();
                for (int item = 0;item<repeatLength;item++) {
                    examineNumbers[a]++;
                    long num1 = Integer.parseInt(operationsByMonkey[a].split(" ")[0].replaceAll("(old)", itemsByMonkey[a].get(0) + ""));
                    long num2 = Integer.parseInt(operationsByMonkey[a].split(" ")[2].replaceAll("(old)", itemsByMonkey[a].get(0) + ""));;
                    char op = operationsByMonkey[a].split(" ")[1].charAt(0);
                    switch (op) {
                        case '*':
                            itemsByMonkey[a].set(0, (num1 * num2) % modNumber);
                            break;
                        case '+':
                            itemsByMonkey[a].set(0, (num1 + num2) % modNumber);
                            break;
                    }
                    if (itemsByMonkey[a].get(0) % divisTestByMonkey[a] == 0) {
                        itemsByMonkey[trueCases[a]].add(itemsByMonkey[a].get(0));
                    } else {
                        itemsByMonkey[falseCases[a]].add(itemsByMonkey[a].get(0));
                    }
                    itemsByMonkey[a].remove(itemsByMonkey[a].get(0));
                }
            }
        }
        //System.out.println(Arrays.toString(examineNumbers));
        Arrays.sort(examineNumbers);
        return "" + (examineNumbers[examineNumbers.length-2] * examineNumbers[examineNumbers.length-1]);
    }
}
