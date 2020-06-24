package org.fross.mkpass;

public class Help{

	static void Display(String ver,String copyright) {
		System.out.println("mkpass: A Simple Password Generator");
		System.out.println("Version " + ver);
		System.out.println(copyright + "\n");
		
		System.out.println("Usage:  mkpass [-l <length>] [-p] [-n <number>] [-s] [-D]");
		System.out.println("  -l [len]    Length: Set password length. Default: 30 characters");
		System.out.println("  -p          Plain:  Do not include special characters");
		System.out.println("  -c [chars]  Custom: Use these symbols instead of standard ones");
		System.out.println("  -n [num]    Number: Generate num passwords");
		System.out.println("  -s          Show:   Display the symbols included in the password");
		System.out.println("  -D          Debug:  Used by dev to debug program");
		System.out.println("  -h | -?     Help:  Show this help information");
		
		System.out.println("\nNote: If mkpass seems to hang, install 'rng-tools'\n");
	}
}
