package chapter2;

public class HelloWorld {
    private static String firstName = "Aaron";
    private static int age = 47;
	
	public static void main(String[] args) {
		System.out.println("Hello world!");
		System.out.printf("Welcome to the world of Java, %s!" + System.lineSeparator(), firstName);
		System.out.printf("age = %d.  It's never too late to learn Java!" + System.lineSeparator(), age);
	}
}
