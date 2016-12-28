import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;

import java.io.IOException;
import java.util.AbstractMap;

/**
 * Created by zeno on 13.12.2016.
 * http://adventofcode.com/2016/day/13
 */
public class Pathfinder {

    private static class Location extends AbstractMap.SimpleImmutableEntry<Long, Long> {
        private static final long SEED = 1364;
        private int bits = -1;

        Location(long x, long y) {
            super(x, y);
        }

        boolean isOpen() {
            if (bits == -1) {
                bits = Long.bitCount(SEED + getKey() * getKey() + 3 * getKey() + 2 * getKey() * getValue() + getValue() + getValue() * getValue());
            }
            return bits % 2 == 0;
        }
    }

    private static int getId(Location loc) {
        return (int) (loc.getKey() * 50 + loc.getValue());
    }

    /*public static void main(String[] args) throws IOException {
        Charset charset = Charset.forName("US-ASCII");
        Path file = Paths.get("./maze.out");
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            final int V = 50*50;
            Map<Long, Location> M = Maps.newHashMapWithExpectedSize(V);
            StringBuilder sb = new StringBuilder();
            int E = 0;
            for (long i = 0; i < 50; i++) {
                for (long j = 0; j < 50; j++) {
                    List<Location> locs = Lists.newArrayList();
                    for (Location loc : new Location[]{new Location(i, j), new Location(i, j + 1), new Location(i + 1, j)}) {
                        if (loc.getKey() < 50 && loc.getValue() < 50) {
                            if (M.putIfAbsent(getId(loc), loc) != null)
                                locs.add(M.get(getId(loc)));
                            else locs.add(loc);
                        }
                    }
                    if (locs.get(0).isOpen()) {
                        for (int k = 1; k < locs.size(); ++k) {
                            if (locs.get(k).isOpen()) {
                                sb.append(getId(locs.get(0))).append(" ").append(getId(locs.get(k))).append('\n');
                                sb.append(getId(locs.get(k))).append(" ").append(getId(locs.get(0))).append('\n');
                                writer.write(sb.toString(), 0, sb.length());
                                sb.setLength(0);
                                E += 2;
                            }
                        }
                    }
                }
            }
            System.out.println(V);
            System.out.println(E);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        Graph G = new Graph(new In(args[0]));
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, (int)getId(new Location(1, 1)));
//        System.out.println(bfs.distTo((int)getId(new Location(31, 39))));
        int count = 0;
        for (int i = 0; i < G.V(); i++) {
            if (bfs.hasPathTo(i) && bfs.distTo(i) <= 50) ++count;
        }
        System.out.println(count);
    }*/
    public static void main(String[] args) throws IOException {
        Graph G = new Graph(50*50);
        for (long i = 0; i < 50; i++) {
            for (long j = 0; j < 50; j++) {
                Location current = new Location(i, j);
                if (current.isOpen()) {
                    for (Location loc : new Location[]{new Location(i, j + 1), new Location(i + 1, j)}) {
                        if (loc.getKey() < 50 && loc.getValue() < 50 && loc.isOpen())
                            G.addEdge(getId(current), getId(loc));
                    }
                }
            }
        }

        BreadthFirstPaths bfs = new BreadthFirstPaths(G, getId(new Location(1, 1)));
        System.out.println(bfs.distTo(getId(new Location(31, 39))));

        int count = 0;
        for (int i = 0; i < G.V(); i++) {
            if (bfs.hasPathTo(i) && bfs.distTo(i) <= 50) ++count;
        }
        System.out.println(count);
    }
}
