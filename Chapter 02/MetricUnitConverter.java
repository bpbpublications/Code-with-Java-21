package chapter2;

import java.util.Scanner;

public class MetricUnitConverter {

	public static void main(String[] args) {

		// instructions
		System.out.println("This program converts units of measure (UOM) between metric and imperial units.");
		System.out.println("Valid UOMS: (in, ft, mi, cm, m, km");
		System.out.println("Examples: (14 ft, 5 km)");		
		System.out.print("Enter a number with its UOM: ");

		// get input
		Scanner inputScanner = new Scanner(System.in);
		String inputStr = inputScanner.nextLine();
		inputScanner.close();
		
		// check for space
		int spacePos = inputStr.indexOf(" ");
		
		if (spacePos > 0) {

			try {
				MeasurementValue measurement = new MeasurementValue(inputStr);
				System.out.print(measurement.toString());
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		} else {
			System.out.println("Check your input; there should "
					+ "be a space between the value and the UOM.");
		}
	}

}
