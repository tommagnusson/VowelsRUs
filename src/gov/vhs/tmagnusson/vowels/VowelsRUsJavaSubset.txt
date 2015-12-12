package gov.vhs.tmagnusson.vowels;

/* Author: Tommy Magnusson
 * 
 * Planet ACLS's vowels are only a, c, l, and s.
 * The program finds the plural and suffix conjugates
 * of words based on a complicated chart on the
 * assignment page.
 * 
*/

public class VowelsRUsJavaSubset {

	// ACSL
	
	public static void main(String[] args) {
		String vowels = "XQAC ZVM\n"
				+ "PDAE AV\n"
				+ "SNIC SY\n"
				+ "AAAB BB\n"
				+ "BBBC DD\n"
				+ "ABAC CC\n"
				+ "ABAC DD\n"
				+ "James Gosling\n"
				+ "JAMES GOSLING\n"
				+ "Rubiks Cube\n"
				+ "RUBIKS CUBE\n"
				+ "BBBACSL ENDING\n"
				+ "AAABDEFGH ENDING\n";
		String[] testData = vowels.split("\n");
		parse(testData);
	}

	// Finds plural and suffix conjugates, outputs the data
	private static void parse(String[] testData) {
		for(String line: testData) {
			
			// Each line contains a word and suffix
			String word = line.split(" ")[0];
			String suffix = line.split(" ")[1];
			
			String conjugatedPlural = pluralize(word);
			String conjugatedSuffix = suffixize(word, suffix);
			outputCongugates(line, conjugatedPlural, conjugatedSuffix);
		}
	}

	// Creates the suffix conjugate based on the word and suffix
	private static String suffixize(String word, String suffix) {
		boolean startsWithConsonant = startsWithConsonant(suffix);
		String firstLetter = suffix.substring(0, 1);
		
		// Outer if conditionals pertain to the word.
		// Inner if conditionals pertain to the suffix.
		if (endsInDoubles(word)) {
			if (startsWithConsonant) {
				// Drop leftmost letter of final
				// sequence of either vowels or
				// consonants, then add suffix
				int i = findLeftmostLetterIndex(word);
				return removeAtIndex(word, i) + suffix;
			} else /* suffix starts with vowel */ {
				// Add the first letter of the suffix,
				// then add the suffix.
				return word + firstLetter + suffix;
			}
		}
		if (endsInVowel(word)) {
			if(startsWithConsonant) {
				// Add the first letter of the suffix
				// and then add suffix
				return word + firstLetter + suffix;
			} else /* suffix starts with vowel */ {
				// Drop the first letter of the suffix
				// and add the rest of the suffix
				String uffix = suffix.substring(1);
				return word + uffix;
			}
		} else /* Word ends in consonant*/ {
			// Add the suffix (for both cases)
			return word + suffix;
		}
	}

	// Removes the letter at the given index in String word.
	// Returns the "purged" String
	private static String removeAtIndex(String word, int i) {
		String pre = word.substring(0, i);
		String post = word.substring(i + 1);
		return pre + post;
	}
	
	// Scans a word right to left. Returns the index
	// of the leftmost letter that is like its previous
	// letters (consonant or vowel).
	private static int findLeftmostLetterIndex(String word) {
		// The value of the boolean itself is irrelevant.
		// It's checked against every letter to find the
		// next "unlike" letter.
		boolean previousIsVowel = isVowel(word.substring(word.length() - 1));
		for (int i = word.length() - 1; i >= 0; i--) {
			String letter = String.valueOf(word.charAt(i));
			if (isVowel(letter) != previousIsVowel) {
				// Found the next "unlike" letter.
				// Return the previous index.
				return i + 1;
			}
		}
		// The whole word consists of the same
		// type of letter (consonant or vowel).
		// Therefore the leftmost letter is the
		// first (index 0).
		return 0;
	}

	private static boolean startsWithConsonant(String suffix) {
		return !isVowel(suffix.substring(0, 1));
	}

	// Returns the plural conjugate of the word based
	// on the rules on the assignment table chart.
	private static String pluralize(String word) {
		if (endsInDoubles(word)) {
			// Double last letter, then add "H"
			String lastLetter = word.substring(word.length() - 1);
			return word + lastLetter + "H";
		}
		if (endsInVowel(word)) {
			// Drop the final vowel and add "G"
			word = word.substring(0, word.length() - 1);
			return word + "G";
		} else /* word ends in consonant */ {
			// Add "GH"
			return word + "GH";
		}
	}

	private static boolean endsInVowel(String word) {
		return isVowel(word.substring(word.length() - 1));
	}

	// Find out if the word given ends in double consonants
	// or double vowels.
	private static boolean endsInDoubles(String word) {
		int lastIndex = word.length() - 1;
		String last = word.substring(lastIndex);
		String penultimate = word.substring(lastIndex - 1, lastIndex);
		
		boolean lastIsVowel = isVowel(last);
		boolean	penultimateIsVowel = isVowel(penultimate);
		
		// If both are true or both are false return true, otherwise false
		// Whoops idk if this ^ is in the subset...
		// But you love boolean math right?!
		return !(lastIsVowel ^ penultimateIsVowel);
	}

	// Checks if the letter given is one of the
	// vowels (a, c, l, s) regardless of case.
	private static boolean isVowel(String letter) {
		return (letter.equalsIgnoreCase("a") 
				|| letter.equalsIgnoreCase("c")
				|| letter.equalsIgnoreCase("l")
				|| letter.equalsIgnoreCase("s") );
	}

	private static void outputCongugates(String line, String plural, String suffix) {
		System.out.println("Original: " + line);
		System.out.println("Plural: " + plural);
		System.out.println("Suffix: " + suffix);
		System.out.println();
	}

}
