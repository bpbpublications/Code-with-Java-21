package chapter4;

public class CoordinateArrays {

	private static String [][] gameGrid = new String[5][5];
	
	public static void main(String[] args) {
		
		gameGrid[3][2] = "Byorki - Ranger";
		gameGrid[3][3] = "K'lar - Fighter";
		gameGrid[4][3] = "Tyrenni - Wizard";
		gameGrid[1][3] = "Athena - Rogue";
		gameGrid[0][1] = "Jarrod - Cleric";
		
		System.out.println(gameGrid[4][3]);
		printGrid();

		// move player Athena from 1,3 to 1,4
		movePlayer('N', 1, 3);
		printGrid();
		
	}

	private static void printGrid() {
		
		System.out.println();
		
		for (int indexY = 4; indexY > -1; indexY--) {
			// print column axis key
			System.out.print(indexY);
			for (int indexX = 0; indexX < 5; indexX++) {
				if ((gameGrid[indexX][indexY] == null) || (gameGrid[indexX][indexY].equals(""))) {
					// print a square character
					System.out.print((char)9634);
				} else {
					// prints first character in hero's name
					System.out.print(gameGrid[indexX][indexY].charAt(0));
				}
			}
			System.out.println();
		}
		// print row axis key
		System.out.println(" 01234");
	}
	
	private static void movePlayer(char direction, int currentX, int currentY) {
		
		int newX = currentX;
		int newY = currentY;
		
		switch (Character.toUpperCase(direction)) {
		case 'N':
			// north
			newY++;
			break;
		case 'S':
			// south
			newY--;
			break;
		case 'W':
			// west
			newX--;
			break;
		default:
			// east
			newX++;
		} 
		
		String hero = gameGrid[currentX][currentY];
		
		// move player
		gameGrid[newX][newY] = hero;
		
		// erase old location
		gameGrid[currentX][currentY] = "";
		
		System.out.println("Moved " + hero + " from (" + currentX + "," + currentY
				 + ") to " + "(" + newX + "," + newY + ")");
	}
}
