package chapter2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleFileWorker {

	public static void main(String[] args) {
		try {
			//FileWriter writer = new FileWriter("gamesCatalog.txt");
			//BufferedWriter buffWriter = new BufferedWriter(writer);
			BufferedWriter writer = new BufferedWriter(new FileWriter("gamesCatalog.txt"));
			
			// header
			writer.write("name, company, year\n");
			
			// data
			writer.write("Pitfall!, Activision, 1982\n");
			writer.write("Crackpots, Activision, 1983\n");
			writer.write("Yars' Revenge, Atari, 1981\n");
			writer.write("Warlords, Atari, 1981\n");
			writer.write("Defender, Atari, 1981\n");
			writer.write("Adventure, Atari, 1980\n");
			
			// close file writer
			writer.close();
		} catch (IOException writerEx) {
			System.out.println("Error occurred while writing:");
			writerEx.printStackTrace();
		}

		System.out.println("Writing completed!");
		System.out.println();

		try {
			BufferedReader reader = new BufferedReader(new FileReader("gamesCatalog.txt"));

			// read the first line
			String gameLine = reader.readLine();
			boolean headerRead = false;
			
			while (gameLine !=  null) {

				if (headerRead) {
					String[] gameColumns = gameLine.split(",");
					// String strYear = gameColumns[2].trim();
				    // int year = Integer.parseInt(strYear);
					int year = Integer.parseInt(gameColumns[2].trim());
						
					if (year == 1981) {
						System.out.println(gameLine);
					}
				} else {
					headerRead = true;
				}
				// read the next line
				gameLine = reader.readLine();
			}

			reader.close();
		} catch (IOException readerEx) {
			System.out.println("Error occurred while writing:");
			readerEx.printStackTrace();
		}

	}

}
