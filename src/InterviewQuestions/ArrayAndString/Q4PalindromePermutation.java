package InterviewQuestions.ArrayAndString;

import java.util.HashMap;

public class Q4PalindromePermutation {
	/* mine */
	public boolean palindromePermutation(String s) {
		HashMap<Character, Integer> countMap = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ')
				continue;
			if (c >= 'A' && c <= 'Z') {
				c = (char) (c + ('a' - 'A'));
			}
			if (!countMap.containsKey(c)) {
				countMap.put(c, 0);
			}
			countMap.put(c, countMap.get(c) + 1);
		}
		int countOdd = 0;
		for (Character key : countMap.keySet()) {
			if (countMap.get(key) % 2 == 1) {
				countOdd++;
			}
		}
		return countOdd <= 1;
	}

	/**
	 * <p>
	 * If you think more deeply about this problem, you might notice that we don't
	 * actually need to know the counts. We just need to know if the count is even
	 * or odd. Think about flipping a light on/off (that is initially off). If the
	 * light winds up in the off state, we don't know how many times we flipped it,
	 * but we do know it was an even count. Given this, we can use a single integer
	 * (as a bit vector). When we see a letter, we map it to an integer between O
	 * and 26 (assuming an English alphabet). Then we toggle the bit at that value.
	 * At the end of the iteration, we check that at most one bit in the integer is
	 * set to 1. We can easily check that no bits in the integer are 1: just compare
	 * the integer to 0. There is actually a very elegant way to check that an
	 * integer has exactly one bit set to 1.
	 * </p>
	 */
	public boolean isPermutationOfPalindrome(String phrase) {
		int bitVector = 0;
		for (int i = 0; i < phrase.length(); i++) {
			char c = phrase.charAt(i);
			if (c == ' ')
				continue;
			if (c >= 'A' && c <= 'Z') {
				c = (char) (c + ('a' - 'A'));
			}
			int index = c - 'a';
			bitVector = toggle(bitVector, index);
		}
		// there is only a odd appearance
		if ((bitVector & (bitVector - 1)) == 0) {
			return true;
		}
		// there is no odd appearances
		return bitVector == 0;
	}

	/* Toggle the ith bit in the integer. */
	int toggle(int bitVector, int index) {
		if (index < 0) {
			return bitVector;
		}
		// example: index = 4 => mask = 010000
		int mask = 1 << index;
		if ((bitVector & mask) == 0) {
			// mask not appear in bitVector so turn the bit on
			// example: 01010 & 00100 = 0
			// 01010 | 00100 = 01110
			bitVector |= mask;
		} else {
			// mask already appear in bitVector so turn the bit off
			// example: 01010 & 01000 = 0
			// 01010 & 10111 = 00010
			bitVector &= ~mask;
		}
		return bitVector;
	}

	public static void main(String[] args) {
		String s = "Tact Coa";
		System.out.println(new Q4PalindromePermutation().palindromePermutation(s));
	}
}
