import com.google.common.base.Preconditions;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;
import com.google.common.primitives.UnsignedInteger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by zeno on 20.12.2016.
 * http://adventofcode.com/2016/day/20
 */

public class IPBreaker {

    public static void main(String[] args) {
        RangeSet<Integer> ipRange = TreeRangeSet.create();
        ipRange.add(Range.closed(0, 9));

        ipRange.remove(Range.closed(5, 8));
        ipRange.remove(Range.closed(0, 2));
        ipRange.remove(Range.closed(4, 7));

        for (Range<Integer> range : ipRange.asRanges()) {
            System.out.println("range "+range.toString());
            System.out.println("canonical range "+range.canonical(DiscreteDomain.integers()));
            System.out.println("lower endpoint "+range.lowerEndpoint());
            System.out.println("upper endpoint "+range.upperEndpoint());
        }

        System.out.println("complement rangeset "+ipRange.complement());

        /*System.out.println(10L -
                ipRange.complement().asRanges().stream()
                    .mapToLong(range -> range.upperEndpoint() - range.lowerEndpoint() + 1)
                    .sum()
        );*/

    }

    public static void main1(String[] args) {
        RangeSet<Long> ipRange = TreeRangeSet.create();
//        ipRange.add(Range.closed(0L, UnsignedInteger.MAX_VALUE.longValue()));

//        Preconditions.checkArgument(ipRange.contains(4294967295L));
//        Preconditions.checkArgument(UnsignedInteger.MAX_VALUE.longValue() == 4294967295L);

        long counter = 0;

        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(args[0]), charset)) {
            for (String line; (line = reader.readLine()) != null; ) {
                String interval[] = line.split("-", 2);
//                Preconditions.checkArgument(interval.length == 2);
//                ipRange.remove(Range.closed(Long.parseLong(interval[0]), Long.parseLong(interval[1])));
                ipRange.add(Range.closed(Long.parseLong(interval[0]), Long.parseLong(interval[1])));
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        long blacklist = ipRange.asRanges().stream().mapToLong(range -> range.upperEndpoint() - range.lowerEndpoint() + 1).sum();
        System.out.println(4294967295L - blacklist + 1);
    }
}
