package de.buw.fm4se.alloySig;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.ast.Command;
import edu.mit.csail.sdg.ast.CommandScope;
import edu.mit.csail.sdg.ast.Module;
import edu.mit.csail.sdg.ast.Sig;
import edu.mit.csail.sdg.parser.CompUtil;
import edu.mit.csail.sdg.translator.A4Options;
import edu.mit.csail.sdg.translator.A4Solution;
import edu.mit.csail.sdg.translator.TranslateAlloyToKodkod;

public class AlloyChecker {

	public static List<String> findDeadSignatures(String fileName, A4Options options, A4Reporter rep) {
		List<String> deadSigs = new ArrayList<>();
		//=== TODO ===
		Module module = CompUtil.parseEverything_fromFile(rep, null, fileName);

		Command cmd = module.getAllCommands().get(0);

		for (Sig s : module.getAllReachableUserDefinedSigs()) {
			Command command = cmd.change(s.some().and(cmd.formula));
			A4Solution ans = TranslateAlloyToKodkod.execute_command(rep, module.getAllReachableSigs(), command,
					options);
			if (!ans.satisfiable()) {
				deadSigs.add(s.label);
			}
		}
		//=== END ===
		return deadSigs;
	}

	public static List<String> findCoreSignatures(String fileName, A4Options options, A4Reporter rep) {
		List<String> coreSigs = new ArrayList<>();
		Module module = CompUtil.parseEverything_fromFile(rep, null, fileName);

		Command cmd = module.getAllCommands().get(0);

		for (Sig s : module.getAllReachableUserDefinedSigs()) {
			Command command = cmd.change(s.no().and(cmd.formula));
			A4Solution ans = TranslateAlloyToKodkod.execute_command(rep, module.getAllReachableSigs(), command,
					options);
			if (!ans.satisfiable()) {
				coreSigs.add(s.label);
			}
		}
		return coreSigs;
	}

	/**
	 * Computes for each user-defines signature a minimal scope for which the model
	 * is still satisfiable. Note that the scopes will be independent, i.e., minimum
	 * 0 for sig A and 0 for sig B does not mean that both can be 0 together.
	 * 
	 * @param fileName
	 * @param options
	 * @param rep
	 * @return map from signature names to minimum scopes
	 */
	public static Map<String, Integer> findMinScope(String fileName, A4Options options, A4Reporter rep) {
		Map<String, Integer> minScopes = new LinkedHashMap<>();
		Module module = CompUtil.parseEverything_fromFile(rep, null, fileName);

		Command cmd = module.getAllCommands().get(0);

		for (Sig s : module.getAllReachableUserDefinedSigs()) {
			for (int newScope = getMaxScope(s, cmd); newScope >= 0; newScope--) {
				Command commandLowerScope = cmd.change(s, false, newScope);
				try {
					A4Solution ans = TranslateAlloyToKodkod.execute_command(rep, module.getAllReachableSigs(),
							commandLowerScope, options);
					if (ans.satisfiable()) {
						minScopes.put(s.label, newScope);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return minScopes;
	}

	/**
	 * Computes the maximum scope for a signature in a command. This is either the
	 * default of 4, the overall scope, or the specific scope for the signature in
	 * the command. 
	 * 
	 * @param sig
	 * @param cmd
	 * @return
	 */
	public static int getMaxScope(Sig sig, Command cmd) {
		int scope = 4; // Alloy's default
		if (cmd.overall != -1) {
			scope = cmd.overall;
		}
		CommandScope cmdScope = cmd.getScope(sig);
		if (cmdScope != null) {
			scope = cmdScope.endingScope;
		}
		return scope;
	}

}
