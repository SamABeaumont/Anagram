import java.io.PrintStream;
import java.util.Arrays;
import java.util.TreeSet;

public class CanonicalWord implements Comparable<CanonicalWord> {
	private char[] chars;
	private TreeSet<String> words;
	
	public CanonicalWord(String word) {
		chars = toChars(word);
		words = new TreeSet<String>();
		words.add(word);
	}
	
	public void add (String word) {
		char[] c = toChars(word);
		if (Arrays.equals(c, chars)) {
			words.add(word);
		}
	}
	
	public char[] toChars (String word) {
		char[] chars = new char[word.length()];
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			for (int j = 0; j < chars.length; j++) {
				if (chars[i] >= c) {
					for (int k = chars.length - 2; k >= j; k--) {
						chars[k + 1] = chars[k];
					}
					chars[i] = c;
				}
			}
		}
		return chars;
	}
	
	public char[] getChars() {
		return chars;
	}
	
	public void print(PrintStream out) {
		for (char c : chars) {
			out.print(c);
		}
		out.println();
		for (String w : words) {
			out.println(w);
		}
		out.println();
	}
	
	@Override
	public int compareTo(CanonicalWord other) {
		String s = "";
		for (int i = 0; i < chars.length; i++) {
			s += chars[i];
		}
		String s2 = "";
		char[] otherChars = other.getChars();
		for (int i = 0; i < other.getChars().length; i++) {
			s2 += otherChars[i];
		}
		return s.compareTo(s2);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof CanonicalWord) {
			CanonicalWord w = (CanonicalWord) other;
			return Arrays.equals(w.chars, chars);
		} else {
			return false;
		}
	}
}
