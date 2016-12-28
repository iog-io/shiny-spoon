import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zeno on 13.12.2016.
 * http://adventofcode.com/2016/day/11
 */

public class Stealer {

    private static final class Pair extends AbstractMap.SimpleImmutableEntry<Integer, Integer> {
        Pair(int chip, int gen) {
            super(chip, gen);
        }
    }

    private static final class State {
        final int stepsMade;
        final int elevator;
        Set<Pair> devices = new HashSet<>();
        State(final int stepsMade, final int elevator) {
            this.stepsMade = stepsMade;
            this.elevator = elevator;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;

            State state = (State) o;

            if (elevator != state.elevator) return false;
            return devices.equals(state.devices);
        }

        @Override
        public int hashCode() {
            int result = elevator;
            result = 31 * result + devices.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "State{" +
                    "stepsMade=" + stepsMade +
                    ", elevator=" + elevator +
                    ", devices=" + devices +
                    '}';
        }
    }

    public static void main(String[] args) {
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(args[0]), charset)) {
            for (String line; (line = reader.readLine()) != null; /**/) {
                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }

        State sample = new State(0, 1);
        sample.devices.add(new Pair(1, 2));     // hydrogen
        sample.devices.add(new Pair(1, 3));     // lithium

        State initial = new State(0, 1);
        initial.devices.add(new Pair(2, 1));    // polonium
        initial.devices.add(new Pair(1, 1));    // thulium
        initial.devices.add(new Pair(2, 1));    // promethium
        initial.devices.add(new Pair(1, 1));    // ruthenium
        initial.devices.add(new Pair(1, 1));    // cobalt

    }
}
