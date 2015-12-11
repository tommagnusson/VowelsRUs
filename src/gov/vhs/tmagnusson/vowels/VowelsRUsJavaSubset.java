package gov.vhs.tmagnusson.vowels;

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

	private static void parse(String[] testData) {
		for(String line: testData) {
			
			String word = line.split(" ")[0];
			String suffix = line.split(" ")[1];
			
			String conjugatedPlural = pluralize(word);
			String conjugatedSuffix = suffixize(word, suffix);
			outputCongugates(line, conjugatedPlural, conjugatedSuffix);
		}
	}

	private static String pluralize(String word) {
		boolean endsInDoubles = endsInDoubles(word);
		return null;
	}

	private static boolean endsInDoubles(String word) {
		int lastIndex = word.length() - 1;
		String last = word.substring(lastIndex, lastIndex - 1);
		String penultimate = word.substring(lastIndex - 1, lastIndex);
		boolean lastIsVowel = isVowel(last);
		boolean	penultimateIsVowel = isVowel(penultimate);
		
		if (lastIsVowel && penultimateIsVowel) {
			return true;
		}
		if (!lastIsVowel && !penultimateIsVowel) {
			return true;
		}
		
		return false;
	}

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
	}

}
