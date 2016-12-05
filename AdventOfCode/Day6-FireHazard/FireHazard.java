import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.LineReader;
import com.google.common.primitives.Ints;

public class FireHazard {
	
	private enum Command { ON, OFF, FLIP }
	
	private ArrayList<BitSet> board;
	private int shinyBoard[][];
	
	public FireHazard(int dim) {
		board = new ArrayList<>(dim);
		IntStream.range(0, dim).forEach(e->board.add(new BitSet(dim)));
		shinyBoard = new int[dim][dim];
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FireHazard lights = new FireHazard(Ints.tryParse(args[1]));
		LineReader reader = new LineReader(Files.newReader(new File(args[0]), Charsets.US_ASCII));
		
		String toggleRegex = "^\\btoggle\\b\\s+(\\d+),(\\d+)\\s+\\bthrough\\b\\s+(\\d+),(\\d+).*$";
		Pattern togglePat = Pattern.compile(toggleRegex, Pattern.CASE_INSENSITIVE);
		Predicate<String> togglePred = togglePat.asPredicate();
		
		String turnOnRegex = "^\\bturn\\b\\s+\\bon\\b\\s+(\\d+),(\\d+)\\s+\\bthrough\\b\\s+(\\d+),(\\d+).*$";
		Pattern turnOnPat = Pattern.compile(turnOnRegex, Pattern.CASE_INSENSITIVE);
		Predicate<String> turnOnPred = turnOnPat.asPredicate();
		
		String turnOffRegex = "^\\bturn\\b\\s+\\boff\\b\\s+(\\d+),(\\d+)\\s+\\bthrough\\b\\s+(\\d+),(\\d+).*$";
		Pattern turnOffPat = Pattern.compile(turnOffRegex, Pattern.CASE_INSENSITIVE);
		Predicate<String> turnOffPred = turnOffPat.asPredicate();
		
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				if (togglePred.test(line)) {
					List<Integer> rect = getInts(togglePat, line);
					lights.doCommand(rect, Command.FLIP);
				}
				else if (turnOnPred.test(line)) {
					List<Integer> rect = getInts(turnOnPat, line);
					lights.doCommand(rect, Command.ON);
				}
				else if (turnOffPred.test(line)) {
					List<Integer> rect = getInts(turnOffPat, line);
					lights.doCommand(rect, Command.OFF);
				}
				else {
					System.err.println(line);
					throw new IllegalArgumentException();
				}
			}
		} catch (IOException | IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("Part 1: "+lights.summary());
		System.out.println("Part 2: "+lights.summaryBrightness());
		
	}
	
	void doCommand(List<Integer> rect, Command cmd) {
		Preconditions.checkArgument(rect.size()==4);
		if (cmd == Command.ON) {
			IntStream.rangeClosed(rect.get(0), rect.get(2)).mapToObj(board::get).forEach(bs->bs.set(rect.get(1), rect.get(3)+1));
			for (int i = rect.get(0); i <= rect.get(2); i++) {
				for (int j = rect.get(1); j <= rect.get(3); j++) {
					shinyBoard[i][j]++;
				}
			}
			return;
		}
		if (cmd == Command.OFF) {
			IntStream.rangeClosed(rect.get(0), rect.get(2)).mapToObj(board::get).forEach(bs->bs.clear(rect.get(1), rect.get(3)+1));
			for (int i = rect.get(0); i <= rect.get(2); i++) {
				for (int j = rect.get(1); j <= rect.get(3); j++) {
					shinyBoard[i][j] = Math.max(0, shinyBoard[i][j] - 1);
				}
			}
			return;
		}
		if (cmd == Command.FLIP) {
			IntStream.rangeClosed(rect.get(0), rect.get(2)).mapToObj(board::get).forEach(bs->bs.flip(rect.get(1), rect.get(3)+1));
			for (int i = rect.get(0); i <= rect.get(2); i++) {
				for (int j = rect.get(1); j <= rect.get(3); j++) {
					shinyBoard[i][j] += 2;
				}
			}
			return;
		}
	}
	
	long summary() {
		return board.stream().mapToInt(BitSet::cardinality).sum();
	}

	long summaryBrightness() {
		return Arrays.stream(shinyBoard).flatMapToInt(Arrays::stream).sum();		
	}
	
	private static List<Integer> getInts(Pattern pattern, String line) {
		Matcher matcher = pattern.matcher(line);
		List<Integer> corners = Lists.newArrayList();
		if (matcher.find()) {
			for (int i = 1; i <= 4; i++) {
				Integer zahl = Ints.tryParse(matcher.group(i));
				if (zahl != null) corners.add(zahl);
			}
		}
		return corners;
	}

}
