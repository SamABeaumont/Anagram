import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

public final class Main {	
	public static void main (String[] args) throws InterruptedException {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the anagram finder.");
		System.out.print("Loading dictionary...");
		Dictionary dict = new Dictionary();
		while (input.hasNext()) {
			if (input.equals("-quit")) {
				input.close();
				if (!new File("canon.txt").exists()) {
					System.out.println("Saving dictionary file...");
					try {
						dict.save(new PrintStream(new File("canon.txt")));
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, "Unable to load dictionary file.", "",
								JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
						System.exit(1);
					}
				}
				System.exit(0);
			} else {
				
			}
		}
	}
}
