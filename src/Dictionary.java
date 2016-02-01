import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public final class Dictionary {
	private static ArrayList<CanonicalWord> canonicalWords;
	
	static {
		Scanner dict = null;
		boolean isCanonical = false;
		if (new File("canon.txt").exists()) {
			try {
				dict = new Scanner(new File("canon.txt"));
			} catch (FileNotFoundException f) {}
			isCanonical = true;
		} else {
			try {
				dict = new Scanner(new File("dict.txt"));
			} catch (FileNotFoundException e) {
				System.out.println("Could not load dictionary file.");
				System.exit(1);
			}
		}
		
		if (isCanonical) {
			dict.useDelimiter("[(\\r\\n)\n]{2}");
			while (dict.hasNext()) {
				String canon = dict.next();
				Scanner sc = new Scanner(canon);
				CanonicalWord w;
				while (sc.hasNextLine()) {
					w = new CanonicalWord(sc.nextLine());
				}
				sc.close();
			}
		} else {
			while (dict.hasNextLine()) {
				String input = dict.nextLine();
				int index = Collections.binarySearch(canonicalWords,
						new CanonicalWord(input));
			}
		}
	}
	
	public void print(PrintStream out) {
		for (CanonicalWord w : canonicalWords) {
			w.print(out);
		}
	}
}
