/**
 * @Title: Search an element in a Rotated Sorted Array
 * @GFG: http://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/
 * @Author: Garvit Sharma <garvits45@gmail.com>
 **/

public class SortedRotatedArray
{
	int modifiedBinarySearch(int[] nums, int low, int high, int key) {

		// Base/Edge case
		if (low > high) {
			return -1;
		}

		int mid = (low+high)/2;

		if (nums[mid] == key) {
			return mid;
		}

		// If left part is sorted
		if (nums[low] <= nums[mid]) {
			if (nums[low] <= key && key <= nums[mid]) {
				return modifiedBinarySearch(nums, low, mid-1, key);
			} else {
				return modifiedBinarySearch(nums, mid+1, high, key);
			}
		}

		// Else right part is sorted
		if (nums[mid] <= key && key <= nums[high]) {
			return modifiedBinarySearch(nums, mid+1, high, key);
		}

		// Else go into left because key lies in the first half
		return modifiedBinarySearch(nums, low, mid-1, key);
	}

	public static void main(String[] args) {

		int data[] = {4, 5, 6, 7, 8, 9, 1, 2, 3};

		SortedRotatedArray sra = new SortedRotatedArray();

		System.out.println(sra.modifiedBinarySearch(data, 0, data.length-1, 12));

	}
}