package com.example.strutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils {

	private StrUtils() {
		throw new IllegalStateException("Utility class");
	}

	// Reverses a string
	public static String reverse(String input) {
		return new StringBuilder(input).reverse().toString();
	}

	// Converts a string to title case
	public static String toTitleCase(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}
		StringBuilder titleCase = new StringBuilder();
		boolean nextTitleCase = true;

		for (char c : input.toCharArray()) {
			if (Character.isSpaceChar(c)) {
				nextTitleCase = true;
			} else if (nextTitleCase) {
				c = Character.toTitleCase(c);
				nextTitleCase = false;
			} else {
				c = Character.toLowerCase(c);
			}
			titleCase.append(c);
		}

		return titleCase.toString();
	}

	// Splits a string by a delimiter and trims each resulting part
	public static String[] splitAndTrim(String input, String delimiter) {
		if (input == null || delimiter == null) {
			return new String[0];
		}
		String[] parts = input.split(Pattern.quote(delimiter));
		List<String> trimmedParts = new ArrayList<>();
		for (String part : parts) {
			trimmedParts.add(part.trim());
		}
		return trimmedParts.toArray(new String[0]);
	}

	// Joins a list of strings with a delimiter
	public static String join(List<String> input, String delimiter) {
		if (input == null || input.isEmpty()) {
			return "";
		}
		return String.join(delimiter, input);
	}

	public static String smartTruncate(String input, int length) {
		if (input == null || length <= 0) {
			return "";
		}
		if (input.length() <= length) {
			return input;
		}
		int end = length;
		while (end > 0 && !Character.isWhitespace(input.charAt(end))) {
			end--;
		}
		return input.substring(0, end) + "...";
	}

	public static String removeDuplicateWords(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}
		String[] words = input.split("\\s+");
		Set<String> uniqueWords = new LinkedHashSet<>();
		for (String word : words) {
			uniqueWords.add(word);
		}
		return String.join(" ", uniqueWords);
	}

	public static int levenshteinDistance(String a, String b) {
		int[][] dp = new int[a.length() + 1][b.length() + 1];
		for (int i = 0; i <= a.length(); i++) {
			for (int j = 0; j <= b.length(); j++) {
				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else {
					dp[i][j] = Math.min(dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1),
							Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
				}
			}
		}
		return dp[a.length()][b.length()];
	}

	public static String normalizeWhitespace(String input) {
		if (input == null) {
			return "";
		}
		return input.trim().replaceAll("\\s+", " ");
	}

	public static List<String> extractNumbers(String input) {
		List<String> numbers = new ArrayList<>();
		if (input == null || input.isEmpty()) {
			return numbers;
		}
		Matcher matcher = Pattern.compile("\\d+").matcher(input);
		while (matcher.find()) {
			numbers.add(matcher.group());
		}
		return numbers;
	}

	public static String reverseWords(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}
		String[] words = input.split("\\s+");
		StringBuilder reversed = new StringBuilder();
		for (int i = words.length - 1; i >= 0; i--) {
			reversed.append(words[i]).append(" ");
		}
		return reversed.toString().trim();
	}

	public static String maskSensitiveData(String input, String regex, String mask) {
		if (input == null || regex == null || mask == null) {
			return input;
		}
		return input.replaceAll(regex, mask);
	}

	public static Map<Character, Integer> characterFrequency(String input) {
		Map<Character, Integer> frequencyMap = new HashMap<>();
		if (input == null || input.isEmpty()) {
			return frequencyMap;
		}
		for (char c : input.toCharArray()) {
			frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
		}
		return frequencyMap;
	}

}

