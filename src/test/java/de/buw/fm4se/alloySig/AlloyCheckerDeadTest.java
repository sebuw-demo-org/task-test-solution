package de.buw.fm4se.alloySig;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.buw.fm4se.alloySig.AlloyChecker;
import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Options.SatSolver;

class AlloyCheckerDeadTest {
	private static A4Reporter rep;
	private static A4Options opt;

	@BeforeAll
	public static void setUpOptions() {
		rep = A4Reporter.NOP;
		opt = new A4Options();
		opt.solver = SatSolver.SAT4J;
	}

	@Test
	void checkDeadSigsForList() {
		String fileName = "src/main/resources/list.als";
		List<String> dead = AlloyChecker.findDeadSignatures(fileName, opt, rep);
		assertEquals(0, dead.size(), "List should have no dead signatures, but has " + dead.size() + " instead. Check the [list.als](src/main/resources/list.als) file.");
	}

	@Test
	void checkDeadSigsForDead1() {
		String fileName = "src/main/resources/dead1.als";
		List<String> dead = AlloyChecker.findDeadSignatures(fileName, opt, rep);
		assertEquals(1, dead.size(), "Dead1 should have one dead signature, but has " + dead.size() + " instead. Check the [dead1.als](src/main/resources/dead1.als) file.");
	}

	@Test
	void checkDeadSigsForDead2() {
		String fileName = "src/main/resources/dead2.als";
		List<String> dead = AlloyChecker.findDeadSignatures(fileName, opt, rep);
		assertEquals(2, dead.size(), "Dead2 should have two dead signatures, but has " + dead.size() + " instead. Check the [dead2.als](src/main/resources/dead2.als) file.");
	}
	
	@Test
	void checkDeadSigsForDreadburyPuzzle() {
		String fileName = "src/main/resources/dreadbury.als";
		List<String> dead = AlloyChecker.findDeadSignatures(fileName, opt, rep);
		assertEquals(0, dead.size(), "Dreadbury Puzzle should have no dead signatures, but has " + dead.size() + " instead. Check the [dreadbury.als](src/main/resources/dreadbury.als) file.");
	}

	@Test
	void checkDeadSigsForHousesPuzzle() {
		String fileName = "src/main/resources/houses.als";
		List<String> dead = AlloyChecker.findDeadSignatures(fileName, opt, rep);
		assertEquals(0, dead.size(), "Houses Puzzle should have no dead signatures, but has " + dead.size() + " instead. Check the [houses.als](src/main/resources/houses.als) file.");
	}

}
