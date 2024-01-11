package chapter3;

import java.nio.charset.Charset;
import java.util.Scanner;

public class FunWithCharacterCodes {

	public static void main(String[] args) {

		String strA = "A";
		String strB = "B";
		String strC = "C";
		
		char upperA = 'A';
		char upperB = 'B';
		char upperC = 'C';

		System.out.println(strA + strA);
		System.out.println(strB);
		System.out.println(strC);

		System.out.println(upperA + upperA);
		System.out.println(upperB);
		System.out.println(upperC);

		System.out.println(Charset.defaultCharset());
		
		// read character from keyboard
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("\nEnter a single character.");
		String inputStr = inputScanner.nextLine();
		char inputChar = inputStr.charAt(0);
		inputScanner.close();
		
		if (inputChar >= 'A' && inputChar < 'L') {
			System.out.println("Character is between A and K.");
		} else if (inputChar >= 'L' && inputChar < 'R') {
			System.out.println("Character is between L and Q.");
		} else if (inputChar >= 'R' && inputChar < 'Z') {
			System.out.println("Character must be between R and Z.");
		} else if (inputChar >= 'a' && inputChar < 'l') {
			System.out.println("Character is between a and k.");
		} else if (inputChar >= 'l' && inputChar < 'r') {
			System.out.println("Character is between l and q.");
		} else {
			System.out.println("Character must be between r and z.");
		}
		
		System.out.println(inputChar + " = " + (int)inputChar);
	}

}
