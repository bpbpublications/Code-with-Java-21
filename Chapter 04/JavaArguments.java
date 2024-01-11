package chapter4;

public class JavaArguments {

	public static void main(String[] args) {
		
		System.out.println("You have supplied " + args.length + " arguments:");
		
		for (int index = 0; index < args.length; index++) {
		//for (int index = args.length - 1; index > -1; index--) {
			System.out.printf("Argument #%d:",index);
			System.out.println(args[index]);
		}
	}
}
