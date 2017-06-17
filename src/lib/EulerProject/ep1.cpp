#include <bits/stdc++.h>
using namespace std;

int main() {

	int n=1000-1, div3=n/3, div5=n/5, div15=n/15, sum3=3*div3*(div3+1), sum5=5*div5*(div5+1), sum15=15*div15*(div15+1);

	//cout << div3 << "\n" << div5 << "\n" << div15 << "\n" << ;

	cout << (sum3+sum5-sum15)/2 << endl;
}
