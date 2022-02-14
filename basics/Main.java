

public class Main {
	public static void main(String[] args) {
		// Pluralize Tests
		System.out.println("~PLURALIZE~");
		int dogCount = 1;
		System.out.println("I own " + dogCount + " " + pluralize("dog", dogCount) + ".");
		int catCount = 2;
		System.out.println("I own " + catCount + " " + pluralize("cat", catCount) + ".");
		int turtleCount = 0;
		System.out.println("I own " + turtleCount + " " + pluralize("turtle", turtleCount) + ".");
		System.out.print("\n\n");
	}

	public static String pluralize(String str, int num) {
		boolean isPlural = num > 1 || num == 0;
		return isPlural ? str + "s" : str;	
	}

	public static void flipNHeads(int n) {

	}
}
