import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Anagram {

	public static Boolean wordAnagram(String a, String b, Boolean sensitive) {
		if (!sensitive) {
			a = a.toLowerCase();
			b = b.toLowerCase();
		}

		HashMap<Character, Integer> hisA = new HashMap<Character, Integer>();
		HashMap<Character, Integer> hisB = new HashMap<Character, Integer>();

		if (a.length() != b.length()) {
			return false;
		}

		// Fill histograms
		for (int i = 0; i < a.length(); i++) {
			if (hisA.containsKey(a.charAt(i))) {
				hisA.put(a.charAt(i), hisA.get(a.charAt(i)) + 1);
			} else {
				hisA.put(a.charAt(i), 1);
			}

			if (hisB.containsKey(b.charAt(i))) {
				hisB.put(b.charAt(i), hisB.get(b.charAt(i)) + 1);
			} else {
				hisB.put(b.charAt(i), 1);
			}
		}

		if (hisA.equals(hisB)) {
			return true;
		} else {
			return false;
		}
	}

	public static Boolean sentenceAnagram(String a, String b, Boolean sensitive) {
		ArrayList<String> listA = new ArrayList<String>(Arrays.asList(a.split(" ")));
		ArrayList<String> listB = new ArrayList<String>(Arrays.asList(b.split(" ")));

		if (a.length() != b.length() || listA.size() != listB.size()) {
			return false;
		}

		// for each word in listA, look for an anagram in listB
		for (int i = 0; i < listA.size(); i++) {
			Boolean anagram = false;
			for (int j = 0; j < listB.size(); j++) {
				if (wordAnagram(listA.get(i), listB.get(j), sensitive)) {
					anagram = true;
					listB.remove(j);
					break;
				}
			}
			if (!anagram) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(wordAnagram("listen", "silent", false));
		System.out.println(sentenceAnagram("listen triangle", "silent integral", false));
	}
}
