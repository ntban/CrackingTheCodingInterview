package InterviewQuestions.ArrayAndString;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * <p>
 * One Away: There are three types of edits that can be performed on strings:
 * insert a character, remove a character, or replace a character. Given two
 * strings, write a function to check if they are one edit (or zero edits) away.
 * <p/>
 * EXAMPLE<br/>
 * pale, ple -> true<br/>
 * pales, pale -> true<br/>
 * pale, bale -> true<br/>
 * pale, bae -> false
 * 
 * @author an.ntb
 *
 */
public class Q5OneWay {
	/* my solution */
	public boolean oneWay(String first, String second) {
		if (Math.abs(first.length() - second.length()) >= 2) {
			return false;
		}
		if (first.length() < second.length()) {
			// swap to only check case remove or replace
			String temp = first;
			first = second;
			second = temp;
		}
		int i = 0;
		int j = 0;
		int diff = 0;
		while (i < first.length() && j < second.length()) {
			char c1 = first.charAt(i);
			char c2 = second.charAt(j);
			if (c1 == c2) {
				i++;
				j++;
				continue;
			}
			diff++;

			if (i + 1 < first.length() && first.charAt(i + 1) == c2) {
				// pale, ple: remove case
				i++;

			} else if (i + 1 == first.length() && j + 1 == second.length()) {
				// pale, pals: replace last character case 
				return diff <= 1;
			} else if (i + 1 < first.length() && j + 1 < second.length()
					&& first.charAt(i + 1) == second.charAt(j + 1)) {
				// pale, bale : replace case
				i++;
				j++;
			} else {
				// can't be the one-way
				return false;
			}
		}
		// pale, pal case
		while (i < first.length()) {
			i++;
			diff++;
		}
		return diff <= 1;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input/arr/q5.in"));
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.next());
		Q5OneWay solution = new Q5OneWay();
		while (T-- > 0) {
			String first = sc.next();
			String second = sc.next();
			System.out.printf("%s %s ", first, second);
			System.out.println(solution.oneWay(first, second));
		}
		sc.close();
	}
}
