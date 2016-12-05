import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineReader;
import com.google.common.primitives.Ints;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Created by zeno on 03.09.2016.
 */
public class Area {
    public static void main(String[] args) throws IOException {
        LineReader reader = new LineReader(Files.newReader(new File(args[0]), Charsets.US_ASCII));
        int totalArea = 0;
        int totalRibbon = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            String[] measures = line.split("x");
            int l = Ints.tryParse(measures[0]);
            int w = Ints.tryParse(measures[1]);
            int h = Ints.tryParse(measures[2]);
            int area = 2*l*w + 2*w*h + 2*h*l;

            int prod = IntStream.of(l,w,h).reduce(1, (a, b)->a*b);
            int maxDim = IntStream.of(l,w,h).max().getAsInt();
            int slack = prod / maxDim;

            totalArea += (area + slack);    // Part 1: 1588178

            int ribbon = IntStream.of(l,w,h).sum() - maxDim;
            totalRibbon += (ribbon*2 + prod);   // Part 2: 3783758
        }
        System.out.println(totalArea);
        System.out.println(totalRibbon);
    }
}
