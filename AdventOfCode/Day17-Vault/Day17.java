/**
 * https://www.reddit.com/r/adventofcode/comments/5isvxv/2016_day_17_solutions/dbarm1x/
 */

import gnu.trove.list.TCharList;
import gnu.trove.list.array.TCharArrayList;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import java.util.IntSummaryStatistics;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class Day17 {

    public static final TIntObjectMap<char[]> success = new TIntObjectHashMap<>();
    public static final HashFunction md5 = Hashing.md5();
    public static final String INPUT = "vwbaicqe";
    public static final char[] dirs = { 'U', 'D', 'L', 'R' };
    public static final int[] dx = { 0, 0, -1, 1 };
    public static final int[] dy = { -1, 1, 0, 0 };

    public static int doors(HashCode hash) {
        final String str = hash.toString();
        int doors = 0;

        for (int i = 0; i < Day17.dirs.length; i++) {
            final char c = str.charAt(i);

            if (c >= 'b' && c <= 'f') {
                doors |= 1 << i;
            }
        }

        return doors;
    }

    public static void delve(TCharList stack, int x, int y) {
        if (x == 3 && y == 3) {
            // WOW, it wanted the PATH, not just the length
//            Day17.success.add(stack.size());
            Day17.success.put(stack.size(), stack.toArray());
            return;
        }

        final int doors = Day17.doors(Day17.md5.hashString(new StringBuilder(Day17.INPUT).append(stack.toArray()), Charsets.US_ASCII));

        for (int i = 0, nx, ny; i < Day17.dirs.length; i++) {
            // Check that the door is open and we won't exit the grid by moving that way
            if ((1 << i & doors) != 0 && (Math.floorDiv(nx = x +  Day17.dx[i], 4) | Math.floorDiv(ny = y + Day17.dy[i], 4)) == 0) {
                stack.add(Day17.dirs[i]);
                Day17.delve(stack, nx, ny);
                stack.removeAt(stack.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        Day17.delve(new TCharArrayList(), 0, 0);

        final IntSummaryStatistics stats;

        System.out.println(stats = IntStream.of(Day17.success.keys()).summaryStatistics());
        System.out.println("Min path: " + String.valueOf(Day17.success.get(stats.getMin())));
        System.out.println("Elapsed "+ TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-start)+"ms");
    }
}