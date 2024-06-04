/* ------------------------------------------------------------------------------
 * RPNCalc
 *
 * RPNCalc is is an easy to use console based RPN calculator
 *
 *  Copyright (c) 2011-2024 Michael Fross
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
 * ------------------------------------------------------------------------------*/
package org.fross.mkpass;

import com.beust.jcommander.Parameter;

public class CommandLineArgs {
   // ---------------------------------------------------------------------------------------------
   // Define command line options that can be used
   // ---------------------------------------------------------------------------------------------

   @Parameter(names = {"-l", "--length"}, description = "Password length")
   protected int clLength = 30;

   @Parameter(names = {"-p", "--plain"}, description = "Do not include special characters")
   protected boolean clPlain = false;

   @Parameter(names = {"-c", "--chars"}, description = "Use these special characters instead of the standard ones")
   protected String clChars = "";

   @Parameter(names = {"-n", "--number"}, description = "Number of passwords to generate")
   protected int clNumber = 1;

   @Parameter(names = {"-s", "--show"}, description = "Display the list of numbers/symbols used in the passwords generation")
   protected boolean clShow = false;

   @Parameter(names = {"-D", "--debug"}, description = "Turn on Debug mode to display extra program information")
   protected boolean clDebug = false;

   @Parameter(names = {"-v", "--version"}, description = "Show current program version and latest release on GitHub")
   protected boolean clVersion = false;

   @Parameter(names = {"-h", "-?", "--help"}, help = true, description = "Display mkpass help and exit")
   protected boolean clHelp = false;

}