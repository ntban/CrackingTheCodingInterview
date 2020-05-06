package InterviewQuestions.ArrayAndString;

/**
 * <p>
 * Write a method to replace all spaces in a string with '%20'. You may assume
 * that the string has sufficient space at the end to hold the additional
 * characters, and that you are given the "true" length of the string. (Note: if
 * implementing in Java, please use a character array so that you can perform
 * this operation in place.)
 * </p>
 * <p>
 * EXAMPLE <br/>
 * Input: "Mr John Smith ", 13 <br/>
 * Output: "Mr%20John%20Smith"
 * </p>
 * 
 * @author an.ntb
 *
 */
public class Q3URLify {
	/**
	 * 
	 * @param arr
	 * @param length
	 */
	public void urlify(char[] arr, int length) {
		int countSpaces = 0;
		for(int i = 0 ; i < length ; i ++) {
			if (arr[i] == ' ') countSpaces++;
		}
		int backwardIndex = length - 1 + 2 * countSpaces;
		for (int i = length - 1; i >= 0; i--) {
			if (arr[i] == ' ') {
				arr[backwardIndex--] = '0';
				arr[backwardIndex--] = '2';
				arr[backwardIndex--] = '%';
			} else {
				arr[backwardIndex--] = arr[i];
			}
		}
	}

	public static void main(String[] args) {
		char arr[] = " A B C D            ".toCharArray();
		new Q3URLify().urlify(arr, 8);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
	}
}
