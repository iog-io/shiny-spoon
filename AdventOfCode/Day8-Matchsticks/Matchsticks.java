import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineReader;

import java.io.File;
import java.io.IOException;

/**
 * Created by zeno on 05.09.2016.
 *
 * Thoughts:
 * Use regex to match: \\ , \" and \x??
 * For each \\ and \" subtract lenght by 1
 * For each \x?? subtract length by 3
 * Finally subtract length by 2 for ""
 */
public class Matchsticks {
//    public static void main(String[] args) throws IOException {
//        LineReader reader = new LineReader(Files.newReader(new File(args[0]), Charsets.US_ASCII));
//        int diff = 0;
//        String line;
//        while ((line = reader.readLine()) != null) {
//            String str =
//                    line.replaceAll("\\\\\"", "Q")
//                        .replaceAll("\\\\\\\\", "S")
//                        .replaceAll("\\\\x..", "A");
//            diff += line.length() - (str.length() - 2);
//        }
//        System.out.println(diff);     // Part 1: 1342
//    }

    public static void main(String[] args) throws IOException {
        LineReader reader = new LineReader(Files.newReader(new File(args[0]), Charsets.US_ASCII));
        int diff = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String str =
                    line.replaceAll("\"", "QQ")
                        .replaceAll("\\\\", "SS");
            diff += str.length() - (line.length() - 2);
        }
        System.out.println(diff);   // Part 2: 2074
    }
}
