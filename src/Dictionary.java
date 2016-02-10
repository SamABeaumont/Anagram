import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public final class Dictionary {
	public static final boolean DEBUG = true;
	
	private static boolean created = false;
	
	private ArrayList<CanonicalWord> canonicalWords;
	
	public Dictionary() {
		if (created) {
			throw new IllegalStateException("Only one instance of the Dictionary class may exist"
					+ " at any given time.");
		}
		log("Began executing constructor.");
		long startTime = System.currentTimeMillis();
		Scanner dict = null;
		boolean isCanonical = false;
		if (new File("canon.txt").exists()) {
			try {
				dict = new Scanner(new File("canon.txt"));
			} catch (FileNotFoundException f) {} // already called exists(), will not execute
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
			log("Executing canonical block.");
			dict.useDelimiter("[(\\r\\n)\\n]{2}");
			while (dict.hasNext()) {
				String canon = dict.next();
				Scanner sc = new Scanner(canon);
				CanonicalWord w = new CanonicalWord(sc.nextLine());
				while (sc.hasNextLine()) {
					w.add(sc.nextLine());
				}
				sc.close();
				canonicalWords.add(w);
			}
		} else {
			log("Executing non-canonical block.");
			while (dict.hasNextLine()) {
				String input = dict.nextLine();
				log("Reading input: " + input);
				int index = Collections.binarySearch(canonicalWords,
						new CanonicalWord(input));
				if (index > 0) {
					canonicalWords.get(index).add(input);
				} else {
					canonicalWords.add(-(index + 1), new CanonicalWord(input));
				}
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println(((double) (endTime - startTime)) / 1000);
		created = true;
	}
	
	public void printAnagrams (String word) {
		int index = Collections.binarySearch(canonicalWords, new CanonicalWord(word));
		if (index < 0) {
			
		}
	}
	
	public void save(PrintStream out) {
		for (CanonicalWord w : canonicalWords) {
			w.print(out, true);
		}
	}
	
	public static void log(String text) {
		if (DEBUG) System.out.println(text);
	}
}
