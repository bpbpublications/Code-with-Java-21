package chapter3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CharacterArt {

	public static void main(String[] args) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/commandKeyCodes.txt"));
			
			// read the first line
			String dataLine = reader.readLine();
			
			while (dataLine != null) {
				String[] data = dataLine.split(",");
				
				for (String strNumber : data) {
					int number = Integer.parseInt(strNumber);
					
					System.out.print((char)number);
				}
				
				System.out.println();
				
				dataLine = reader.readLine();
			}
			
			reader.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
