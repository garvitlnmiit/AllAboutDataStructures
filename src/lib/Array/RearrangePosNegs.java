/**
 * @Title: Place possitives and negatives alternatively, when order doesn't matter.
 * @Complexity: O(n).
 **/

public class RearrangePosNegs
{
	public void rearrangeNums(int[] data) {
		int len = data.length;

		int i=-1;

		for(int j=0; j<len; j++) {
			if (data[j]<0) {
				i++;
				if (i!=j) {
					data[j]=data[i]^data[j];
					data[i]=data[i]^data[j];
					data[j]=data[i]^data[j];
				}
			}
		}

		// Place alternatively
		int pos=i+1;
		int neg=0;

		while(pos<len && neg<pos && data[neg]<0) {
			data[neg]=data[neg]^data[pos];
			data[pos]=data[neg]^data[pos];
			data[neg]=data[neg]^data[pos];
			pos++;
			neg+=2;
		}
	}

	public void printData(int[] data) {
		int len = data.length;

		// Iter
		for(int iter=0; iter<len; iter++) {
			System.out.print(data[iter] + " ");
		}

		System.out.println();
	}

	public static void main(String[] args) {

		int data[] = {-1, 2, -3, 4, 5, 6, -7, 8, 9};

		RearrangePosNegs alter = new RearrangePosNegs();
		alter.rearrangeNums(data);
		alter.printData(data);
	}
}