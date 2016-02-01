import java.util.Scanner;

public final class Main {
	public static void main (String[] args) throws InterruptedException {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the anagram finder.");
		System.out.print("Loading dictionary...");
		while (input.hasNext()) {
			if (input.equals("-quit")) {
				System.out.println("Saving dictionary files...");
				System.exit(0);
			} else {
				
			}
		}
	}
}
