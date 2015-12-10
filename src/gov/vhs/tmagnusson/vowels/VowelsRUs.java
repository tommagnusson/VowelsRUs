package gov.vhs.tmagnusson.vowels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class VowelsRUs {

	// Vowels: a, c, s, l

	private enum Rule {
		ENDS_IN_SINGLE_CONSONANT, ENDS_IN_SINGLE_VOWEL, ENDS_IN_DOUBLE_CONSONANTS_OR_VOWELS;
	}

	private enum Lettering {
		VOWEL, CONSONANT
	}

	public static void main(String[] args) {
		try {
			// testData contains each line of the file specified
			String[] testData = getTestDataFromFile("vowels.txt");
			parse(testData);
		} catch (FileNotFoundException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private static void parse(String[] testData) {
		for (String line : testData) {
			Rule r = findRule(line);
			String plural = pluralize(line, r);
			String suffix = suffixize(line, r);
		}
	}

	private static String suffixize(String word, Rule r) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String pluralize(String word, Rule r) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Rule findRule(String word) {
		Lettering[] letterings = mapLettersToLettering(word);
		return null;
	}

	private static Lettering[] mapLettersToLettering(String word) {
		return Arrays.stream(word.split(""))
					 .map(l -> findLettering(l))
					 .toArray(Lettering[]::new);
	}

	private static Lettering findLettering(String letter) {
		Lettering l = Lettering.CONSONANT;
		switch (letter) {
		case "a":
		case "c":
		case "s":
		case "l":
			l = Lettering.VOWEL;
			break;
		default:
			break;
		}
		return l;
	}

	/**
	 * Converts the file's lines into an array of Strings. Uses the Classloader
	 * to find the specified file.
	 * 
	 * @param fileName
	 * @return an String[] of the lines of the file.
	 * @throws FileNotFoundException
	 * @throws URISyntaxException
	 */
	private static String[] getTestDataFromFile(String fileName)
			throws FileNotFoundException, URISyntaxException {
		// String -> URL (from the ClassLoader)
		URL url = VowelsRUs.class.getClassLoader().getResource(fileName);
		// URL -> File (to get the correct path name)
		File f = Paths.get(url.toURI()).toFile();

		// Create a stream of the lines of the file
		Stream<String> stream = new BufferedReader(new FileReader(f)).lines();
		// Return a String[], each index contains a line of the file
		return stream.toArray(String[]::new);
	}

}
