import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import edu.princeton.cs.algs4.Out;
import com.google.common.io.LineReader;
import com.google.common.io.Files;
import com.google.common.base.Charsets;

public class InternElves {
    public static void main(String argv[]) throws IOException {
        LineReader reader = new LineReader(Files.newReader(new File("input.txt"), Charsets.US_ASCII));
        // Out fout = new Out("filtered.txt");
        
        // Pattern xxx = Pattern.compile("(\\w)\\1\\1"); // this pattern should not be used because "wwww" is still accepted as not overlapping
        Pattern pair = Pattern.compile("([a-z][a-z]).*\\1");
        Pattern xyx = Pattern.compile("([a-z]).\\1");
        
        String line;
        int cnt = 0;
        while ( (line = reader.readLine()) != null ) {
            // if ( !xxx.matcher(line).find() ) {
                if (pair.matcher(line).find() && xyx.matcher(line).find()) {
                    cnt++;
                    // fout.println(line);
                }
            // }
        }
        System.out.println(cnt);
        // fout.close();
    }
}
