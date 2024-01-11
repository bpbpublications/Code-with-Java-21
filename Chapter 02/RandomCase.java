package chapter2;

import java.util.Random;

public class RandomCase {

	public static void main(String[] args) {
		Random random = new Random();
		boolean fourFound = false;
		//boolean fourFound = true;
		
		//for (int counter = 0; counter < 10; counter++) {
		while (!fourFound) {
		// do {
			int rndNumber = random.nextInt(5) + 1;
			
			switch(rndNumber) {
			case 1:
				System.out.println("One");
				break;
			case 2:
				System.out.println("Two");
				break;
			case 3:
				System.out.println("Three");
				break;
			case 4:
				System.out.println("Four");
				fourFound = true;
				break;
			case 5:
				System.out.println("Five");
				break;
			default:
				System.out.println("Something goofy happened here...");			
			}
		}//  while (!fourFound);
	}

}
