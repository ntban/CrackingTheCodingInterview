package InterviewQuestions.ArrayAndString;

/**
 * <p>
 * Implement an algorithm to determine if a string has all unique characters.
 * </p>
 * What if you cannot use additional data structures?
 * 
 * @author ntban
 *
 */
public class IsUnique {
	String s;
	int sizeCharacterSet;

	public IsUnique(String s, int sizeCharacterSet) {
		this.s = s;
		this.sizeCharacterSet = sizeCharacterSet;
	}

	/**
	 * create an array of boolean values,</br>
	 * where the flag at index i indicates</br>
	 * whether character i in the alphabet is contained in the string. </br>
	 * The second time you see this character, you can immediately return false.
	 * 
	 * @return
	 */
	boolean isUnique() {
		if (s.length() > sizeCharacterSet)
			return false;
		boolean check[] = new boolean[sizeCharacterSet];
		for (int i = 0; i < s.length(); i++) {
			if (check[s.charAt(i)])
				return false;
			check[s.charAt(i)] = true;
		}
		return true;
	}

	/**
	 * Reduce our space using a bit vector</br>
	 * Assume that the string only uses the lowercase letters a-z. </br>
	 * This will allow us to use just a single int.
	 * 
	 * @return
	 */
	boolean isUnique2() {
		if (s.length() > sizeCharacterSet)
			return false;
		int checker = 0;
		for (int i = 0; i < s.length(); i++) {
			int val = s.charAt(i) - 'a';
			// check if 2^val already has in checker
			if ((checker & (1 << val)) > 0)
				return false;
			// add 2^val to checker
			checker |= 1 << val;
		}
		return true;
	}

	/**
	 * If we can't use additional data structures, we can do the following:</br>
	 * 1. Compare every character of the string to every other character of the
	 * string.</br>
	 * This will take 0(N^2) time and 0(1) space.
	 * 
	 * @return
	 */
	boolean notUseAnotherDS() {
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 2. If we are allowed to modify the input string,</br>
	 * we could sort the string in O(n log(n)) time</br>
	 * then linearly check for neighboring characters that are identical.</br>
	 * Careful, though: many sorting algorithms take up extra space.
	 * 
	 * @return
	 */
	boolean notUseAnotherDS2() {
		String sorted = sortStr(s);
		for (int i = 0; i < sorted.length() - 1; i++) {
			if (sorted.charAt(i) == sorted.charAt(i + 1))
				return false;
		}
		return true;
	}

	/**
	 * sort the string by alphabet order </br>
	 * Quick sort - Time: O(NlongN), Space: O(1)
	 * 
	 * @param s2 string to sort
	 * @return sorted one
	 */
	String sortStr(String s2) {
		char a[] = s2.toCharArray();
		quickSort(a, 0, s2.length() - 1);
		return new String(a);
	}

	void quickSort(char a[], int left, int right) {
		int i = left, j = right;
		int pivot = a[(left + right) / 2];
		// chia dãy thành 2 phần
		while (i <= j) {
			while (a[i] < pivot)
				++i;
			while (a[j] > pivot)
				--j;

			if (i <= j) {
				// swap(a[i], a[j])
				char temp = a[i];
				a[i] = a[j];
				a[j] = temp;

				++i;
				--j;
			}
		}
		// Gọi đệ quy để sắp xếp các nửa
		if (left < j)
			quickSort(a, left, j);
		if (i < right)
			quickSort(a, i, right);
	}

	public static void main(String[] args) {
		System.out.println(new IsUnique("abcde123", 256).isUnique());
		System.out.println(new IsUnique("abcddefe", 32).isUnique2());
		System.out.println(new IsUnique("abcdefgh", 256).notUseAnotherDS());
		System.out.println(new IsUnique("abcdefgha", 256).notUseAnotherDS2());
	}
}
