import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * Created by zeno on 17.12.2016.
 * http://adventofcode.com/2016/day/17
 */
public class VaultFinder {

    private static final int SIZE = 3;

    private final String INPUT;
    private List<Node> paths = new LinkedList<>();

    private static Predicate<Node> vaultLocator = (Predicate<Node>) v -> v.mRow == SIZE && v.mCol == SIZE;
    private static Predicate<Node> validator = ((Predicate<Node>) v -> v.mRow >= 0 && v.mCol >= 0)
                                                            .and(v -> v.mRow <= SIZE && v.mCol <= SIZE);

    VaultFinder(final String inputStr) {
        this.INPUT = inputStr;
        dfs(Collections.singleton(new Node(0, 0, "")));
    }

    // part 1
    String shortestPath() {
        Optional<Node> optional = paths.stream().min(Comparator.comparingInt(v -> v.mPath.length()));
        return optional.isPresent() ? optional.get().mPath : "FUCK";
    }

    // part 2
    int longestPathLength() {
        return paths.stream().mapToInt(v->v.mPath.length()).max().orElseGet(()->Integer.MIN_VALUE);
    }

    private void dfs(Iterable<Node> nodes) {
        for (Node v : nodes) {
            if (validator.test(v)) {
                if (vaultLocator.test(v)) {
                    paths.add(v);
                } else {
                    // TODO: try mPath as byte[]
                    String md5 = /*DigestUtils.*/md5Hex(INPUT + v.mPath);
                    List<Node> nexts = new LinkedList<>();
                    for (int i = 0; i <= SIZE; i++) {
                        if (md5.charAt(i) > 'a') {
                            nexts.add(Node.create(v, i));
                        }
                    }
                    if (!nexts.isEmpty()) dfs(nexts);
                }
            }
        }
    }

    private static final class Node {

        final int    mRow;
        final int    mCol;
        final String mPath;

        Node(final int mRow, final int mCol, final String mPath) {
            this.mRow  = mRow;
            this.mCol  = mCol;
            this.mPath = mPath;
        }

        static Node create(final Node from, final int direction) {
            int row = 0;
            int col = 0;
            char dir;
            switch (direction) {
                case 0: // UP
                    row = -1;
                    dir = 'U';
                    break;
                case 1: // DOWN
                    row = +1;
                    dir = 'D';
                    break;
                case 2: // LEFT
                    col = -1;
                    dir = 'L';
                    break;
                case 3: // RIGHT
                    col = +1;
                    dir = 'R';
                    break;
                default:
                    throw new IllegalArgumentException(
                            "ERROR:"+from.toString()+"\nDirection:"+direction);
            }
            return new Node(from.mRow + row, from.mCol + col, from.mPath + dir);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "mRow=" + mRow +
                    ", mCol=" + mCol +
                    ", mPath='" + mPath + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        /*final String test1 = "ihgpwlah";
        VaultFinder finder = new VaultFinder(test1);
        System.out.println(finder.shortestPath());
        System.out.println(finder.longestPathLength());

        final String test2 = "kglvqrro";
        finder = new VaultFinder(test2);
        System.out.println(finder.shortestPath());
        System.out.println(finder.longestPathLength());

        final String test3 = "ulqzkmiv";
        finder = new VaultFinder(test3);
        System.out.println(finder.shortestPath());
        System.out.println(finder.longestPathLength());*/

        long start = System.nanoTime();
        final String myInput = "vwbaicqe";
        VaultFinder finder = new VaultFinder(myInput);
        System.out.println(finder.shortestPath());
        System.out.println(finder.longestPathLength());
        System.out.println("Elapsed "+ TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-start)+"ms");
    }
}
