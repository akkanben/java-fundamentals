import java.util.Random;

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

		System.out.println("~FLIPPING COINS~");
		flipNHeads(3);
	}

	public static String pluralize(String str, int num) {
		boolean isPlural = num > 1 || num == 0;
		return isPlural ? str + "s" : str;	
	}

	public static void flipNHeads(int n) {
		Random rand = new Random();
		int headCount = 0;
		int flips = 0;
		while(headCount < n) {
			flips++;
			if (rand.nextFloat() >= 0.5) { // nextFloat() gives us a float beteen 0.0 and 1.0;
				headCount++;
				System.out.println("heads");
			} else {
				headCount = 0;
				System.out.println("tails");
			}
		}
		System.out.println("It took " + flips + " flips to flip " + n + " heads in a row.");
	}
}
