package Year_2022.Day13;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

public class Part2 {
    public String start(String input) {
        String[] packetPairs = input.split("\\n\\n");
        ArrayList<Packet> packets = new ArrayList<>();
        Packet divider1 = new Packet("[[2]]");
        Packet divider2 = new Packet("[[6]]");
        packets.add(divider1);
        packets.add(divider2);
        for (int i=0;i<packetPairs.length;i++) {
            for (String packet: packetPairs[i].split("\\n")) {
                packets.add(new Packet(packet));
            }
        }
        Collections.sort(packets);
        //System.out.println(packets);
        return ((packets.indexOf(divider1)+1) * (packets.indexOf(divider2)+1)) + "";
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
                return -1;
            }
            if (i >= packet2.size()) {
                return 1;
            }
            if (packet1.get(i) instanceof Integer && packet2.get(i) instanceof Integer) {
                if ((Integer) packet2.get(i) > (Integer) packet1.get(i)) {
                    return -1;
                } else if ((Integer) packet2.get(i) < (Integer) packet1.get(i)) {
                    return 1;
                }
            } else if (packet1.get(i) instanceof ArrayList && packet2.get(i) instanceof ArrayList) {
                int output = compare((ArrayList<Object>) packet1.get(i), (ArrayList<Object>) packet2.get(i));
                if (output != 0) {
                    return output;
                }
            } else {
                ArrayList<Object> newPacket = new ArrayList<>();
                if (packet1.get(i) instanceof ArrayList) {
                    newPacket.add(packet2.get(i));
                    int output = compare((ArrayList<Object>) packet1.get(i), newPacket);
                    if (output != 0) {
                        return output;
                    }
                } else {
                    newPacket.add(packet1.get(i));
                    int output = compare(newPacket, (ArrayList<Object>) packet2.get(i));
                    if (output != 0) {
                        return output;
                    }
                }
            }
        }
        return 0;
    }
    public class Packet implements Comparable<Packet> {
        public ArrayList<Object> data;
        String stringFormat;
        @Override
        public int compareTo(@NotNull Packet o) {
            return compare(data, o.data);
        }
        public Packet(String stringData) {
            stringFormat = stringData;
            this.data = stringToArray(stringData);
        }
        public String toString() {
            return stringFormat;
        }
    }
}
