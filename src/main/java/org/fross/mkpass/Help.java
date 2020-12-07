/******************************************************************************************
 * mkpass: Generate Secure Passwords on the command line
 * 
 * This code is based on the following Wikipedia article:
 * https://en.wikipedia.org/wiki/Random_password_generator
 * 
 * I've made some usability enhancements however to make it easier to use for me.
 * 
 *  Copyright (c) 2020 Michael Fross
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

public class Help {

	static void Display(String ver, String copyright) {
		System.out.println("mkpass: A Simple Password Generator");
		System.out.println("Version " + ver);
		System.out.println(copyright + "\n");

		System.out.println("Usage:  java -jar mkpass.jar");
		System.out.println("  -l length   Length: Set password length. Default: 30 characters");
		System.out.println("  -p          Plain:  Do not include special characters");
		System.out.println("  -c chars    Custom: Use these special characters instead of the standard ones");
		System.out.println("  -n number   Number: Generate num passwords");
		System.out.println("  -s          Show:   Display the list of symbols used");
		System.out.println("  -D          Debug:  Used by dev to debug program");
		System.out.println("  -v          Display program version and exit");
		System.out.println("  -h | -?     Help:  Show this help information");

		System.out.println("\nNote:\nThere is a known Linux issue where /dev/random blocks until");
		System.out.println("it has enough entropy to produce a secure random number.");
		System.out.println("Install 'rng-tools' to work around this issue.\n");
	}
}
