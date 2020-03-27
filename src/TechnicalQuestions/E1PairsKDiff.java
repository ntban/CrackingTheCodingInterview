package TechnicalQuestions;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Problem: Example: Given an array of distinct integer values,</br>
 * count the number of pairs of integers that have difference k. </br>
 * For example, given the array { 1, 7, 5, 9, 2, 12, 3}</br>
 * and the difference k = 2,</br>
 * there are four pairs with difference2: (1, 3), (3, 5), (5, 7), (7, 9).
 *
 */
public class E1PairsKDiff {
	int n;
	int k;
	int a[];

	public E1PairsKDiff(int n, int k, int[] a) {
		this.n = n;
		this.k = k;
		this.a = a;
	}

	void print(int i, int j) {
		System.out.printf("(%d, %d) ", a[i], a[j]);
	}

	/**
	 * For each element, go through each others and check the different Time</br>
	 * Time Complexity: O(N^2)</br>
	 * Space Complexity: O(1)
	 */
	void bruteForceApproach() {
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (Math.abs(a[i] - a[j]) == k) {
					print(i, j);
				}
			}
		}
	}

	/**
	 * 1. Sort the array : O(NlogN) </br>
	 * 2. For each element, find its k-diff element : O(NlogN)</br>
	 * Complexity: O(NlogN) </br>
	 * Space Complexity: O(1)
	 */
	void sortApproach() {
		Arrays.sort(a);
		for (int i = 0; i < a.length; i++) {
			int index = binSearch(i + 1, n - 1, a[i] + k);
			if (index >= 0) {
				print(i, index);
			}
		}
	}

	int binSearch(int start, int end, int value) {
		if (start > end)
			return -1;
		int mid = (start + end) / 2;
		if (a[mid] == value)
			return mid;
		else if (a[mid] > value)
			return binSearch(start, mid - 1, value);
		else
			return binSearch(mid + 1, end, value);
	}

	/**
	 * 1. Save the elements in the hash table: O(N)</br>
	 * 2. For each elements, find its k-diff element in the hash table: O(N)</br>
	 * Complexity: O(N) </br>
	 * Space Complexity: O(N)
	 */
	void hashApproach() {
		HashMap<Integer, Integer> hashTable = new HashMap<>();
		for (int i = 0; i < n; i++) {
			hashTable.put(a[i], i);
		}
		for (int i = 0; i < n; i++) {
			int k1 = a[i] + k;
			if(hashTable.containsKey(k1)) {
				print(i, hashTable.get(k1));
			}
		}
	}

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input/e1.in"));
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int a[] = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();
		E1PairsKDiff solution = new E1PairsKDiff(n, k, a);
		long startTime;
		startTime = System.currentTimeMillis();
		System.out.println("Brute Force Approach");
		solution.bruteForceApproach();
		System.out.printf("\nCost %d ms", System.currentTimeMillis() - startTime);
		
		startTime = System.currentTimeMillis();
		System.out.println("\nSort Approach");
		solution.sortApproach();
		System.out.printf("\nCost %d ms", System.currentTimeMillis() - startTime);
		
		startTime = System.currentTimeMillis();
		System.out.println("\nHash Approach");
		solution.hashApproach();
		System.out.printf("\nCost %d ms", System.currentTimeMillis() - startTime);
	}
}
