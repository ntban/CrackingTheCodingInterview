package TechnicalQuestions;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Example: Print all positive integer solutions </br>
 * to the equation a^3 + b^3 = c^3 + d^3</br>
 * where a, b, c, and d are integers between 1 and 1000.
 *
 */

public class E2UnWork {
	void print(int a, int b, int c, int d) {
		if ((a == c && b == d) || (a == d && b == c) )
			return;
		if (a * a * a + b * b * b == c * c * c + d * d * d) {
			System.out.printf("(%d, %d, %d, %d)\n", a, b, c, d);
			count ++;
		}
	}

	int n = 1000;
	int count;

	/**
	 * iterates through all possible values of a, b, c, and d </br>
	 * and checks if that combination happens to work. </br>
	 * Time: O(N^4) </br>
	 * Space: O(1)
	 */
	void bruteForceApproach() {
		count = 0;
		for (int a = 1; a <= n; a++) {
			for (int b = a + 1; b <= n; b++) {
				for (int c = 1; c <= n; c++) {
					for (int d = c + 1; d <= n; d++) {
						print(a, b, c, d);
					}
				}
			}

		}
		System.out.println("count = " + count);
	}

	/**
	 * If there's only one valid d value for each (a, b, c),</br>
	 * then we can just compute it.</br>
	 * This is just simple math: d = Math.pow( a^3 + b^3 - c^3, 1/3) </br>
	 * Time: O(N^3) </br>
	 * Space: O(1) </br>
	 */
	void anotherBruteForceApproach() {
		count = 0;
		for (int a = 1; a <= n; a++) {
			for (int b = a + 1; b <= n; b++) {
				for (int c = 1; c <= n; c++) {
					int d = (int) Math.pow(a * a * a + b * b * b - c * c * c, 1 / (double) 3);
					print(a, b, c, d);
				}
			}
		}
		System.out.println("count = " + count);
	}

	class Pair {
		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

	}

	/**
	 * locate the matches by inserting each ( c, d) pair into a hash table</br>
	 * that maps from the sum to the pair </br>
	 * Time: O(N^2) </br>
	 * Space: O(N^2)
	 */
	void hashApproach() {
		HashMap<Integer, ArrayList<Pair>> map = new HashMap<Integer, ArrayList<Pair>>();
		for (int c = 1; c <= n; c++) {
			for (int d = c + 1; d <= n; d++) {
				int result = c * c * c + d * d * d;
				Pair pair = new Pair(c, d);
				if (map.containsKey(result)) {
					map.get(result).add(pair);
				} else {
					ArrayList<Pair> pairList = new ArrayList<>();
					pairList.add(pair);
					map.put(result, pairList);
				}
			}
		}
		count = 0;
		for (Integer sum : map.keySet()) {
			ArrayList<Pair> pairList = map.get(sum);
			for (int i = 0; i < pairList.size(); i++) {
				for (int j = i + 1; j < pairList.size(); j++) {
					int a = pairList.get(i).first;
					int b = pairList.get(i).second;
					int c = pairList.get(j).first;
					int d = pairList.get(j).second;
					System.out.printf("(%d, %d, %d, %d)\n", a, b, c, d);
					count++;
				}

			}
		}
		System.out.println("count = " + count);
	}

	public static void main(String[] args) {
		E2UnWork solution = new E2UnWork();
		long startTime;
		startTime = System.currentTimeMillis();
//		System.out.println("Brute Force Approach");
//		solution.bruteForceApproach();
//		System.out.printf("\nCost %d ms", System.currentTimeMillis() - startTime);
//
		startTime = System.currentTimeMillis();
		System.out.println("\nAnother Brute Force Approach");
		solution.anotherBruteForceApproach();
		System.out.printf("\nCost %d ms", System.currentTimeMillis() - startTime);

//		startTime = System.currentTimeMillis();
//		System.out.println("\nHash Approach");
//		solution.hashApproach();
//		System.out.printf("\nCost %d ms", System.currentTimeMillis() - startTime);
	}
}
