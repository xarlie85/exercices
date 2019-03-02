package basics.algorithms.strings.compare;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import markdown.ReadmeFileCreator;

public class StringPermutationFinder {

	static Map<Character, Integer> result;

	/**
	 * This method just applies addition or substraction to the map entry value related to the key (c).
	 * Every time the value gets 0, the entry is removed for the c key.
	 * 
	 * @param c The character currently involved
	 * @param op The operation to apply in the value related to the c (key map) character
	 */
	private static void createEntry(Character c, String op) {
		
		Integer n = result.get((char) c);
		
		switch(op) {
		case "+":
			if ( n != null )
				result.put(c, n+1);
			else 
				result.put(c, 1);
			break;
			
		case "-":
			if ( n != null )
				result.put(c, n-1);
			else 
				result.put(c, -1);
			break;
		default:
				System.out.println("No possible operation.");
		}
		
		if ( result.get(c) == 0 )
			result.remove(c);
	}

	/**
	 * Returns true if the origin parameter is a permutation of the perm parameter.
	 * Algorithm: origin adds key value entries in a map for each of its characters
	 * and perm substracts for each character. If there is a value different form 0
	 * for a key at the end... it should return false
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

		for ( int i = 0 ; i < origin.length ; i++ ) {
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
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		ReadmeFileCreator readme = new ReadmeFileCreator("src/basics/algorithms/strings/compare/README.md");
		
		// list of words to check for permutations among them
		String[] perms = {"tupamaru", "marutupa","patumadre","matupadre","putamura","murutapa","pan","nip","pna","npi"};
		
		// map of word as key and list of permutations as a value
		Map<String, List<String>> permsMap = new HashMap<>();
		
		for ( int i = 0 ; i < perms.length ; i ++ ) {
			for ( int j = 1; j < perms.length; j++ ) {
				if ( isPermutationOf(perms[i].toCharArray(), perms[j].toCharArray()) ) {
					List<String> permValue = permsMap.get(perms[i]);
					if ( permValue != null ) {
						permValue.add(perms[j]);
					} else {
						List<String> permVal = new ArrayList<>();
						permVal.add(perms[j]);
						permsMap.put(perms[i], permVal);
					}
				}
			}
		}
		
		// print the map of permutations created above.
		permsMap.forEach((x,y) -> {
			ReadmeFileCreator.readme.write("[" + x + "]:: PERMUTATIONS ::  \n" );
			y.forEach(z -> {
				ReadmeFileCreator.readme.write("- " + z + "  \n");
			});
		});
		
		readme.close();
	}
}
