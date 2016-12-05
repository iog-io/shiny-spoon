import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.io.LineReader;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;

import java.io.File;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Circuit {

    private static Map<String, Short> wires = Maps.newHashMap();

    public static void main(String[] args) throws IOException {
        Stack<String> cmds = new Stack<>();
        LineReader reader = new LineReader(Files.newReader(new File(args[0]), Charsets.US_ASCII));
        String line;
        if ((line = reader.readLine()) != null) cmds.push(line);
        else System.exit(1);
        while (!cmds.empty()) {
            String currentCmd = cmds.peek();
            if (!processCmd(currentCmd)) {
                if ((line = reader.readLine()) != null) cmds.push(line);
                else {
                    cleanUp(cmds);
                    break;
                }
            }
            else cmds.pop();
        }
        System.out.println(wires.get("a"));
    }

    private static void cleanUp(Iterable<String> cmds) {
        Deque<String> deque = new LinkedList<>();
        cmds.forEach(deque::add);
        while (!wires.containsKey("a")) {
            String cmd = deque.poll();
            if (!processCmd(cmd)) deque.add(cmd);
        }
    }

    private static boolean processCmd(String cmd) {
        if (cmd.contains("AND")) {
            String[] tokens = cmd.split("->");
            String assWire = tokens[1];
            String[] operands = tokens[0].split("AND");
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
