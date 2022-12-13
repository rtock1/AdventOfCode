package Year_2022.Day13;

import java.util.ArrayList;

public class Part1 {
    public String start(String input) {
        int count = 0;
        String[] packetPairs = input.split("\\n\\n");
        for (int i=0;i<packetPairs.length;i++) {
            String[] packets = packetPairs[i].split("\\n");
            if (compare(stringToArray(packets[0]), stringToArray(packets[1])) == 0) {
                count += i + 1;
            }
        }

        return count + "";
    }
    public ArrayList<Object> stringToArray(String in) {
        ArrayList<Object> out = new ArrayList<>();
        String nonListString = in.substring(1,in.length()-1);
        ArrayList<Integer> commaPositions = new ArrayList<>();
        int brackets = 0;
        for (int i=0;i<nonListString.length();i++) {
            if (nonListString.charAt(i) == '[') {
                brackets++;
            } else if (nonListString.charAt(i) == ']') {
                brackets--;
            }
            if (nonListString.charAt(i) == ',' && brackets == 0) {
                commaPositions.add(i);
            }
        }
        for (int i=0;i<commaPositions.size() + 1;i++) {
            String entry;
            if (commaPositions.size() == 0) {
                entry = nonListString;
            } else if (i==0) {
                entry = nonListString.substring(0, commaPositions.get(i));
            } else if (i==commaPositions.size()) {
                entry = nonListString.substring(commaPositions.get(i-1) + 1);
            } else {
                entry = nonListString.substring(commaPositions.get(i-1)+1, commaPositions.get(i));
            }
            if ("".equals(entry)) {
                return new ArrayList<>();
            }
            if (entry.charAt(0) == '[') {
                out.add(stringToArray(entry));
            } else {
                out.add(Integer.parseInt(entry));
            }
        }
        return out;
    }
    public int compare(ArrayList<Object> packet1, ArrayList<Object> packet2) {
        for (int i=0;i<Math.max(packet1.size(), packet2.size()); i++) {
            if (i >= packet1.size()) {
                return 0;
            }
            if (i >= packet2.size()) {
                return 2;
            }
            if (packet1.get(i) instanceof Integer && packet2.get(i) instanceof Integer) {
                if ((Integer) packet2.get(i) > (Integer) packet1.get(i)) {
                    return 0;
                } else if ((Integer) packet2.get(i) < (Integer) packet1.get(i)) {
                    return 2;
                }
            } else if (packet1.get(i) instanceof ArrayList && packet2.get(i) instanceof ArrayList) {
                int output = compare((ArrayList<Object>) packet1.get(i), (ArrayList<Object>) packet2.get(i));
                if (output != 1) {
                    return output;
                }
            } else {
                ArrayList<Object> newPacket = new ArrayList<>();
                if (packet1.get(i) instanceof ArrayList) {
                    newPacket.add(packet2.get(i));
                    int output = compare((ArrayList<Object>) packet1.get(i), newPacket);
                    if (output != 1) {
                        return output;
                    }
                } else {
                    newPacket.add(packet1.get(i));
                    int output = compare(newPacket, (ArrayList<Object>) packet2.get(i));
                    if (output != 1) {
                        return output;
                    }
                }
            }
        }
        return 1;
    }
}
