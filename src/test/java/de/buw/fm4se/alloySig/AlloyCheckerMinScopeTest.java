package de.buw.fm4se.alloySig;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.buw.fm4se.alloySig.AlloyChecker;
import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Options.SatSolver;

class AlloyCheckerMinScopeTest {
	private static A4Reporter rep;
	private static A4Options opt;

	@BeforeAll
	public static void setUpOptions() {
		rep = A4Reporter.NOP;
		opt = new A4Options();
		opt.solver = SatSolver.SAT4J;
	}

	@Test
	void checkMinScopesForList() {
		String fileName = "src/main/resources/list.als";
		Map<String, Integer> minScope = AlloyChecker.findMinScope(fileName, opt, rep);
		assertEquals(0, minScope.get("this/Node"), "Node should have min scope 0, but has " + minScope.get("this/Node")
				+ " instead. Check the [list.als](src/main/resources/list.als) file.");
		assertEquals(1, minScope.get("this/List"), "List should have min scope 1, but has " + minScope.get("this/List")
				+ " instead. Check the [list.als](src/main/resources/list.als) file.");
	}

	@Test
	void checkMinScopesForDead1() {
		String fileName = "src/main/resources/dead1.als";
		Map<String, Integer> minScope = AlloyChecker.findMinScope(fileName, opt, rep);
		assertEquals(0, minScope.get("this/Node"), "Node should have min scope 0, but has " + minScope.get("this/Node")
				+ " instead. Check the [dead1.als](src/main/resources/dead1.als) file.");
		assertEquals(0, minScope.get("this/List"), "List should have min scope 0, but has " + minScope.get("this/List")
				+ " instead. Check the [dead1.als](src/main/resources/dead1.als) file.");
	}

	@Test
	void checkMinScopesForDead2() {
		String fileName = "src/main/resources/dead2.als";
		Map<String, Integer> minScope = AlloyChecker.findMinScope(fileName, opt, rep);
		assertEquals(0, minScope.get("this/Node"), "Node should have min scope 0, but has " + minScope.get("this/Node")
				+ " instead. Check the [dead2.als](src/main/resources/dead2.als) file.");
		assertEquals(0, minScope.get("this/List"), "List should have min scope 0, but has " + minScope.get("this/List")
				+ " instead. Check the [dead2.als](src/main/resources/dead2.als) file.");
	}

	@Test
	void checkMinScopesForDreadburyPuzzle() {
		String fileName = "src/main/resources/dreadbury.als";
		Map<String, Integer> minScope = AlloyChecker.findMinScope(fileName, opt, rep);
		assertEquals(0, minScope.get("this/Person"), "Person should have min scope 0, but has "
				+ minScope.get("this/Person") + " instead. Check the [dreadbury.als](src/main/resources/dreadbury.als) file.");
		assertEquals(1, minScope.get("this/Agatha"), "Agatha should have min scope 1, but has "
				+ minScope.get("this/Agatha") + " instead. Check the [dreadbury.als](src/main/resources/dreadbury.als) file.");
		assertEquals(1, minScope.get("this/Butler"), "Butler should have min scope 1, but has "
				+ minScope.get("this/Butler") + " instead. Check the [dreadbury.als](src/main/resources/dreadbury.als) file.");
		assertEquals(1, minScope.get("this/Charles"), "Charles should have min scope 1, but has "
				+ minScope.get("this/Charles") + " instead. Check the [dreadbury.als](src/main/resources/dreadbury.als) file.");
	}

	@Test
	void checkMinScopesForHousesPuzzle() {
		String fileName = "src/main/resources/houses.als";
		Map<String, Integer> minScope = AlloyChecker.findMinScope(fileName, opt, rep);
		assertEquals(0, minScope.get("this/House"), "House should have min scope 0, but has "
				+ minScope.get("this/House") + " instead. Check the [houses.als](src/main/resources/houses.als) file.");
		assertEquals(1, minScope.get("this/H1"), "H1 should have min scope 1, but has "
				+ minScope.get("this/H1") + " instead. Check the [houses.als](src/main/resources/houses.als) file.");
		assertEquals(1, minScope.get("this/H2"), "H2 should have min scope 1, but has "
				+ minScope.get("this/H2") + " instead. Check the [houses.als](src/main/resources/houses.als) file.");
		assertEquals(1, minScope.get("this/H3"), "H3 should have min scope 1, but has "
				+ minScope.get("this/H3") + " instead. Check the [houses.als](src/main/resources/houses.als) file.");
		assertEquals(1, minScope.get("this/Math"), "Math should have min scope 1, but has "
				+ minScope.get("this/Math") + " instead. Check the [houses.als](src/main/resources/houses.als) file.");
		assertEquals(1, minScope.get("this/White"), "White should have min scope 1, but has "
				+ minScope.get("this/White") + " instead. Check the [houses.als](src/main/resources/houses.als) file.");
	}

}
