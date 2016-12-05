import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.LineReader;
import com.google.common.primitives.Ints;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Created by zeno on 25.09.2016.
 */
public class Olympics {
    public static void main(String[] args) throws IOException {
        LineReader reader = new LineReader(Files.newReader(new File(args[0]), Charsets.US_ASCII));
        Pattern pattern = Pattern.compile("([A-Za-z]+)\\scan fly\\s(\\d+)\\skm/s for\\s(\\d+)\\sseconds, but then must rest for\\s(\\d+)\\sseconds.");
        List<Flighter> flighters = Lists.newArrayList();
        String line;
        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                String name = matcher.group(1);
                int velocity = Ints.tryParse(matcher.group(2));
                int fly = Ints.tryParse(matcher.group(3));
                int rest = Ints.tryParse(matcher.group(4));
                flighters.add(new Flighter(name, velocity, fly, rest));
            }
        }
        final int TIME = 2503;
        int elapsedTime = 0;
        while (elapsedTime++ < TIME) {
            flighters.forEach(Flighter::proceed);
            int maxVelocity = flighters.stream().mapToInt(Flighter::getDistant).max().getAsInt();
            flighters.stream().filter(f->f.getDistant()==maxVelocity).forEach(Flighter::score);
        }
//        System.out.println(flighters.stream().mapToInt(Flighter::getDistant).max());
        System.out.println(flighters.stream().mapToInt(Flighter::getPoint).max());
    }
}
