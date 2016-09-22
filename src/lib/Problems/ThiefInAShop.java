/**
 * @Author Garvit Sharma <garvits45@gmail.com>
 * @Date April 15, 2016
 * @Org Paytm
 * @Problem http://codeforces.com/problemset/problem/632/E
 **/

import java.util.*;

public class ThiefInAShop
{
	private Set<Integer> unSet;

	public ThiefInAShop() {
		unSet = new TreeSet<Integer>();
	}

	/*private int memoize[][];

	public ThiefInAShop(int n, int k, int[] costs) {
		memoize = new int[1005][1005];

		for(int i=0; i<=k; i++){
			for(int j=0; j<=n; j++){
				if (i==0) memoize[i][j]=0;
				else if(j==0) memoize[i][j]=0;
				else if(i==1) memoize[i][j]=costs[j];
				else memoize[i][j]=-1;
			}
		}
	}*/



	public void printCosts(int n, int k, int[] costs, int sum, int start) {
		if (k==0) {
			unSet.add(sum);
			return;
		}

		for(int i=start; i<n; i++) {
			printCosts(n,k-1,costs,sum+costs[i], i);
		}
	}

	public void printMemoize() {
		for(int i:unSet) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		int k = Integer.parseInt(sc.next());
		int costs[] = new int[n];

		for(int i=0; i<n; i++) {
			costs[i] = Integer.parseInt(sc.next());
		}

		ThiefInAShop tis = new ThiefInAShop();
		tis.printCosts(n,k,costs,0,0);
		tis.printMemoize();
	}
}