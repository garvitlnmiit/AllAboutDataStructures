/**
 * @Author: Garvit Sharma <garvits45@gmail.com>
 * @Title: String Edit Distance
 **/

public class StringEditDistance
{
	char[] from, to;
	int dp[][];
	int backTrace[][];

	StringEditDistance(String from, String to) {
		this.from = from.toCharArray();
		this.to = to.toCharArray();

		dp = new int[from.length() + 1][to.length() + 1];
		backTrace = new int[from.length() + 1][to.length() + 1];
	}

	int getDistance() {

		int m=from.length;
		int n=to.length;
		int minD = 0;

		for(int iter_i=0; iter_i<=m; iter_i++) {
			for(int iter_j=0; iter_j<=n; iter_j++) {

				// Base cases
				if (iter_i == 0) dp[iter_i][iter_j] = iter_j;

				else if (iter_j == 0) dp[iter_i][iter_j] = iter_i;

				else if (from[iter_i-1] == to[iter_j-1]) {
					dp[iter_i][iter_j] = dp[iter_i - 1][iter_j - 1];
					backTrace[iter_i][iter_j] = -1;

				} else {
					minD = Math.min(dp[iter_i-1][iter_j], dp[iter_i][iter_j-1]);
					minD = Math.min(minD, dp[iter_i-1][iter_j-1]);
					dp[iter_i][iter_j] = 1 + minD;

					if (minD == dp[iter_i-1][iter_j]) {
						backTrace[iter_i][iter_j] = 1; // Delete
					} else if (minD == dp[iter_i][iter_j-1]) {
						backTrace[iter_i][iter_j] = 2; // Insert
					} else if (minD == dp[iter_i-1][iter_j-1]) {
						backTrace[iter_i][iter_j] = 3; // Update
					}
				}
			}
		}

		return dp[m][n];
	}

	void showChanges() {

		int m=from.length;
		int n=to.length;

		showTraces(m,n);
	}

	private void showTraces(int m, int n) {

		if (m<1 || n<1) return;

		if (backTrace[m][n] == -1) { // Same
			System.out.println(from[m-1] + " same as " + to[n-1]);
			showTraces(m-1, n-1);
			return;
		} else if (backTrace[m][n] == 1) { // Delete
			System.out.println(from[m-1] + " deleted");
			showTraces(m-1, n);
			return;
		} else if (backTrace[m][n] == 2) { // Insert
			System.out.println("inserted " + to[n-1]);
			showTraces(m, n-1);
			return;
		} else if (backTrace[m][n] == 3) { // Update
			System.out.println(from[m-1] + " updated " + to[n-1]);
			showTraces(m-1, n-1);
			return;
		}
	}

	public static void main(String[] args) {

		StringEditDistance sed = new StringEditDistance("intention", "execution");
		int d = sed.getDistance();
		System.out.println(d);
		sed.showChanges();
	}
}