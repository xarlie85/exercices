package basics.bitwise;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.stream.Stream;

/**
 * The class AciiCaseSwitch shows how to change case by bitwise operations.
 * 
 * @author Carlos de la Fuente Antequera
 *
 */
public class AciiCaseSwitch {
	
	/**
	 * These start and end tags are for keeping spaces in the markdown output file
	 */
	private static final String startKeepingSpaces = "<pre>";
	private static final String endKeepingSpaces = "</pre>";
	
	private static PrintWriter readme = null;
	
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
		readme.write("[" + c + "] To lower case:  \n");
		readme.write("  " + bin(c) + "  \n|  " + bin(mask) + "  \n  " + bin(r) + "  \n\n" );
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
		readme.write("[" + c + "] To upper case:  \n");
		readme.write("  " + bin(c) + "  \n& " + bin(mask) + "  \n  " + bin(r) + "  \n\n");
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		readme = new PrintWriter("src/basics/bitwise/README.md", "UTF-8");
		readme.write(startKeepingSpaces);
		//iteration over all the ascii uppper case chars
		Stream.iterate(65, (d) -> d + 1).limit(26).forEach((d) -> toLowerCase((char) d.intValue()));
		
		//iteration over all the ascii lower case chars
		Stream.iterate(97, (d) -> d + 1).limit(26).forEach((d) -> toUpperCase((char) d.intValue()));
		
		// non-ascii char: ñ
		char c = 'ñ';
		toUpperCase(c); // not working properly!
		
		readme.write(endKeepingSpaces);
		readme.close();
	}

}
