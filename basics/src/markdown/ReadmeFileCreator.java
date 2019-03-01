package markdown;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * The ReadmeFileCreator class is in charge of managing the readme file in markdown format.
 * 
 * @author Carlos de la Fuente Antequera
 *
 */
public class ReadmeFileCreator {

	/**
	 * These start and end tags are for keeping spaces in the markdown output file
	 */
	public static final String START_KEEPING_SPACES = "  \n<pre>  \n";
	public static final String END_KEEPING_SPACES = "  \n</pre>  \n";
	
	public static PrintWriter readme = null;
	
	public ReadmeFileCreator(String path) throws FileNotFoundException, UnsupportedEncodingException {
		readme = new PrintWriter(path, "UTF-8");
		readme.write("Output:");
		readme.write(START_KEEPING_SPACES);
	}

	public void close() {
		readme.write(END_KEEPING_SPACES);
		readme.close();
	}
}
