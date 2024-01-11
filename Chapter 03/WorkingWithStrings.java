package chapter3;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class WorkingWithStrings {

	public static void main(String[] args) {

		String email = "victoria.ploetz@largecompany.com";
		
		// get string positions
		int dotPos = email.indexOf('.');
		int atPos = email.indexOf('@');
		int dot2Pos = email.indexOf('.', atPos + 1);
		
		String firstName = email.substring(0, dotPos);
		String lastName = email.substring(dotPos + 1, atPos);
		String company = email.substring(atPos + 1, dot2Pos);
		
		firstName = properCase(firstName);
		lastName = properCase(lastName);
		
		System.out.println("First name: " + firstName);
		System.out.println("Last name: " + lastName);
		System.out.println("Company: " + company);
		System.out.println();
		
		String messedUpEmail = "bObJoNeS@BIGGERCOMPANY.com";
		
		System.out.println("messedUpEmail.toLower() = " +
				messedUpEmail.toLowerCase());
		System.out.println();
		
		if (firstName.equals("Victoria")) {
			System.out.println("Your name is Victoria!");
		} else {
			System.out.println("Sorry, your name is NOT Victoria.");
		}

		System.out.println();

		String email2 = "khadiya8821@mnsu.edu";
		
		if (isBusinessEmail(email)) {
			System.out.println(email + " is valid!");
		} else {
			System.out.println(email + " is not valid!");			
		}

		if (isBusinessEmail(email2)) {
			System.out.println(email2 + " is valid!");
		} else {
			System.out.println(email2 + " is not valid!");			
		}

		System.out.println();

		if (lastName.toUpperCase().startsWith("PL")) {
			System.out.println(firstName + "'s last name of "
					+ lastName + " does indeed start with Pl.");
			System.out.println();
		}
		
		String phoneNumber = "444-867-5309";
		String phoneNumber2 = "444-188-2300";
		
		if (phoneNumber.contains("-188-")) {
			System.out.println(phoneNumber + " is a valid 188 number!");
		}

		if (phoneNumber2.contains("-188-")) {
			System.out.println(phoneNumber2 + " is a valid 188 number!");
		}

		System.out.println();

		Pattern phone188Pattern = Pattern.compile("[0-9]{3}\\-188\\-[0-9]{4}");
		Matcher phone188Matcher = phone188Pattern.matcher(phoneNumber);
		Matcher phone188Matcher2 = phone188Pattern.matcher(phoneNumber2);

		if (phone188Matcher.find()) {
			System.out.println(phoneNumber + " is a valid 188 number! (regex)");
		}

		if (phone188Matcher2.find()) {
			System.out.println(phoneNumber2 + " is a valid 188 number! (regex)");
		}

		System.out.println();

		Pattern validPhonePattern = Pattern.compile("[0-9]{3}\\-[0-9]{3}\\-[0-9]{4}");
		Matcher phoneMatcher = validPhonePattern.matcher(phoneNumber);
		Matcher phoneMatcher2 = validPhonePattern.matcher(phoneNumber2);
		
		if (phoneMatcher.find()) {
			System.out.println(phoneNumber + " is a valid phone number! (regex)");
		}

		if (phoneMatcher2.find()) {
			System.out.println(phoneNumber2 + " is a valid phone number! (regex)");
		}
		
		String nickname = "Toria";
		String nickname2 = "Vicky";
		String robert = "Robert";
		String nickname3 = "Rob";
		String nickname4 = "Bob";
		
		System.out.println("\nBob pattern:");
		Pattern bobPattern = Pattern.compile("[B|R]ob");

		matchName(bobPattern, nickname);
		matchName(bobPattern, nickname2);
		matchName(bobPattern, nickname3);
		matchName(bobPattern, nickname4);
		matchName(bobPattern, robert);
		matchName(bobPattern, firstName);

		System.out.println("\nVictoria pattern:");
		Pattern victoriaPattern = Pattern.compile("[Vic|][[T|t]oria]");

		matchName(victoriaPattern, nickname);
		matchName(victoriaPattern, nickname2);
		matchName(victoriaPattern, nickname3);
		matchName(victoriaPattern, nickname4);
		matchName(victoriaPattern, robert);
		matchName(victoriaPattern, firstName);		
	}
	
	private static void matchName(Pattern pattern, String name) {
		
		Matcher matcher = pattern.matcher(name);
		
		if (matcher.find()) {
			System.out.println("Match found!  Welcome " + name + "!");
		} else {
			System.out.println("Sorry " + name + ", no match found.");
		}
	}
	
	private static String properCase(String name) {
		// grab the first character and convert it to uppercase
		char firstLetter = Character.toUpperCase(name.charAt(0));
		
		// return firstLetter concatenated with the substring of name from position 1 through to the end.
		return firstLetter + name.substring(1);
	}
	
	private static boolean isBusinessEmail(String email) {
		
		boolean validEmail = true;
		
		if (email.endsWith("@gmail.com")) {
			validEmail = false;
		} else if (email.endsWith(".edu")) {
			validEmail = false;
		}
		
		return validEmail;
	}
}
