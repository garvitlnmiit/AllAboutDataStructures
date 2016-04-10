/**
 * @Title: Largest Bitonic Subsequence
 * @Author: Garvit Sharma
 * @GFG: http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
 **/

public class LargestBitonicSeq
{

	// Method to get longest-bitonic-subsequence
	int getLargestBitonicSeq(int[] data) {
		int dataSize = data.length;
		// For forward
		int lis[] = new int[dataSize];
		// For reverse
		int lds[] = new int[dataSize];

		int max=0;

		for (int iter=0; iter<dataSize; iter++) {
			lis[iter] = 1;
			lds[iter] = 1;
		}

		// Forward
		for (int iter=1; iter<dataSize; iter++) {
			for (int iterj=0; iterj<iter; iterj++) {
				if (data[iter] > data[iterj] && lis[iter] < lis[iterj] + 1) {
					lis[iter] = lis[iterj] + 1;
				}
			}
		}

		// Reverse
		for (int iter = dataSize-2; iter>0; iter--) {
			for (int iterj = dataSize-1; iterj>iter; iterj--) {
				if (data[iter] > data[iterj] && lis[iter] < lis[iterj] + 1) {
					lis[iter] = lis[iterj] + 1;
				}
			}
		}

		max = lis[0]+lds[0]-1;
		for (int iter=1; iter<dataSize; iter++) {
			if (max < lis[iter]+lds[iter]-1) {
				max = lis[iter]+lds[iter]-1;
			}
		}

		return max;
	}

	public static void main(String[] args) {

		int data[] = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5,
              13, 3, 11, 7, 15};

		LargestBitonicSeq lbs = new LargestBitonicSeq();
		// STDOUT
		System.out.println(lbs.getLargestBitonicSeq(data));
	}
}

