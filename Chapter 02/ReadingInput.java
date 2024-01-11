package chapter2;

import java.util.Scanner;

public class ReadingInput {

	public static void main(String[] args) {
		System.out.print("Enter a number between 31 and 256: ");

		Scanner inputScanner = new Scanner(System.in);
		String inputStr = inputScanner.nextLine();
		inputScanner.close();

		try {
			int number = Integer.parseInt(inputStr);
			
			//if (number > 31 && number < 256) {
			if (number > 31) {
				System.out.printf("The character for ASCII code %d is %c", number, (char) number);
			} else {
				System.out.println("Sorry, only numbers 32 or higher are permitted.");
			}
		} catch (NumberFormatException ex) {
			System.out.println("Sorry, only numbers are permitted.");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
