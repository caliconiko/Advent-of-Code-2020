import java.util.*;

interface DayInterface {
	
	String fileNum();
	
	void part1(ArrayList<String> in);
	void part2(ArrayList<String> in);
	default void doDay(Part p) {
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
