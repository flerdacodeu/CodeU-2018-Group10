package assignment1;

import java.util.Arrays;

public class Anagram {

	/**
	 * Determines if one string is an anagram of another string
	 * 
	 * @param s1 first string
	 * @param s2 second string
	 * 
	 * @return true if s1 and s2 are anagrams of each other
	 */
	public static boolean isAnagram(String s1, String s2) {

		String string1 = s1.replaceAll("\\s", "");
		String string2 = s2.replaceAll("\\s", "");

		if (string1.length() != string2.length()) {
			return false;
		} else {

			char[] s1Array = string1.toCharArray();
			char[] s2Array = string2.toCharArray();

			Arrays.sort(s1Array);
			Arrays.sort(s2Array);

			return Arrays.equals(s1Array, s2Array);
		}
	}

	/**
	 * Handles both case sensitive and case insensitive anagrams
	 */
	
	public static boolean isWordAnagram(String s1, String s2, boolean isCaseSensitive) {
		if (isCaseSensitive) {
			return isAnagram(s1, s2);
		} else {
			return isAnagram(s1.toLowerCase(), s2.toLowerCase());
		}
	}

	/**
	 * Determines if one sentence is an anagram of another sentence
	 * where each word in the resulting sentence is an anagram
	 * of one of the words in the original sentence
	 * 
	 * @param s1 first sentence
	 * @param s2 second sentence
	 * 
	 * @return true if s1 and s2 are anagrams of each other
	 */
	public static boolean isSentenceAnagram(String s1, String s2) {

		String[] sentence1 = s1.split("\\s*");
		String[] sentence2 = s2.split("\\s*");

		if (sentence1.length != sentence2.length) {
			return false;
		} else {

			boolean[] isAnagramFoundFirst = new boolean[sentence1.length];
			boolean[] isAnagramFoundSecond = new boolean[sentence2.length];

			for (int i = 0; i < sentence1.length; i++) {

				for (int j = 0; j < sentence2.length; j++) {

					if (isWordAnagram(sentence1[i], sentence2[j], false)) {
						isAnagramFoundFirst[i] = true;
						isAnagramFoundSecond[j] = true;
					}
				}
			}

			for (int i = 0; i < isAnagramFoundFirst.length; i++) {
				if (!isAnagramFoundFirst[i] || !isAnagramFoundSecond[i]) {
					return false;
				}
			}
			return true;
		}
	}

	public static void main(String[] args) {

		System.out.println(isAnagram("listen", "silent"));
		System.out.println(isAnagram("triangle", "integral"));
		System.out.println(isWordAnagram("lemon", "Monel", false));
		System.out.println(isWordAnagram("lemon", "Monel", true));
		System.out.println(isSentenceAnagram("I love chocolate", "vole i Latechoco"));
		System.out.println(isSentenceAnagram("I love chocolate", "I love choco"));
		System.out.println(isSentenceAnagram("abc abc", "abc def"));
	}
}
