/*
 * mkpass: Generate Secure Passwords on the command line
 * 
 * This code is directly from the Wikipedia article:
 * https://en.wikipedia.org/wiki/Random_password_generator
 * 
 * I've made some usability enhancements however to make it easier to use for me.
 * 
 */

package org.fross.mkpass;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import gnu.getopt.Getopt;

/**
 * Main(): Main Execution Loop
 * 
 * @author Michael
 *
 */
public class Main {
	// Class Constants
	public static String VERSION;
	public static String COPYRIGHT;
	public static final String PROPERTIES_FILE = "app.properties";
	private static boolean debugMode = false;

	/**
	 * Main(): Program entry point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int optionEntry = 0;
		int pwLen = 30;
		boolean useSpecialChars = true;
		boolean showSymbols = false;
		int numberToGenerate = 1;
		String customSymbols = "";

		// Process application level properties file
		// Update properties from Maven at build time:
		// https://stackoverflow.com/questions/3697449/retrieve-version-from-maven-pom-xml-in-code
		try {
			InputStream iStream = Main.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
			Properties prop = new Properties();
			prop.load(iStream);
			VERSION = prop.getProperty("Application.version");
			COPYRIGHT = "Copyright " + prop.getProperty("Application.inceptionYear") + "-" + java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
					+ " by Michael Fross.  All rights reserved";

		} catch (IOException ex) {
			System.out.println("Unable to read property file '" + PROPERTIES_FILE + "'");
			System.exit(1);
		}

		// Process Command Line Options and set flags where needed
		Getopt optG = new Getopt("mkpass", args, "Dl:n:pshc:?");
		while ((optionEntry = optG.getopt()) != -1) {
			switch (optionEntry) {
			case 'D':
				debugMode = true;
				break;

			case 'l':
				try {
					pwLen = Integer.valueOf(optG.getOptarg());
				} catch (NumberFormatException ex) {
					System.out.println("ERROR: Invalid length parameter: '" + optG.getOptarg() + "'");
					System.out.println("Use -h switch for help information");
					System.exit(0);
				}
				break;

			case 'n':
				try {
					numberToGenerate = Integer.valueOf(optG.getOptarg());
				} catch (NumberFormatException ex) {
					System.out.println("ERROR: Invalid number parameter: '" + optG.getOptarg() + "'");
					System.out.println("Use -h switch for help information");
					System.exit(0);
				}
				break;

			case 'p':
				useSpecialChars = false;
				break;

			case 's':
				showSymbols = true;
				break;

			case 'c':
				customSymbols = optG.getOptarg();
				break;

			case '?': // Help
			case 'h':
				Help.Display(VERSION, COPYRIGHT);
				System.exit(0);
				break;

			default:
				System.out.println("ERROR: Unknown Command Line Option: '" + (char) optionEntry + "'");
				System.out.println("Use -h for help information");
				System.exit(0);
				break;
			}
		}

		// Generate and display the password
		for (int i = 0; i < numberToGenerate; i++) {
			String pw = generatePW(pwLen, useSpecialChars, showSymbols, customSymbols);
			System.out.println(pw);
		}
	}

	/**
	 * generatePW(): Generate and return the secure password
	 * 
	 * @param pwLen
	 * @param useSpecialChars
	 * @return
	 */
	public static String generatePW(int pwLen, boolean useSpecialChars, boolean showSymbols, String customSymb) {
		Random random = null;

		String[] standardSymbols = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
				"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
				"R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		String[] specialSymbols = { "!", "@", "#", "$", "%", "^", "&", "*", "~", "-", "+", "_", "=" };
		String[] pwSymbols;

		// If custom symbols were entered, use those instead of the built in ones defined above
		if (!customSymb.isEmpty()) {
			specialSymbols = customSymb.split("");
		}

		// Determine if we are building with special characters and build symbol array we'll use.
		if (useSpecialChars == true) {
			// Concatenate the two string arrays
			pwSymbols = Arrays.copyOf(standardSymbols, standardSymbols.length + specialSymbols.length);
			System.arraycopy(specialSymbols, 0, pwSymbols, standardSymbols.length, specialSymbols.length);
		} else {
			// Just use the standard symbols
			pwSymbols = standardSymbols.clone();
		}

		// Display debugging information before password generation
		if (debugMode == true) {
			System.out.println("\n\nPassword Length: " + pwLen);
			System.out.println("Use Special Characters: " + useSpecialChars + "\n");
			System.out.println("Ruler:   1         2         3         4         5         6         7");
			System.out.println("1234567890123456789012345678901234567890123456789012345678901234567890");
		}

		if (showSymbols == true) {
			System.out.print("These " + pwSymbols.length + " symbols used in this password generation:");
			for (int j = 0; j < pwSymbols.length; j++) {
				if (j % 10 == 0)
					System.out.println();
				System.out.print(pwSymbols[j] + " ");
			}
			System.out.println("\n");
		}

		try {
			random = SecureRandom.getInstanceStrong();    // as of JDK 8, this should return the strongest algorithm available to the JVM
		} catch (Exception ex) {
			System.out.println("ERROR:  Issue getting random PW from SecureRandom\n" + ex.getMessage());
		}

		// Loop through the number of password characters and get a random item from the symbol list
		StringBuilder sb = new StringBuilder(pwLen);
		for (int i = 0; i < pwLen; i++) {
			int indexRandom = random.nextInt(pwSymbols.length);
			sb.append(pwSymbols[indexRandom]);
		}

		return (sb.toString());
	} // END METHOD

} // END CLASS