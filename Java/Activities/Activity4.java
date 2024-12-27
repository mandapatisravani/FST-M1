import java.util.Arrays;

public class Activity4 {

	public static void main(String[] args) {
		Activity4 act4 = new Activity4();
		// Create a unsorted array
		int[] nums = { 12, 8, 46, 216, 46, 6, 26 };
		act4.insertionSort(nums);
		System.out.println("Sorted Array" + Arrays.toString(nums));

	}

	public void insertionSort(int[] nums) {
		int size = nums.length;
		
		for (int i = 1; i < size; i++) {
			//set the key value and the first  compare value
			int key = nums[i];
			int j = i - 1;
			//check if the key is lesser
			//if it is ,swap it with left value
			while (j > 0 && key < nums[j]) {
				nums[j + 1] = nums[j];
				--j;
			}
			nums[j + 1] = key;

		}

	}

}