import java.util.*;

public class RunWithObstacles
{
	public static void main(String[] args) {
		RunWithObstacles rwo = new RunWithObstacles();
		Scanner sc = new Scanner(System.in);
		int n,m,s,d, obsPts[], start, end;
		String imp = "IMPOSSIBLE";
		n = Integer.parseInt(sc.next());
		m = Integer.parseInt(sc.next());
		s = Integer.parseInt(sc.next());
		d = Integer.parseInt(sc.next());

		start=0;
		end=m;

		obsPts = new int[n];

		for(int i=0; i<n; i++) {
			obsPts[i] = Integer.parseInt(sc.next());
		}

		Array.sort(obsPts);

		// left
		if (obsPts-1 < s) {
			System.out.println(imp);
			return;
		}
	}
}