import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineReader;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zeno on 03.09.2016.
 */
public class Dispatcher {
//    public static void main(String[] args) throws IOException {
//        LineReader reader = new LineReader(Files.newReader(new File(args[0]), Charsets.US_ASCII));
//        String moves = reader.readLine();
//        Set<Pair<Integer, Integer>> locations = new HashSet<>();
//        int x = 0, y = 0;
//        locations.add(new Pair<>(x, y));    // starting location
//        for (int dir : moves.toCharArray()) {
//            switch (dir) {
//                case 118:   // v
//                    y -= 1;
//                    break;
//                case 62:    // >
//                    x += 1;
//                    break;
//                case 60:    // <
//                    x -= 1;
//                    break;
//                case 94:    // ^
//                    y += 1;
//                    break;
//                default:
//                    throw new RuntimeException("!§$/&%$/(&%/§$%&/$§");
//            }
//            locations.add(new Pair<>(x, y));
//        }
//        System.out.println(locations.size());   // Part 1: 2572
//    }

    public static void main(String[] args) throws IOException {
        LineReader reader = new LineReader(Files.newReader(new File(args[0]), Charsets.US_ASCII));
        String moves = reader.readLine();
        Set<Pair<Integer, Integer>> locations = new HashSet<>();
        int xS = 0, yS = 0;
        int xR = 0, yR = 0;
        locations.add(new Pair<>(xS, yS));    // starting location of Santa and Robot
        int turn = 0;
        for (int dir : moves.toCharArray()) {
            switch (dir) {
                case 118:   // v
                    if (turn % 2 == 0) yS -= 1;
                    else yR -= 1;
                    break;
                case 62:    // >
                    if (turn % 2 == 0) xS += 1;
                    else xR += 1;
                    break;
                case 60:    // <
                    if (turn % 2 == 0) xS -= 1;
                    else xR -= 1;
                    break;
                case 94:    // ^
                    if (turn % 2 == 0) yS += 1;
                    else yR += 1;
                    break;
                default:
                    throw new RuntimeException("!§$/&%$/(&%/§$%&/$§");
            }
            if (turn % 2 == 0) locations.add(new Pair<>(xS, yS));
            else locations.add(new Pair<>(xR, yR));
            turn++;
        }
        System.out.println(locations.size());   // Part 2: 2631
    }
}
