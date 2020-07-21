package org.fross.mkpass;

public class Help{

	static void Display(String ver,String copyright) {
		System.out.println("mkpass: A Simple Password Generator");
		System.out.println("Version " + ver);
		System.out.println(copyright + "\n");
		
		System.out.println("Usage:  java -jar mkpass.jar");
		System.out.println("  -l [len]    Length: Set password length. Default: 30 characters");
		System.out.println("  -p          Plain:  Do not include special characters");
		System.out.println("  -c [chars]  Custom: Use these special characters instead of the standard ones");
		System.out.println("  -n [num]    Number: Generate num passwords");
		System.out.println("  -s          Show:   Display the list of symbols used");
		System.out.println("  -D          Debug:  Used by dev to debug program");
		System.out.println("  -h | -?     Help:  Show this help information");
		
		System.out.println("\nNote:\nThere is a known issue where /dev/random blocks until");
		System.out.println("it has enough entropy to produce a secure random number.");
		System.out.println("Install 'rng-tools' to work around this issue.\n");
	}
}
