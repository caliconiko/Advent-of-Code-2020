import java.util.*;

abstract class Day {
	
	public abstract String fileNum();
	
	public abstract void part1(ArrayList<String> in);
	public abstract void part2(ArrayList<String> in);
	public void doDay(Part p) {
		AdventOfCode.readInput(fileNum());
		switch (p) {
		case ONE:
			part1(AdventOfCode.Inputs);
			break;

		case TWO:
			part2(AdventOfCode.Inputs);
			break;
		}
	}
}
