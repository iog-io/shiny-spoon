package com.aoc.soln;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.primitives.Ints;

public class FireHazard {
	
	private enum Command { ON, OFF, FLIP }
	
	private BitSet board[];
	
	public FireHazard(int dim) {
		board = new BitSet[dim];
		Arrays.fill(board, new BitSet(dim));
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FireHazard lights = new FireHazard(Ints.tryParse(args[1]).intValue());
		BufferedReader reader = Files.newReader(new File(args[0]), Charsets.US_ASCII);
		String line;
		
//		String toggle = Pattern.quote("^\\btoggle\\b\\s+(\\d+),(\\d+)\\s+\\bthrough\\b\\s+(\\d+),(\\d+).*$");
		Pattern togglePat = Pattern.compile("^\\btoggle\\b\\s+(\\d+),(\\d+)\\s+\\bthrough\\b\\s+(\\d+),(\\d+).*$", Pattern.CASE_INSENSITIVE);
		Predicate<String> togglePred = togglePat.asPredicate();
		
//		String turnOn = Pattern.quote("^\\bturn\\b\\s+\\bon\\b\\s+(\\d+),(\\d+)\\s+\\bthrough\\b\\s+(\\d+),(\\d+).*$");
		Pattern turnOnPat = Pattern.compile("^\\bturn\\b\\s+\\bon\\b\\s+(\\d+),(\\d+)\\s+\\bthrough\\b\\s+(\\d+),(\\d+).*$", Pattern.CASE_INSENSITIVE);
		Predicate<String> turnOnPred = turnOnPat.asPredicate();
		
//		String turnOff = Pattern.quote("^\\bturn\\b\\s+\\boff\\b\\s+(\\d+),(\\d+)\\s+\\bthrough\\b\\s+(\\d+),(\\d+).*$");
		Pattern turnOffPat = Pattern.compile("^\\bturn\\b\\s+\\boff\\b\\s+(\\d+),(\\d+)\\s+\\bthrough\\b\\s+(\\d+),(\\d+).*$", Pattern.CASE_INSENSITIVE);
		Predicate<String> turnOffPred = turnOffPat.asPredicate();
		
		try {
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
		} finally {
			reader.close();
		}
		
		System.out.println(lights.summary());
		
	}
	
	void doCommand(List<Integer> rect, Command cmd) {
		Preconditions.checkArgument(rect.size()==4);
		if (cmd == Command.ON) {
			for (int i = rect.get(0); i < rect.get(2); i++) {
				BitSet bitSet = board[i];
				bitSet.set(rect.get(1), rect.get(3));
			}
			return;
		}
		if (cmd == Command.OFF) {
			for (int i = rect.get(0); i < rect.get(2); i++) {
				BitSet bitSet = board[i];
				bitSet.clear(rect.get(1), rect.get(3));
			}
		}
		if (cmd == Command.FLIP) {
			for (int i = rect.get(0); i < rect.get(2); i++) {
				BitSet bitSet = board[i];
				bitSet.flip(rect.get(1), rect.get(3));
			}
		}
	}
	
	long summary() {
		return Arrays.stream(board).mapToInt(bs->bs.cardinality()).sum();
	}
	
	private static List<Integer> getInts(Pattern pattern, String line) {
		Matcher matcher = pattern.matcher(line);
		System.out.println(matcher.groupCount());
		List<Integer> corners = Lists.newArrayList();
		int group = 1;
		while (matcher.find()) {
			Integer zahl = Ints.tryParse(matcher.group(group++));
			if (zahl != null) corners.add(zahl);
			System.out.println("zahl"+zahl);
		}
		System.out.println("group"+group);
		return corners;
	}

}
