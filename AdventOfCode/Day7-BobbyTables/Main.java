import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.LineReader;
import com.google.common.primitives.Ints;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeno on 07.09.2016.
 */
public class Main {

    public static Map<String, Integer> Values = Maps.newHashMap();

    public static void main(String[] args) throws IOException {
        Map<String, String> cmds = Maps.newHashMap();
        LineReader reader = new LineReader(Files.newReader(new File(args[0]), Charsets.US_ASCII));
        String line;
        if ((line = reader.readLine()) != null) {
            String[] tokens = line.split("->");

        }
    }

    private static Instruction processCmd(String cmd) {
        if (cmd.contains("AND")) {
            String[] operands = cmd.split("AND");
            String a = operands[0], b = operands[1];
            if (!wires.containsKey(a) || !wires.containsKey(b)) {
                return false;
            }
            else wires.put(assWire, (short)(wires.get(a) & wires.get(b)));
        }
        else if (cmd.contains("OR")) {
            String[] tokens = cmd.split("->");
            String assWire = tokens[1];
            String[] operands = tokens[0].split("OR");
            String a = operands[0], b = operands[1];
            if (!wires.containsKey(a) || !wires.containsKey(b)) {
                return false;
            }
            else wires.put(assWire, (short)(wires.get(a) | wires.get(b)));
        }
        else if (cmd.contains("LSHIFT")) {
            String[] tokens = cmd.split("->");
            String assWire = tokens[1];
            String[] operands = tokens[0].split("LSHIFT");
            String a = operands[0], b = operands[1];
            if (!wires.containsKey(a)) {
                return false;
            }
            else wires.put(assWire, (short)(wires.get(a) << Integer.valueOf(b)));
        }
        else if (cmd.contains("RSHIFT")) {
            String[] tokens = cmd.split("->");
            String assWire = tokens[1];
            String[] operands = tokens[0].split("RSHIFT");
            String a = operands[0], b = operands[1];
            if (!wires.containsKey(a)) {
                return false;
            }
            else wires.put(assWire, (short)(wires.get(a) >> Integer.valueOf(b)));
        }
        else if (cmd.contains("NOT")) {
            String[] tokens = cmd.split("->");
            String assWire = tokens[1];
            String a = tokens[0].split("NOT")[0];
            if (!wires.containsKey(a)) {
                return false;
            }
            else wires.put(assWire, (short)(wires.get(a)^0xFFFF));
        }
        else {
            String[] tokens = cmd.split("->");
            if (Ints.tryParse(tokens[0]) != null) {
                wires.put(tokens[1], Short.valueOf(tokens[0]));
            }
            else {
                if (!wires.containsKey(tokens[0])) return false;
                else wires.put(tokens[1], wires.get(tokens[0]));
            }
        }
        return true;
    }
}
