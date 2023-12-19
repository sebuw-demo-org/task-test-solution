package de.buw.fm4se.alloySig;

import org.junit.jupiter.api.Test;

import de.buw.fm4se.alloySig.AlloyChecker;
import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Options.SatSolver;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;

class AlloyCheckerCoreTest {
	private static A4Reporter rep;
	private static A4Options opt;

	@BeforeAll
	public static void setUpOptions() {
		rep = A4Reporter.NOP;
		opt = new A4Options();
		opt.solver = SatSolver.SAT4J;
	}

	@Test
	void checkCoreSigsForList() {
		String fileName = "src/main/resources/list.als";
		List<String> core = AlloyChecker.findCoreSignatures(fileName, opt, rep);
		assertEquals(1, core.size(), "List should have one core signature, but has " + core.size() + " instead. Check the [list.als](src/main/resources/list.als) file.");
	}

	@Test
	void checkCoreSigsForDead1() {
		String fileName = "src/main/resources/dead1.als";
		List<String> core = AlloyChecker.findCoreSignatures(fileName, opt, rep);
		assertEquals(0, core.size(), "Dead1 should have no core signatures, but has " + core.size() + " instead. Check the [dead1.als](src/main/resources/dead1.als) file.");
	}

	@Test
	void checkCoreSigForDead2() {
		String fileName = "src/main/resources/dead2.als";
		List<String> core = AlloyChecker.findCoreSignatures(fileName, opt, rep);
		assertEquals(0, core.size(), "Dead2 should have no core signatures, but has " + core.size() + " instead. Check the [dead2.als](src/main/resources/dead2.als) file.");
	}
	
	@Test
	void checkCoreSigsForDreadburyPuzzle() {
		String fileName = "src/main/resources/dreadbury.als";
		List<String> core = AlloyChecker.findCoreSignatures(fileName, opt, rep);
		assertEquals(4, core.size(), "Dreadbury Puzzle should have four core signatures, but has " + core.size() + " instead. Check the [dreadbury.als](src/main/resources/dreadbury.als) file.");
	}

	@Test
	void checkCoreSigsForHousesPuzzle() {
		String fileName = "src/main/resources/houses.als";
		List<String> core = AlloyChecker.findCoreSignatures(fileName, opt, rep);
		assertEquals(14, core.size(), "Houses Puzzle should have fourteen core signatures, but has " + core.size() + " instead. Check the [houses.als](src/main/resources/houses.als) file.");
	}	

}
