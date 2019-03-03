package basics.algorithms.strings.compare;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import markdown.ReadmeFileCreator;

public class StringPermutationFinder {

	// map of characters vs amount found
	static Map<Character, Integer> result;

	// map of word vs list of permutations
	static Map<String, List<String>> permsFamilyMap = new HashMap<>();

	/**
	 * This method just applies addition or substraction to the map entry value
	 * related to the key (c). Every time the value gets 0, the entry is removed for
	 * the c key.
	 * 
	 * @param c
	 *            The character currently involved
	 * @param op
	 *            The operation to apply in the value related to the c (key map)
	 *            character
	 */
	private static void createEntry(Character c, String op) {

		Integer n = result.get((char) c);

		switch (op) {
		case "+":
			if (n != null)
				result.put(c, n + 1);
			else
				result.put(c, 1);
			break;

		case "-":
			if (n != null)
				result.put(c, n - 1);
			else
				result.put(c, -1);
			break;
		default:
			System.out.println("No possible operation.");
		}

		if (result.get(c) == 0)
			result.remove(c);
	}

	/**
	 * Returns true if the origin parameter is a permutation of the perm parameter.
	 * 
	 * Algorithm: origin char array adds key value entries in a map for each of its
	 * characters and perm subtracts for each of its characters. When the value for
	 * a key is 0 in the map, the algorithm deletes the entry. If the map is not
	 * empty after going through the two char arrays is because they have different
	 * characters.
	 * 
	 * @param origin
	 *            The origin String parameter
	 * @param perm
	 *            The possible character's permutation of the origin parameter
	 * @return true if perm is a character's permutation of origin
	 */
	public static boolean isPermutationOf(char[] origin, char[] perm) {

		result = new HashMap<>();

		if (origin.length != perm.length)
			return false;

		for (int i = 0; i < origin.length; i++) {
			Character co = origin[i];
			Character cp = perm[i];
			createEntry(co, "+");
			createEntry(cp, "-");
		}

		if (!result.isEmpty()) {
			return false;
		}

		return true;
	}

	/**
	 * Searches for a family of character's permutation word and returns true if already exists in the map
	 * 
	 * Algorithm: Going through all the keys and checking if the word is a permutation of one of them
	 * 
	 * @param word
	 * @return
	 */
	private static boolean existsPermutationFamily(char[] word) {
		
		for ( String k : permsFamilyMap.keySet() ) {
			if (isPermutationOf(k.toCharArray(), word)) return true;
		}
		return false;
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		ReadmeFileCreator readme = new ReadmeFileCreator("src/basics/algorithms/strings/compare/README.md");

		// list of words to check for permutations among them
		String[] perms = { "tupamaru", "pin", "marutupa", "patumadre", "matupadre", "putamura", "murutapa", "pan", "nip",
				"pna", "npi", "npa" };

		ReadmeFileCreator.readme.write("## Input List:  \n");
		Arrays.stream(perms).forEach(z -> ReadmeFileCreator.readme.write("- " + z + "  \n"));
		ReadmeFileCreator.readme.write("  \n");

		for (int i = 0; i < perms.length; i++) {
			for (int j = 1; j < perms.length; j++) {
				if (isPermutationOf(perms[i].toCharArray(), perms[j].toCharArray())) {
					List<String> permValue = permsFamilyMap.get(perms[i]);
					if (permValue != null) {
						permValue.add(perms[j]);
					} else {
						if ( existsPermutationFamily(perms[j].toCharArray()) ) continue;
						List<String> permVal = new ArrayList<>();
						permVal.add(perms[j]);
						permsFamilyMap.put(perms[i], permVal);
					}
				}
			}
		}

		// print the map of permutations created above.
		ReadmeFileCreator.readme.write("## Family of words by character permutation found:  ");
		permsFamilyMap.forEach((x, y) -> {
			ReadmeFileCreator.readme.write("\n[" + x + "] :: PERMUTATIONS ::  \n");
			y.forEach(z -> {
				ReadmeFileCreator.readme.write("- " + z + "  \n");
			});
		});

		readme.close();
	}
}
