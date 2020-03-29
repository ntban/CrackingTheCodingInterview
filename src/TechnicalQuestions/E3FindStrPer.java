package TechnicalQuestions;

import java.util.HashMap;

/**
 * Given a smaller strings and a bigger string b,</br>
 * design an algorithm to find all permutations of the shorter string</br>
 * within the longer one. Print the location of each permutation.
 *
 */
public class E3FindStrPer {

	String s;
	String b;

	public E3FindStrPer(String s, String b) {
		this.s = s;
		this.b = b;
	}

	/**
	 * THE MOST STUPID WAY! >> SKIPPED</br>
	 * Time: O(S!*B)</br>
	 * Space: O(S) for permutation
	 */
	void bruteForceApproach() {
	}

	/**
	 * Walk through b.</br>
	 * Every time you see a character in s, </br>
	 * check if the next S characters are a permutation of s. </br>
	 * Time: O(B*K) : not the best optimize </br>
	 * Space: O(B) for hash table
	 */
	void hashApproach() {
		if (b.length() < s.length()) {
			return;
		}
		HashMap<Character, Integer> sMap = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char key = s.charAt(i);
			if (sMap.containsKey(key)) {
				sMap.put(key, sMap.get(key) + 1);
			} else {
				sMap.put(key, 1);
			}
		}
		int count = 0;
		for (int i = 0; i < b.length(); i++) {
			if (sMap.containsKey(b.charAt(i)) && b.length() - i >= s.length() && check(sMap, i)) {
				count++;
//				System.out.println(b.substring(i, i+s.length()));
			}
		}
		System.out.println(count);
	}

	/**
	 * 
	 * @param sMap map of small string
	 * @param i    the start index
	 * @return true if the substring(i, i+s.length()) of b is the permutation of s
	 */
	boolean check(HashMap<Character, Integer> sMap, int i) {
		HashMap<Character, Integer> bMap = new HashMap<>();
		for (int j = i; j < i + s.length(); j++) {
			char key = b.charAt(j);
			if (bMap.containsKey(key)) {
				bMap.put(key, bMap.get(key) + 1);
			} else {
				bMap.put(key, 1);
			}
		}
		return sMap.equals(bMap);
	}

	/**
	 * Walk through b.</br>
	 * Every time you see a character in s, </br>
	 * check if the next S characters are a permutation of s. </br>
	 * Time: O(B) : the best optimize </br>
	 * Space: O(B) for hash table
	 */
	void anotherHashApproach() {
		if (b.length() < s.length()) {
			return;
		}
		HashMap<Character, Integer> sMap = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char key = s.charAt(i);
			if (sMap.containsKey(key)) {
				sMap.put(key, sMap.get(key) + 1);
			} else {
				sMap.put(key, 1);
			}
		}
		int count = 0;
		HashMap<Character, Integer> bMap = new HashMap<>();
		for (int i = 0; i < b.length(); i++) {
			char key = b.charAt(i);
			if (bMap.containsKey(key)) {
				bMap.put(key, bMap.get(key) + 1);
			} else {
				bMap.put(key, 1);
			}
			// remove the one is outside bound
			if (i >= s.length()) {
				char c = b.charAt(i - s.length());
				if (bMap.get(c) > 1) {
					bMap.put(c, bMap.get(c) - 1);
				} else {
					bMap.remove(c);
				}
			}
			if (bMap.equals(sMap)) {
				count++;
				System.out.println(b.substring(i - s.length() + 1, i + 1));
			}
		}
		System.out.println(count);
	}

	public static void main(String[] args) {
		String s = "abbc";
		String b = "cbabadcbbabbcbabaabccbabc";
		new E3FindStrPer(s, b).anotherHashApproach();
	}
}
