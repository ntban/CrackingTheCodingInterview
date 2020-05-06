package InterviewQuestions.ArrayAndString;

import java.util.HashMap;

public class Q2CheckPermutation {

	/**
	 * Given two strings, <br/>
	 * write a method to decide if one is a permutation of the other.
	 */
	public boolean checkPermutation(String one, String two) {
		HashMap<Character, Integer> countOne = countCharacter(one);
		HashMap<Character, Integer> countTwo = countCharacter(two);
		return countOne.equals(countTwo);
	}

	private HashMap<Character, Integer> countCharacter(String s) {
		HashMap<Character, Integer> countMap = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!countMap.containsKey(c)) {
				countMap.put(c, 0);
			}
			countMap.put(c, countMap.get(c) + 1);
		}
		return countMap;
	}

	public static void main(String[] args) {
		String one = "i can hear you";
		String two = "you can hear i";
		System.out.println(new Q2CheckPermutation().checkPermutation(one, two));
	}
}
