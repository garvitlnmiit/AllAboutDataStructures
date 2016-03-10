/**
 * @Title: Rain Water Trapping
 * @GFG: http://www.geeksforgeeks.org/trapping-rain-water/
 * @Author: Garvit Sharma <garvits45@gmail.com>
 **/

public class RainWaterTrap
{
	int accumulatedWater(int[] heights) {

		int size = heights.length;
		int left[] = new int[size];
		int right[] = new int[size];
		int waterAcc = 0;

		left[0] = heights[0];
		right[size-1] = heights[size-1];

		for (int iter=1; iter<size; iter++) {
			left[iter] = Math.max(heights[iter], left[iter-1]);
		}

		for (int iter=size-2; iter>=0; iter--) {
			right[iter] = Math.max(heights[iter], right[iter+1]);
		}

		for (int iter=0; iter<size; iter++) {
			waterAcc += Math.min(left[iter],right[iter]) - heights[iter];
		}

		return waterAcc;
	}

	public static void main(String[] args) {

		// Input
		int heights[] = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

		RainWaterTrap rwt = new RainWaterTrap();

		System.out.println(rwt.accumulatedWater(heights));
	}
}