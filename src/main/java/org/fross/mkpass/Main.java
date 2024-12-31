/******************************************************************************************
 * mkpass: Generate Secure Passwords on the command line
 *
 * This code is based on the following Wikipedia article:
 * https://en.wikipedia.org/wiki/Random_password_generator
 *
 * I've made some usability enhancements however to make it easier to use for me.
 *
 *  Copyright (c) 2020-2025 Michael Fross
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 ******************************************************************************************/
package org.fross.mkpass;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

/**
 * Main(): Main Execution Loop
 *
 * @author Michael
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
      int pwLen;
      boolean useSpecialChars = true;
      boolean showSymbols = false;
      int numberToGenerate;
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

      // ---- BEGIN Command Line Parsing -------------------------------------------------------------
      CommandLineArgs cli = new CommandLineArgs();
      JCommander jc = new JCommander();

      // JCommander parses the command line
      try {
         jc.setProgramName("mkpass");
         jc = JCommander.newBuilder().addObject(cli).build();
         jc.parse(args);
      } catch (ParameterException ex) {
         System.out.println(ex.getMessage());
         jc.usage();
         System.exit(0);
      }


      // -----------------------------------------------------------------
      // CLI: Debug Switch
      // -----------------------------------------------------------------
      // Debug
      if (cli.clDebug) {
         debugMode = true;
      }

      // Length
      pwLen = cli.clLength;

      // Number of PWs to generate
      numberToGenerate = cli.clNumber;

      // Plain mode - no special characters
      if (cli.clPlain)
         useSpecialChars = false;

      // Show Symbols during execution
      if (cli.clShow)
         showSymbols = true;

      // Custom Characters - use these instead of default special characters
      customSymbols = cli.clChars;

      // Version - show and exit
      if (cli.clVersion) {
         System.out.println("Current mkpass version: " + VERSION);
         System.exit(0);
      }

      // Help
      if (cli.clHelp) {
         Help.Display(VERSION, COPYRIGHT);
         System.exit(0);
      }

      // ---- END Command Line Parsing -------------------------------------------------------------

      // If custom characters are entered and -p (plain) password is selected, show a warning
      if (!customSymbols.isBlank() && !useSpecialChars) {
         System.out.println("\n+----------------------------------------------------------------------+");
         System.out.println("WARNING: -p and -c are not compatible. Custom characters will be ignored");
         System.out.println("+----------------------------------------------------------------------+\n");
         customSymbols = "";
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

      String[] standardSymbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
      String[] specialSymbols = {"!", "@", "#", "$", "%", "^", "&", "*", "~", "-", "+", "_", "="};
      String[] pwSymbols;

      // If custom symbols were entered, use those instead of the built-in ones defined above
      if (!customSymb.isEmpty()) {
         specialSymbols = customSymb.split("");
      }

      // Determine if we are building with special characters and build symbol array we'll use.
      if (useSpecialChars) {
         // Concatenate the two string arrays
         pwSymbols = Arrays.copyOf(standardSymbols, standardSymbols.length + specialSymbols.length);
         System.arraycopy(specialSymbols, 0, pwSymbols, standardSymbols.length, specialSymbols.length);
      } else {
         // Just use the standard symbols
         pwSymbols = standardSymbols.clone();
      }

      // Display debugging information before password generation
      if (debugMode) {
         System.out.println("\n\nPassword Length: " + pwLen);
         System.out.println("Use Special Characters: " + useSpecialChars + "\n");
         System.out.println("Ruler:   1         2         3         4         5         6         7");
         System.out.println("1234567890123456789012345678901234567890123456789012345678901234567890");
      }

      // Show the symbols that will be used to generate the password if desired
      if (showSymbols) {
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
         System.out.println("FATAL ERROR:  Issue getting random PW from Java's SecureRandom\n" + ex.getMessage());
         System.exit(10);
      }

      // Loop through the number of password characters and get a random item from the symbol list
      StringBuilder sb = new StringBuilder(pwLen);
      for (int i = 0; i < pwLen; i++) {
         int indexRandom = random.nextInt(pwSymbols.length);
         sb.append(pwSymbols[indexRandom]);
      }

      return (sb.toString());
   }

} // END MAIN CLASS
