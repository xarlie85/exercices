package basics.bitwise;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.stream.Stream;

import markdown.ReadmeFileCreator;

/**
 * The class AciiCaseSwitch shows how to change case by bitwise operations.
 * 
 * @author Carlos de la Fuente Antequera
 *
 */
public class AciiCaseSwitch {
	
	private static String bin(char c) {
		return Integer.toBinaryString(c) + "(" + (int) c + ") --> [" + c + "]";
	}

	/**
	 * to lower case.
	 * shows how the operation is performed at bitwise level
	 * 
	 * @param c the character to switch the case from
	 */
	public static void toLowerCase(char c) {
		char mask = ' ';
		char r = (char) (c | mask);
		ReadmeFileCreator.readme.write("[" + c + "] To lower case:  \n");
		ReadmeFileCreator.readme.write("  " + bin(c) + "  \n|  " + bin(mask) + "  \n  " + bin(r) + "  \n\n" );
	}

	/**
	 * to upper case.
	 * shows how the operation is performed at bitwise level
	 * 
	 * @param c the character to switch the case from
	 */
	private static void toUpperCase(char c) {
		Character mask = '_';
		char r = (char) (c & mask);
		ReadmeFileCreator.readme.write("[" + c + "] To upper case:  \n");
		ReadmeFileCreator.readme.write("  " + bin(c) + "  \n& " + bin(mask) + "  \n  " + bin(r) + "  \n\n");
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		ReadmeFileCreator readmeFileCreator = new ReadmeFileCreator("src/basics/bitwise/README.md");
		
		//iteration over all the ascii uppper case chars
		Stream.iterate(65, (d) -> d + 1).limit(26).forEach((d) -> toLowerCase((char) d.intValue()));
		
		//iteration over all the ascii lower case chars
		Stream.iterate(97, (d) -> d + 1).limit(26).forEach((d) -> toUpperCase((char) d.intValue()));
		
		// non-ascii char: ñ
		char c = 'ñ';
		toUpperCase(c); // not working properly!
		
		readmeFileCreator.close();
		
	}

}
